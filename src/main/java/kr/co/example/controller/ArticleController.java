package kr.co.example.controller;

import kr.co.example.dto.ArticleDTO;
import kr.co.example.dto.CommentDTO;
import kr.co.example.dto.PageRequestDTO;
import kr.co.example.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Controller
public class ArticleController {

    private final ArticleService articleService;

    // 게시글 조회 (전체)
    @PostMapping("/article/list")
    public ResponseEntity<?> articleList(@RequestBody PageRequestDTO pageRequestDTO) {
        log.info("pageRequestDTO : " + pageRequestDTO);
        return articleService.articleList(pageRequestDTO);
    }

    // 게시글 조회 (1개)
    @GetMapping("/article/view/{articleNo}")
    public ResponseEntity<?> articleView(@PathVariable("articleNo") int articleNo) {
        log.info("articleNo : " + articleNo);
        return articleService.articleView(articleNo);
    }

    // 게시글 작성
    @PostMapping("/article/write")
    public ResponseEntity<?> articleWrite(@RequestBody ArticleDTO articleDTO) {
        log.info("articleDTO : " + articleDTO);
        return articleService.articleWrite(articleDTO);
    }
    
    // 게시글 삭제
    @PostMapping("/article/delete")
    public ResponseEntity<?> articleDelete(@RequestBody Map<String, Integer> request) {
        int articleNo = request.get("articleNo");
        log.info("articleNo : " + articleNo);
        return articleService.articleDelete(articleNo);
    }

    // 댓글 작성
    @PostMapping("/comment/write")
    public ResponseEntity<?> commentInsert(@RequestBody CommentDTO commentDTO) {
        log.info("commentDTO : " + commentDTO);

        return articleService.commentInsert(commentDTO);
    }
}
