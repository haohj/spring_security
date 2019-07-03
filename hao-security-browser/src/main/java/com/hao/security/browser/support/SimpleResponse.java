package com.hao.security.browser.support;

import lombok.Data;

@Data
public class SimpleResponse {
    private Object content;

    public SimpleResponse(String content) {
        this.content = content;
    }
}
