package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.InformationTabService;
import com.aiksanov.api.project.web.DTO.InformationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projects/{id}/tabs")
public class InformationTabController {
    private static final Logger LOGGER = LoggerFactory.getLogger(InformationTabController.class);
    private InformationTabService informationService;

    @Autowired
    public InformationTabController(InformationTabService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/information")
    public InformationDTO getData(@PathVariable Integer id){
        LOGGER.info("GET /api/projects/{}/tabs/information", id);
        return this.informationService.getInfoTabData(id);
    }

    @CrossOrigin(origins = "*")
    @PostMapping("/information")
    public void saveInfo(@PathVariable Integer id, @RequestBody InformationDTO dto){
        LOGGER.info("POST /api/projects/{}/tabs/information", id);
        this.informationService.saveInformationData(id, dto);
    }
}
