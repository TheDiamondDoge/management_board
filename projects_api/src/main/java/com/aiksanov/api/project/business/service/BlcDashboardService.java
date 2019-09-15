package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.BlcDashboard;
import com.aiksanov.api.project.data.entity.BlcRoles;
import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import com.aiksanov.api.project.data.repository.BlcDashboardRepository;
import com.aiksanov.api.project.data.repository.BlcRolesRepository;
import com.aiksanov.api.project.web.DTO.BlcDashboardDTO;
import com.aiksanov.api.project.web.DTO.BlcRowDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BlcDashboardService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BlcDashboardService.class);
    private BlcRolesRepository rolesRepository;
    private BlcDashboardRepository dashboardRepository;

    @Autowired
    public BlcDashboardService(BlcRolesRepository rolesRepository, BlcDashboardRepository dashboardRepository) {
        this.rolesRepository = rolesRepository;
        this.dashboardRepository = dashboardRepository;
    }

    public BlcDashboardDTO getBlcDTO(int projectID) {
        Iterable<BlcRoles> roles = this.rolesRepository.findAll();
        List<BlcRowDTO> dashboardRows = new ArrayList<>();
        roles.forEach((role) -> {
            BlcRowDTO blcRow = new BlcRowDTO(this.dashboardRepository.findById(
                    new BlcDashboardPK(projectID, role.getRoleID())).orElse(new BlcDashboard()
            ));

            blcRow.setRole(role.getName());
            dashboardRows.add(blcRow);
        });

        BlcDashboardDTO blcDashboardDTO = new BlcDashboardDTO();
        dashboardRows.forEach((row) -> {
            mapRowToDTO(row, blcDashboardDTO);
        });

        return blcDashboardDTO;
    }

    private void mapRowToDTO(BlcRowDTO row, BlcDashboardDTO dto) {
        switch (row.getRoleID()){
            case "pm":
                dto.setPm(row);
                break;
            case "pmo":
                dto.setPmo(row);
                break;
            case "sales":
                dto.setSales(row);
                break;
        }
    }
}
