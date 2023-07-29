package com.wms.controller;

import com.wms.config.UserAuthProvider;
import com.wms.dto.CredentialsDto;
import com.wms.dto.UserDto;
import com.wms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticationController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto userDto = userService.login(credentialsDto);

        userDto.setToken(userAuthProvider.createToken(userDto.getLogin()));
        return ResponseEntity.ok(userDto);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(@RequestBody CredentialsDto credentialsDto) {
        UserDto userDto = userService.register(credentialsDto);

        userDto.setToken(userAuthProvider.createToken(userDto.getLogin()));
        return ResponseEntity.created(URI.create("/users/" + userDto.getId()))
                .body(userDto);
    }
}
