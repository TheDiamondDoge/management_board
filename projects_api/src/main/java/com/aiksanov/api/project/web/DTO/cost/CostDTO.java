package com.aiksanov.api.project.web.DTO.cost;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CostDTO {
    private Date updated;
    private CostTableDTO charged;
    private CostTableDTO capex;
    private boolean isFileExists;
}
