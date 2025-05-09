package com.moviesearch.movie.controller;

import com.moviesearch.movie.entity.User;
import com.moviesearch.movie.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody User user) {
        User savedUser = authService.signup(user.getUserId(), user.getPasswd(), user.getEmail());
        return ResponseEntity.ok(savedUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginUser) {
        Optional<User> user = authService.login(loginUser.getUserId(), loginUser.getPasswd());
        if (user.isPresent()) {
            return ResponseEntity.ok(Map.of(
                "message", "로그인 성공",
                "userId", user.get().getUserId()
            ));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                "message", "로그인 실패"
            ));
        }
    }
}