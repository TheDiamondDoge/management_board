package com.aiksanov.api.project.web.DTO.quality;

import com.aiksanov.api.project.data.entity.QualityIndicators;
import com.aiksanov.api.project.data.entity.QualityIndicatorsComments;

import java.util.Objects;

public class QualityIndicatorDTO {
    private int rowNumber;
    private String objective;
    private String actual;
    private String comment;

    public QualityIndicatorDTO() {
    }

    public QualityIndicatorDTO(QualityIndicators indicators, QualityIndicatorsComments comment) {
        this.rowNumber = indicators.getRowNumber();
        this.objective = indicators.getObjective();
        this.actual = indicators.getActual();

        if (Objects.nonNull(comment)) {
            this.comment = comment.getComment();
        } else {
            this.comment = "";
        }
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public String getObjective() {
        return objective;
    }

    public String getActual() {
        return actual;
    }

    public String getComment() {
        return comment;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }
}
