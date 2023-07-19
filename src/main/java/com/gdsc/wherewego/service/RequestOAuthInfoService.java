package com.gdsc.wherewego.service;

import com.gdsc.wherewego.oauth.OAuthApiClient;
import com.gdsc.wherewego.oauth.OAuthInfoResponse;
import com.gdsc.wherewego.oauth.OAuthLoginParams;
import org.springframework.stereotype.Component;

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