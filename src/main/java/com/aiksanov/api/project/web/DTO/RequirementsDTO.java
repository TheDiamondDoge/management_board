package com.aiksanov.api.project.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequirementsDTO {
    private String reqId;
    private String headline;
    private String priority;
    private String status;
    private List<String> fixVersions;
    private String components;
}