package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Risk;
import com.aiksanov.api.project.data.repository.RisksRepository;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.web.DTO.ErrorExportDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksDTO;
import com.aiksanov.api.project.web.DTO.risks.RisksFromFileDTO;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class RisksService {
    private String PROCESSOR_URL = "http://localhost:8081/processors/risks";

    private RisksRepository risksRepository;
    private ServiceUtils serviceUtils;

    @Autowired
    public RisksService(RisksRepository risksRepository, ServiceUtils serviceUtils) {
        this.risksRepository = risksRepository;
        this.serviceUtils = serviceUtils;
    }

    public Set<Risk> getRisksByIds(List<String> risksIds) {
        return this.risksRepository.findByRiskDisplayIdIn(risksIds);
    }

    public List<RisksDTO> getProjectRisks(int projectId) {
        return this.risksRepository.findAllByProjectId(projectId)
                .stream()
                .map(RisksDTO::new)
                .collect(Collectors.toList());
    }

    public List<String> getListOfProjectsRisks(int projectId) {
        return this.risksRepository.findAllByProjectId(projectId)
                .stream()
                .map(Risk::getRiskDisplayId)
                .collect(Collectors.toList());
    }

    public List<Risk> getRiskList(int projectId) {
        return this.risksRepository.findAllByProjectId(projectId);
    }

    @Transactional
    public void saveEditedRisk(RisksDTO dto, int projectId) {
        Risk risk = dto.createRiskObjWOProjectId();
        risk.setProjectId(projectId);
        this.risksRepository.save(risk);
    }

    public int getActiveRisks(int projectId) {
        return this.risksRepository.countAllByProjectId(projectId);
    }

    @Transactional
    public List<ErrorExportDTO> processRiskFile(MultipartFile file, int projectId) throws IOException, RestTemplateException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RisksFromFileDTO> response = null;
        try {
            response = restTemplate.postForEntity(PROCESSOR_URL, requestEntity, RisksFromFileDTO.class);
        } catch (HttpStatusCodeException e) {
            String responseString = e.getResponseBodyAsString();
            ObjectMapper mapper = new ObjectMapper();
            throw mapper.readValue(responseString, RestTemplateException.class);
        }
        RisksFromFileDTO dto = response.getBody();
        List<RisksDTO> risksDTOS = dto.getRisks();
        AtomicInteger i = new AtomicInteger(0);
        List<Risk> risksToSave = risksDTOS.stream().map((riskDto) -> {
            Risk risk = riskDto.createRiskObjWOProjectId();
            risk.setProjectId(projectId);
            risk.setRiskId(i.addAndGet(1));
            return risk;
        }).collect(Collectors.toList());

        this.risksRepository.deleteAllByProjectId(projectId);
        this.risksRepository.saveAll(risksToSave);
        //save risk
        //return found errors
        return dto.getErrors();
    }
}
