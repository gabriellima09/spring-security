package com.example.security.seguranca.Config;

import com.example.security.seguranca.Models.MeuUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class Security extends WebSecurityConfigurerAdapter {
    
    @Autowired
    private MeuUserDetailsService detailService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        
        auth
            .userDetailsService(detailService)
                .passwordEncoder(new BCryptPasswordEncoder());
            // .inMemoryAuthentication()
            //     .withUser("user")
            //     .password("{noop}123")
            //     .authorities("SUPORTE")
            // .and()
            //     .withUser("admin")
            //     .password("{noop}123")
            //     .authorities("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        
        http
            .antMatcher("/**")
                .authorizeRequests()
                    .antMatchers("/exemplo")
                        .permitAll()
                    //.antMatchers("/admin/**")
                    //   .hasAnyAuthority("ADMIN")
                    .anyRequest()
                        .authenticated()
                .and()
                    .formLogin();
    }
}