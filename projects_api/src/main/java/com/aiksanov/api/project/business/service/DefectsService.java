package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.web.DTO.BacklogDefectsChartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DefectsService {
    private ServiceUtils utils;

    @Autowired
    public DefectsService(ServiceUtils utils) {
        this.utils = utils;
    }

    public BacklogDefectsChartDTO getChartData(int projectId) {
        //Mock for time being
        String[] labels = {"2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016"};

        BacklogDefectsChartDTO dto = new BacklogDefectsChartDTO();
        dto.setDev(utils.generateRandomDataset());
        dto.setIn(utils.generateRandomDataset());
        dto.setNewIssues(utils.generateRandomDataset());
        dto.setOut(utils.generateRandomDataset());
        dto.setQa(utils.generateRandomDataset());
        dto.setQaDone(utils.generateRandomDataset());
        dto.setLabels(new ArrayList<>(Arrays.asList(labels)));
        dto.setUpdatedOn(new Date());

        return dto;
    }
}
