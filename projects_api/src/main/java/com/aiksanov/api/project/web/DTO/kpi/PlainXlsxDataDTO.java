package com.aiksanov.api.project.web.DTO.kpi;

public class PlainXlsxDataDTO {
    private String[] header;
    private String[][] data;

    public PlainXlsxDataDTO() {
    }

    public PlainXlsxDataDTO(String[] header, String[][] data) {
        this.header = header;
        this.data = data;
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }
}
