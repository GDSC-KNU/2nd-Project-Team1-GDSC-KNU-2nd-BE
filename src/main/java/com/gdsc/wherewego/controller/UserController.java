package com.gdsc.wherewego.controller;

import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.dto.response.UserDto;
import com.gdsc.wherewego.oauth.AuthTokensGenerator;
import com.gdsc.wherewego.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> findAll() {

        return ResponseEntity.ok(userService.findAllUser());
    }

    @GetMapping("/{accessToken}")
    public ResponseEntity<UserDto> findByAccessToken(@PathVariable String accessToken) {

        return ResponseEntity.ok(userService.findByAccessToken(accessToken));
    }
}
