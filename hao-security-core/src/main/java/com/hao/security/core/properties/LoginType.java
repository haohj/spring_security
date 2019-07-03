package com.hao.security.core.properties;

import lombok.Getter;

@Getter
public enum LoginType {
    /**
     * 跳转
     */
    REDIRECT,

    /**
     * 返回JSON
     */
    JSON
}
