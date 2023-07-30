package com.wms.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wms.dto.CredentialsDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class UserAuthenticationFilter extends OncePerRequestFilter {

    private static final String LOGIN_PATH = "/login";
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final UserAuthenticationProvider userAuthenticationProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        if (LOGIN_PATH.equals(request.getServletPath()) && HttpMethod.POST.matches(request.getMethod())) {
            CredentialsDto credentialsDto = OBJECT_MAPPER.readValue(request.getInputStream(), CredentialsDto.class);

            try {
                SecurityContextHolder.getContext()
                        .setAuthentication(userAuthenticationProvider.validateCredentials(credentialsDto));
            } catch (RuntimeException e) {
                SecurityContextHolder.clearContext();
                throw e;
            }
        }
        filterChain.doFilter(request, response);
    }
}
