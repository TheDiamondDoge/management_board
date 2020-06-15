package com.aiksanov.api.project.web.DTO.kpi;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlainXlsxDataDTO {
    private String[] header;
    private String[][] data;
}
