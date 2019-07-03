package com.hao.security.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.hao.security")
public class SecurityProperties {
    private BrowserProperties browserProperties = new BrowserProperties();
}
