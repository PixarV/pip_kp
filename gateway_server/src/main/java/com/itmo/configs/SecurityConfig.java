package com.itmo.configs;

//
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
////
////    @Autowired
////    FirmService firmService;
////
////    @Bean
////    PasswordEncoder noEncoder(){
////        return NoOpPasswordEncoder.getInstance();
////    }
////
////    @Override
////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth
////                .userDetailsService(firmService)
////                .passwordEncoder(noEncoder());
////    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                    .antMatchers( "/*").permitAll()
////                    .anyRequest().authenticated()
//                .and()
////                .formLogin()
////                    .loginPage("/hello.xhtml")
////                    .usernameParameter("j_idt4:username")
////                    .passwordParameter("j_idt4:password")
////                    .defaultSuccessUrl("/firms/getAll", true)
////                    .permitAll()
////                .and()
////                .logout()
////                    .invalidateHttpSession(true)
////                    .deleteCookies("JSESSIONID")
////                    .permitAll()
////                .and()
//                .csrf().disable();
//    }
//}
