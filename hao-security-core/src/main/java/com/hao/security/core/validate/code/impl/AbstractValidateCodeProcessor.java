package com.hao.security.core.validate.code.impl;

import com.hao.security.core.validate.code.ValidateCodeGenerator;
import com.hao.security.core.validate.code.ValidateCodeProcessor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Map;

@Component
public abstract class AbstractValidateCodeProcessor<C> implements ValidateCodeProcessor {
    private static final String SEPARATOR = "/code/";

    /**
     * 操作session的工具集
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    /**
     * 这是Spring的一个特性，就是在项目启动的时候会自动收集系统中 {@link ValidateCodeGenerator} 接口的实现类对象
     */
    @Autowired
    private Map<String, ValidateCodeGenerator> validateCodeGeneratorMap;

    @Override
    public void create(ServletWebRequest request) throws Exception {
        C validateCode = generate(request);
        save(request, validateCode);
        send(request, validateCode);
    }

    /**
     * 发送验证码
     *
     * @param request      ServletWebRequest实例对象
     * @param validateCode 验证码
     * @throws Exception 异常
     */
    protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;

    /**
     * 保存验证码到session中
     *
     * @param request      ServletWebRequest实例对象
     * @param validateCode 验证码
     */
    private void save(ServletWebRequest request, C validateCode) {
        sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX.concat(getProcessorType(request).toUpperCase()), validateCode);
    }

    /**
     * 生成验证码
     *
     * @param request ServletWebRequest实例对象
     * @return 验证码实例对象
     */
    private C generate(ServletWebRequest request) {
        String type = getProcessorType(request);
        ValidateCodeGenerator validateCodeGenerator = validateCodeGeneratorMap.get(type.concat(ValidateCodeGenerator.CODE_GENERATOR));
        return (C) validateCodeGenerator.generate(request);
    }

    /**
     * 获取请求URL中具体请求的验证码类型
     *
     * @param request ServletWebRequest实例对象
     * @return 验证码类型
     */
    private String getProcessorType(ServletWebRequest request) {
        // 获取URI分割后的第二个片段
        return StringUtils.substringAfter(request.getRequest().getRequestURI(), SEPARATOR);
    }
}
