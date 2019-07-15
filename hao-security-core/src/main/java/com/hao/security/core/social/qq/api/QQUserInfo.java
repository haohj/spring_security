package com.hao.security.core.social.qq.api;

import lombok.*;
import org.codehaus.jackson.annotate.JsonProperty;

/**
 * QQ用户信息
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QQUserInfo {
    /**
     * 用户的OpenId
     */
    private String openId;

    /**
     * 返回码
     */
    private Integer ret;

    /**
     * 返回消息，如果ret<0，会有相应的错误信息提示，返回数据全部用UTF-8编码
     */
    private String msg;

    /**
     * 用户在QQ空间的昵称
     */
    private String nickname;

    /**
     * 大小为30×30像素的QQ空间头像URL
     */
    @JsonProperty("figureurl")
    private String figureUrl30;

    /**
     * 大小为50×50像素的QQ空间头像URL
     */
    @JsonProperty("figureurl_1")
    private String figureUrl50;

    /**
     * 大小为100×100像素的QQ空间头像URL
     */
    @JsonProperty("figureurl_2")
    private String figureUrl100;

    /**
     * 大小为40×40像素的QQ头像URL
     */
    @JsonProperty("figureurl_qq_1")
    private String figureUrlQq40;

    /**
     * 大小为100×100像素的QQ头像URL。需要注意，不是所有的用户都拥有QQ的100x100的头像，但40x40像素则是一定会有
     */
    @JsonProperty("figureurl_qq_2")
    private String figureUrlQq100;

    /**
     * 性别。 如果获取不到则默认返回"男"
     */
    private String gender;

    /**
     * 是否是黄钻，0否，1是
     */
    @JsonProperty("is_yellow_vip")
    private String isYellowVip;

    /**
     * 是否是会员，0否，1是
     */
    private String vip;

    /**
     * 黄钻等级
     */
    @JsonProperty("yellow_vip_level")
    private String yellowVipLevel;

    /**
     * 等级
     */
    private String level;

    /**
     * 是否是黄钻年费VIP，0否，1是
     */
    @JsonProperty("is_yellow_year_vip")
    private String isYellowYearVip;
}
