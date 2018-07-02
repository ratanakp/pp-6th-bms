package com.example.topic03pp.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {


        HttpSession httpSession = httpServletRequest.getSession();

        httpSession.setMaxInactiveInterval(5);


        System.out.println("success");

        System.out.println("====================getAuthorities======================");
        System.out.println(authentication.getAuthorities());
        System.out.println("====================getName======================");
        System.out.println(authentication.getName());
        System.out.println("=====================getCredentials=====================");
        System.out.println(authentication.getCredentials());
        System.out.println("=====================getPrincipal=====================");
        System.out.println(authentication.getPrincipal());
        System.out.println("=====================getDetails=====================");
        System.out.println(authentication.getDetails());
        System.out.println("==========================================");


//        String redirectURI = (String) httpServletRequest.getSession().getAttribute("REDIRECT_URI");


        String redirectURI = "";
//        System.out.println(httpServletRequest.getRequestURI());


        for (GrantedAuthority grantedAuthority:
                authentication.getAuthorities()) {

            System.out.println(grantedAuthority.getAuthority());

            if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
                redirectURI = "/admin";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_DBA")) {
                redirectURI = "/dba";
                break;
            } else if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
                redirectURI = "/user";
                break;
            }

        }

//        System.out.println(redirectURI);

        httpServletResponse.sendRedirect(redirectURI);


    }
}
