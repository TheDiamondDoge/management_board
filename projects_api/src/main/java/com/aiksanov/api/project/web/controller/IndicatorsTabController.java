package com.aiksanov.api.project.web.controller;

import com.aiksanov.api.project.data.entity.IndicatorsReqs;
import com.aiksanov.api.project.data.repository.IndicatorsReqsRepository;
import com.aiksanov.api.project.web.DTO.IndicatorsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/projects/{id}/tabs")
public class IndicatorsTabController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndicatorsTabController.class);
    private IndicatorsReqsRepository reqsRepository;

    @Autowired
    public IndicatorsTabController(IndicatorsReqsRepository reqsRepository) {
        this.reqsRepository = reqsRepository;
    }

    @RequestMapping("/indicators")
    public IndicatorsReqs getIndicatorsTab(@PathVariable Integer id) {
        LOGGER.info("GET /api/projects/{}/tabs/summary", id);
        return reqsRepository.findById(id).get();
    }
}
