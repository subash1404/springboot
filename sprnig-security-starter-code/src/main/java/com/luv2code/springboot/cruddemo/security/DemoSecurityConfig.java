package com.luv2code.springboot.cruddemo.security;


import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {

    // @Bean
    // public InMemoryUserDetailsManager userDetailsManager() {
    //     UserDetails john = User.builder()
    //                         .username("john")
    //                         .password("{noop}john")
    //                         .roles("EMPLOYEE")
    //                         .build();
    //     UserDetails mary = User.builder()
    //                         .username("mary")
    //                         .password("{noop}mary")
    //                         .roles("EMPLOYEE","MANAGER")
    //                         .build();
    //     UserDetails susan = User.builder()
    //                         .username("susan")
    //                         .password("{noop}susan")
    //                         .roles("EMPLOYEE","MANAGER","ADMIN")
    //                         .build();
    //     return new InMemoryUserDetailsManager(john, mary, susan);
    // }

    @Bean
    // Spring will manage the Datasource param
    public UserDetailsManager detailsManager(DataSource dataSource){
        JdbcUserDetailsManager theUserDetailsManager = new JdbcUserDetailsManager(dataSource);
        theUserDetailsManager.setUsersByUsernameQuery("select user_id,pw,active from members where user_id=?");
        theUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id,role from roles where user_id=?");
        return theUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
            configurer
                .requestMatchers(HttpMethod.GET,"api/employees").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.GET,"api/employees/**").hasRole("EMPLOYEE")
                .requestMatchers(HttpMethod.POST,"api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.PUT,"api/employees").hasRole("MANAGER")
                .requestMatchers(HttpMethod.DELETE,"api/employees/*").hasAnyRole("ADMIN")
        );

        // We are kinda overriding the basic authentication provided by the HTTP
        // Here we are telling http to use the basic authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Rquest Forgery(CSRF)
        // In general it is not required for stateless REST APIs that use GET,POST,PUT,DELETE methods
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

}
