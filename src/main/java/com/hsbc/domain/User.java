package com.hsbc.domain;

import com.hsbc.base.Bean;
import com.hsbc.util.AESUtils;

import java.io.Serializable;
import java.util.HashSet;

public class User implements Serializable {
    private String name;
    private String password;
    private HashSet<Role> roles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HashSet<Role> getRoles() {
        return roles;
    }

    public void setRoles(HashSet<Role> roles) {
        this.roles = roles;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = AESUtils.AESEncode(password);
        this.roles = new HashSet<>();
    }

    public static boolean createUser(String name, String password) {
        User user = Bean.userDao.selectUser(name);
        if (null != user) {
            return false;
        }
        return Bean.userDao.createUser(name, password);
    }

    public static boolean deleteUser(String name) {
        return Bean.userDao.deleteUser(name);
    }

    public static String authenticate() {
        return null;
    }


}
