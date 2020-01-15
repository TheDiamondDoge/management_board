package com.aiksanov.api.project.data.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ActionsPK implements Serializable {
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "uid")
    private int uid;

    public ActionsPK() {
    }

    public ActionsPK(int projectID, int uid) {
        this.projectID = projectID;
        this.uid = uid;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActionsPK actionsPK = (ActionsPK) o;
        return projectID == actionsPK.projectID &&
                uid == actionsPK.uid;
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, uid);
    }
}
