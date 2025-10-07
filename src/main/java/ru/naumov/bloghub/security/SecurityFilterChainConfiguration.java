package ru.naumov.bloghub.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityFilterChainConfiguration {
//    private static final String AUTH_URL = "/auth";
//    private final AuthenticationProvider authenticationProvider;
//    private final JWTUtil jwtUtil;
//    private final UserDetailsService userDetailsService;

//    @Bean
//    public SecurityFilterChain securityFilterChainBasic(HttpSecurity http) throws Exception {
//        http
//                .csrf(CsrfConfigurer::disable)
//                .httpBasic(Customizer.withDefaults())
//                .authorizeHttpRequests(authorize -> authorize
//                        .anyRequest().authenticated());
//
//        return http.build();
//    }

//    public SecurityFilterChain securityFilterChainJWT(HttpSecurity http) throws Exception {
//        http
//                .csrf(CsrfConfigurer::disable)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers(HttpMethod.POST, AUTH_URL).permitAll()
//                        .anyRequest().authenticated())
//                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(
//                        new JWTRequestFilter(jwtUtil, userDetailsService),
//                        UsernamePasswordAuthenticationFilter.class
//                );
//
//        return http.build();
//    }
}
