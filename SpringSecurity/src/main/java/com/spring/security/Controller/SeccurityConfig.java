package com.spring.security.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SeccurityConfig {

    @Autowired
    DataSource dataSource;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests.anyRequest().authenticated());
        //http.formLogin(withDefaults()); //For UI
        http.httpBasic(withDefaults()); //without UI.. so that we can use authC & authR in tools like PostMan
        return http.build();
    }

    //Manager users - we can manage more than 1 users to access the application.
    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails user1 = User.withUsername("user1")
                .password("{noop}pass1")
                .roles("user")
                .build();

        UserDetails admin = User.withUsername("admin")
                .password("{noop}AdminPass")
                .roles("ADMIN")
                .build();

        // JdbcUserDetailsManager: this is use to create users in database
        JdbcUserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
                userDetailsManager.createUser(user1);
                userDetailsManager.createUser(admin);
                return userDetailsManager;

        // InMemoryUserDetailsManager: this is use to create users in memory not database
//        return new InMemoryUserDetailsManager(user1, admin);
    }
}
