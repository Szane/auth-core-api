package com.hsbc.dao;

import com.hsbc.domain.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public interface TokenDao {
    Map<String, String> TOKEN_MAP = new HashMap<>();

    String createToken(String userName, String password);

    boolean deleteToken(String token);
}
