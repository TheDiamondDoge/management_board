package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.QualityIndicators;

import java.util.Objects;

public class QualityIndicatorDTO {
    private int rowNumber;
    private int objective;
    private int actual;
    private String comment;

    public QualityIndicatorDTO() {
    }

    public QualityIndicatorDTO(QualityIndicators indicators) {
        this.rowNumber = indicators.getRowNumber();
        this.objective = indicators.getObjective();
        this.actual = indicators.getActual();

        if (Objects.nonNull(indicators.getComment())) {
            this.comment = indicators.getComment().getComment();
        } else {
            this.comment = "";
        }
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getObjective() {
        return objective;
    }

    public int getActual() {
        return actual;
    }

    public String getComment() {
        return comment;
    }
}
