package com.gdsc.wherewego.domain.category;

import com.gdsc.wherewego.domain.Category;
import com.gdsc.wherewego.domain.Schedule;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
public class FoodType {
//    KOREAN("한식"),
//    CHINESE("중식"),
//    JAPANESE("일식"),
//    WESTERN("양식");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    private String type;
}
