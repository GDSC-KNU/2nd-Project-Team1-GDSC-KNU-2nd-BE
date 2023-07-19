package com.gdsc.wherewego.controller;

import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.oauth.AuthTokensGenerator;
import com.gdsc.wherewego.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{accessToken}")
    public ResponseEntity<User> findByAccessToken(@PathVariable String accessToken) {
        Long userId = authTokensGenerator.extractMemberId(accessToken);
        return ResponseEntity.ok(userRepository.findById(userId).get());
    }
}
