package com.hsbc.dao;

public interface TokenDao {

    String createToken(String userName, String password);

    boolean deleteToken(String token);
}
