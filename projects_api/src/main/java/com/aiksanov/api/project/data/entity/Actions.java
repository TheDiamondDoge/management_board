package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.ActionsPK;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "prj_actions")
public class Actions {
    @EmbeddedId
    private ActionsPK actionsPK;

    @OneToOne
    @JoinColumn(name = "registry", referencedColumnName = "registry_id")
    private ActionsRegistry registry;

    @Column(name = "title")
    private String title;

    @OneToOne
    @JoinColumn(name = "state", referencedColumnName = "state_id")
    private ActionsState state;

    @OneToOne
    @JoinColumn(name = "priority", referencedColumnName = "priority_id")
    private ActionsPriority priority;

    @Column(name = "owner")
    private String owner;

    @Column(name = "optional_info")
    private String optionalInfo;

    @Column(name = "due")
    private Date dueDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private String status;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "closed_date")
    private Date closedDate;

    public Actions() {
    }

    public ActionsPK getActionsPK() {
        return actionsPK;
    }

    public void setActionsPK(ActionsPK actionsPK) {
        this.actionsPK = actionsPK;
    }

    public ActionsRegistry getRegistry() {
        return registry;
    }

    public void setRegistry(ActionsRegistry registry) {
        this.registry = registry;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ActionsState getState() {
        return state;
    }

    public void setState(ActionsState state) {
        this.state = state;
    }

    public ActionsPriority getPriority() {
        return priority;
    }

    public void setPriority(ActionsPriority priority) {
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
}