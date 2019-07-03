package com.hao.security.core.properties;

import lombok.Data;

@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
}
