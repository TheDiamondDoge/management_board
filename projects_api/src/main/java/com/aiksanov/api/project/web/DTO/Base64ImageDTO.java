package com.aiksanov.api.project.web.DTO;

import java.util.Objects;

public class Base64ImageDTO implements Comparable<Base64ImageDTO> {
    private String filename;
    private String base64Image;

    public Base64ImageDTO(String filename, String base64Image) {
        this.filename = filename;
        this.base64Image = base64Image;
    }

    public Base64ImageDTO() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }

    @Override
    public int compareTo(Base64ImageDTO o) {
        if (Objects.isNull(this.filename)) return -1;
        if (Objects.isNull(o.getFilename())) return 1;
        return o.getFilename().compareTo(this.filename);
    }
}