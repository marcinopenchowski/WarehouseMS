package com.wms.authentication.controller;

import com.wms.authentication.dto.AuthenticationRequest;
import com.wms.authentication.dto.AuthenticationResponse;
import com.wms.authentication.dto.UpdateUserRequest;
import com.wms.authentication.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/updateUser")
    public ResponseEntity<String> updateUser(
            @RequestBody UpdateUserRequest updateUserRequest,
            HttpServletRequest httpServletRequest) {
        boolean userChanged = authenticationService.updateUser(updateUserRequest, httpServletRequest);

        if (userChanged) {
            return ResponseEntity.ok("User changed successfully.");
        } else {
            return ResponseEntity.status(403).body("Unable to change password.");
        }
    }

    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) throws IOException {
        authenticationService.refreshToken(httpServletRequest, httpServletResponse);
    }
}
