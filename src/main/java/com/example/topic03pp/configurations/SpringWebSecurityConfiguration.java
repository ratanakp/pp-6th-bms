package com.example.topic03pp.configurations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SpringWebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    private AuthenticationSuccessHandler successHandler;


    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password("{noop}admin")
                .roles("USER", "DBA", "ADMIN")
                .and()
                .withUser("dba").password("{noop}dba").roles("DBA", "USER");

        auth.inMemoryAuthentication().withUser("user").password("{noop}user")
                .roles("USER");
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers("/api/v1/book/**");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/**").authenticated();

        http.csrf().disable();

        http.formLogin()
                .loginProcessingUrl("/login-pp")
                .successHandler(successHandler)
                .usernameParameter("username-pp")
                .passwordParameter("password-pp")
                .loginPage("/login");


        //Change default logout url
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));


        http.authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN");
        http.authorizeRequests().antMatchers("/dba/**").hasAnyRole("DBA", "ADMIN");
        http.authorizeRequests().antMatchers("/user/**").hasAnyRole("USER", "DBA", "ADMIN");


        http.exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint);



        http.sessionManagement().invalidSessionUrl("/book");
    }
}
