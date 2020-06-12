package com.aiksanov.api.project.web.DTO.kpi;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class QualityDTO extends QualityIndicatorsAbstract{
    private int id;
    private String type;
    private String TSsubmitDate;
}
