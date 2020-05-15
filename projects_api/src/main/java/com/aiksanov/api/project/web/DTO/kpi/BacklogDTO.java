package com.aiksanov.api.project.web.DTO.kpi;


public class BacklogDTO extends QualityIndicatorsAbstract{
    private String inBacklogAtDR4;
    private String backlogReduction;

    public BacklogDTO() {
    }

    public String getInBacklogAtDR4() {
        return inBacklogAtDR4;
    }

    public void setInBacklogAtDR4(String inBacklogAtDR4) {
        this.inBacklogAtDR4 = inBacklogAtDR4;
    }

    public String getBacklogReduction() {
        return backlogReduction;
    }

    public void setBacklogReduction(String backlogReduction) {
        this.backlogReduction = backlogReduction;
    }
}
