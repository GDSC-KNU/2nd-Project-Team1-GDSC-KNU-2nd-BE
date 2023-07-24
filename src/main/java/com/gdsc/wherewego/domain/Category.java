package com.gdsc.wherewego.domain;

import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.FoodType;
import com.gdsc.wherewego.domain.category.Theme;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID")
    private Schedule schedule;

    @OneToOne(mappedBy = "Category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Place place;

    @Column(nullable = false)
    @OneToMany(mappedBy = "Category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<FoodType> foodType = new ArrayList<>();

    @Column(nullable = false)
    @OneToMany(mappedBy = "Category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Theme> theme = new ArrayList<>();

    @Column(nullable = false)
    @OneToMany(mappedBy = "Category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<District> district = new ArrayList<>();

}
