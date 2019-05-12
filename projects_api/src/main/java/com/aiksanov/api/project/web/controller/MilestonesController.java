package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.MilestoneService;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.MilestonePK;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/milestones")
public class MilestonesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MilestonesController.class);
    private MilestoneService milestoneService;

    @Autowired
    public MilestonesController(MilestoneService milestoneService) {
        this.milestoneService = milestoneService;
    }

    @GetMapping("/{projectId}")
    public List<MilestoneDTO> getMilestonesByProjectID(@PathVariable Integer projectId){
        LOGGER.info("GET /api/milestones/{}", projectId);
        return this.milestoneService.getMilestonesByProjectID(projectId);
    }

    @GetMapping("/{projectId}/{label}")
    public Milestone getProjectMilestone(@PathVariable Integer projectId, @PathVariable String label){
        LOGGER.info("GET /api/milestones/{}/{}", projectId, label);
        return this.milestoneService.getProjectMilestoneById(projectId, label);
    }

    @PutMapping
    public void addMilestones(@RequestBody List<MilestoneDTO> milestoneDTOs){
        LOGGER.info("PUT /api/milestones/");
        this.milestoneService.addMilestonesData( milestoneDTOs);
    }

    @DeleteMapping
    public void deleteMilestones(@RequestBody List<MilestonePK> milestonesPKs){
        LOGGER.info("DELETE /api/milestones/");
        this.milestoneService.deleteMilestonesData(milestonesPKs);
    }
}
