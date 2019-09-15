package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsCommentsPK;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "prj_indicators_comments")
public class HealthIndicatorsComments {
    @JsonIgnore
    @EmbeddedId
    private HealthIndicatorsCommentsPK pk;

    @Column(name = "comment")
    private String comment;

    public HealthIndicatorsComments() {
    }

    public HealthIndicatorsComments(HealthIndicatorsCommentsPK pk, String comment) {
        this.pk = pk;
        this.comment = comment;
    }

    public HealthIndicatorsCommentsPK getPk() {
        return pk;
    }

    public void setPk(HealthIndicatorsCommentsPK pk) {
        this.pk = pk;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
