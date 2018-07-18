package com.example.topic03pp.configurations;

import io.swagger.models.HttpMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(1)
@EnableWebSecurity
public class ApiSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("API_USERNAME1")
                .password(passwordEncoder.encode("API_PASSWORD1"))
                .roles("API");
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();

        http.formLogin()
                .loginProcessingUrl("/login-pp")
//                .successHandler(successHandler)
                .usernameParameter("username-pp")
                .passwordParameter("password-pp")
                .loginPage("/login");

        http.antMatcher("/api/**")
                .authorizeRequests()
                .anyRequest()
//                .requestMatchers()
                .hasAnyRole("API");

        http.httpBasic();
    }



}
