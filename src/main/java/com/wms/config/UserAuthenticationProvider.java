package com.wms.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.wms.dto.CredentialsDto;
import com.wms.dto.UserDto;
import com.wms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

import java.util.Base64;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class UserAuthenticationProvider {

    private final AuthenticationService authenticationService;

    @Value("${security.jwt.token.secret-key:secret-value}")
    private String secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    public String createToken(String login) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000);

        return JWT.create()
                .withIssuer(login)
                .withIssuedAt(now)
                .withExpiresAt(validity)
                .sign(Algorithm.HMAC256(secretKey));
    }

    public Authentication validateToken(String token) {
        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secretKey)).build();
        DecodedJWT decodedJWT = verifier.verify(token);
        UserDto userDto = authenticationService.findByLogin(decodedJWT.getIssuer());

        return new UsernamePasswordAuthenticationToken(
                userDto,
                null,
                userDto.getAuthRoles().stream().map(authRole -> (GrantedAuthority) () -> authRole).toList());
    }

    public Authentication validateCredentials(CredentialsDto credentialsDto) {
        UserDto userDto = authenticationService.login(credentialsDto);
        return new UsernamePasswordAuthenticationToken(
                userDto,
                null,
                userDto.getAuthRoles().stream().map(authRole -> (GrantedAuthority) () -> authRole).toList()
        );
    }
}
