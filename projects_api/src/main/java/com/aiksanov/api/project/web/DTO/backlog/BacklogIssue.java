package com.aiksanov.api.project.web.DTO.backlog;

import com.aiksanov.api.project.web.DTO.IndicatorsKpiAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BacklogIssue extends IndicatorsKpiAbstract {
    private int id;
    private int projectId;
    private String crdbId;
    private String inBacklogAtDR4;
    private String backlogReduction;
}