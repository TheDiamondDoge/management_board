package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Risk;
import com.aiksanov.api.project.data.repository.RisksRepository;
import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.web.DTO.risks.RisksDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RisksService {

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

    public void processRiskFile(MultipartFile file, int projectId) throws IOException {
        String filepath = serviceUtils.saveFile(file, "risks_");
        //Send file to service
    }
}
