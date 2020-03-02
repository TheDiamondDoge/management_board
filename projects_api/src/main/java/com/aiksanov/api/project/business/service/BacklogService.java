package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.web.DTO.BacklogDefectsChartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BacklogService {
    private ServiceUtils utils;

    @Autowired
    public BacklogService(ServiceUtils utils) {
        this.utils = utils;
    }

    //TODO: add population from metrics portal;
    //TODO: replace mock with actual data
    public BacklogDefectsChartDTO getChartData(int projectId) {
        //Mock for time being
        String[] labels = {"1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916"};

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