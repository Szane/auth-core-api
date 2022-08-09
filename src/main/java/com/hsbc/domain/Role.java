package com.hsbc.domain;

import com.hsbc.base.Bean;
import com.hsbc.dao.TokenDao;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * An user's role
 */
public class Role implements Serializable {
    /**
     * A role's name
     */
    private String name;

    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static boolean createRole(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        Role role = Bean.roleDao.selectRole(name);
        if (null != role) {
            return false;
        }
        return Bean.roleDao.createRole(name);
    }

    public static boolean deleteRole(String name) {
        if (StringUtils.isBlank(name)) {
            return false;
        }
        Role role = Bean.roleDao.selectRole(name);
        if (null != role) {
            return false;
        }
        return Bean.roleDao.deleteRole(name);
    }

    public static boolean addRoleToUser(String userName, String roleName) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(roleName)) {
            return false;
        }
        User user = Bean.userDao.selectUser(userName);
        Role role = Bean.roleDao.selectRole(roleName);
        if (null == user || null == role) {
            return false;
        }
        return user.getRoles().add(role);
    }

    public static boolean checkRole(String token, String roleName) {
        if (StringUtils.isBlank(token) || StringUtils.isBlank(roleName)) {
            return false;
        }
        if (!TokenDao.TOKEN_MAP.containsKey(token)) {
            return false;
        }
        String userName = TokenDao.TOKEN_MAP.get(token);
        User user = Bean.userDao.selectUser(userName);
        for (Role role : user.getRoles()) {
            if (role.getName().equals(roleName)) {
                return true;
            }
        }
        return false;
    }

    public static Set<Role> allRoles(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        if (!TokenDao.TOKEN_MAP.containsKey(token)) {
            return null;
        }
        String userName = TokenDao.TOKEN_MAP.get(token);
        User user = Bean.userDao.selectUser(userName);
        return user.getRoles();
    }


}
