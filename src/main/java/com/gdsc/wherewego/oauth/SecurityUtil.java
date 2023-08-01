package com.gdsc.wherewego.oauth;

import com.gdsc.wherewego.exception.NoAuthorizationException;
import com.gdsc.wherewego.exception.NoSuchElementException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static com.gdsc.wherewego.domain.constant.Constants.*;


@RequiredArgsConstructor
public class SecurityUtil {
    private static OAuthApiClient oAuthApiClient;

    public static String getCurrentUserEmail() {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String authorization = request.getHeader(AUTHORIZATION_HEADER);

        if(StringUtils.hasText(authorization) && authorization.startsWith(BEARER_PREFIX)) {
            String accessToken = authorization.substring(BEARER_PREFIX.length());
            try {
                return oAuthApiClient.requestOauthInfo(accessToken).getEmail();
            } catch (RuntimeException e) {
                throw new NoSuchElementException(USER);
            }
        }
        else
            throw new NoAuthorizationException(HEADER);
    }

}
