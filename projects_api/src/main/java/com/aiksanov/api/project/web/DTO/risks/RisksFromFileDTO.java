package com.aiksanov.api.project.web.DTO.risks;

import com.aiksanov.api.project.web.DTO.ErrorExportDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RisksFromFileDTO {
    private List<RisksDTO> risks;
    private List<ErrorExportDTO> errors;
}
