package com.gdsc.wherewego.service;


import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.oauth.AuthTokens;
import com.gdsc.wherewego.oauth.AuthTokensGenerator;
import com.gdsc.wherewego.oauth.OAuthInfoResponse;
import com.gdsc.wherewego.oauth.OAuthLoginParams;
import com.gdsc.wherewego.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UserRepository userRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long userId = findOrCreateUser(oAuthInfoResponse);
        return authTokensGenerator.generate(userId);
    }

    private Long findOrCreateUser(OAuthInfoResponse oAuthInfoResponse) {
        return userRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(User::getId)
                .orElseGet(() -> newUser(oAuthInfoResponse));
    }

    private Long newUser(OAuthInfoResponse oAuthInfoResponse) {
        User user = User.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .build();

        return userRepository.save(user).getId();
    }
}
