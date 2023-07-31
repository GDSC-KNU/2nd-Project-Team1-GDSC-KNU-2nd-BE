package com.gdsc.wherewego.domain;

import com.gdsc.wherewego.domain.category.District;
import com.gdsc.wherewego.domain.category.Theme;
import com.gdsc.wherewego.domain.enumCategory.DistrictEnum;
import com.gdsc.wherewego.domain.enumCategory.ThemeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private ThemeEnum theme;

    @Enumerated(EnumType.STRING)
    private DistrictEnum district;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;
}
