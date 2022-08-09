package com.hsbc.dao;

import com.hsbc.domain.Role;
import com.hsbc.domain.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface RoleDao {
    /**
     * mock database by a static map
     */
    Map<String, Role> ROLE_MAP = new ConcurrentHashMap<>();
    boolean createRole(String name);
    boolean deleteRole(String name);
    Role selectRole(String name);
}
