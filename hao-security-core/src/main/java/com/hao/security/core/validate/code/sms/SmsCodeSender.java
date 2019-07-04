package com.hao.security.core.validate.code.sms;

/**
 * 短信发送接口
 * */
public interface SmsCodeSender {
    /**
     * 短信验证码发送接口
     *
     * @param mobile 手机号
     * @param code   验证码
     */
    void send(String mobile, String code);
}
