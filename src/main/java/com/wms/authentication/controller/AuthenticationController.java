package com.wms.authentication.controller;

import com.wms.authentication.dto.AuthenticationRequest;
import com.wms.authentication.dto.AuthenticationResponse;
import com.wms.authentication.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.login(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody AuthenticationRequest authenticationRequest) {
        return ResponseEntity.ok(authenticationService.register(authenticationRequest));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        authenticationService.refreshToken(httpServletRequest, httpServletResponse);
    }

}
