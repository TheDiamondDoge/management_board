package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.util.enums.actions.ActionPriorityVals;
import com.aiksanov.api.project.util.enums.actions.ActionsRegistryVals;
import com.aiksanov.api.project.util.enums.actions.ActionsStateVals;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_actions")
public class Actions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Integer uid;

    @Column(name = "project_id")
    private int projectId;

    @Enumerated(EnumType.STRING)
    private ActionsRegistryVals registry;

    @Column(name = "title")
    private String title;

    @Enumerated(EnumType.STRING)
    private ActionsStateVals state;

    @Enumerated(EnumType.STRING)
    private ActionPriorityVals priority;

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
}