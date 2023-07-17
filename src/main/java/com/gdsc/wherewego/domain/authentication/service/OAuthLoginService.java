package com.gdsc.wherewego.domain.authentication.service;

import com.gdsc.wherewego.domain.authentication.domain.oauth.OAuthLoginParams;
import com.gdsc.wherewego.domain.authentication.domain.AuthTokens;
import com.gdsc.wherewego.domain.authentication.domain.AuthTokensGenerator;
import com.gdsc.wherewego.domain.authentication.domain.oauth.OAuthInfoResponse;
import com.gdsc.wherewego.domain.member.entity.Member;
import com.gdsc.wherewego.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final MemberRepository memberRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        Long memberId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(memberId);
    }

    private Long findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return memberRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Member::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private Long newMember(OAuthInfoResponse oAuthInfoResponse) {
        Member member = Member.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .build();

        return memberRepository.save(member).getId();
    }
}
