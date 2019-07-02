package com.hao.security.web.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    public Docket createRestfulApiDocs(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.hao.security.web.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("自定义RESTful API文档")
                .description("更多内容请关注CSDN博客：https://blog.csdn.net/Lammonpeter")
                .termsOfServiceUrl("https://blog.csdn.net/Lammonpeter")
                .contact(new Contact("Lemon", "https://blog.csdn.net/Lammonpeter", "lemon_jiang@aliyun.com"))
                .version("1.0.0")
                .build();
    }

}
