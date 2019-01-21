package com.itmo.configs;

import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
class ConfigureJSFContextParameters implements ServletContextInitializer {

    @Override
    public void onStartup(ServletContext servletContext)
            throws ServletException {

        servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX",
                ".xhtml");
        servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD",
                "client");
        servletContext.setInitParameter(
                "javax.faces.PARTIAL_STATE_SAVING_METHOD", "true");
        servletContext.setInitParameter("javax.faces.PROJECT_STAGE",
                "Development");
        servletContext.setInitParameter("facelets.DEVELOPMENT", "true");
        servletContext.setInitParameter(
                "javax.faces.FACELETS_REFRESH_PERIOD", "1");
        servletContext.setInitParameter(
                "com.sun.faces.forceLoadConfiguration", "true"
        );
        servletContext.setInitParameter(
                "com.sun.faces.injectionProvider",
                "com.sun.faces.vendor.WebContainerInjectionProvider"
        );
    }
}
