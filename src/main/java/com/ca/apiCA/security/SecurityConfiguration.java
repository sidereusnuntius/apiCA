package com.ca.apiCA.security;

import com.ca.apiCA.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import java.security.NoSuchAlgorithmException;

@Configuration
public class SecurityConfiguration {
    protected final String[] swaggerUrlPatterns = {"/v3/**", "/swagger-ui/**"};
    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.
                authorizeHttpRequests(requests -> requests
                .requestMatchers(HttpMethod.OPTIONS).permitAll()
                .requestMatchers(swaggerUrlPatterns).permitAll()
                .requestMatchers("/user/new").permitAll()
                .anyRequest().authenticated())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilter(new AuthenticationFilter(authenticationConfiguration.getAuthenticationManager()))
                .oauth2ResourceServer(configure -> configure.jwt(Customizer.withDefaults()))
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new LoginService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtDecoder jwtDecoder() throws NoSuchAlgorithmException {
        KeysHolder.generateKeys();
        SignatureAlgorithm algorithm = SignatureAlgorithm.RS512;
        return NimbusJwtDecoder.withPublicKey(KeysHolder.publicKey).signatureAlgorithm(algorithm).build();
    }
}
