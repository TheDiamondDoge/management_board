package com.aiksanov.api.project.web.DTO.blc;

public class BlcDashboardDTO {
    private BlcRowDTO pm;
    private BlcRowDTO pmo;
    private BlcRowDTO sales;
    private String rowToSave;

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

    public String getRowToSave() {
        return rowToSave;
    }

    public void setRowToSave(String rowToSave) {
        this.rowToSave = rowToSave;
    }
}
