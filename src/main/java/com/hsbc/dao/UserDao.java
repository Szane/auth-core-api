package com.hsbc.dao;

import com.hsbc.domain.User;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface UserDao {
    /**
     * mock database by a static map
     */
    Map<String, User> USER_MAP = new ConcurrentHashMap<String, User>();

    /**
     * create a new user
     */
    boolean createUser(String name, String password);

    /**
     * remove an exists user
     */
    boolean deleteUser(String name);

    /**
     * modify a user
     */
    boolean updateUser(User user);

    /**
     * query a user by name
     */
    User selectUser(String name);
}
