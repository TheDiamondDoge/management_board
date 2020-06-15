package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.util.enums.ProjectRigors;
import com.aiksanov.api.project.util.enums.ProjectStates;
import com.aiksanov.api.project.util.enums.ProjectTemplates;
import com.aiksanov.api.project.util.enums.ProjectTypes;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

//TODO: PWS status - separate entity (???)
@Data
@NoArgsConstructor
@Entity
@Table(name = "PRJ_WORKSPACE_GENERAL")
public class Project {
    @Id
    @Column(name = "project_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int projectID;

    @Column(name = "project_uid")
    private String uid;

    @Column(name = "project_name")
    private String name;

    @Column(name = "project_type")
//    @Convert(converter = ProjectTypeConverter.class)
    private ProjectTypes type;

    @Column(name = "project_rigor")
    private ProjectRigors rigor;

    @Column(name = "project_state")
    @Enumerated(EnumType.STRING)
    private ProjectStates state;

    @Column(name = "project_manager")
    private String manager;

    @Column(name = "EPM_project")
    private boolean epm;

    @Column(name = "project_template")
    private ProjectTemplates template;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private Product product;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private WorkspaceInfo workspaceInfo;

    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @OneToOne
    @JoinColumn(name = "project_id", referencedColumnName = "project_id")
    private ProjectAdditionalInfo additionalInfo;
}
