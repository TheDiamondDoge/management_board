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
public class StatusReportPK implements Serializable {
    @Column(name = "project_id")
    private Integer projectId;

    @Column(name = "report_id")
    private int reportId;
}
