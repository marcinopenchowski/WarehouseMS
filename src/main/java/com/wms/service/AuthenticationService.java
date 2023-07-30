package com.wms.service;

import com.wms.dto.CredentialsDto;
import com.wms.dto.UserDto;
import com.wms.entity.User;
import com.wms.exception.AuthenticationException;
import com.wms.mapper.UserMapper;
import com.wms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class AuthenticationService {
    private static final String UNKNOWN_USER = "Unknown user";
    private static final String WRONG_PASSWORD = "Wrong password";
    private static final String LOGIN_EXISTS = "Login already exists";
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public UserDto findByLogin(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new AuthenticationException(UNKNOWN_USER, HttpStatus.NOT_FOUND));

        return userMapper.toUserDto(user);
    }

    public UserDto login(CredentialsDto credentialsDto) {
        User user = userRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AuthenticationException(UNKNOWN_USER, HttpStatus.BAD_REQUEST));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), user.getPassword())) {
            return userMapper.toUserDto(user);
        }

        throw new AuthenticationException(WRONG_PASSWORD, HttpStatus.BAD_REQUEST);
    }

    public UserDto register(CredentialsDto credentialsDto) {
        Optional<User> userOptional = userRepository.findByLogin(credentialsDto.getLogin());

        if (userOptional.isPresent()) {
            throw new AuthenticationException(LOGIN_EXISTS, HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.registerUser(credentialsDto);
        user.setPassword(encodePassword(credentialsDto.getPassword()));
        userRepository.save(user);

        return userMapper.toUserDto(user);
    }

    private String encodePassword(char[] password) {
        return passwordEncoder.encode(CharBuffer.wrap(password));
    }
}
