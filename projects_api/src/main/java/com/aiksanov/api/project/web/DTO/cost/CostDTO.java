package com.aiksanov.api.project.web.DTO.cost;

import java.util.Date;

public class CostDTO {
    private Date updated;
    private CostTableDTO charged;
    private CostTableDTO capex;
    private boolean isFileExists;

    public CostDTO() {
    }

    public CostDTO(Date updated, CostTableDTO charged, CostTableDTO capex) {
        this.updated = updated;
        this.charged = charged;
        this.capex = capex;
    }

    public CostDTO(Date updated, CostTableDTO charged, CostTableDTO capex, boolean isFileExists) {
        this.updated = updated;
        this.charged = charged;
        this.capex = capex;
        this.isFileExists = isFileExists;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    public CostTableDTO getCharged() {
        return charged;
    }

    public void setCharged(CostTableDTO charged) {
        this.charged = charged;
    }

    public CostTableDTO getCapex() {
        return capex;
    }

    public void setCapex(CostTableDTO capex) {
        this.capex = capex;
    }

    public boolean isFileExists() {
        return isFileExists;
    }

    public void setFileExists(boolean fileExists) {
        isFileExists = fileExists;
    }
}
