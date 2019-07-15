package com.hao.security.core.properties;

import lombok.Data;

@Data
public class SocialProperties {
    /**
     * 这个属性是为了设置自定义社交登录拦截路径的
     */
    private String filterProcessesUrl = "/auth";

    private QQProperties qq = new QQProperties();
}
