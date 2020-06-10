package com.aiksanov.api.project.data.entity.pk;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActionRelatedRisksPK implements Serializable {
    private int actionId;
    private String risksId;
    private int projectId;
}
