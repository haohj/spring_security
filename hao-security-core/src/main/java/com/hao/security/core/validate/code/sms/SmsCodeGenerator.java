package com.hao.security.core.validate.code.sms;

import com.hao.security.core.properties.SecurityProperties;
import com.hao.security.core.validate.code.ValidateCode;
import com.hao.security.core.validate.code.ValidateCodeGenerator;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Data
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {
    private final SecurityProperties securityProperties;

    @Autowired
    public SmsCodeGenerator(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new SmsCode(code,securityProperties.getCode().getSms().getExpireIn());
    }
}
