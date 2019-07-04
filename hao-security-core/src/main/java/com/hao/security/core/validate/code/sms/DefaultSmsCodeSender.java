package com.hao.security.core.validate.code.sms;

/**
 * 默认短信发送逻辑
 * */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        // 这里仅仅写个打印，具体逻辑一般都是调用第三方接口发送短信
        System.out.println("向手机号为：" + mobile + "的用户发送验证码：" + code);
    }
}
