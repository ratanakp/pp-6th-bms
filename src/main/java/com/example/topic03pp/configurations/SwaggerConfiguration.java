package com.example.topic03pp.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
//                .apis(RequestHandlerSelectors.any())
                .apis(RequestHandlerSelectors.basePackage("com.example.topic03pp.controllers.restcontrollers")) // specific package
                .paths(PathSelectors.any())
//                .paths(PathSelectors.ant("/api/v1/book/**")) // specific path
                .build()
                .apiInfo(apiInfo());

    }

    private ApiInfo apiInfo() {
        List<VendorExtension> vendorExtensions = new ArrayList<>();
//        vendorExtensions.add("")
        Contact contact = new Contact("Som Nak", "www.somank.com", "somnak@gmail.com");
        ApiInfo apiInformation = new ApiInfo(
                "PP-6th-API-BMS",
                "PP Class mazer laor - Api Documentation",
                "Version 1.0",
                "Term of Service",
                contact,
                "License: Copy Righted",
                "http://www.facebook.com",
                vendorExtensions
        );
        return apiInformation;
    }



}
