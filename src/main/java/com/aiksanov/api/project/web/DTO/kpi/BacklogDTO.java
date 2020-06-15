package com.aiksanov.api.project.web.DTO.kpi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BacklogDTO extends QualityIndicatorsAbstract{
    private String inBacklogAtDR4;
    private String backlogReduction;
}
