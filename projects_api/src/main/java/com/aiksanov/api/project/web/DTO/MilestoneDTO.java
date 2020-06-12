package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.Milestone;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@NoArgsConstructor
public class MilestoneDTO {
    private String label;
    private Date baselineDate;
    private Date actualDate;
    private int completion;
    private String meetingMinutes;
    private boolean shown;


    public MilestoneDTO(Milestone milestone) {
        if (Objects.nonNull(milestone.getMilestonePK())) {
            this.label = milestone.getMilestonePK().getLabel();
        }
        this.baselineDate = milestone.getBaselineDate();
        this.actualDate = milestone.getActualDate();
        this.completion = milestone.getCompletion();
        this.meetingMinutes = milestone.getMeetingMinutes();
        this.shown = milestone.isShown();
    }
}
