package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.EcmaBacklogTarget;
import com.aiksanov.api.project.data.repository.EcmaBacklogTargetRepo;
import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.web.DTO.backlog.BacklogDefectsChartDTO;
import com.aiksanov.api.project.web.DTO.backlog.BacklogIssue;
import com.aiksanov.api.project.web.DTO.information.EcmaBacklogTargetDTO;
import com.aiksanov.api.project.web.DTO.kpi.PlainXlsxDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class BacklogService {
    private final EcmaBacklogTargetRepo ecmaBacklogTargetRepo;

    @Value("${chart.base.url}")
    private String CHART_URL;

    public BacklogDefectsChartDTO getChartData(int projectId) {
        String finalUrl = CHART_URL + "/backlog/" + projectId;
        RestTemplate restTemplate = new RestTemplate();
        BacklogDefectsChartDTO dto = restTemplate.getForObject(finalUrl, BacklogDefectsChartDTO.class);
        if (Objects.nonNull(dto)) {
            dto.setTarget(getBacklogTarget(projectId));
        }

        return dto;
    }

    public EcmaBacklogTargetDTO getBacklogTarget(int projectId) {
        String[] milestones = {"DR5", "DR4", "TR", "CI"};
        List<EcmaBacklogTarget> targets = this.ecmaBacklogTargetRepo.findAllByProjectId(projectId);
        for (String milestone : milestones) {
            for (EcmaBacklogTarget target : targets) {
                if (Objects.nonNull(target) && target.getLabel().equalsIgnoreCase(milestone)) {
                    return new EcmaBacklogTargetDTO(target);
                }
            }
        }

        return null;
    }

    public PlainXlsxDataDTO getBacklogIssuesDataForXlsx(List<BacklogIssue> issues) {
        String[] headers = getListOfBacklogIssuesExcelHeader();
        String[][] data = getDataAsStrings(issues);
        return new PlainXlsxDataDTO(headers, data);
    }

    private String[] getListOfBacklogIssuesExcelHeader() {
        return new String[]{
                "week", "crdbid", "crid", "productname", "releasename", "component", "item", "created", "OUT",
                "resolution", "isDuplicate", "priority", "state", "proposedAction", "recomputedOn", "inBacklogAtDR4",
                "BacklogReduction"
        };
    }

    private String[][] getDataAsStrings(List<BacklogIssue> issues) {
        int rowSize = 0;
        int rowsAmount = issues.size();
        List<List<String>> data = new ArrayList<>();
        for (BacklogIssue backlogIssue : issues) {
            List<String> row = new ArrayList<>();
            row.add(backlogIssue.getWeek());
            row.add(backlogIssue.getCrdbId());
            row.add(backlogIssue.getCrId());
            row.add(backlogIssue.getProductName());
            row.add(backlogIssue.getReleaseName());
            row.add(backlogIssue.getComponent());
            row.add(backlogIssue.getItem());
            row.add(backlogIssue.getCreated());
            row.add(backlogIssue.getOutState());
            row.add(backlogIssue.getResolution());
            row.add(backlogIssue.getIsDuplicate());
            row.add(backlogIssue.getPriority());
            row.add(backlogIssue.getState());
            row.add(backlogIssue.getProposedAction());
            row.add(Utils.dateToDateTimeString(backlogIssue.getRecomputedOn()));
            row.add(backlogIssue.getInBacklogAtDR4());
            row.add(backlogIssue.getBacklogReduction());
            data.add(row);
            rowSize = row.size();
        }

        return Utils.listOfListsToStringArr(data, rowsAmount, rowSize);
    }
}