package com.ll.basic1.domain.article.service;

import com.ll.basic1.domain.article.entity.Article;
import com.ll.basic1.domain.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    public Article save(String title, String body) {
        Article article = Article
                .builder()
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);
        return article;
    }


}