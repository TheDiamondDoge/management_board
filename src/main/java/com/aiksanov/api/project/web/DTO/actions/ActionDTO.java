package com.aiksanov.api.project.web.DTO.actions;

import com.aiksanov.api.project.data.entity.ActionRelatedRisks;
import com.aiksanov.api.project.data.entity.Action;
import com.aiksanov.api.project.util.enums.actions.ActionPriorityVals;
import com.aiksanov.api.project.util.enums.actions.ActionsRegistryVals;
import com.aiksanov.api.project.util.enums.actions.ActionsStateVals;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class ActionDTO {
    private ActionsRegistryVals registry;
    private Integer uid;
    private String title;
    private ActionsStateVals state;
    private ActionPriorityVals priority;
    private String owner;
    private String optionalInfo;
    private Date dueDate;
    private String description;
    private String status;
    private Date createdDate;
    private Date closedDate;
    private List<String> relatedRisks;

    public ActionDTO(Action action, List<ActionRelatedRisks> relatedRisks) {
        if (Objects.nonNull(action)) {
            this.setValues(action, relatedRisks);
        }
    }

    private void setValues(Action action, List<ActionRelatedRisks> relatedRisks) {
        this.title = action.getTitle();
        this.owner = action.getOwner();
        this.optionalInfo = action.getOptionalInfo();
        this.dueDate = action.getDueDate();
        this.description = action.getDescription();
        this.status = action.getStatus();
        this.createdDate = action.getCreatedDate();
        this.closedDate = action.getClosedDate();
        this.relatedRisks = this.getRisksIds(relatedRisks);
        this.uid = action.getUid();

        if (Objects.nonNull(action.getRegistry())) {
            this.registry = action.getRegistry();
        }

        if (Objects.nonNull(action.getState())) {
            this.state = action.getState();
        }

        if (Objects.nonNull(action.getPriority())) {
            this.priority = action.getPriority();
        }
    }

    public Action toActionEntity(int projectId) {
        Action action = new Action();

        action.setUid(uid);
        action.setProjectId(projectId);
        action.setRegistry(registry);
        action.setTitle(title);
        action.setState(state);
        action.setPriority(priority);
        action.setOwner(owner);
        action.setOptionalInfo(optionalInfo);
        action.setDueDate(dueDate);
        action.setDescription(description);
        action.setStatus(status);

        Date createdDate = this.createdDate;
        if (Objects.isNull(createdDate)) {
            createdDate = new Date(new java.util.Date().getTime());
        }

        action.setCreatedDate(createdDate);
        action.setClosedDate(closedDate);

        return action;
    }

    private List<String> getRisksIds(List<ActionRelatedRisks> riskList) {
        if (Objects.nonNull(riskList)) {
            return riskList.stream().map(ActionRelatedRisks::getRisksId).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }
}