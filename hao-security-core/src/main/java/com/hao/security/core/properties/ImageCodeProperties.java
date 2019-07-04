package com.hao.security.core.properties;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCodeProperties extends CodeProperties {

    public ImageCodeProperties() {
        setLength(4);
    }

    /**
     * 验证码宽度
     */
    private int width = 67;
    /**
     * 验证码高度
     */
    private int height = 23;

}
