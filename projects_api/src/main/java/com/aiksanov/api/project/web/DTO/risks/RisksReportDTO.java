package com.aiksanov.api.project.web.DTO.risks;

import java.util.List;

public class RisksReportDTO {
    private List<RisksMinimalDTO> risks;

    public RisksReportDTO(List<RisksMinimalDTO> risks) {
        this.risks = risks;
    }

    public List<RisksMinimalDTO> getRisks() {
        return risks;
    }

    public void setRisks(List<RisksMinimalDTO> risks) {
        this.risks = risks;
    }
}
