package com.aiksanov.api.project.web.DTO.actions;

import com.aiksanov.api.project.util.enums.actions.EditTypes;
import lombok.Getter;

@Getter
public class EditSaveDTO {
    private EditTypes type;
    private ActionDTO data;
}
