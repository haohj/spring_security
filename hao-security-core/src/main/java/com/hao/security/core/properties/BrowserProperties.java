package com.hao.security.core.properties;

import lombok.Data;

@Data
public class BrowserProperties {
    private String loginPage = "/login.html";

    private LoginType loginType = LoginType.JSON;
}
