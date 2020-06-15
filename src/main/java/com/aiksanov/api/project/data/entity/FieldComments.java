package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.FieldCommentsPK;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_field_comments")
public class FieldComments {
    @EmbeddedId
    private FieldCommentsPK pk;

    @Column(name = "comment")
    private String comment;
}
