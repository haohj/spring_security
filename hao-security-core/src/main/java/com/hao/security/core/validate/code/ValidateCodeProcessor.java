package com.hao.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成接口
 */
public interface ValidateCodeProcessor {
    String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";
    String CODE_PROCESSOR = "CodeProcessor";

    /**
     * 生成验证码
     *
     * @param request 封装了 {@link ServletWebRequest} 实例对象的请求
     * @throws Exception 异常
     */
    void create(ServletWebRequest request) throws Exception;
}
