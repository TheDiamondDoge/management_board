package com.aiksanov.api.project.data.entity.pk;

import com.aiksanov.api.project.util.enums.BlcRoles;
import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlcDashboardPK implements Serializable {
    private int projectID;
    private BlcRoles role;
}
