package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.*;
import com.aiksanov.api.project.exceptions.RestTemplateException;
import com.aiksanov.api.project.util.Utils;
import com.aiksanov.api.project.util.enums.actions.ActionsStateVals;
import com.aiksanov.api.project.web.DTO.actions.ActionDTO;
import com.aiksanov.api.project.web.DTO.kpi.PlainXlsxDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ActionsService {
    private final ActionsRepository actionsRepository;
    private final ActionRelatedRisksRepo actionRelatedRisksRepo;
    private final ProjectGeneralService generalService;

    @Value("${xlsx.plain.creator}")
    private String PLAIN_XLSX_CREATOR_URL;


    public List<ActionDTO> getAllActionsByProjectId(int projectId) {
        List<Action> actions = this.actionsRepository.findActionsByProjectId(projectId);
        return actions.stream().map(action -> {
            int actionId = action.getUid();
            List<ActionRelatedRisks> relatedRisks = this.actionRelatedRisksRepo.findByActionId(actionId);
            return new ActionDTO(action, relatedRisks);
        }).collect(Collectors.toList());
    }

    @Transactional
    public void deleteAction(int uid) {
        Action action = this.actionsRepository.findById(uid).orElseThrow(RuntimeException::new);
        int projectId = action.getProjectId();

        this.actionsRepository.delete(action);
        this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestActDel");
    }

    @Transactional
    public void saveAction(int projectId, ActionDTO actionDTO) {
        if (Objects.nonNull(actionDTO)) {
            Action action = actionDTO.toActionEntity(projectId);
            Action savedAction = this.actionsRepository.save(action);
            List<String> riskIds = actionDTO.getRelatedRisks();
            if (Objects.nonNull(riskIds)) {
                this.saveRelatedRisks(actionDTO.getRelatedRisks(), savedAction);
            }

            this.generalService.modifyWorkspaceUpdatedBy(projectId, "TestActSav");
        }
    }

    public int getActiveActions(int projectId) {
        return this.actionsRepository.countActionsByProjectIdAndState(projectId, ActionsStateVals.ACTIVE);
    }

    private void saveRelatedRisks(List<String> riskIds, Action action) {
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
        List<Action> actions = this.actionsRepository.findActionsByProjectId(projectId);
        PlainXlsxDataDTO dto = getDataForActionsXlsx(actions);
        ByteArrayResource actionsFile = Utils.getDataFile(PLAIN_XLSX_CREATOR_URL, dto);
        String filename = Utils.projectNameDecorator(project.getName())  + ".xlsx";
        return Utils.giveFileToUser(filename, actionsFile);
    }

    private PlainXlsxDataDTO getDataForActionsXlsx(List<Action> actions) {
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

    private String[][] getActionsDataForExcel(List<Action> actions) {
        List<List<String>> data = new ArrayList<>();
        int rowSize = 0;
        int rowsAmount = actions.size();
        for (Action action: actions) {
            List<String> row = new ArrayList<>();
            row.add(action.getRegistry().toString());
            row.add(action.getUid().toString());
            row.add(action.getTitle());
            row.add(action.getState().toString());
            row.add(action.getPriority().toString());
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

        return Utils.listOfListsToStringArr(data, rowsAmount, rowSize);
    }
}