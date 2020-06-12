package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.business.service.ProjectGeneralService;
import com.aiksanov.api.project.business.service.SummaryTabService;
import com.aiksanov.api.project.web.DTO.contrib.ContribProjectsDataDTO;
import com.aiksanov.api.project.web.DTO.summary.SummaryDTO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("${api.project.tabs}")
public class SummaryTabController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SummaryTabController.class);
    private final SummaryTabService summaryTabService;
    private final ProjectGeneralService generalService;

    @GetMapping("/summary")
    public SummaryDTO getSummaryTabData(@PathVariable Integer id){
        LOGGER.info("GET /api/projects/{}/tabs/summary", id);
        return this.summaryTabService.getSummaryDTO(id);
    }

    @GetMapping("/contrib")
    public ContribProjectsDataDTO getContribData(@PathVariable int id) {
        LOGGER.info("GET /api/projects/{}/tabs/contrib", id);
        return this.generalService.getContibData(id);
    }
}
