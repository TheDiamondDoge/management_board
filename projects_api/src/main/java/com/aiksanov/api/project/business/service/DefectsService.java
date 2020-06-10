package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.web.DTO.DefectsIssue;
import com.aiksanov.api.project.web.DTO.backlog.BacklogDefectsChartDTO;
import com.aiksanov.api.project.web.DTO.kpi.PlainXlsxDataDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class DefectsService {
    private String CHART_URL = "http://localhost:8100/general/chart";

    public BacklogDefectsChartDTO getChartData(int projectId) {
        String finalUrl = CHART_URL + "/" + "defects/" + projectId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(finalUrl, BacklogDefectsChartDTO.class);
    }

    public PlainXlsxDataDTO getDefectsIssuesDataForXlsx(List issues) {
        String[] headers = getListOfDefectsIssuesExcelHeader();
        String[][] data = getDataAsStrings(issues);
        return new PlainXlsxDataDTO(headers, data);
    }

    private String[] getListOfDefectsIssuesExcelHeader() {
        return new String[]{"week", "crdbid", "crid", "productname", "releasename", "component", "item", "inState",
                "created", "TSsubmitDate", "assignee", "reporter", "outState", "resolution", "isDuplicate", "priority",
                "questStatus", "state", "proposedAction", "recomputedOn",
               };
    }

    private String[][] getDataAsStrings(List issues) {
        ObjectMapper mapper = new ObjectMapper();
        int rowSize = 0;
        int rowsAmount = issues.size();
        List<List<String>> data = new ArrayList<>();
        for (Object issue : issues) {
            List<String> row = new ArrayList<>();
            DefectsIssue defectsIssue = mapper.convertValue(issue, DefectsIssue.class);
            row.add(defectsIssue.getWeek());
            row.add(defectsIssue.getCrdbId());
            row.add(defectsIssue.getCrId());
            row.add(defectsIssue.getProductName());
            row.add(defectsIssue.getReleaseName());
            row.add(defectsIssue.getComponent());
            row.add(defectsIssue.getItem());
            row.add(defectsIssue.getInState());
            row.add(defectsIssue.getCreated());
            row.add(defectsIssue.getTSsubmitDate());
            row.add(defectsIssue.getAssignee());
            row.add(defectsIssue.getReporter());
            row.add(defectsIssue.getOutState());
            row.add(defectsIssue.getResolution());
            row.add(defectsIssue.getIsDuplicate());
            row.add(defectsIssue.getPriority());
            row.add(defectsIssue.getQuestStatus());
            row.add(defectsIssue.getState());
            row.add(defectsIssue.getProposedAction());
            row.add(Utils.dateToDateTimeString(defectsIssue.getRecomputedOn()));
            data.add(row);
            rowSize = row.size();
        }

        String[][] result = new String[rowsAmount][rowSize];
        for (int i = 0; i < rowsAmount; i++) {
            List<String> row = data.get(i);
            for (int j = 0; j < rowSize; j++) {
                result[i][j] = row.get(j);
            }
        }

        return result;
    }
}
