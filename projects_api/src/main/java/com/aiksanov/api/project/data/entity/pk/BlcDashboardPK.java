package com.aiksanov.api.project.data.entity.pk;

import java.io.Serializable;
import java.util.Objects;

public class BlcDashboardPK implements Serializable {
    private int projectID;
    private String roleID;

    public BlcDashboardPK() {
    }

    public BlcDashboardPK(int projectID, String role) {
        this.projectID = projectID;
        this.roleID = role;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getRole() {
        return roleID;
    }

    public void setRole(String role) {
        this.roleID = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlcDashboardPK that = (BlcDashboardPK) o;
        return projectID == that.projectID &&
                roleID.equals(that.roleID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, roleID);
    }
}
