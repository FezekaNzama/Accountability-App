package com.fezekanzama.accountable_app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import com.fezekanzama.accountable_app.security.filter.AuthenticationFilter;
import com.fezekanzama.accountable_app.security.filter.ExceptionHandlerFilter;
import com.fezekanzama.accountable_app.security.filter.JWTAuthorizationFilter;
import com.fezekanzama.accountable_app.security.manager.CustomAuthenticationManager;

import lombok.AllArgsConstructor;

@Configuration
@AllArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        AuthenticationFilter authenticationFilter = new AuthenticationFilter(customAuthenticationManager);
        authenticationFilter.setFilterProcessesUrl("/authenticate");
        http
            .headers(headers -> headers
                .frameOptions(FrameOptionsConfig::disable))
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers(antMatcher("/h2/**")).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.POST, SecurityConstants.REGISTER_PATH)).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.POST, SecurityConstants.REGISTER_PATH_GOAL_SETTER)).permitAll()
                    .requestMatchers(antMatcher(HttpMethod.POST, SecurityConstants.REGISTER_PATH_ACCOUNTABILITY_PARTNER)).permitAll()
                    .anyRequest().authenticated())
            .addFilterBefore(new ExceptionHandlerFilter(), AuthenticationFilter.class)
            .addFilter(authenticationFilter)
            .addFilterAfter(new JWTAuthorizationFilter(), AuthenticationFilter.class)
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    
}
