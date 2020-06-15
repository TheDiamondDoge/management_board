package com.aiksanov.api.project.web.DTO.cost;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CostTableDTO {
    private CostRowDTO committed;
    private CostRowDTO realized;
}
