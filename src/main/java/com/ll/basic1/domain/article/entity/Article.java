package com.ll.basic1.domain.article.entity;
import com.ll.basic1.shared.baseEntity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

import static jakarta.persistence.GenerationType.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;
    private String title;
    private String body;
}
