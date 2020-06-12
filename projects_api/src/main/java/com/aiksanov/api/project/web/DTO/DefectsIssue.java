package com.aiksanov.api.project.web.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DefectsIssue extends IndicatorsKpiAbstract{
    private int id;
    private int projectId;
    private String crdbId;
    private String TSsubmitDate;
}
