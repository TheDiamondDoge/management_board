package com.aiksanov.api.project.business.service;

import com.aiksanov.api.project.data.entity.Actions;
import com.aiksanov.api.project.data.repository.ActionsRepository;
import com.aiksanov.api.project.web.DTO.actions.ActionDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActionsService {
    private ActionsRepository actionsRepository;

    @Autowired
    public ActionsService(ActionsRepository actionsRepository) {
        this.actionsRepository = actionsRepository;
    }

    public List<ActionDTO> getAllActionsByProjectId(int projectId) {
        List<Actions> actions = this.actionsRepository.findActionsByActionsPK_ProjectID(projectId);
        return actions.stream().map(ActionDTO::new).collect(Collectors.toList());
    }
}
