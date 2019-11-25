package com.aiksanov.api.project.data.entity.pk;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class FieldCommentsPK implements Serializable {
    @Column(name = "project_id")
    private int projectID;

    @Column(name = "field_name")
    private String fieldName;

    public FieldCommentsPK() {
    }

    public FieldCommentsPK(int projectID, String fieldName) {
        this.projectID = projectID;
        this.fieldName = fieldName;
    }

    public int getProjectID() {
        return projectID;
    }

    public void setProjectID(int projectID) {
        this.projectID = projectID;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FieldCommentsPK that = (FieldCommentsPK) o;
        return projectID == that.projectID &&
                Objects.equals(fieldName, that.fieldName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectID, fieldName);
    }
}
