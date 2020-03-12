package com.aiksanov.api.project.web.DTO.risks;

import com.aiksanov.api.project.web.DTO.ErrorExportDTO;

import java.util.List;

public class RisksFromFileDTO {
    private List<RisksFromFile> risks;
    private List<ErrorExportDTO> errors;

    public RisksFromFileDTO() {
    }

    public RisksFromFileDTO(List<RisksFromFile> risks, List<ErrorExportDTO> errors) {
        this.risks = risks;
        this.errors = errors;
    }

    public List<RisksFromFile> getRisks() {
        return risks;
    }

    public void setRisks(List<RisksFromFile> risks) {
        this.risks = risks;
    }

    public List<ErrorExportDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorExportDTO> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "data.RisksDTO{" +
                "risks=" + risks +
                ", errors=" + errors +
                '}';
    }
}
