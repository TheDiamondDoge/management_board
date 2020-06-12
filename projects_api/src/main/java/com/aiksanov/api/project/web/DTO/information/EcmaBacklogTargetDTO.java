package com.aiksanov.api.project.web.DTO.information;

import com.aiksanov.api.project.data.entity.EcmaBacklogTarget;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class EcmaBacklogTargetDTO {
    private String milestone;
    private int value;

    public EcmaBacklogTargetDTO(EcmaBacklogTarget target) {
        this.milestone = target.getLabel();
        this.value = target.getValue();
    }

    public EcmaBacklogTarget getEcmaBacklogTargetObj(int projectId) {
        return new EcmaBacklogTarget(projectId, milestone, value);
    }
}
