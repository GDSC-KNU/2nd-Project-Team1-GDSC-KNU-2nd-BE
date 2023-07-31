package com.gdsc.wherewego.oAuth;

import com.gdsc.wherewego.oauth.AuthTokens;
import com.gdsc.wherewego.oauth.AuthTokensGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class AuthTokensGeneratorTest {
    @Autowired
    private AuthTokensGenerator authTokensGenerator;

    @Test
    @DisplayName("JWT 토큰 생성 성공")
    void testGenerate() {
        // given
        Long memberId = 0L;

        // when
        AuthTokens authTokens = authTokensGenerator.generate(memberId);

        // then
        assertThat(authTokens.getGrantType()).isEqualTo("Bearer");
        assertThat(authTokens.getAccessToken()).isNotBlank();
        assertThat(authTokens.getRefreshToken()).isNotBlank();
        assertThat(authTokens.getExpiresIn()).isNotNull();
    }

    @Test
    @DisplayName("JWT 토큰 검증 성공")
    void testExtractSubject() {
        // given
        Long memberId = 0L;
        AuthTokens authTokens = authTokensGenerator.generate(memberId);
        String accessToken = authTokens.getAccessToken();

        // when
        Long extractedMemberId = authTokensGenerator.extractMemberId(accessToken);

        // then
        assertThat(extractedMemberId).isEqualTo(memberId);
    }
}
