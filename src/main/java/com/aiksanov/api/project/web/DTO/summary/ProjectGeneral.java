package com.aiksanov.api.project.web.DTO.summary;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectGeneral {
    private String projectName;
    private String projectManager;
    private String url;
    private Date date;
}
