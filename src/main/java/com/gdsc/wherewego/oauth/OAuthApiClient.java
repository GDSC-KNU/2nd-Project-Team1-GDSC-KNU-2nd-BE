package com.gdsc.wherewego.oauth;

public interface OAuthApiClient {
    String requestAccessToken(OAuthLoginParams params);
    OAuthInfoResponse requestOauthInfo(String accessToken);
}
