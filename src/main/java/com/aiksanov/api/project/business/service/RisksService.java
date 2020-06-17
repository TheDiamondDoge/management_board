package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Risk;
import com.aiksanov.api.project.data.entity.RisksTableInfo;
import com.aiksanov.api.project.data.repository.RisksRepository;
import com.aiksanov.api.project.data.repository.RisksTableInfoRepo;
import com.aiksanov.api.project.exceptions.FileNotSavedException;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.web.DTO.ErrorExportDTO;
import com.aiksanov.api.project.web.DTO.risks.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RisksService {
    private final RisksRepository risksRepository;
    private final RisksTableInfoRepo risksTableInfoRepo;
    private final ProjectGeneralService generalService;
    private final String RISKS_SUFFIX = "_risks.xlsx";

    @Value("${risks.processor.url}")
    private String PROCESSOR_URL;

    @Value("${risks.generator.url}")
    private String GET_RISKS_URL;

    @Value("${risks.file.storage}")
    private String RISKS_STORAGE;


    public List<RisksDTO> getProjectRisks(int projectId) {
        return this.risksRepository.findAllByProjectId(projectId)
                .stream()
                .map(RisksDTO::new)
                .collect(Collectors.toList());
    }

    public RisksTabDTO getRisksTab(int projectId) {
        List<RisksDTO> risks = this.getProjectRisks(projectId);
        boolean isRisksFileExists = this.isRisksFileExists(projectId);
        RisksTableInfo risksTableInfo = this.risksTableInfoRepo.findById(projectId).orElseGet(RisksTableInfo::new);
        return new RisksTabDTO(risks, isRisksFileExists, risksTableInfo.getUploadedOn());
    }

    public List<String> getListOfProjectsRisks(int projectId) {
        return this.risksRepository.findAllByProjectId(projectId)
                .stream()
                .map(Risk::getRiskDisplayId)
                .collect(Collectors.toList());
    }

    @Transactional
    public void saveEditedRisk(RisksDTO dto, int projectId) {
        Risk risk = dto.createRiskObjWOProjectId();
        risk.setProjectId(projectId);
        this.risksRepository.save(risk);
        this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestRiskEd");
    }

    public int getActiveRisks(int projectId) {
        return this.risksRepository.countAllByProjectId(projectId);
    }

    @Transactional
    public List<ErrorExportDTO> processRiskFile(MultipartFile file, int projectId) throws IOException, RestTemplateException, FileNotSavedException {
        RisksFromFileDTO risksFromFile =
                (RisksFromFileDTO) Utils.sendFileToService(file, PROCESSOR_URL, RisksFromFileDTO.class).getBody();

        AtomicInteger i = new AtomicInteger(0);
        List<Risk> risksToSave = new ArrayList<>();
        if (Objects.nonNull(risksFromFile)) {
            risksToSave = risksFromFile.getRisks().stream().map((riskDto) -> {
                Risk risk = riskDto.createRiskObjWOProjectId();
                risk.setProjectId(projectId);
                risk.setRiskId(i.addAndGet(1));
                return risk;
            }).collect(Collectors.toList());
        } else {
            risksFromFile = new RisksFromFileDTO();
        }

        this.risksRepository.deleteAllByProjectId(projectId);
        this.risksRepository.saveAll(risksToSave);

        String filename = projectId + RISKS_SUFFIX;
        try {
            Utils.createDirIfNotExist(RISKS_STORAGE);
            Utils.saveFile(file, filename, RISKS_STORAGE);
        } catch (Exception e) {
            throw new FileNotSavedException(filename);
        }

        RisksTableInfo risksTableInfo = new RisksTableInfo(projectId, new Date(), "TestRisksUpl");
        this.risksTableInfoRepo.save(risksTableInfo);
        this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestRisksUpl");
        return risksFromFile.getErrors();
    }

    public ResponseEntity<Resource> getRisksAsFileToUser(int projectId) throws IOException, RestTemplateException {
        String projectName = generalService.getProjectName(projectId);
        String filename = projectName + RISKS_SUFFIX;
        ByteArrayResource reader = getRisksFileAsByteArrResource(projectId);
        return Utils.giveFileToUser(filename, reader);
    }

    private ByteArrayResource getRisksFileAsByteArrResource(int projectId) throws IOException, RestTemplateException {
        String projectName = generalService.getProjectName(projectId);
        List<RisksDTO> risks = getProjectRisks(projectId);
        String url = GET_RISKS_URL + projectName;

        return Utils.getDataFile(url, risks);
    }

    public ResponseEntity<Resource> getLastUpdatedFile(int projectId) throws IOException {
        Utils.createDirIfNotExist(RISKS_STORAGE);
        String projectName = generalService.getProjectName(projectId);
        String filepath = RISKS_STORAGE + File.separator + projectId + RISKS_SUFFIX;
        String filename = projectName + RISKS_SUFFIX;

        return Utils.giveFileToUser(filename, filepath);
    }

    private boolean isRisksFileExists(int projectId) {
        Utils.createDirIfNotExist(RISKS_STORAGE);
        String filename = projectId + RISKS_SUFFIX;
        String filepath = RISKS_STORAGE + File.separator + filename;
        File file = new File(filepath);

        return file.exists() && file.isFile();
    }

    public List<RisksMinimalDTO> getMinimalRisks(int projectId) {
        List<Risk> risks = this.risksRepository.findAllByProjectId(projectId);
        return risks.stream().filter(Risk::isReport).map(RisksMinimalDTO::new).collect(Collectors.toList());
    }
}