package com.gdsc.wherewego;

import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@SpringBootTest
public class H2Test {
    
    @Autowired
    UserRepository userRepository;
    
    @Test
    @Transactional
    @Rollback(false)
    public void 멤버조회() throws Exception {
        //given
        User user = new User();
        user.setName("Lee");
        user.setAge(26);

        //when
        User dbUser = userRepository.save(user);
        Optional<User> optionalUser = userRepository.findById(dbUser.getId());
        User findUser = optionalUser.get();

        //then
        Assertions.assertThat(findUser.getId()).isEqualTo(user.getId());
        Assertions.assertThat(findUser.getName()).isEqualTo(user.getName());
        Assertions.assertThat(findUser.getAge()).isEqualTo(user.getAge());

        Assertions.assertThat(findUser).isEqualTo(user);
    }
        
    
}
