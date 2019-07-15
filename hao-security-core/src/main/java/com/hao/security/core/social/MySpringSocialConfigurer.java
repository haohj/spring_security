package com.hao.security.core.social;

import lombok.AllArgsConstructor;
import org.springframework.social.security.SocialAuthenticationFilter;
import org.springframework.social.security.SpringSocialConfigurer;

/**
 * 配置社交登录的拦截路径
 *
 */
@AllArgsConstructor
public class MySpringSocialConfigurer extends SpringSocialConfigurer {
    private String filterProcessesUrl;

    @Override
    @SuppressWarnings("unchecked")
    protected <T> T postProcess(T object) {
        // 获取父类的处理结果
        SocialAuthenticationFilter filter = (SocialAuthenticationFilter) super.postProcess(object);
        filter.setFilterProcessesUrl(filterProcessesUrl);
        return (T) filter;
    }
}
