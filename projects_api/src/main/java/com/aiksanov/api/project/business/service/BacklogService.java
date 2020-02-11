package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.web.DTO.backlog.BacklogChartDTO;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BacklogService {
    public BacklogChartDTO getChartData(int projectId) {
        //Mock for time being
        String[] labels = {"1901", "1902", "1903", "1904", "1905", "1906", "1907", "1908", "1909", "1910", "1911", "1912", "1913", "1914", "1915", "1916"};

        BacklogChartDTO dto = new BacklogChartDTO();
        dto.setDev(generateRandomDataset());
        dto.setIn(generateRandomDataset());
        dto.setNewIssues(generateRandomDataset());
        dto.setOut(generateRandomDataset());
        dto.setQa(generateRandomDataset());
        dto.setQaDone(generateRandomDataset());
        dto.setLabels(new ArrayList<>(Arrays.asList(labels)));
        dto.setUpdatedOn(new Date());

        return dto;
    }

    private List<Integer> generateRandomDataset() {
        List<Integer> numbers = new ArrayList<>();
        for (int j = 0; j < 15; j++) {
            numbers.add(getRandomInt() / 10000000);
        }

        return numbers;
    }

    private int getRandomInt() {
        Random rand = new Random();
        return Math.abs(rand.nextInt());
    }
}