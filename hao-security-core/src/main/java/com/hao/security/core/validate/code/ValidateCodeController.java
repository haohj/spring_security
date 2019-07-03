package com.hao.security.core.validate.code;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ValidateCodeController {

    static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private static final String FORMAT_NAME = "JPEG";
}
