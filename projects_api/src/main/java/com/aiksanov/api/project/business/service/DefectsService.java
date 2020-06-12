package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.web.DTO.DefectsIssue;
import com.aiksanov.api.project.web.DTO.backlog.BacklogDefectsChartDTO;
import com.aiksanov.api.project.web.DTO.kpi.PlainXlsxDataDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class DefectsService {
    @Value("${chart.base.url}")
    private String CHART_URL;

    public BacklogDefectsChartDTO getChartData(int projectId) {
        String finalUrl = CHART_URL + "/defects/" + projectId;
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(finalUrl, BacklogDefectsChartDTO.class);
    }

    public PlainXlsxDataDTO getDefectsIssuesDataForXlsx(List<DefectsIssue> issues) {
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

    private String[][] getDataAsStrings(List<DefectsIssue> issues) {
        int rowSize = 0;
        int rowsAmount = issues.size();
        List<List<String>> data = new ArrayList<>();
        for (DefectsIssue defectsIssue : issues) {
            List<String> row = new ArrayList<>();
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

        return Utils.listOfListsToStringArr(data, rowsAmount, rowSize);
    }
}
