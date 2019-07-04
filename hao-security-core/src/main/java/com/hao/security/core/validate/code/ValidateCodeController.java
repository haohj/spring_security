package com.hao.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class ValidateCodeController {
    private final Map<String, ValidateCodeProcessor> validateCodeProcessorMap;

    @Autowired
    public ValidateCodeController(Map<String, ValidateCodeProcessor> validateCodeProcessorMap) {
        this.validateCodeProcessorMap = validateCodeProcessorMap;
    }

    @GetMapping("/code/{type}")
    public void createCode(HttpServletRequest request, HttpServletResponse response, @PathVariable String type) throws Exception {
        validateCodeProcessorMap.get(type.concat(ValidateCodeProcessor.CODE_PROCESSOR)).create(new ServletWebRequest(request, response));
    }
}
