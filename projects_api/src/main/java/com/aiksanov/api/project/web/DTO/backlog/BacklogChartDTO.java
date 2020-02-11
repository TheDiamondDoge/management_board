package com.aiksanov.api.project.web.DTO.backlog;

import java.util.Date;
import java.util.List;

public class BacklogChartDTO {
    List<Integer> dev;
    List<Integer> in;
    List<String> labels;
    List<Integer> newIssues;
    List<Integer> out;
    List<Integer> qa;
    List<Integer> qaDone;
    Date updatedOn;

    public BacklogChartDTO() {
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

    public Date getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(Date updatedOn) {
        this.updatedOn = updatedOn;
    }
}
