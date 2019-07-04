package com.hao.security.core.validate.code.sms;

import com.hao.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<SmsCode> {
    private static final String SMS_CODE_PARAM_NAME = "mobile";

    private final SmsCodeSender smsCodeSender;

    @Autowired
    public SmsCodeProcessor(SmsCodeSender smsCodeSender) {
        this.smsCodeSender = smsCodeSender;
    }

    @Override
    protected void send(ServletWebRequest request, SmsCode validateCode) throws Exception {

    }
}
