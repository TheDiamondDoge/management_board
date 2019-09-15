package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.BlcDashboard;
import com.aiksanov.api.project.data.entity.BlcRoles;

import java.util.List;

public class BlcDashboardDTO {
    private BlcRowDTO pm;
    private BlcRowDTO pmo;
    private BlcRowDTO sales;

    public BlcDashboardDTO() {
    }

    public BlcRowDTO getPm() {
        return pm;
    }

    public void setPm(BlcRowDTO pm) {
        this.pm = pm;
    }

    public BlcRowDTO getPmo() {
        return pmo;
    }

    public void setPmo(BlcRowDTO pmo) {
        this.pmo = pmo;
    }

    public BlcRowDTO getSales() {
        return sales;
    }

    public void setSales(BlcRowDTO sales) {
        this.sales = sales;
    }
}
