package com.hsbc.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hsbc.base.Result;
import com.hsbc.domain.Role;
import com.hsbc.domain.User;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

@SuppressWarnings("unchecked")
public class AuthApi {
    final static ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Create User API
     */
    public Result<Boolean> createUser(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            boolean r = User.createUser(param.get("name"), param.get("password"));
            if (r) {
                return successResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    /**
     * Delete User API
     */
    public Result<Boolean> deleteUser(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            boolean r = User.deleteUser(param.get("name"));
            if (r) {
                return successResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    /**
     * Create Role API
     */
    public Result<Boolean> createRole(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            boolean r = Role.createRole(param.get("name"));
            if (r) {
                return successResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    /**
     * Delete Role API
     */
    public Result<Boolean> deleteRole(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            boolean r = Role.deleteRole(param.get("name"));
            if (r) {
                return successResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    /**
     * Add a Role to a User API
     */
    public Result<Boolean> addRoleToUser(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            boolean r = Role.addRoleToUser(param.get("userName"), param.get("roleName"));
            if (r) {
                return successResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    /**
     * authenticate API
     */
    public Result<String> authenticate(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            String token = User.authenticate(param.get("name"), param.get("password"));
            if (StringUtils.isNotBlank(token)) {
                return successResult(token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    /**
     * invalidate API
     */
    public Result<Boolean> invalidate(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            boolean r = User.invalidate(param.get("token"));
            if (r) {
                return successResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    /**
     * check role API
     */
    public Result<Boolean> checkRole(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            boolean r = Role.checkRole(param.get("token"), param.get("roleName"));
            if (r) {
                return successResult(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    /**
     * get a token's all roles API
     */
    public Result<Set<Role>> allRoles(String paramJson) {
        try {
            Map<String, String> param = objectMapper.readValue(paramJson, Map.class);
            Set<Role> r = Role.allRoles(param.get("token"));
            if (null != r) {
                return successResult(r);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return failedResult();
    }

    public <T> Result<T> successResult(T data) {
        return new Result<>(0, "success", data);
    }

    public <T> Result<T> failedResult() {
        return new Result<>(-1, "request failed", null);
    }


}
