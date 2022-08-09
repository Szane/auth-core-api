package com.hsbc.base;

import org.apache.commons.lang3.StringUtils;

/**
 * mapping URL and API,return result of APIs
 */
public class HandlerMapping {
    public static Result getApiResult(String url, String json) {
        if (StringUtils.isBlank(url)) {
            return null;
        }
        switch (url) {
            case "/api/auth/createUser":
                return Bean.authApi.createUser(json);
            case "/api/auth/deleteUser":
                return Bean.authApi.deleteUser(json);
            case "/api/auth/createRole":
                return Bean.authApi.createRole(json);
            case "/api/auth/deleteRole":
                return Bean.authApi.deleteRole(json);
            case "/api/auth/addRoleToUser":
                return Bean.authApi.addRoleToUser(json);
            case "/api/auth/authenticate":
                return Bean.authApi.authenticate(json);
            case "/api/auth/invalidate":
                return Bean.authApi.invalidate(json);
            case "/api/auth/checkRole":
                return Bean.authApi.checkRole(json);
            case "/api/auth/allRoles":
                return Bean.authApi.allRoles(json);
        }
        return null;
    }
}
