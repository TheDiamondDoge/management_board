package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Risk;
import com.aiksanov.api.project.data.repository.RisksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RisksService {
    private RisksRepository risksRepository;

    @Autowired
    public RisksService(RisksRepository risksRepository) {
        this.risksRepository = risksRepository;
    }

    public List<Risk> getProjectRisks(int projectId) {
        return this.risksRepository.findAllByProjectId(projectId);
    }

    public List<Float> getListOfProjectsRisks(int projectId) {
        return this.risksRepository.findAllByProjectId(projectId)
                .stream()
                .map(Risk::getRiskId)
                .collect(Collectors.toList());
    }
}
