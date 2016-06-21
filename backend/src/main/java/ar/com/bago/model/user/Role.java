/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.bago.model.user;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import ar.com.bago.model.BackEndEntity;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Role extends BackEndEntity {

    private static final long serialVersionUID = -655321606681663452L;

    private String name;

    private List<Permission> permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
