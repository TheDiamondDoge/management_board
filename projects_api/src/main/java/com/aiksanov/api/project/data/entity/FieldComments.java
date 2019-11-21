package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.FieldCommentsPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prj_field_comments")
public class FieldComments {
    @EmbeddedId
    private FieldCommentsPK pk;

    @Column(name = "comment")
    private String comment;

    public FieldComments(FieldCommentsPK pk, String comment) {
        this.pk = pk;
        this.comment = comment;
    }

    public FieldComments() {

    }

    public FieldCommentsPK getPk() {
        return pk;
    }

    public void setPk(FieldCommentsPK pk) {
        this.pk = pk;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
