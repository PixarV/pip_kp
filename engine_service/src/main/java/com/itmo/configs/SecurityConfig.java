package com.itmo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public void configureGlobal1(
            AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic()
                .disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/engines").permitAll()
                .antMatchers(HttpMethod.GET, "/engines/*").permitAll()
                .antMatchers(HttpMethod.POST, "/engines").hasRole("ADMIN")
                .antMatchers(HttpMethod.PATCH, "/engines/*").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/engines/*").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable();
    }
}

