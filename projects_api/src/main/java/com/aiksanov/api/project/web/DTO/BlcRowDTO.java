package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.BlcDashboard;

import java.util.Date;

public class BlcRowDTO {
    private String roleID;
    private String role;
    private String csl;
    private Date updatedOn;
    private int or;
    private int charter;
    private int prjPlan;
    private int tailoring;
    private int accPrgMgr;
    private int accCoreTeam;
    private int bpPlan;
    private int bpSales;
    private int launchPlan;
    private int launchSales;
    private int lessons;
    private int risks;
    private String comment;

    public BlcRowDTO() {
    }

    public BlcRowDTO(BlcDashboard row) {
        this.roleID = row.getPk().getRole();
        this.csl = row.getCsl();
        this.updatedOn = row.getUpdatedOn();
        this.or = row.getOr();
        this.charter = row.getCharter();
        this.prjPlan = row.getPrjPlan();
        this.tailoring = row.getTailoring();
        this.accPrgMgr = row.getAccPrgMgr();
        this.accCoreTeam = row.getAccCoreTeam();
        this.bpPlan = row.getBpPlan();
        this.bpSales = row.getBpSales();
        this.launchPlan = row.getLaunchPlan();
        this.launchSales = row.getLaunchSales();
        this.lessons = row.getLessons();
        this.risks = row.getRisks();
        this.comment = row.getComment();
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public String getCsl() {
        return csl;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public int getOr() {
        return or;
    }

    public int getCharter() {
        return charter;
    }

    public int getPrjPlan() {
        return prjPlan;
    }

    public int getTailoring() {
        return tailoring;
    }

    public int getAccPrgMgr() {
        return accPrgMgr;
    }

    public int getAccCoreTeam() {
        return accCoreTeam;
    }

    public int getBpPlan() {
        return bpPlan;
    }

    public int getBpSales() {
        return bpSales;
    }

    public int getLaunchPlan() {
        return launchPlan;
    }

    public int getLaunchSales() {
        return launchSales;
    }

    public int getLessons() {
        return lessons;
    }

    public int getRisks() {
        return risks;
    }

    public String getComment() {
        return comment;
    }
}
