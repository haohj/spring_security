package com.hao.security.core.validate.code.image;

import com.hao.security.core.validate.code.ValidateCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class ImageCode extends ValidateCode {
    private BufferedImage image;

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code, expireTime);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code, LocalDateTime.now().plusSeconds(expireIn));
        this.image = image;
    }
}
