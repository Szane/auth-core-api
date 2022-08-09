package com.hsbc.domain;

import java.io.Serializable;

/**
 * An user's role
 */
public class Role implements Serializable {
    /**
     * A role's name
     */
    private String name;

    public boolean createRole() {
        return false;
    }

    public boolean deleteRole() {
        return false;
    }

    public boolean addRoleToUser() {
        return false;
    }

    public boolean checkRole() {
        return false;
    }

    public String allRoles() {
        return null;
    }


}
