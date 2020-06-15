package com.aiksanov.api.project.web.DTO.backlog;

import com.aiksanov.api.project.web.DTO.information.EcmaBacklogTargetDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class BacklogDefectsChartDTO {
    private List<Integer> dev;
    private List<Integer> in;
    private List<String> labels;
    private List<Integer> newIssues;
    private List<Integer> out;
    private List<Integer> qa;
    private List<Integer> qaDone;
    private EcmaBacklogTargetDTO target;
    private Date updatedOn;
}
