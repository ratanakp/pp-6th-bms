package com.example.topic03pp.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {

        System.out.println("Login Required!!!");


//        System.out.println(httpServletRequest.getRequestURL().toString());
        System.out.println(httpServletRequest.getRequestURI());



//        httpServletRequest.getSession().setAttribute("REDIRECT_URI", httpServletRequest.getRequestURI());

        httpServletResponse.sendRedirect("/login");




    }
}
