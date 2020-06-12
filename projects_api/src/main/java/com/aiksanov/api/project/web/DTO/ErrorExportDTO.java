package com.aiksanov.api.project.web.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorExportDTO {
    private int cellIndex;
    private int rowIndex;
    private String message;
}
