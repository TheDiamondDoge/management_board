package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.BlcDashboard;
import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import com.aiksanov.api.project.data.repository.BlcDashboardRepository;
import com.aiksanov.api.project.web.DTO.BlcDashboardDTO;
import com.aiksanov.api.project.web.DTO.BlcRowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BlcDashboardService {
    private BlcDashboardRepository dashboardRepository;

    @Autowired
    public BlcDashboardService(BlcDashboardRepository dashboardRepository) {
        this.dashboardRepository = dashboardRepository;
    }

    public BlcDashboardDTO getBlcDTO(int projectID) {
        BlcDashboardDTO blcDTO = new BlcDashboardDTO();
        blcDTO.setPm(getRowDTO(getBlcRow(projectID, "pm")));
        blcDTO.setPmo(getRowDTO(getBlcRow(projectID, "pmo")));
        blcDTO.setSales(getRowDTO(getBlcRow(projectID, "sales")));
        return blcDTO;
    }

    private BlcDashboard getBlcRow(int projectID, String role) {
        return this.dashboardRepository
                .findById(new BlcDashboardPK(projectID, role))
                .orElse(new BlcDashboard());
    }

    private BlcRowDTO getRowDTO(BlcDashboard blcRow){
        return new BlcRowDTO(blcRow);
    }

}
