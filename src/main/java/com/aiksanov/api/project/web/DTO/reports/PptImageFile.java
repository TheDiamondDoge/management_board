package com.aiksanov.api.project.web.DTO.reports;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PptImageFile {
    private String filename;
    private byte[] imageInBytes;
}
