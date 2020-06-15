package com.aiksanov.api.project.web.DTO.contrib;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContributingDTO {
    private int projectID;
    private String projectName;
}
