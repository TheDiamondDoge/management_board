package com.aiksanov.api.project.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prj_blc_roles")
public class BlcRoles {
    @Id
    @Column(name = "role_id")
    private String roleID;

    @Column(name = "role_name")
    private String name;

    public BlcRoles() {
    }

    public BlcRoles(String roleID, String name) {
        this.roleID = roleID;
        this.name = name;
    }

    public String getRoleID() {
        return roleID;
    }

    public void setRoleID(String roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
