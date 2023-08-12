package com.gdsc.wherewego.domain;


import com.gdsc.wherewego.domain.enumCategory.Transportation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private Schedule schedule;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @ElementCollection
    private List<String> district = new ArrayList<>();

    @ElementCollection
    private List<String> foodType = new ArrayList<>();

    @ElementCollection
    private List<String> theme = new ArrayList<>();

    private Integer withPeople;

    private Integer budget;

    @Enumerated(EnumType.STRING)
    private Transportation transportation;

    @Builder
    public Category(String startDate, String endDate, List<String> district, List<String> foodType, List<String> theme, Integer withPeople, Integer budget, Transportation transportation){
        this.startDate = startDate;
        this.endDate = endDate;
        this.district = district;
        this.foodType = foodType;
        this.theme = theme;
        this.withPeople = withPeople;
        this.budget = budget;
        this.transportation = transportation;
    }


}
