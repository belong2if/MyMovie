package com.moviesearch.movie.service;

import com.moviesearch.movie.entity.User;
import com.moviesearch.movie.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User signup(String userId, String passwd, String email) {
        User user = new User();
        user.setUserId(userId);
        user.setPasswd(passwd);
        user.setEmail(email);
        return userRepository.save(user);
    }

    public Optional<User> login(String userId, String passwd) {
        return userRepository.findByUserIdAndPasswd(userId, passwd);
    }
}