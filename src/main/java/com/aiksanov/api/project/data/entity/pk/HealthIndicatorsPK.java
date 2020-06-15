package com.aiksanov.api.project.data.entity.pk;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class HealthIndicatorsPK implements Serializable {
    @Column(name = "project_id", insertable = false, updatable = false)
    private int projectID;

    @Column(name = "modification_date")
    private Date modificationDate;
}
