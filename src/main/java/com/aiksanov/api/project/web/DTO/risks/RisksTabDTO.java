package com.aiksanov.api.project.web.DTO.risks;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RisksTabDTO {
    private List<RisksDTO> risks;
    private boolean isFileExists;
    private Date lastUploaded;
}