package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.*;
import com.aiksanov.api.project.util.ServiceUtils;
import com.aiksanov.api.project.web.DTO.actions.ActionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ActionsService {
    private ActionRelatedRisksRepo relatedRisksRepo;
    private ActionsRepository actionsRepository;
    private ActionsRegistryRepo actionsRegistryRepo;
    private ActionsStateRepo actionsStateRepo;
    private ActionsPriorityRepo actionsPriorityRepo;
    private RisksService risksService;
    private ServiceUtils serviceUtils;

    @Autowired
    public ActionsService(ActionRelatedRisksRepo relatedRisksRepo, ActionsRepository actionsRepository, ActionsRegistryRepo actionsRegistryRepo,
                          ActionsStateRepo actionsStateRepo, ActionsPriorityRepo actionsPriorityRepo, ServiceUtils serviceUtils,
                          RisksService riskService) {
        this.relatedRisksRepo = relatedRisksRepo;
        this.actionsRepository = actionsRepository;
        this.actionsRegistryRepo = actionsRegistryRepo;
        this.actionsStateRepo = actionsStateRepo;
        this.actionsPriorityRepo = actionsPriorityRepo;
        this.risksService = riskService;
        this.serviceUtils = serviceUtils;
    }

    public List<ActionDTO> getAllActionsByProjectId(int projectId) {
        List<Actions> actions = this.actionsRepository.findActionsByProjectId(projectId);
        return actions.stream().map(ActionDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public void saveAction(int projectId, ActionDTO actionDTO) {
        if (Objects.nonNull(actionDTO)) {
            Actions action = createActionsEntry(actionDTO, projectId);
            Actions savedAction = this.actionsRepository.save(action);
            saveRelatedRisks(actionDTO, savedAction.getUid(), projectId);
        }
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
            createdDate = new Date(serviceUtils.getCurrentDate().getTime());
        }

        action.setCreatedDate(createdDate);
        action.setClosedDate(dto.getClosedDate());

        return action;
    }

    private void saveRelatedRisks(ActionDTO dto, int actionId, int projectId) {
        List<String> riskDisplayIds = dto.getRelatedRisks();
        Set<Risk> risks = this.risksService.getRisksByIds(riskDisplayIds);
        List<Integer> riskIds = risks.stream().map(Risk::getRiskId).collect(Collectors.toList());
        if (riskIds.size() != 0) {
            List<ActionRelatedRisks> relatedRisks = riskIds.stream()
                    .map((riskId) -> new ActionRelatedRisks(actionId, riskId, projectId))
                    .collect(Collectors.toList());

            this.relatedRisksRepo.saveAll(relatedRisks);
        }
    }
}
