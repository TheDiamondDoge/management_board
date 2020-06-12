package com.aiksanov.api.project.web.DTO.contrib;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContribProjectsDataDTO {
    private List<ContributingProjectDTO> offer;
    private List<ContributingProjectDTO> products;
    private Date maxDate;
    private Date minDate;
}