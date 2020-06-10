package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.util.enums.KpiTypes;
import com.aiksanov.api.project.web.DTO.kpi.PlainXlsxDataDTO;
import com.aiksanov.api.project.web.DTO.kpi.QualityIndicatorsAmountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class KpiService {
    private final String VALUES_URL = "http://localhost:8100/general/qualityIndicators/amount";
    private final String KPI_URL_BASE = "http://localhost:8100";
    private final String PLAIN_XLSX_CREATOR = "http://localhost:8081/processors/plainXlsx";
    private final GeneralRepository generalRepository;
    private final QualityService qualityService;
    private final BacklogService backlogService;
    private final DefectsService defectsService;

    @Autowired
    public KpiService(QualityService qualityService, BacklogService backlogService,
                      DefectsService defectsService, GeneralRepository generalRepository) {
        this.qualityService = qualityService;
        this.backlogService = backlogService;
        this.defectsService = defectsService;
        this.generalRepository = generalRepository;
    }

    public QualityIndicatorsAmountDTO getQualityIndicatorsValues(int projectId) {
        RestTemplate restTemplate = new RestTemplate();
        QualityIndicatorsAmountDTO dto;
        try {
            dto = restTemplate.getForObject(VALUES_URL + "/" + projectId, QualityIndicatorsAmountDTO.class);
        } catch (Exception e) {
            dto = new QualityIndicatorsAmountDTO();
        }
        return dto;
    }

    public ByteArrayResource getKpiFile(int projectId, KpiTypes type) throws IOException, RestTemplateException {
        RestTemplate restTemplate = new RestTemplate();
        String url = KPI_URL_BASE + "/" + type.getValue() + "/list/" + projectId;
        List issues = restTemplate.getForObject(url, List.class);
        PlainXlsxDataDTO dto = null;
        switch (type) {
            case QUALITY:
                dto = this.qualityService.getQualityIssuesDataForXlsx(issues);
                break;
            case BACKLOG:
                dto = this.backlogService.getBacklogIssuesDataForXlsx(issues);
                break;
            case DEFECTS:
                dto = this.defectsService.getDefectsIssuesDataForXlsx(issues);
                break;
        }

        return Utils.getDataFile(PLAIN_XLSX_CREATOR, dto);
    }

    public ResponseEntity<Resource> getIssuesListFile(int projectId, String type) throws IOException, RestTemplateException {
        Project project = this.generalRepository.findById(projectId).orElseThrow(ProjectDoesNotExistException::new);
        KpiTypes kpiType = KpiTypes.getTypeIgnoreCase(type);
        ByteArrayResource kpiFile = getKpiFile(projectId, kpiType);
        String name = Utils.projectNameDecorator(project.getName()) + "_" + type + ".xlsx";
        return Utils.giveFileToUser(name, kpiFile);
    }
}