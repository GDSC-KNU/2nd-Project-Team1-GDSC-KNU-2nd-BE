package com.gdsc.wherewego.dto.request;

import com.gdsc.wherewego.domain.Category;
import com.gdsc.wherewego.domain.enumCategory.Transportation;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequest {
    private String startDate;
    private String endDate;
    private List<String> district= new ArrayList<>();
    private List<String> foodType= new ArrayList<>();
    private List<String> theme= new ArrayList<>();
    private Integer withPeople;
    private Integer budget;
    private Transportation transportation;

    public Category toEntity(){
        return Category.builder()
                .startDate(startDate)
                .endDate(endDate)
                .district(district)
                .foodType(foodType)
                .theme(theme)
                .withPeople(withPeople)
                .budget(budget)
                .transportation(transportation)
                .build();
    }
}
