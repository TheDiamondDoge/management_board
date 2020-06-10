package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Cost;
import com.aiksanov.api.project.data.entity.CostDetails;
import com.aiksanov.api.project.data.repository.CostDetailsRepository;
import com.aiksanov.api.project.data.repository.CostRepository;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.util.enums.cost.CostRowTypes;
import com.aiksanov.api.project.util.enums.cost.CostStates;
import com.aiksanov.api.project.web.DTO.cost.CostDTO;
import com.aiksanov.api.project.web.DTO.cost.CostRowDTO;
import com.aiksanov.api.project.web.DTO.cost.CostTableDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class CostService {
    private static final String UPLOAD_URL = "http://localhost:8081/processors/cost/";
    private static final String COST_SUFFIX = "_cost.xlsx";

    @Value("${cost.file.storage}")
    private String COST_STORAGE;
    private final CostRepository costRepository;
    private final CostDetailsRepository costDetailsRepository;
    private final ProjectGeneralService generalService;


    public List<Cost> getAllCostObjectsByPrjId(int projectId) {
        return this.costRepository.findAllByProjectId(projectId);
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
            CostDetails costDetails = this.costDetailsRepository.findById(projectId).orElseGet(CostDetails::new);
            updated = costDetails.getUpdated();
        }
        CostTableDTO charged = buildCostTable(chargeRows);
        CostTableDTO capex = buildCostTable(capexRows);
        boolean isCostFileExists = this.isCostFileExists(projectId);
        return new CostDTO(updated, charged, capex, isCostFileExists);
    }

    private CostTableDTO buildCostTable(List<CostRowDTO> rows) {
        CostTableDTO tableDto = new CostTableDTO();
        if (Objects.nonNull(rows) && rows.size() > 0) {
            rows.forEach(row -> {
                if (Objects.nonNull(row) && row.getState() == CostStates.COMMITTED.getValue()) {
                    tableDto.setCommitted(row);
                } else if (Objects.nonNull(row) && row.getState() == CostStates.RELEASED.getValue()) {
                    tableDto.setRealized(row);
                }
            });
        }
        return tableDto;
    }

    //TODO: Can produce null - fix (or should throw smthng)
    public void processCostFile(MultipartFile file, int projectId) throws IOException, RestTemplateException {
        String bd = generalService.getProjectsBD(projectId);
        CostDTO costFromFile =
                (CostDTO) Utils.sendFileToService(file, UPLOAD_URL + bd, CostDTO.class).getBody();
        if (Objects.nonNull(costFromFile)) {
            saveCostData(costFromFile, projectId);
        }

        String filename = projectId + COST_SUFFIX;
        try {
            Utils.saveFile(file, filename, COST_STORAGE);
        } catch (Exception e) {
            //Log this
        }
    }

    @Transactional
    public void saveCostData(CostDTO dto, int projectId) {
        List<Cost> costsToSave = new ArrayList<>();
        CostTableDTO chargedTableDTO = dto.getCharged();
        CostTableDTO capexTableDTO = dto.getCapex();

        CostRowDTO chargedCommittedDTO = chargedTableDTO.getCommitted();
        CostRowDTO chargedReleasedDTO = chargedTableDTO.getRealized();
        CostRowDTO capexCommittedDTO = capexTableDTO.getCommitted();
        CostRowDTO capexReleasedDTO = capexTableDTO.getRealized();

        Cost chargedCommitted = chargedCommittedDTO.getCostWithoutTypeAndPrjId();
        chargedCommitted.setProjectId(projectId);
        chargedCommitted.setType(CostRowTypes.CHARGE.getValue());
        costsToSave.add(chargedCommitted);

        Cost chargedReleased = chargedReleasedDTO.getCostWithoutTypeAndPrjId();
        chargedReleased.setProjectId(projectId);
        chargedReleased.setType(CostRowTypes.CHARGE.getValue());
        costsToSave.add(chargedReleased);

        Cost capexCommitted = capexCommittedDTO.getCostWithoutTypeAndPrjId();
        capexCommitted.setProjectId(projectId);
        capexCommitted.setType(CostRowTypes.CAPEX.getValue());
        costsToSave.add(capexCommitted);

        Cost capexReleased = capexReleasedDTO.getCostWithoutTypeAndPrjId();
        capexReleased.setProjectId(projectId);
        capexReleased.setType(CostRowTypes.CAPEX.getValue());
        costsToSave.add(capexReleased);

        this.costRepository.saveAll(costsToSave);
        this.costDetailsRepository.save(new CostDetails(projectId, new Date()));
        this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestCostUpl");
    }

    public ResponseEntity<Resource> getLastUpdatedFile(int projectId) throws IOException {
        String projectName = generalService.getProjectName(projectId);
        String filepath = COST_STORAGE + File.separator + projectId + COST_SUFFIX;
        String filename = projectName + COST_SUFFIX;
        return Utils.giveFileToUser(filename, filepath);
    }

    private boolean isCostFileExists(int projectId) {
        String filename = projectId + COST_SUFFIX;
        String filepath = COST_STORAGE + File.separator + filename;
        File file = new File(filepath);

        return file.exists() && file.isFile();
    }
}
