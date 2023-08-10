package com.wms.authentication.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.authentication.dto.AuthenticationRequest;
import com.wms.authentication.dto.AuthenticationResponse;
import com.wms.authentication.dto.TokenType;
import com.wms.authentication.dto.UpdateUserRequest;
import com.wms.authentication.entity.Token;
import com.wms.authentication.entity.User;
import com.wms.authentication.repository.TokenRepository;
import com.wms.authentication.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private static final String BEARER_WHITESPACE = "Bearer ";
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getLogin(),
                        authenticationRequest.getPassword()
                )
        );

        var user = userRepository.findByLogin(authenticationRequest.getLogin()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public AuthenticationResponse register(AuthenticationRequest authenticationRequest) {
        var user = User.builder()
                .login(authenticationRequest.getLogin())
                .password(passwordEncoder.encode(authenticationRequest.getPassword()))
                .build();
        var savedUser = userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    public boolean updateUser(UpdateUserRequest updateUserRequest, HttpServletRequest httpServletRequest) {
        var authHeader = httpServletRequest.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(BEARER_WHITESPACE)) {
            return false;
        }

        var token = getTokenFromHeader(authHeader);
        var login = jwtService.extractUsername(token);
        var user = userRepository.findByLogin(login).orElseThrow();

        Optional.ofNullable(updateUserRequest.getNewLogin())
                .filter(StringUtils::isNotBlank)
                .ifPresent(user::setLogin);

        Optional.ofNullable(updateUserRequest.getNewPassword())
                .filter(StringUtils::isNotBlank)
                .map(passwordEncoder::encode)
                .ifPresent(user::setPassword);

        userRepository.save(user);
        return true;
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String login;
        if (authHeader == null || !authHeader.startsWith(BEARER_WHITESPACE)) {
            return;
        }
        refreshToken = getTokenFromHeader(authHeader);
        login = jwtService.extractUsername(refreshToken);
        if (login != null) {
            var user = this.userRepository.findByLogin(login)
                    .orElseThrow();
            if (jwtService.isTokenValid(refreshToken, user)) {
                var accessToken = jwtService.generateToken(user);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }

    private String getTokenFromHeader(String header) {
        return StringUtils.substringAfter(header, BEARER_WHITESPACE);
    }
}
