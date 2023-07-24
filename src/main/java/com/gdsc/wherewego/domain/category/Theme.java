package com.gdsc.wherewego.domain.category;

import com.gdsc.wherewego.domain.Category;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Theme {
    //    CAFE("카페"),
//    SCENERY("경치관람"),
//    ARBORETUM("수목원/정원"),
//    THEME("테마파크/아쿠아리움"),
//    WALK("가벼운 산책"),
//    HIKING("가벼운 등산"),
//    HISTORY("역사"),
//    ART("미술/예술"),
//    NATURE("자연/과학"),
//    SHOOTING("사격"),
//    ANIMAL("동물/이색체험"),
//    GUIDE("가이드 투어/클래스");
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private Category category;

    private String type;

    public Theme(Category category, String type){
        this.category = category;
        this.type = type;
    }
}

