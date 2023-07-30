package com.wms.controller;

import com.wms.config.UserAuthenticationProvider;
import com.wms.dto.CredentialsDto;
import com.wms.dto.UserDto;
import com.wms.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserAuthenticationProvider userAuthenticationProvider;

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto userDto = authenticationService.login(credentialsDto);

        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CredentialsDto credentialsDto) {
        UserDto userDto = authenticationService.register(credentialsDto);

        userDto.setToken(userAuthenticationProvider.createToken(userDto.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + userDto.getId()))
                .body(userDto);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@AuthenticationPrincipal UserDto userDto) {
        SecurityContextHolder.clearContext();
        return ResponseEntity.noContent().build();
    }
}
