package com.hsbc.base;

import com.hsbc.api.AuthApi;
import com.hsbc.dao.RoleDao;
import com.hsbc.dao.UserDao;
import com.hsbc.dao.impl.RoleDaoImpl;
import com.hsbc.dao.impl.UserDaoImpl;
import com.hsbc.domain.User;

public class Bean {
    public static UserDao userDao = new UserDaoImpl();
    public static RoleDao roleDao = new RoleDaoImpl();
    public static AuthApi authApi = new AuthApi();
}
