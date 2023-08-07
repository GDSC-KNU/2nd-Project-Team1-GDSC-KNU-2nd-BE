package com.gdsc.wherewego;

import com.gdsc.wherewego.api.dto.CategoryDTO;
import com.gdsc.wherewego.domain.Schedule;
import com.gdsc.wherewego.domain.User;
import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.FoodType;
import com.gdsc.wherewego.domain.category.Theme;
import com.gdsc.wherewego.repository.UserRepository;
import com.gdsc.wherewego.service.CategoryService;
import com.gdsc.wherewego.service.ScheduleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Transactional
public class CategoryServiceTest {

    public User user;
    public Schedule scheduleByUserId;
    @Autowired
    private ScheduleService scheduleService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void Init(){
        user = User.builder()
                .nickname("LEE")
                .email("LEE@gmail.com")
                .profileUrl("www.asd.com")
                .build();
        userRepository.save(user);
        CategoryDTO.UserDayRequestDTO userDayRequestDTO = new CategoryDTO.UserDayRequestDTO("2023.07.29", "2023.07.31");

        //when
        scheduleService.saveSchedule(userDayRequestDTO, user.getId());
        scheduleByUserId = scheduleService.findByUserId(user.getId());
    }

    @Test
    public void 스케쥴생성() throws Exception {
        Assertions.assertThat(scheduleByUserId.getStartDate()).isEqualTo("2023.07.29");
        Assertions.assertThat(scheduleByUserId.getEndDate()).isEqualTo("2023.07.31");

    }


    @Test
    public void 도시선택() throws Exception {
        //given
        ArrayList<String> objects = new ArrayList<>();
        objects.add("북구");
        objects.add("동구");
        CategoryDTO.UserDistrictRequestDTO userDistrictRequestDTO = new CategoryDTO.UserDistrictRequestDTO(objects, 2, 100000);

        //when
        categoryService.selectDistrict(userDistrictRequestDTO, scheduleByUserId.getId());
        List<District> scheduleDistrict = scheduleByUserId.getDistrict();

        //then
        Assertions.assertThat(scheduleDistrict).hasSize(objects.size());
        for(int i = 0; i < scheduleDistrict.size(); i++){
            Assertions.assertThat(scheduleDistrict.get(i).getCity()).isEqualTo(objects.get(i));
        }
        Assertions.assertThat(scheduleByUserId.getWithPeople()).isEqualTo(2);
        Assertions.assertThat(scheduleByUserId.getBudget()).isEqualTo(100000);
    }

    @Test
    public void 음식선택() throws Exception {
        //given
        ArrayList<String> objects = new ArrayList<>();
        objects.add("한식");
        objects.add("일식");
        CategoryDTO.UserFoodRequestDTO userFoodRequestDTO = new CategoryDTO.UserFoodRequestDTO(objects, "버스");

        //when
        categoryService.selectFood(userFoodRequestDTO, scheduleByUserId.getId());
        List<FoodType> scheduleFoodType = scheduleByUserId.getFoodType();

        //then
        Assertions.assertThat(scheduleFoodType).hasSize(objects.size());
        for(int i = 0; i < scheduleFoodType.size(); i++){
            Assertions.assertThat(scheduleFoodType.get(i).getType()).isEqualTo(objects.get(i));
        }
        Assertions.assertThat(scheduleByUserId.getTransportation()).isEqualTo("버스");
    }

    @Test
    public void 테마선택() throws Exception {
        //given
        ArrayList<String> objects = new ArrayList<>();
        objects.add("CAFE");
        objects.add("WALK");
        objects.add("ANIMAL");
        CategoryDTO.UserThemeRequestDTO userThemeRequestDTO = new CategoryDTO.UserThemeRequestDTO(objects);

        //when
        categoryService.selectTheme(userThemeRequestDTO, scheduleByUserId.getId());
        List<Theme> scheduleTheme = scheduleByUserId.getTheme();

        //then
        Assertions.assertThat(scheduleTheme).hasSize(objects.size());
        for(int i = 0; i < scheduleTheme.size(); i++){
            Assertions.assertThat(scheduleTheme.get(i).getType()).isEqualTo(objects.get(i));
        }
    }
}
