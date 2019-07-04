package com.hao.security.core.validate.code;

import com.hao.security.core.validate.code.image.ImageCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface ValidateCodeGenerator {
    String CODE_GENERATOR = "CodeGenerator";

    /**
     * 生成图片验证码
     *
     * @param request 请求
     * @return ImageCode实例对象
     */
    ValidateCode generate(ServletWebRequest request);
}
