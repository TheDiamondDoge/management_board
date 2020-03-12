package com.aiksanov.api.project.web.DTO;

public class ErrorExportDTO {
    private int cellIndex;
    private int rowIndex;
    private String message;

    public ErrorExportDTO() {
    }

    public ErrorExportDTO(int cellIndex, int rowIndex, String message) {
        this.cellIndex = cellIndex;
        this.rowIndex = rowIndex;
        this.message = message;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "data.Error{" +
                "cellIndex=" + cellIndex +
                ", rowIndex=" + rowIndex +
                ", message='" + message + '\'' +
                '}';
    }
}
