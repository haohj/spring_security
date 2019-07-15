package com.hao.security.core.social.qq.connect;

import com.hao.security.core.social.qq.api.QQ;
import com.hao.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

public class QQAdapter implements ApiAdapter<QQ> {
    /**
     * 这个方法用来判断QQ服务是否可用
     *
     * @param api API接口
     * @return 是否可用
     */
    @Override
    public boolean test(QQ api) {
        return true;
    }

    /**
     * 将API中获取到的用户信息转换成创建Connection所需的值
     *
     * @param api    用户信息获取API
     * @param values 创建Connection所需的值
     */
    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
        QQUserInfo userInfo = api.getUserInfo();
        values.setDisplayName(userInfo.getNickname());
        values.setImageUrl(userInfo.getFigureUrlQq40());
        // QQ用户信息接口没有主页这个值
        values.setProfileUrl(null);
        values.setProviderUserId(userInfo.getOpenId());
    }

    @Override
    public UserProfile fetchUserProfile(QQ api) {
        return null;
    }

    @Override
    public void updateStatus(QQ api, String message) {

    }
}
