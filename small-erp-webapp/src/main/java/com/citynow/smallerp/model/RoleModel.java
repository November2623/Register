package com.citynow.smallerp.model;

import com.citynow.smallerp.entity.RoleName;

import java.util.HashSet;
import java.util.Set;

public class RoleModel {
    private Long id;
    private RoleName name;
    private Set<RoleModel> roles = new HashSet<RoleModel>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<RoleModel> getRoles() {
        return roles;
    }

    public void setRoles(Set<RoleModel> roles) {
        this.roles = roles;
    }
}
