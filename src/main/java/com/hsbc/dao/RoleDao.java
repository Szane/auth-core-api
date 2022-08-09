package com.hsbc.dao;

import com.hsbc.domain.Role;

public interface RoleDao {
    boolean createRole(String name);
    boolean deleteRole(String name);
    Role selectRole(String name);
}
