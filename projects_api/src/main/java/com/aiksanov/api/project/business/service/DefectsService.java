package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.web.DTO.BacklogDefectsChartDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class DefectsService {
    private String CHART_URL = "http://localhost:8100/general/chart";

    public BacklogDefectsChartDTO getChartData(int projectId) {
        String finalUrl = CHART_URL + "/" + "defects/" + projectId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(finalUrl, BacklogDefectsChartDTO.class);
    }
}
