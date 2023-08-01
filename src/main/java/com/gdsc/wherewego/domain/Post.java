package com.gdsc.wherewego.domain;

import com.gdsc.wherewego.domain.constant.AuditingFields;
import jakarta.persistence.*;
import lombok.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post extends AuditingFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Setter
    @Column(nullable = false, length = 50)
    private String title;

    @Setter
    @Column(nullable = false, length = 1000)
    private String content;

    @Setter
    @Builder.Default
    private int likes = 0;

    @OneToOne
    private Schedule schedule;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @Builder.Default
    private final Set<Image> images = new LinkedHashSet<>();
}
