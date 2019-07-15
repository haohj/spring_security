package com.hao.security.core.social.qq.connect;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Template;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Slf4j
public class QQOAuth2Template extends OAuth2Template {
    public QQOAuth2Template(String clientId, String clientSecret, String authorizeUrl, String accessTokenUrl) {
        super(clientId, clientSecret, authorizeUrl, accessTokenUrl);
        // 因为OAuth2Template的exchangeCredentialsForAccess方法，在封装OAuth协议的时候，默认不会带上client_id和client_secret
        // 也就是说默认的useParametersForClientAuthentication值为false，所以这里需要改成true
        setUseParametersForClientAuthentication(true);
    }

    @Override
    protected RestTemplate createRestTemplate() {
        RestTemplate restTemplate = super.createRestTemplate();
        // 添加一个StringHttpMessageConverter，他能处理text/html类型的数据
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        return restTemplate;
    }

    @Override
    protected AccessGrant postForAccessGrant(String accessTokenUrl, MultiValueMap<String, String> parameters) {
        String responseString = getRestTemplate().postForObject(accessTokenUrl, parameters, String.class);
        log.info("获取access token的响应为：{}", responseString);
        // QQ服务器返回的数据类型为access_token=FE04******CCE2&expires_in=7776000&refresh_token=88E4******BE14
        String[] items = StringUtils.splitByWholeSeparatorPreserveAllTokens(responseString, "&");
        // 分割数据
        String accessToken = StringUtils.substringAfterLast(items[0], "=");
        Long expiresIn = new Long(StringUtils.substringAfterLast(items[1], "="));
        String refreshToken = StringUtils.substringAfterLast(items[2], "=");
        // 封装AccessGrant对象
        return new AccessGrant(accessToken, null, refreshToken, expiresIn);
    }
}
