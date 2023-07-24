package com.gdsc.wherewego.domain.category;

import com.gdsc.wherewego.domain.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@NoArgsConstructor
@Getter
public class District {
//    JUNG_GU("중구"),
//    SEO_GU("서구"),
//    DONG_GU("동구"),
//    NAM_GU("남구"),
//    BUK_GU("북구"),
//    SUSEONG_GU("수성구"),
//    DALSEO_GU("달서구"),
//    DALSEONG_GUN("달성군"),
//    GUNWI_GUN("군위군");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    private String city;

}
