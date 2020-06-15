package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.InformationTabService;
import com.aiksanov.api.project.business.service.MilestoneService;
import com.aiksanov.api.project.business.service.ProjectGeneralService;
import com.aiksanov.api.project.web.DTO.MilestoneDTO;
import com.aiksanov.api.project.web.DTO.information.InformationDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/projects/{id}/tabs")
public class InformationTabController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InformationTabController.class);
    private final InformationTabService informationService;
    private final MilestoneService milestoneService;
    private final ProjectGeneralService projectGeneralService;

    @GetMapping("/information")
    public InformationDTO getData(@PathVariable Integer id){
        LOGGER.info("GET /api/projects/{}/tabs/information", id);
        return this.informationService.getInfoTabData(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/information")
    public void saveInfo(@PathVariable Integer id, @RequestBody InformationDTO dto){
        LOGGER.info("POST /api/projects/{}/tabs/information", id);
        List<MilestoneDTO> milestones = dto.getMilestones();
        this.milestoneService.saveMilestones(id, milestones);
        this.informationService.saveInformationData(id, dto);
        this.projectGeneralService.updateProjectStateByMilestones(id);
    }
}
