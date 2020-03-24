package com.aiksanov.api.project.web.DTO.risks;

import java.util.List;

public class RisksTabDTO {
    private List<RisksDTO> risks;
    private boolean isFileExists;

    public RisksTabDTO() {
    }

    public RisksTabDTO(List<RisksDTO> risks, boolean isFileExists) {
        this.risks = risks;
        this.isFileExists = isFileExists;
    }

    public List<RisksDTO> getRisks() {
        return risks;
    }

    public void setRisks(List<RisksDTO> risks) {
        this.risks = risks;
    }

    public boolean isFileExists() {
        return isFileExists;
    }

    public void setFileExists(boolean fileExists) {
        isFileExists = fileExists;
    }
}