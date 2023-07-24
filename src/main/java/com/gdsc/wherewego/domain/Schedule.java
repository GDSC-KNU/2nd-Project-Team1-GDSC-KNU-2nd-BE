package com.gdsc.wherewego.domain;

import com.gdsc.wherewego.api.dto.UserDayRequestDTO;
import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.FoodType;
import com.gdsc.wherewego.domain.category.Theme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @JoinColumn(name = "CATEGORY_ID")
    @OneToOne(mappedBy = "schedule")
    private Category category;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private List<DailySchedule> dailySchedules = new ArrayList<>();

    @Column(nullable = false)
    private Integer withPeople;

    @Column(nullable = false)
    private Integer budget;


    public Schedule(User user, UserDayRequestDTO dayDTO){
        this.user = user;
        this.startDate = dayDTO.getStartDate();
        this.endDate = dayDTO.getEndDate();
    }

    public void setBasicInfo(Integer withPeople, Integer budget){
        this.withPeople = withPeople;
        this.budget = budget;
    }
}
