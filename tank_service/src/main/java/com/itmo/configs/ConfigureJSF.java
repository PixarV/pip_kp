package com.itmo.configs;

import com.sun.faces.config.FacesInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.HashSet;
import java.util.Set;

@Configuration
public class ConfigureJSF {

//    @Bean
//    public ServletRegistrationBean facesServletRegistration() {
//
//        ServletRegistrationBean servletRegistrationBean = new JsfServletRegistrationBean();
//
//        return servletRegistrationBean;
//    }

    @Bean
    public ServletRegistrationBean axisServletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new FacesServlet());
        registration.addUrlMappings("*.jsf");
        return registration;
    }

//    @Bean
//    public ServletRegistrationBean adminServletRegistrationBean() {
//        return new ServletRegistrationBean(new AdminServlet(), "/servlet/AdminServlet");
//    }
//    public class JsfServletRegistrationBean extends ServletRegistrationBean {
//
//
//        public JsfServletRegistrationBean() {
//            super();
//        }
//
//        @Override
//        public void onStartup(ServletContext servletContext)
//                throws ServletException {
//
//            FacesInitializer facesInitializer = new FacesInitializer();
//
//            Set<Class<?>> clazz = new HashSet<Class<?>>();
//            clazz.add(ConfigureJSF.class);
//            facesInitializer.onStartup(clazz, servletContext);
//        }
//    }
}
