package com.aiksanov.api.project.data.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ContributingProjectsPK implements Serializable {
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "contrib_id")
    private int contribID;

    public ContributingProjectsPK() {
    }

    public ContributingProjectsPK(int projectID, int contribID) {
        this.projectID = projectID;
        this.contribID = contribID;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getContribID() {
        return contribID;
    }

    public void setContribID(int contribID) {
        this.contribID = contribID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContributingProjectsPK that = (ContributingProjectsPK) o;
        return projectID == that.projectID &&
                contribID == that.contribID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, contribID);
    }
}
