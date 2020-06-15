package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.HealthIndicatorsCommentsPK;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_indicators_comments")
public class HealthIndicatorsComments {
    @JsonIgnore
    @EmbeddedId
    private HealthIndicatorsCommentsPK pk;

    @Column(name = "comment")
    private String comment;
}
