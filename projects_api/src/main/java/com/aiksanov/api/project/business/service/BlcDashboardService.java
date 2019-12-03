package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.BlcDashboard;
import com.aiksanov.api.project.data.entity.BlcDashboardComments;
import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import com.aiksanov.api.project.data.repository.BlcDashboardCommentsRepo;
import com.aiksanov.api.project.data.repository.BlcDashboardRepository;
import com.aiksanov.api.project.util.enums.BlcRoles;
import com.aiksanov.api.project.web.DTO.BlcDashboardDTO;
import com.aiksanov.api.project.web.DTO.BlcIndicators;
import com.aiksanov.api.project.web.DTO.BlcRowDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


@Service
public class BlcDashboardService {
    private BlcDashboardRepository dashboardRepository;
    private BlcDashboardCommentsRepo commentsRepo;

    @Autowired
    public BlcDashboardService(BlcDashboardRepository dashboardRepository, BlcDashboardCommentsRepo commentsRepo) {
        this.dashboardRepository = dashboardRepository;
        this.commentsRepo = commentsRepo;
    }

    public BlcDashboardDTO getBlcDTO(int projectID) {
        BlcDashboardDTO blcDTO = new BlcDashboardDTO();
        blcDTO.setPm(getRowDTO(getBlcRow(projectID, BlcRoles.PM.getRoleName())));
        blcDTO.setPmo(getRowDTO(getBlcRow(projectID, BlcRoles.PMO.getRoleName())));
        blcDTO.setSales(getRowDTO(getBlcRow(projectID, BlcRoles.SALES.getRoleName())));
        return blcDTO;
    }

    private BlcDashboard getBlcRow(int projectID, String role) {
        return this.dashboardRepository
                .findById(new BlcDashboardPK(projectID, role))
                .orElse(new BlcDashboard());
    }

    private BlcRowDTO getRowDTO(BlcDashboard blcRow) {
        String comment = getBlcComment(blcRow.getProjectID(), blcRow.getRole());
        return new BlcRowDTO(blcRow, comment);
    }

    private String getBlcComment(int projectId, String role) {
        BlcDashboardComments commentObj = this.commentsRepo.findById(new BlcDashboardPK(projectId, role))
                .orElseGet(BlcDashboardComments::new);
        return commentObj.getComment();
    }

    @Transactional
    public void saveBlcIndicators(int projectID, BlcDashboardDTO dto) {
        String rowName = dto.getRowToSave();
        if (Objects.isNull(rowName)) {
            throw new RuntimeException("RowToSave should not be null");
        }
        BlcDashboard indicators = getBlcDashboardFromDto(dto, rowName, projectID);

        this.dashboardRepository.save(indicators);
    }

    private BlcDashboard getBlcDashboardFromDto(BlcDashboardDTO dto, String role, int projectID) {
        if (Objects.isNull(dto)) {
            return null;
        }

        BlcRowDTO row = getRow(dto, role);

        if (Objects.nonNull(row)) {
            BlcDashboard blcObj = new BlcDashboard();
            blcObj.setProjectID(projectID);
            blcObj.setRole(row.getRole());
            //TODO: when session will be done - get csl form it
            blcObj.setCsl(row.getCsl());
            blcObj.setUpdatedOn(new Date());

            BlcIndicators indicators = row.getIndicators();
            if (Objects.nonNull(indicators)) {
                blcObj.setOr(indicators.getOr());
                blcObj.setCharter(indicators.getCharter());
                blcObj.setPrjPlan(indicators.getPrjPlan());
                blcObj.setTailoring(indicators.getTailoring());
                blcObj.setAccPrgMgr(indicators.getAccPrgMgr());
                blcObj.setAccCoreTeam(indicators.getAccCoreTeam());
                blcObj.setBpPlan(indicators.getBpPlan());
                blcObj.setBpSales(indicators.getBpSales());
                blcObj.setLaunchPlan(indicators.getLaunchPlan());
                blcObj.setLaunchSales(indicators.getLaunchSales());
                blcObj.setLessons(indicators.getLessons());
                blcObj.setRisks(indicators.getRisks());
            }
            return blcObj;
        }

        return null;
    }

    @Transactional
    public void saveBlcComments(int projectID, BlcDashboardDTO dto) {
        BlcRoles[] dtoRoles = {BlcRoles.PM, BlcRoles.PMO, BlcRoles.SALES};
        ArrayList<BlcDashboardComments> comments = new ArrayList<>();

        for (BlcRoles role : dtoRoles) {
            String comment = getCommentFromBlcDashDto(dto, role.getRoleName());
            comments.add(new BlcDashboardComments(new BlcDashboardPK(projectID, role.getRoleName()), comment));
        }

        this.commentsRepo.saveAll(comments);
    }

    private String getCommentFromBlcDashDto(BlcDashboardDTO dto, String rowName) {
        BlcRowDTO row = getRow(dto, rowName);
        if (Objects.nonNull(row)) {
            return row.getComment();
        } else {
            return null;
        }
    }

    private BlcRowDTO getRow(BlcDashboardDTO dto, String rowName) {
        if (Objects.isNull(dto)) {
            return null;
        }

        BlcRowDTO row = null;

        switch (rowName) {
            case "pm":
                row = dto.getPm();
                break;
            case "pmo":
                row = dto.getPmo();
                break;
            case "sales":
                row = dto.getSales();
                break;
        }

        return row;
    }
}
