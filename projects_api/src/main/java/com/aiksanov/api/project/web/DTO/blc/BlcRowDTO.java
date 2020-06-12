package com.aiksanov.api.project.web.DTO.blc;

import com.aiksanov.api.project.data.entity.BlcDashboardRow;
import com.aiksanov.api.project.util.enums.BlcRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class BlcRowDTO {
    private BlcRoles role;
    private String csl;
    private Date updatedOn;
    private BlcIndicators indicators;
    private String comment;

    public BlcRowDTO(BlcDashboardRow row, String comment) {
        if (Objects.nonNull(row)) {
            this.csl = row.getCsl();
            this.role = row.getRole();
            this.updatedOn = row.getUpdatedOn();
            this.indicators = setBlcIndicators(row);
        }
        this.comment = comment;
    }

    private BlcIndicators setBlcIndicators (BlcDashboardRow row) {
        return new BlcIndicators(
                row.getOr(),
                row.getCharter(),
                row.getPrjPlan(),
                row.getTailoring(),
                row.getAccPrgMgr(),
                row.getAccCoreTeam(),
                row.getBpPlan(),
                row.getBpSales(),
                row.getLaunchPlan(),
                row.getLaunchSales(),
                row.getLessons(),
                row.getRisks()
        );
    }
}
