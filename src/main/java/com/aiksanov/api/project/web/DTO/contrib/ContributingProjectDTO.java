package com.aiksanov.api.project.web.DTO.contrib;

import com.aiksanov.api.project.util.enums.ProjectStates;
import com.aiksanov.api.project.util.enums.ProjectTypes;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ContributingProjectDTO {
    private int projectId;
    private String projectName;
    private ProjectStates projectState;
    private String projectType;
    private MilestoneDTO lastApproved;
    private List<MilestoneDTO> milestones;

    public ContributingProjectDTO(int projectId, String projectName, ProjectStates projectState, ProjectTypes projectType, MilestoneDTO lastApproved, List<MilestoneDTO> milestones) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectState = projectState;
        this.projectType = projectType.getValue();
        this.lastApproved = lastApproved;
        this.milestones = milestones;
    }
}