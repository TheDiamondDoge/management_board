package com.aiksanov.api.project.data.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QualityIndicatorsPK implements Serializable {
    private int projectID;
    private String kpiID;
    private int rowNumber;
}
