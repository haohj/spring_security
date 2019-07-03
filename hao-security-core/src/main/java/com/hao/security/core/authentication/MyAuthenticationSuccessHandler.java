package com.hao.security.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hao.security.core.properties.LoginType;
import com.hao.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component("myAuthenticationSuccessHandler")
@Slf4j
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private final ObjectMapper objectMapper;
    private final SecurityProperties securityProperties;

    @Autowired
    public MyAuthenticationSuccessHandler(ObjectMapper objectMapper, SecurityProperties securityProperties) {
        this.objectMapper = objectMapper;
        this.securityProperties = securityProperties;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        log.info("登陆成功");
        if(LoginType.JSON.equals(securityProperties.getBrowserProperties().getLoginType())){
            // 如果用户自定义了处理成功后返回JSON（默认方式也是JSON），那么这里就返回JSON
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        }else {
            // 如果用户定义的是跳转，那么就使用父类方法进行跳转
            super.onAuthenticationSuccess(request,response,authentication);
        }

    }
}
