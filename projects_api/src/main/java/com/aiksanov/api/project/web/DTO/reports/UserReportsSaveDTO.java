package com.aiksanov.api.project.web.DTO.reports;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserReportsSaveDTO {
    private String summary;
    private String red;
    private String orange;
    private String green;
    private String details;
}
