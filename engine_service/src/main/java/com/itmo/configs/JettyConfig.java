//package com.itmo.configs;
//
//import org.springframework.boot.web.embedded.jetty.JettyServletWebServerFactory;
//import org.springframework.boot.web.server.ErrorPage;
//import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpStatus;
//
//@Configuration
//public class JettyConfig {
//
//    @Bean
//    public ConfigurableServletWebServerFactory webServerFactory() {
//        JettyServletWebServerFactory factory = new JettyServletWebServerFactory();
//        factory.setPort(9002);
//        factory.setContextPath("/engine_services");
//        factory.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/notfound.html"));
//        return factory;
//    }
//}
