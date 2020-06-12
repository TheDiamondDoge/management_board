package com.aiksanov.api.project.web.DTO.reports;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class ReportTabDTO {
    private Date updatedOn;
    private String projectName;
    private String projectManager;
}
