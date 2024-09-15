package com.nicha.user_service.controller;


import com.nicha.user_service.dto.response.ApiResponse;
import com.nicha.user_service.entity.User;
import com.nicha.user_service.enums.ErrorCode;
import com.nicha.user_service.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;

    @PostMapping("/register")
    public ApiResponse<User> registerUser(@RequestBody User user) {
        return ApiResponse.<User>builder()
                .code(HttpStatus.CREATED.value())
                .message("User registered successfully")
                .result(userService.saveUser(user))
                .build();
    }

    @GetMapping("/{username}")
    public ApiResponse<User> getUserByUsername(@PathVariable String username) {
        return ApiResponse.<User>builder()
                .code(HttpStatus.OK.value())
                .message(ErrorCode.USER_NOT_EXISTED.getMessage())
                .result(userService.findByUsername(username))
                .build();
    }
}