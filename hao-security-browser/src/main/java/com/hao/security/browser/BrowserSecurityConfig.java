package com.hao.security.browser;

import com.hao.security.core.SecurityCoreConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * 浏览器安全验证的配置类
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    private final SecurityCoreConfig securityCoreConfig;
    private final AuthenticationSuccessHandler authenticationSuccessHandler;
    private final AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    public BrowserSecurityConfig(SecurityCoreConfig securityCoreConfig, AuthenticationSuccessHandler authenticationSuccessHandler, AuthenticationFailureHandler authenticationFailureHandler) {
        this.securityCoreConfig = securityCoreConfig;
        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
    }

    /**
     * 配置了这个Bean以后，从前端传递过来的密码将被加密
     *
     * @return PasswordEncoder实现类对象
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
