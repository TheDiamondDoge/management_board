package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import com.aiksanov.api.project.util.enums.BlcRoles;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "prj_blc_dashboard")
@IdClass(BlcDashboardPK.class)
public class BlcDashboardRow {
    @Id
    @Column(name = "project_id")
    private int projectID;

    @Id
    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private BlcRoles role;

    @Column(name = "updated_by")
    private String csl;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "or_rank")
    private int or;

    @Column(name = "charter")
    private int charter;

    @Column(name = "prj_plan")
    private int prjPlan;

    @Column(name = "tailoring")
    private int tailoring;

    @Column(name = "acc_prg_mgr")
    private int accPrgMgr;

    @Column(name = "acc_core_team")
    private int accCoreTeam;

    @Column(name = "bp_plan")
    private int bpPlan;

    @Column(name = "bp_sales")
    private int bpSales;

    @Column(name = "launch_plan")
    private int launchPlan;

    @Column(name = "launch_sales")
    private int launchSales;

    @Column(name = "lessons")
    private int lessons;

    @Column(name = "risks")
    private int risks;
}
