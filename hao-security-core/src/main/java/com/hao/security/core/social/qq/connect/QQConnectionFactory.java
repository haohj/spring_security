package com.hao.security.core.social.qq.connect;

import com.hao.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {
    /**
     * QQ Connection Factory的构造方法
     *
     * @param providerId 第三方服务提供商的ID，如facebook
     * @param appId      第三方服务提供商给予的应用ID
     * @param appSecret  第三方服务提供商给予的应用Secret
     */
    public QQConnectionFactory(String providerId, String appId, String appSecret) {
        super(providerId, new QQServiceProvider(appId, appSecret), new QQAdapter());
    }
}
