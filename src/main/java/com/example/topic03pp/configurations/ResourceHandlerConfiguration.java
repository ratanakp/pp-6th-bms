package com.example.topic03pp.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:/bms.properties")
public class ResourceHandlerConfiguration implements WebMvcConfigurer {

    @Value("${file.server.path}")
    private String SERVER_PATH;

    @Value("${file.client.path}")
    private String CLIENT_PATH;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");


        registry.addResourceHandler(CLIENT_PATH + "**").addResourceLocations("file:" + SERVER_PATH);

        registry.addResourceHandler("/swagger-ui/**")
                .addResourceLocations("classpath:/static/swagger/");

    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/swagger").setViewName("/swagger/index");


        registry.addViewController("/login").setViewName("login-page");
    }
}
