package com.aiksanov.api.project.data.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ContributingProjectsPK implements Serializable {
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "contrib_id")
    private int contribID;
}
