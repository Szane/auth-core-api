package com.hsbc.base;

import com.hsbc.api.AuthApi;
import com.hsbc.dao.RoleDao;
import com.hsbc.dao.TokenDao;
import com.hsbc.dao.UserDao;
import com.hsbc.dao.impl.RoleDaoImpl;
import com.hsbc.dao.impl.TokenDaoImpl;
import com.hsbc.dao.impl.UserDaoImpl;

public class Bean {
    public static UserDao userDao = new UserDaoImpl();
    public static RoleDao roleDao = new RoleDaoImpl();
    public static TokenDao tokenDao = new TokenDaoImpl();
    public static AuthApi authApi = new AuthApi();
}
