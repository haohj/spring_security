package com.hao.security.core.properties;

import lombok.Data;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class BrowserProperties {
    private String loginPage = "login.html";
    private LoginType loginType = LoginType.JSON;
}
