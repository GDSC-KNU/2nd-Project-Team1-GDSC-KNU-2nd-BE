package com.gdsc.wherewego.dto.response;

import com.gdsc.wherewego.oauth.AuthTokens;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AuthTokensDto {
    private String accessToken;
    private String refreshToken;
    private String grantType;
    private Long expiresIn;

    public void AuthTokensDto(AuthTokens authTokens){
        this.accessToken = authTokens.getAccessToken();
        this.refreshToken = authTokens.getRefreshToken();
        this.grantType = authTokens.getGrantType();
        this.expiresIn = authTokens.getExpiresIn();
    }
}
