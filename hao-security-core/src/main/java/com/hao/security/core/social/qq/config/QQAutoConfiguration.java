package com.hao.security.core.social.qq.config;

import com.hao.security.core.properties.QQProperties;
import com.hao.security.core.properties.SecurityProperties;
import com.hao.security.core.social.qq.connect.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

@Configuration
@ConditionalOnProperty(prefix = "com.hao.security.social.qq",name = "app-id")
public class QQAutoConfiguration extends SocialAutoConfigurerAdapter {
    private final SecurityProperties securityProperties;

    @Autowired
    public QQAutoConfiguration(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {
        QQProperties qqProperties = securityProperties.getSocial().getQq();
        return new QQConnectionFactory(qqProperties.getProviderId(), qqProperties.getAppId(), qqProperties.getAppSecret());
    }
}
