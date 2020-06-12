package com.aiksanov.api.project.web.DTO.quality;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QualityIndicatorsTableDTO {
    private List<QualityIndicatorDTO> quality;
    private List<QualityIndicatorDTO> defects;
    private List<QualityIndicatorDTO> backlog;
    private List<QualityIndicatorDTO> testExecution;
    private List<QualityIndicatorDTO> testRate;
    private Date syncDate;
    private boolean updateInProcess;
}