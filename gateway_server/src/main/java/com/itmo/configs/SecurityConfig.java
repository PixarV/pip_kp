package com.itmo.configs;


import com.itmo.services.FirmService;
import com.itmo.services.HumanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Order(1)
    @Configuration
    public static class App1ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        public App1ConfigurationAdapter() {
            super();
        }

        @Autowired
        HumanService humanService;

        @Bean
        PasswordEncoder noEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(humanService)
                    .passwordEncoder(noEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/human*/**")
                    .authorizeRequests()
                    .antMatchers("/human_service/hello_human.xhtml", "/human_service/human_reg.xhtml").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/human_service/hello_human.xhtml")
                    .usernameParameter("j_idt5:username")
                    .passwordParameter("j_idt5:password")
                    .defaultSuccessUrl("/human_service/human_profile.xhtml", true)
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/human_service/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }

    @Order(2)
    @Configuration
    public static class App2ConfigurationAdapter extends WebSecurityConfigurerAdapter {
        public App2ConfigurationAdapter() {
            super();
        }

        @Autowired
        FirmService firmService;

        @Bean
        PasswordEncoder noEncoder() {
            return NoOpPasswordEncoder.getInstance();
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth
                    .userDetailsService(firmService)
                    .passwordEncoder(noEncoder());
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .antMatcher("/firm*/**")
                    .authorizeRequests()
                    .antMatchers("/firm_service/hello_firm.xhtml", "/firm_service/firm_reg.xhtml").permitAll()
                    .anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginPage("/firm_service/hello_firm.xhtml")
                    .usernameParameter("j_idt5:username")
                    .passwordParameter("j_idt5:password")
                    .defaultSuccessUrl("/firm_service/firm_profile.xhtml", true)
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/firm_service/logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/images/**", "/css/**", "/javax.faces.resource/**");
    }
}
