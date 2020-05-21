package com.aiksanov.api.project.web.DTO;

import com.aiksanov.api.project.data.entity.EcmaBacklogTarget;
import com.aiksanov.api.project.web.DTO.information.EcmaBacklogTargetDTO;

import java.util.Date;
import java.util.List;

public class BacklogDefectsChartDTO {
    private List<Integer> dev;
    private List<Integer> in;
    private List<String> labels;
    private List<Integer> newIssues;
    private List<Integer> out;
    private List<Integer> qa;
    private List<Integer> qaDone;
    private EcmaBacklogTargetDTO target;
    private Date updatedOn;

    public BacklogDefectsChartDTO() {
    }

    public List<Integer> getDev() {
        return dev;
    }

    public void setDev(List<Integer> dev) {
        this.dev = dev;
    }

    public List<Integer> getIn() {
        return in;
    }

    public void setIn(List<Integer> in) {
        this.in = in;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Integer> getNewIssues() {
        return newIssues;
    }

    public void setNewIssues(List<Integer> newIssues) {
        this.newIssues = newIssues;
    }

    public List<Integer> getOut() {
        return out;
    }

    public void setOut(List<Integer> out) {
        this.out = out;
    }

    public List<Integer> getQa() {
        return qa;
    }

    public void setQa(List<Integer> qa) {
        this.qa = qa;
    }

    public List<Integer> getQaDone() {
        return qaDone;
    }

    public void setQaDone(List<Integer> qaDone) {
        this.qaDone = qaDone;
    }

    public EcmaBacklogTargetDTO getTarget() {
        return target;
    }

    public void setTarget(EcmaBacklogTargetDTO target) {
        this.target = target;
    }

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
