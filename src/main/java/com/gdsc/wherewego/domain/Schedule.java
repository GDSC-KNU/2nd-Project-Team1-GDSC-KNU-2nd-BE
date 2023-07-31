package com.gdsc.wherewego.domain;

import com.gdsc.wherewego.api.dto.CategoryDTO;
import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.FoodType;
import com.gdsc.wherewego.domain.category.Theme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Builder.Default
    private boolean isDone = false;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private List<DailySchedule> dailySchedules = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FoodType> foodType = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Theme> theme = new ArrayList<>();

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<District> district = new ArrayList<>();

    private Integer withPeople;

    private Integer budget;

    private String transportation;

    public Schedule(User user, CategoryDTO.UserDayRequestDTO dayDTO){
        this.user = user;
        this.startDate = dayDTO.getStartDate();
        this.endDate = dayDTO.getEndDate();
    }

    public void setBasicInfo(Integer withPeople, Integer budget){
        this.withPeople = withPeople;
        this.budget = budget;
    }

    public void setTransportation(String transportation){
        this.transportation = transportation;
    }

    public void setDistrict(List<District> district){
        this.district = district;
    }

    public void setFoodType(List<FoodType> foodType){
        this.foodType = foodType;
    }

    public void setTheme(List<Theme> theme){
        this.theme = theme;
    }
}
