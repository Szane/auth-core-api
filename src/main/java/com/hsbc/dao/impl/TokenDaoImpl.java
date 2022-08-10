package com.hsbc.dao.impl;

import com.hsbc.Start;
import com.hsbc.base.Bean;
import com.hsbc.dao.TokenDao;
import com.hsbc.domain.User;
import com.hsbc.util.AESUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

public class TokenDaoImpl implements TokenDao {
    private long tokenExpire;

    public long getTokenExpire() {
        return tokenExpire;
    }

    public void setTokenExpire(long tokenExpire) {
        this.tokenExpire = tokenExpire;
    }

    {
        Properties props = new Properties();
        InputStream in = Start.class.getResourceAsStream("/config.properties");
        InputStreamReader inputStreamReader = null;
        try {
            inputStreamReader = new InputStreamReader(in, StandardCharsets.UTF_8);
            props.load(inputStreamReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String expireTime = props.getProperty("token_expire", "7200");
        this.tokenExpire = Long.parseLong(expireTime);
    }

    @Override
    public String createToken(String userName, String password) {
        if (StringUtils.isBlank(userName) || StringUtils.isBlank(password)) {
            return null;
        }
        User user = Bean.userDao.selectUser(userName);
        if (user == null) {
            return null;
        }
        String storePwd = AESUtils.AESDecode(user.getPassword());
        if (Objects.equals(password, storePwd)) {
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
