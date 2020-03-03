package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Cost;
import com.aiksanov.api.project.data.repository.CostDetailsRepository;
import com.aiksanov.api.project.data.repository.CostRepository;
import com.aiksanov.api.project.util.enums.CostRowTypes;
import com.aiksanov.api.project.web.DTO.cost.CostDTO;
import com.aiksanov.api.project.web.DTO.cost.CostRowDTO;
import com.aiksanov.api.project.web.DTO.cost.CostTableDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CostService {
    private CostRepository costRepository;

    @Autowired
    public CostService(CostRepository costRepository) {
        this.costRepository = costRepository;
    }

    public CostDTO getCostData(int projectId) {
        List<Cost> costList = this.costRepository.findAllByProjectId(projectId);
        List<CostRowDTO> chargeRows = new ArrayList<>();
        List<CostRowDTO> capexRows = new ArrayList<>();
        costList.forEach((cost) -> {
            CostRowDTO rowDTO = new CostRowDTO(cost);
            int costType = cost.getType();
            if (costType == CostRowTypes.CHARGE.getValue()) {
                chargeRows.add(rowDTO);
            } else if (costType == CostRowTypes.CAPEX.getValue()) {
                capexRows.add(rowDTO);
            }
        });

        Date updated = null;
        if (costList.size() > 0) {
            updated = costList.get(0).getUpdated().getUpdated();
        }
        CostTableDTO charged = buildCostTable(chargeRows);
        CostTableDTO capex = buildCostTable(capexRows);
        return new CostDTO(updated, charged, capex);
    }

    private CostTableDTO buildCostTable(List<CostRowDTO> rows) {
        CostTableDTO tableDto = new CostTableDTO();
        if (Objects.nonNull(rows) && rows.size() > 0) {
            rows.forEach(row -> {
                if (Objects.nonNull(row) && row.getState() == 0) {
                    tableDto.setCommitted(row);
                } else if (Objects.nonNull(row) && row.getState() == 1) {
                    tableDto.setRealized(row);
                }
            });
        }
        return tableDto;
    }
}
