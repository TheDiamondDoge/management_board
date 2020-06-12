package com.aiksanov.api.project.web.DTO.blc;

import com.aiksanov.api.project.util.enums.BlcRoles;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BlcDashboardDTO {
    private BlcRowDTO pm;
    private BlcRowDTO pmo;
    private BlcRowDTO sales;
    private String rowToSave;

    public BlcRowDTO getRowDto(BlcRoles rowName) {
        switch (rowName) {
            case PM:
                return pm;
            case PMO:
                return pmo;
            case SALES:
                return sales;
            default:
                return null;
        }
    }
}
