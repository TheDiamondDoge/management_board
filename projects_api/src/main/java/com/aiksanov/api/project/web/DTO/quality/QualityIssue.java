package com.aiksanov.api.project.web.DTO.quality;


import com.aiksanov.api.project.web.DTO.IndicatorsKpiAbstract;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QualityIssue extends IndicatorsKpiAbstract {
    private int id;
    private int projectId;
    private String crdbId;
    private String type;
    private String TSsubmitDate;
}
