package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.BlcDashboardRow;
import com.aiksanov.api.project.data.entity.BlcDashboardComments;
import com.aiksanov.api.project.data.entity.pk.BlcDashboardPK;
import com.aiksanov.api.project.data.repository.BlcDashboardCommentsRepo;
import com.aiksanov.api.project.data.repository.BlcDashboardRepository;
import com.aiksanov.api.project.exceptions.NoRowToSaveException;
import com.aiksanov.api.project.util.enums.BlcRoles;
import com.aiksanov.api.project.web.DTO.blc.BlcDashboardDTO;
import com.aiksanov.api.project.web.DTO.blc.BlcIndicators;
import com.aiksanov.api.project.web.DTO.blc.BlcRowDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;


@RequiredArgsConstructor
@Service
public class BlcDashboardService {
    private final BlcDashboardRepository dashboardRepository;
    private final BlcDashboardCommentsRepo commentsRepo;

    public BlcDashboardDTO getBlcDashboardDTO(int projectID) {
        BlcDashboardDTO blcDTO = new BlcDashboardDTO();
        blcDTO.setPm(getRow(projectID, BlcRoles.PM));
        blcDTO.setPmo(getRow(projectID, BlcRoles.PMO));
        blcDTO.setSales(getRow(projectID, BlcRoles.SALES));
        return blcDTO;
    }

    private BlcRowDTO getRow(int projectID, BlcRoles role) {
        BlcDashboardRow dashboard = getBlcDashboardRow(projectID, role);
        return getRowDTO(projectID, role, dashboard);
    }

    private BlcDashboardRow getBlcDashboardRow(int projectID, BlcRoles role) {
        return this.dashboardRepository
                .findById(new BlcDashboardPK(projectID, role))
                .orElse(new BlcDashboardRow());
    }

    private BlcRowDTO getRowDTO(int projectID, BlcRoles role, BlcDashboardRow blcRow) {
        String comment = getBlcComment(projectID, role);
        return new BlcRowDTO(blcRow, comment);
    }

    private String getBlcComment(int projectId, BlcRoles role) {
        BlcDashboardComments commentObj = this.commentsRepo.findById(new BlcDashboardPK(projectId, role))
                .orElseGet(BlcDashboardComments::new);
        return commentObj.getComment();
    }

    @Transactional
    public void saveBlcIndicators(int projectID, BlcDashboardDTO dto) throws NoRowToSaveException {
        BlcRoles rowName;
        try {
            String rowToSave = dto.getRowToSave().toUpperCase();
            rowName = BlcRoles.valueOf(rowToSave);
        } catch (Exception e) {
            throw new NoRowToSaveException();
        }

        BlcDashboardRow indicators = createBlcDashboardRowFromDto(dto, rowName, projectID);
        if (Objects.nonNull(indicators)) {
            this.dashboardRepository.save(indicators);
        }
    }

    private BlcDashboardRow createBlcDashboardRowFromDto(BlcDashboardDTO dto, BlcRoles role, int projectID) {
        if (Objects.isNull(dto)) {
            return null;
        }

        BlcRowDTO row = dto.getRowDto(role);

        if (Objects.nonNull(row)) {
            BlcDashboardRow blcObj = new BlcDashboardRow();
            blcObj.setProjectID(projectID);
            blcObj.setRole(role);
            //TODO: when session will be done - get csl form it
            blcObj.setCsl("admin_user");
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
            String comment = getCommentFromBlcDashDto(dto, role);
            comments.add(new BlcDashboardComments(new BlcDashboardPK(projectID, role), comment));
        }

        this.commentsRepo.saveAll(comments);
    }

    private String getCommentFromBlcDashDto(BlcDashboardDTO dto, BlcRoles rowName) {
        BlcRowDTO row = dto.getRowDto(rowName);
        if (Objects.nonNull(row)) {
            return row.getComment();
        } else {
            return null;
        }
    }
}
