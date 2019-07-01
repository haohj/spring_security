package com.hao.security;

import com.battcn.swagger.annotation.EnableSwagger2Doc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
@SpringBootApplication
@RestController
@EnableSwagger2Doc
public class DemoController {
    public static void main(String[] args) {
        SpringApplication.run(DemoController.class, args);
    }

    @GetMapping("hello")
    public String hello() {
        return "hello spring security";
    }
}
