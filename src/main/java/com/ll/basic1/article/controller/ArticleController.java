package com.ll.basic1.article.controller;

import com.ll.basic1.article.entity.Article;
import com.ll.basic1.article.repository.ArticleRepository;
import com.ll.basic1.article.service.ArticleService;
import com.ll.basic1.resultData.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor //final이 붙은 것만 입력받는 생성자를 만들어준다.
public class ArticleController {
    private final ArticleService articleService;
    @GetMapping("/write")
    @ResponseBody
    public Result write(String title, String body) {
        Article article = articleService.getArticle(title, body);
        articleService.save(article);
        return new Result("S-1", "1번 글이 생성되었습니다.", article);
    }
}