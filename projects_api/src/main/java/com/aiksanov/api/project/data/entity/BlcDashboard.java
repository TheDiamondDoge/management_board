package com.aiksanov.api.project.data.entity;

import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "prj_blc_dashboard")
public class BlcDashboard {
    @EmbeddedId
    private BlcDashboardPK pk;

    @Column(name = "updated_by")
    private String csl;

    @Column(name = "updated_on")
    private Date updatedOn;

    @Column(name = "or")
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

    @Column(name = "comments")
    private String comment;

    public BlcDashboard() {
    }

    public BlcDashboard(BlcDashboardPK pk, String csl, Date updatedOn, int or, int charter, int prjPlan, int tailoring,
                        int accPrgMgr, int accCoreTeam, int bpPlan, int bpSales, int launchPlan, int launchSales,
                        int lessons, int risks, String comment)
    {
        this.pk = pk;
        this.csl = csl;
        this.updatedOn = updatedOn;
        this.or = or;
        this.charter = charter;
        this.prjPlan = prjPlan;
        this.tailoring = tailoring;
        this.accPrgMgr = accPrgMgr;
        this.accCoreTeam = accCoreTeam;
        this.bpPlan = bpPlan;
        this.bpSales = bpSales;
        this.launchPlan = launchPlan;
        this.launchSales = launchSales;
        this.lessons = lessons;
        this.risks = risks;
        this.comment = comment;
    }

    public BlcDashboardPK getPk() {
        return pk;
    }

    public void setPk(BlcDashboardPK pk) {
        this.pk = pk;
    }

    public String getCsl() {
        return csl;
    }

    public void setCsl(String csl) {
        this.csl = csl;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }

    public int getOr() {
        return or;
    }

    public void setOr(int or) {
        this.or = or;
    }

    public int getCharter() {
        return charter;
    }

    public void setCharter(int charter) {
        this.charter = charter;
    }

    public int getPrjPlan() {
        return prjPlan;
    }

    public void setPrjPlan(int prjPlan) {
        this.prjPlan = prjPlan;
    }

    public int getTailoring() {
        return tailoring;
    }

    public void setTailoring(int tailoring) {
        this.tailoring = tailoring;
    }

    public int getAccPrgMgr() {
        return accPrgMgr;
    }

    public void setAccPrgMgr(int accPrgMgr) {
        this.accPrgMgr = accPrgMgr;
    }

    public int getAccCoreTeam() {
        return accCoreTeam;
    }

    public void setAccCoreTeam(int accCoreTeam) {
        this.accCoreTeam = accCoreTeam;
    }

    public int getBpPlan() {
        return bpPlan;
    }

    public void setBpPlan(int bpPlan) {
        this.bpPlan = bpPlan;
    }

    public int getBpSales() {
        return bpSales;
    }

    public void setBpSales(int bpSales) {
        this.bpSales = bpSales;
    }

    public int getLaunchPlan() {
        return launchPlan;
    }

    public void setLaunchPlan(int launchPlan) {
        this.launchPlan = launchPlan;
    }

    public int getLaunchSales() {
        return launchSales;
    }

    public void setLaunchSales(int launchSales) {
        this.launchSales = launchSales;
    }

    public int getLessons() {
        return lessons;
    }

    public void setLessons(int lessons) {
        this.lessons = lessons;
    }

    public int getRisks() {
        return risks;
    }

    public void setRisks(int risks) {
        this.risks = risks;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
