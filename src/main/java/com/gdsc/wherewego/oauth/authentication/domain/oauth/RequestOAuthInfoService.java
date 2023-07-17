package com.gdsc.wherewego.oauth.authentication.domain.oauth;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class RequestOAuthInfoService {
    private final OAuthApiClient client;

    public RequestOAuthInfoService(OAuthApiClient client) {
        this.client = client;
    }

    public OAuthInfoResponse request(OAuthLoginParams params) {
        String accessToken = client.requestAccessToken(params);
        return client.requestOauthInfo(accessToken);
    }
}