package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.*;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.util.enums.actions.ActionsStateVals;
import com.aiksanov.api.project.web.DTO.actions.ActionDTO;
import com.aiksanov.api.project.web.DTO.kpi.PlainXlsxDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ActionsService {
    private String PLAIN_XLSX_CREATOR = "http://localhost:8081/processors/plainXlsx";

    private final ActionsRepository actionsRepository;
    private final ActionRelatedRisksRepo actionRelatedRisksRepo;
    private final ActionsRegistryRepo actionsRegistryRepo;
    private final ActionsStateRepo actionsStateRepo;
    private final ActionsPriorityRepo actionsPriorityRepo;
    private final ProjectGeneralService generalService;


    public List<ActionDTO> getAllActionsByProjectId(int projectId) {
        List<Actions> actions = this.actionsRepository.findActionsByProjectId(projectId);
        return actions.stream().map(action -> {
            int actionId = action.getUid();
            List<ActionRelatedRisks> relatedRisks = this.actionRelatedRisksRepo.findByActionId(actionId);
            return new ActionDTO(action, relatedRisks);
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAction(int uid) {
        Actions actions = this.actionsRepository.findById(uid).orElseThrow(RuntimeException::new);
        int projectId = actions.getProjectId();

        this.actionsRepository.delete(actions);
        this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestActDel");
    }

    @Transactional
    public void saveAction(int projectId, ActionDTO actionDTO) {
        if (Objects.nonNull(actionDTO)) {
            Actions action = createActionsEntry(actionDTO, projectId);
            Actions savedAction = this.actionsRepository.save(action);
            List<String> riskIds = actionDTO.getRelatedRisks();
            if (Objects.nonNull(riskIds)) {
                this.saveRelatedRisks(actionDTO.getRelatedRisks(), savedAction);
            }

            this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestActSav");
        }
    }

    public int getActiveActions(int projectId) {
        Optional<ActionsState> state = this.actionsStateRepo.findById(ActionsStateVals.ACTIVE);
        return state.map(actionsState -> this.actionsRepository.countActionsByProjectIdAndState(projectId, actionsState))
                .orElse(0);
    }

    private Actions createActionsEntry(ActionDTO dto, int projectId) {
        Actions action = new Actions();

        String registryLabel = dto.getRegistry();
        ActionsRegistry registry = this.actionsRegistryRepo.findByRegistryLabel(registryLabel);
        ActionsState state = this.actionsStateRepo.findByStateLabel(dto.getState());
        ActionsPriority priority = this.actionsPriorityRepo.findByPriorityLabel(dto.getPriority());

        Integer uid = dto.getUid();

        action.setUid(uid);
        action.setProjectId(projectId);
        action.setRegistry(registry);
        action.setTitle(dto.getTitle());
        action.setState(state);
        action.setPriority(priority);
        action.setOwner(dto.getOwner());
        action.setOptionalInfo(dto.getOptionalInfo());
        action.setDueDate(dto.getDueDate());
        action.setDescription(dto.getDescription());
        action.setStatus(dto.getStatus());

        Date createdDate = dto.getCreatedDate();
        if (Objects.isNull(createdDate)) {
            createdDate = new Date(new java.util.Date().getTime());
        }

        action.setCreatedDate(createdDate);
        action.setClosedDate(dto.getClosedDate());

        return action;
    }

    private void saveRelatedRisks(List<String> riskIds, Actions action) {
        int projectId = action.getProjectId();
        int actionId = action.getUid();
        List<ActionRelatedRisks> relatedRisks = riskIds.stream()
                .map(id -> new ActionRelatedRisks(actionId, id, projectId))
                .collect(Collectors.toList());
        this.actionRelatedRisksRepo.deleteAllByActionId(actionId);
        this.actionRelatedRisksRepo.saveAll(relatedRisks);
    }

    public ResponseEntity<Resource> getActionsFile(int projectId) throws IOException, RestTemplateException {
        Project project = this.generalService.getProjectGeneralInfo(projectId);
        List<Actions> actions = this.actionsRepository.findActionsByProjectId(projectId);
        PlainXlsxDataDTO dto = getDataForActionsXlsx(actions);
        ByteArrayResource actionsFile = Utils.getDataFile(PLAIN_XLSX_CREATOR, dto);
        String filename = Utils.projectNameDecorator(project.getName() + ".xlsx");
        return Utils.giveFileToUser(filename, actionsFile);
    }

    private PlainXlsxDataDTO getDataForActionsXlsx(List<Actions> actions) {
        String[] headers = getActionExcelHeaders();
        String[][] data = getActionsDataForExcel(actions);
        return new PlainXlsxDataDTO(headers, data);
    }

    private String[] getActionExcelHeaders() {
        return new String[] {
                "Registry Type", "Unique ID", "Title", "State", "Priority", "Owner", "Optional Information", "Due Date",
                "Detailed Description", "Status Updates and Resolution Description", "Created Date", "Closed Date",
                "Related Risks"
        };
    }

    private String[][] getActionsDataForExcel(List<Actions> actions) {
        List<List<String>> data = new ArrayList<>();
        int rowSize = 0;
        int rowsAmount = actions.size();
        for (Actions action: actions) {
            List<String> row = new ArrayList<>();
            row.add(action.getRegistry().getRegistryLabel());
            row.add(action.getUid().toString());
            row.add(action.getTitle());
            row.add(action.getState().getStateLabel());
            row.add(action.getPriority().getPriorityLabel());
            row.add(action.getOwner());
            row.add(action.getOptionalInfo());
            row.add(Utils.dateToString(action.getDueDate()));
            row.add(action.getDescription());
            row.add(action.getStatus());
            row.add(Utils.dateToString(action.getCreatedDate()));
            row.add(Utils.dateToString(action.getClosedDate()));
            List<ActionRelatedRisks> relatedRisks = this.actionRelatedRisksRepo.findByActionId(action.getUid());
            List<String> riskIds = relatedRisks.stream()
                    .map(ActionRelatedRisks::getRisksId)
                    .collect(Collectors.toList());
            row.add(String.join(", ", riskIds));
            rowSize = row.size();

            data.add(row);
        }

        String[][] result = new String[rowsAmount][rowSize];
        for (int i = 0; i < rowsAmount; i++) {
            List<String> row = data.get(i);
            for (int j = 0; j < rowSize; j++) {
                result[i][j] = row.get(j);
            }
        }

        return result;
    }
}