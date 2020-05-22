package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.web.DTO.kpi.PlainXlsxDataDTO;
import com.aiksanov.api.project.web.DTO.quality.QualityIssue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QualityService {
    private final ServiceUtils serviceUtils;

    @Autowired
    public QualityService(ServiceUtils serviceUtils) {
        this.serviceUtils = serviceUtils;
    }

    public PlainXlsxDataDTO getQualityIssuesDataForXlsx(List issues) {
        String[] headers = getListOfQualityIssuesExcelHeader();
        String[][] data = getDataAsStrings(issues);
        return new PlainXlsxDataDTO(headers, data);
    }

    private String[] getListOfQualityIssuesExcelHeader() {
        return new String[]{"week", "crdbid", "crid", "productname", "releasename", "component", "item", "inState",
                "created", "TSsubmitDate", "assignee", "reporter", "outState", "resolution", "isDuplicate", "priority",
                "questStatus", "state", "proposedAction", "recomputedOn",
                "type"};
    }

    private String[][] getDataAsStrings(List issues) {
        ObjectMapper mapper = new ObjectMapper();
        int rowSize = 0;
        int rowsAmount = issues.size();
        List<List<String>> data = new ArrayList<>();
        for (Object issue : issues) {
            List<String> row = new ArrayList<>();
            QualityIssue qualityIssue = mapper.convertValue(issue, QualityIssue.class);
            row.add(qualityIssue.getWeek());
            row.add(qualityIssue.getCrdbId());
            row.add(qualityIssue.getCrId());
            row.add(qualityIssue.getProductName());
            row.add(qualityIssue.getReleaseName());
            row.add(qualityIssue.getComponent());
            row.add(qualityIssue.getItem());
            row.add(qualityIssue.getInState());
            row.add(qualityIssue.getCreated());
            row.add(qualityIssue.getTSsubmitDate());
            row.add(qualityIssue.getAssignee());
            row.add(qualityIssue.getReporter());
            row.add(qualityIssue.getOutState());
            row.add(qualityIssue.getResolution());
            row.add(qualityIssue.getIsDuplicate());
            row.add(qualityIssue.getPriority());
            row.add(qualityIssue.getQuestStatus());
            row.add(qualityIssue.getState());
            row.add(qualityIssue.getProposedAction());
            row.add(serviceUtils.dateToDateTimeString(qualityIssue.getRecomputedOn()));
            row.add(qualityIssue.getType());
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
