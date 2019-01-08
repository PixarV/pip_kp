package com.itmo.configs;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;

@Configuration
public class ConfigureJSF {

    @Bean
    public ServletRegistrationBean axisServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet());
        registration.addUrlMappings("*.jsf", "*.xhtml");
        return registration;
    }
}
