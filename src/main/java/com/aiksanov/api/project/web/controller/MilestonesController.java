package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.MilestoneService;
import com.aiksanov.api.project.data.entity.Milestone;
import com.aiksanov.api.project.data.entity.pk.MilestonePK;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/milestones")
public class MilestonesController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MilestonesController.class);
    private final MilestoneService milestoneService;

    @GetMapping("/{projectId}")
    public List<MilestoneDTO> getMilestonesByProjectID(@PathVariable Integer projectId, @RequestParam boolean isShown) {
        LOGGER.info("GET /api/milestones/{}?isShown={}", projectId, isShown);
        if (isShown) {
            return this.milestoneService.getTimelineMilestones(projectId);
        } else {
            return this.milestoneService.getMilestoneDTOsForInfoTab(projectId);
        }
    }

    @GetMapping("/{projectId}/{label}")
    public Milestone getProjectMilestone(@PathVariable Integer projectId, @PathVariable String label) {
        LOGGER.info("GET /api/milestones/{}/{}", projectId, label);
        return this.milestoneService.getProjectMilestoneById(projectId, label);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/{projectId}")
    public void saveMilestones(@PathVariable Integer projectId, @RequestBody List<MilestoneDTO> dtos) {
        LOGGER.info("POST /api/milestones/{}", projectId);
        this.milestoneService.saveMilestones(projectId, dtos);
    }

    @DeleteMapping
    public void deleteMilestones(@RequestBody List<MilestonePK> milestonesPKs) {
        LOGGER.info("DELETE /api/milestones/");
        this.milestoneService.deleteMilestonesData(milestonesPKs);
    }
}
