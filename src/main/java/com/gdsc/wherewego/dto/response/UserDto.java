package com.gdsc.wherewego.dto.response;

import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserDto {
    Long id;
    String nickname;
    String email;
    String profileUrl;
    List<Schedule> schedules;

    public UserDto(User user) {
        this.id = user.getId();
        this.nickname = user.getNickname();
        this.email = user.getEmail();
        this.profileUrl = user.getProfileUrl();
        this.schedules = user.getSchedules();
    }
}
