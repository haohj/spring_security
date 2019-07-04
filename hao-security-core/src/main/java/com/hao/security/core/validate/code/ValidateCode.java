package com.hao.security.core.validate.code;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ValidateCode {
    private String code;
    private LocalDateTime expireTime;

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
