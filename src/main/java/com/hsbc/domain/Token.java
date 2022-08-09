package com.hsbc.domain;

import java.io.Serializable;

public class Token implements Serializable {
    private String secret;

    public boolean invalidate() {
        return false;
    }

}
