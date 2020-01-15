package com.aiksanov.api.project.web.DTO.actions;

import com.aiksanov.api.project.util.enums.actions.EditTypes;

public class EditSaveDTO {
    private EditTypes type;
    private ActionDTO data;

    public EditTypes getType() {
        return type;
    }

    public ActionDTO getData() {
        return data;
    }
}
