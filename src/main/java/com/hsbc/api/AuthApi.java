package com.hsbc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.base.Result;
import com.hsbc.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("unchecked")
public class AuthApi {
    final static ObjectMapper objectMapper = new ObjectMapper();

    public Result<Boolean> createUser(String json) {
        try {
            Map<String, String> param = objectMapper.readValue(json, Map.class);
            boolean r = User.createUser(param.get("name"), param.get("password"));
            if (r) {
                return successResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    public <T> Result<T> successResult(T data) {

    }

    public Result failedResult() {
        return new Result(-1, "request failed", null);
    }


}
