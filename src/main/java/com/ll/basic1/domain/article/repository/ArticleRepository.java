package com.ll.basic1.domain.article.repository;

import com.ll.basic1.domain.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

//엔티티를 db로 다루기 위해선 공식처럼 사용한다.
//Article객체의 pk는 long
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
