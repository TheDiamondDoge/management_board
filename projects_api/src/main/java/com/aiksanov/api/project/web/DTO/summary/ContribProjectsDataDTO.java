package com.aiksanov.api.project.web.DTO.summary;

import com.aiksanov.api.project.web.DTO.information.MilestoneDTO;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class ContribProjectsDataDTO {
    private String offerName;
    private List<MilestoneDTO> milestones;
    private Map<String, List<MilestoneDTO>> products;
    private Date maxDate;
    private Date minDate;

    public ContribProjectsDataDTO() {
    }

    public ContribProjectsDataDTO(String offerName, List<MilestoneDTO> milestones, Map<String,
            List<MilestoneDTO>> products, Date maxDate, Date minDate) {
        this.offerName = offerName;
        this.milestones = milestones;
        this.products = products;
        this.maxDate = maxDate;
        this.minDate = minDate;
    }

    public String getOfferName() {
        return offerName;
    }

    public void setOfferName(String offerName) {
        this.offerName = offerName;
    }

    public List<MilestoneDTO> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<MilestoneDTO> milestones) {
        this.milestones = milestones;
    }

    public Map<String, List<MilestoneDTO>> getProducts() {
        return products;
    }

    public void setProducts(Map<String, List<MilestoneDTO>> products) {
        this.products = products;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Date getMinDate() {
        return minDate;
    }

    public void setMinDate(Date minDate) {
        this.minDate = minDate;
    }
}