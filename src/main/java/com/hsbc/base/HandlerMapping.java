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
        url = url.toLowerCase();
        if ("/api/auth/user/create".equals(url)) {
            return Bean.authApi.createUser(json);
        } else if ("/api/auth/user/delete".equals(url)) {
            return null;
        }
        return null;
    }
}
