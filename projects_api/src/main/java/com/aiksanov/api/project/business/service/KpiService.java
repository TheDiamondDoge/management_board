package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.web.DTO.kpi.QualityIndicatorsAmountDTO;
import com.aiksanov.api.project.web.DTO.kpi.QualityIndicatorsDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class KpiService {
    private final String VALUES_URL = "http://localhost:8100/general/qualityIndicators/amount";
    private final String ISSUES_URL = "http://localhost:8100/general/qualityIndicators/issues";

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

    public QualityIndicatorsDTO getQualityIndicatorsIssues(int projectId) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(ISSUES_URL + "/" + projectId, QualityIndicatorsDTO.class);
    }
}
