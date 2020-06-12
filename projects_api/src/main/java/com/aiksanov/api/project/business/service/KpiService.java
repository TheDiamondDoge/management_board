package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Project;
import com.aiksanov.api.project.data.repository.GeneralRepository;
import com.aiksanov.api.project.exceptions.ProjectDoesNotExistException;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.util.enums.KpiTypes;
import com.aiksanov.api.project.web.DTO.DefectsIssue;
import com.aiksanov.api.project.web.DTO.backlog.BacklogIssue;
import com.aiksanov.api.project.web.DTO.kpi.PlainXlsxDataDTO;
import com.aiksanov.api.project.web.DTO.kpi.QualityIndicatorsAmountDTO;
import com.aiksanov.api.project.web.DTO.quality.QualityIssue;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class KpiService {
    private final GeneralRepository generalRepository;
    private final QualityService qualityService;
    private final BacklogService backlogService;
    private final DefectsService defectsService;

    @Value("${qualityIndicators.amount.url}")
    private String VALUES_URL;

    @Value("${kpi.base.url}")
    private String KPI_URL_BASE;

    @Value("${xlsx.plain.creator}")
    private String PLAIN_XLSX_CREATOR;


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
        List result = restTemplate.getForObject(url, List.class);
        PlainXlsxDataDTO dto = null;
        switch (type) {
            case QUALITY:
                List<QualityIssue> qualityIssues = (List<QualityIssue>) result;
                dto = this.qualityService.getQualityIssuesDataForXlsx(qualityIssues);
                break;
            case BACKLOG:
                List<BacklogIssue> backlogIssues = (List<BacklogIssue>) result;
                dto = this.backlogService.getBacklogIssuesDataForXlsx(backlogIssues);
                break;
            case DEFECTS:
                List<DefectsIssue> defectsIssues = (List<DefectsIssue>) result;
                dto = this.defectsService.getDefectsIssuesDataForXlsx(defectsIssues);
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