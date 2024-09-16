package com.nicha.user_service.service;

import com.nicha.user_service.entity.User;
import com.nicha.user_service.repository.UserRepository;
import com.nicha.user_service.util.JwtUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {
    UserRepository userRepository;
    JwtUtil jwtUtil;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);

    }

    public User saveUser(User user) {
        if (userRepository.findByUsername(user.getUsername()) != null)
            return null;

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }

    public String login(String username, String password) throws Exception {
        User user = findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            return null;
        }

        return jwtUtil.generateToken(user.getUsername());
    }

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }
}