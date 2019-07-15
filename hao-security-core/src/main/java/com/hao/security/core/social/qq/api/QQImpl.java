package com.hao.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * 获取QQ用户信息的实现类
 *
 */
@Slf4j
public class QQImpl extends AbstractOAuth2ApiBinding implements QQ {
    /**
     * Open ID的获取链接，它需要传递令牌，也就是OAuth协议的前五步获取到的数据访问令牌
     */
    private static final String URL_GET_OPEN_ID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    /**
     * 获取用户信息的链接：https://graph.qq.com/user/get_user_info?access_token=YOUR_ACCESS_TOKEN&oauth_consumer_key=YOUR_APP_ID&openid=YOUR_OPENID
     * 其中，access_token会被父类AbstractOAuth2ApiBinding处理，在请求之前，会被拼接到请求链接中，故这里删除即可
     */
    private static final String URL_GET_USER_INFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    /**
     * appId是腾讯要求的应用ID，需要开发者去QQ互联上申请，对应的参数字段是oauth_consumer_key
     */
    private String appId;

    /**
     * openId是腾讯对应用和用户之间的关系管理的一个参数，用户在一个应用的openID唯一
     */
    private String openId;

    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken, String appId) {
        // 这里的父类构造方法传入两个参数，第二个参数的意思是在构造方法中构建restTemplate的时候，将accessToken作为请求参数集成到请求链接中
        // 父类的默认构造也就是一个参数的构造，默认行为是将参数放到了请求头中，这个就和QQ的API接口要求的传参方式不一样了
        super(accessToken, TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId = appId;
        // 获取openId
        String url = String.format(URL_GET_OPEN_ID, accessToken);
        String result = getRestTemplate().getForObject(url, String.class);
        // 返回的数据结构体为：callback( {"client_id":"YOUR_APPID","openid":"YOUR_OPENID"} );
        this.openId = StringUtils.substringBetween(result, "\"openid\":", "}");
    }
    @Override
    public QQUserInfo getUserInfo() {
        String url = String.format(URL_GET_USER_INFO, appId, openId);
        String result = getRestTemplate().getForObject(url, String.class);
        try {
            QQUserInfo userInfo = objectMapper.readValue(result, QQUserInfo.class);
            // 这里需要将openId存储到userInfo中
            userInfo.setOpenId(openId);
            return userInfo;
        } catch (IOException e) {
            e.printStackTrace();
            log.error("转换QQ用户信息失败：{}", e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
