package com.user.progress.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket countryApi() {
        return new Docket(SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.user.progress.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo());
    }

    private Contact contact() {
        Contact contact = new Contact(
                "User Progress ",
                "http://user.com/",
                "info@user.com"
        );
        return contact;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("User Progress Api")
                .description("User Progress Api")
                .version("1.0")
                .license("User Progress Service")
                .licenseUrl("http://info.user.com/")
                .contact(contact())
                .build();
    }

}
