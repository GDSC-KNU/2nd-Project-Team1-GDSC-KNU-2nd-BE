package com.gdsc.wherewego.domain;

import com.gdsc.wherewego.domain.constant.District;
import com.gdsc.wherewego.domain.constant.FoodType;
import com.gdsc.wherewego.domain.constant.Theme;
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
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Builder.Default
    private boolean isDone = false;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private District district;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Theme theme;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @Builder.Default
    private List<DailySchedule> dailySchedules = new ArrayList<>();
}
