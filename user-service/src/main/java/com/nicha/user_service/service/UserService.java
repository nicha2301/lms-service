package com.nicha.user_service.service;

import com.nicha.user_service.entity.User;
import com.nicha.user_service.enums.ErrorCode;
import com.nicha.user_service.exception.AppException;
import com.nicha.user_service.repository.UserRepository;
import com.nicha.user_service.util.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    UserRepository userRepository;
    JwtUtil jwtUtil;

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
    }

    public User saveUser(User user) {
        if (findByUsername(user.getUsername()) != null)
            throw new AppException(ErrorCode.USER_EXISTED);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String login(String username, String password) throws Exception {
        User user = findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new AppException(ErrorCode.INVALID_PASSWORD);
        }

        return jwtUtil.generateToken(user.getUsername());

    }
}