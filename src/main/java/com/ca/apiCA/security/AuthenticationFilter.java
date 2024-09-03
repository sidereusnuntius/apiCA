package com.ca.apiCA.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.ca.apiCA.dto.Credentials;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
    Algorithm algorithm;
    private AuthenticationManager authenticationManager;

    public AuthenticationFilter(AuthenticationManager authenticationManager) throws NoSuchAlgorithmException {
        this.authenticationManager = authenticationManager;
        KeysHolder.generateKeys();
        this.privateKey = KeysHolder.privateKey;
        this.publicKey = KeysHolder.publicKey;

        this.algorithm = Algorithm.RSA512(publicKey, privateKey);
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper mapper = new ObjectMapper();

        try {
            Credentials user = mapper.readValue(request.getInputStream(), Credentials.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.username(),
                    user.password(), new ArrayList<>());
            return authenticationManager.authenticate(token);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        User user = ((User) authResult.getPrincipal());


        String role = user.getAuthorities().stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));
        JWTCreator.Builder builder = JWT.create().withIssuer("CA")
                .withSubject(user.getUsername())
                .withClaim("scope", role);

        String token = builder.withExpiresAt(new Date(System.currentTimeMillis() + 36000))
                .sign(algorithm);

        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().print(token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.addHeader("Content-Type", "application/json;charset=UTF-8");
        response.getWriter().print(failed.getMessage());
    }
}
