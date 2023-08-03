package com.gdsc.wherewego.repository;

import com.gdsc.wherewego.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@DisplayName("사용자 관련 테스트")
@Disabled
public class UserRepoTest extends RepositoryTest {
    private User user;
    @BeforeEach
    void init(){
        user = User.builder()
                .nickname("가희")
                .profileUrl("www.gahee.com")
                .email("rkgml@mail.com")
                .build();
    }

    @Test
    @DisplayName("이메일로 사용자를 찾는다.")
    void save(){
        //given, when
        users.save(user);
        User findUser = users.findByEmail(user.getEmail()).get();

        //then
        Assertions.assertThat(user).isEqualTo(findUser);
    }
}
