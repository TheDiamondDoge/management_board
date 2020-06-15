package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.util.enums.WorkspaceStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_workspace_info")
public class WorkspaceInfo {
    @Id
    @Column(name = "project_id")
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(name = "workspace_status")
    private WorkspaceStatus status;

    @Column(name = "workspace_created")
    private Date created;

    @Column(name = "workspace_modified")
    private Date modified;

    @Column(name = "workspace_modified_by")
    private String modifiedBy;
}
