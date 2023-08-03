package com.gdsc.wherewego.service;

import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.dto.response.UserDto;
import com.gdsc.wherewego.oauth.AuthTokensGenerator;
import com.gdsc.wherewego.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;

    public List<UserDto> findAllUser(){
        return userRepository.findAll().stream().map(UserDto::new).toList();
    }
    public UserDto findByAccessToken(String accessToken){
        Long userId = authTokensGenerator.extractMemberId(accessToken);
        return userRepository.findById(userId).map(UserDto::new).get();
    }
}
