package com.aiksanov.api.project.data.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prj_risks_info")
public class RisksTableInfo {
    @Id
    private int projectId;
    private Date uploadedOn;
    private String uploadedBy;
}
