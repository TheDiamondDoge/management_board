package com.aiksanov.api.project.web.DTO.actions;

import com.aiksanov.api.project.data.entity.ActionRelatedRisks;
import com.aiksanov.api.project.data.entity.Actions;
import com.aiksanov.api.project.data.entity.Risk;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class ActionDTO {
    private String registry;
    private Integer uid;
    private String title;
    private String state;
    private String priority;
    private String owner;
    private String optionalInfo;
    private Date dueDate;
    private String description;
    private String status;
    private Date createdDate;
    private Date closedDate;
    private List<String> relatedRisks;

    public ActionDTO(Actions actions) {
        if (Objects.nonNull(actions)) {
            this.setValues(actions);
        }
    }

    public ActionDTO() {
    }

    private void setValues(Actions actions) {
        this.title = actions.getTitle();
        this.owner = actions.getOwner();
        this.optionalInfo = actions.getOptionalInfo();
        this.dueDate = actions.getDueDate();
        this.description = actions.getDescription();
        this.status = actions.getStatus();
        this.createdDate = actions.getCreatedDate();
        this.closedDate = actions.getClosedDate();
        this.relatedRisks = this.getRisksIds(actions.getRelatedRisks());
        this.uid = actions.getUid();

        if (Objects.nonNull(actions.getRegistry())) {
            this.registry = actions.getRegistry().getRegistryLabel();
        }

        if (Objects.nonNull(actions.getState())) {
            this.state = actions.getState().getStateLabel();
        }

        if (Objects.nonNull(actions.getPriority())) {
            this.priority = actions.getPriority().getPriorityLabel();
        }
    }

    private List<String> getRisksIds(Set<Risk> riskSet) {
        if (Objects.nonNull(riskSet)) {
            return riskSet.stream().map(Risk::getRiskDisplayId).collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOptionalInfo() {
        return optionalInfo;
    }

    public void setOptionalInfo(String optionalInfo) {
        this.optionalInfo = optionalInfo;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }

    public List<String> getRelatedRisks() {
        return relatedRisks;
    }

    public void setRelatedRisks(List<String> relatedRisks) {
        this.relatedRisks = relatedRisks;
    }

    @Override
    public String toString() {
        return "ActionDTO{" +
                "registry='" + registry + '\'' +
                ", uid=" + uid +
                ", title='" + title + '\'' +
                ", state='" + state + '\'' +
                ", priority='" + priority + '\'' +
                ", owner='" + owner + '\'' +
                ", optionalInfo='" + optionalInfo + '\'' +
                ", dueDate=" + dueDate +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createdDate=" + createdDate +
                ", closedDate=" + closedDate +
                ", relatedRisks=" + relatedRisks +
                '}';
    }
}
