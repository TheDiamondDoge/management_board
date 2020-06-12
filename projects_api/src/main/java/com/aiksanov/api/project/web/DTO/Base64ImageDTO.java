package com.aiksanov.api.project.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Base64ImageDTO implements Comparable<Base64ImageDTO> {
    private String filename;
    private String base64Image;

    @Override
    public int compareTo(Base64ImageDTO o) {
        if (Objects.isNull(this.filename)) return -1;
        if (Objects.isNull(o.getFilename())) return 1;
        return o.getFilename().compareTo(this.filename);
    }
}