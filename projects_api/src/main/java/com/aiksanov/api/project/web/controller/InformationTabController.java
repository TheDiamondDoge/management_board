package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.InformationTabService;
import com.aiksanov.api.project.web.DTO.InformationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
