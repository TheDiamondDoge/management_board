package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.*;
import com.aiksanov.api.project.data.repository.ActionsPriorityRepo;
import com.aiksanov.api.project.data.repository.ActionsRegistryRepo;
import com.aiksanov.api.project.data.repository.ActionsRepository;
import com.aiksanov.api.project.data.repository.ActionsStateRepo;
import com.aiksanov.api.project.web.DTO.actions.ActionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ActionsService {
    private ActionsRepository actionsRepository;
    private ActionsRegistryRepo actionsRegistryRepo;
    private ActionsStateRepo actionsStateRepo;
    private ActionsPriorityRepo actionsPriorityRepo;

    @Autowired
    public ActionsService(ActionsRepository actionsRepository, ActionsRegistryRepo actionsRegistryRepo,
                          ActionsStateRepo actionsStateRepo, ActionsPriorityRepo actionsPriorityRepo) {
        this.actionsRepository = actionsRepository;
        this.actionsRegistryRepo = actionsRegistryRepo;
        this.actionsStateRepo = actionsStateRepo;
        this.actionsPriorityRepo = actionsPriorityRepo;
    }

    public List<ActionDTO> getAllActionsByProjectId(int projectId) {
        List<Actions> actions = this.actionsRepository.findActionsByProjectId(projectId);
        return actions.stream().map(ActionDTO::new).collect(Collectors.toList());
    }

    public void saveAction(int projectId, ActionDTO actionDTO) {
        if (Objects.nonNull(actionDTO)) {
            Actions action = createActionsEntry(actionDTO, projectId);
            this.actionsRepository.save(action);
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
        action.setDescription(dto.getStatus());
        action.setCreatedDate(dto.getCreatedDate());
        action.setClosedDate(dto.getClosedDate());

        if (Objects.nonNull(action.getRelatedRisks())) {
            List<ActionRelatedRisks> actionRelatedRisks = action.getRelatedRisks()
                    .stream()
                    .map(risk -> new ActionRelatedRisks(uid, risk.getRisksId(), projectId)).collect(Collectors.toList());

            action.setRelatedRisks(actionRelatedRisks);
        }

        return action;
    }
}
