package com.hsbc.dao.impl;

import com.hsbc.dao.RoleDao;
import com.hsbc.domain.Role;
import com.hsbc.domain.User;

public class RoleDaoImpl implements RoleDao {
    @Override
    public boolean createRole(String name) {
        try {
            ROLE_MAP.put(name, new Role(name));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteRole(String name) {
        try {
           return null!= ROLE_MAP.remove(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Role selectRole(String name) {
        try {
            return ROLE_MAP.get(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
