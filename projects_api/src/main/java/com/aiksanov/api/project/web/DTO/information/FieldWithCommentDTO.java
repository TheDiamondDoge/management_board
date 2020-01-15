package com.aiksanov.api.project.web.DTO.information;

public class FieldWithCommentDTO {
    private String value;
    private String comment;

    public FieldWithCommentDTO() {
    }

    public FieldWithCommentDTO(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
