package com.aiksanov.api.project.web.DTO.risks;

import java.util.Date;
import java.util.List;

public class RisksTabDTO {
    private List<RisksDTO> risks;
    private boolean isFileExists;
    private Date lastUploaded;

    public RisksTabDTO() {
    }

    public RisksTabDTO(List<RisksDTO> risks, boolean isFileExists, Date lastUploaded) {
        this.risks = risks;
        this.isFileExists = isFileExists;
        this.lastUploaded = lastUploaded;
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

    public Date getLastUploaded() {
        return lastUploaded;
    }

    public void setLastUploaded(Date lastUploaded) {
        this.lastUploaded = lastUploaded;
    }
}