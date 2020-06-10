package com.aiksanov.api.project.data.entity;

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

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "registry", referencedColumnName = "registry_id")
    private ActionsRegistry registry;

    @Column(name = "title")
    private String title;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "state", referencedColumnName = "state_id")
    private ActionsState state;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "priority", referencedColumnName = "priority_id")
    private ActionsPriority priority;

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