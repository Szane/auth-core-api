package com.hsbc.dao.impl;

import com.hsbc.dao.UserDao;
import com.hsbc.domain.User;

public class UserDaoImpl implements UserDao {
    @Override
    public boolean createUser(String name, String password) {
        try {
            USER_MAP.put(name, new User(name, password));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(String name) {
        try {
           return null!= USER_MAP.remove(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateUser(User user) {
        try {
            if (USER_MAP.containsKey(user.getName())) {
                USER_MAP.put(user.getName(), user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public User selectUser(String name) {
        try {
            return USER_MAP.get(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
