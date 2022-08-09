package com.hsbc.dao.impl;

import com.hsbc.base.Bean;
import com.hsbc.dao.TokenDao;
import com.hsbc.domain.User;
import com.hsbc.util.AESUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class TokenDaoImpl implements TokenDao {

    @Override
    public String createToken(String userName, String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return null;
        }
        String inputPwd = AESUtils.AESDecode(password);
        User user = Bean.userDao.selectUser(userName);
        String storePwd = AESUtils.AESDecode(user.getPassword());
        if (Objects.equals(inputPwd, storePwd)) {
            String token = AESUtils.AESEncode(userName + storePwd + System.currentTimeMillis());
            TOKEN_MAP.put(token, userName);
            return token;
        }
        return null;
    }

    @Override
    public boolean deleteToken(String token) {
        return null != TOKEN_MAP.remove(token);
    }
}
