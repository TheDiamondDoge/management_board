package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.BlcDashboard;

import java.util.Date;
import java.util.Objects;

public class BlcRowDTO {
    private String role;
    private String csl;
    private Date updatedOn;
    private BlcIndicators indicators;
    private String comment;

    public BlcRowDTO() {
    }

    public BlcRowDTO(BlcDashboard row) {
        this.csl = row.getCsl();
        this.role = setRole(row);
        this.updatedOn = row.getUpdatedOn();
        this.indicators = setBlcIndicators(row);
        this.comment = row.getComment();
    }

    private String setRole(BlcDashboard row) {
        if (Objects.isNull(row.getRoleObj())) {
            return null;
        } else {
            return row.getRoleObj().getName();
        }
    }

    private BlcIndicators setBlcIndicators (BlcDashboard row) {
        return new BlcIndicators(
                row.getOr(),
                row.getCharter(),
                row.getPrjPlan(),
                row.getTailoring(),
                row.getAccPrgMgr(),
                row.getAccCoreTeam(),
                row.getBpPlan(),
                row.getBpSales(),
                row.getLaunchPlan(),
                row.getLaunchSales(),
                row.getLessons(),
                row.getRisks()
        );
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

    public BlcIndicators getIndicators() {
        return indicators;
    }

    public String getComment() {
        return comment;
    }
}
