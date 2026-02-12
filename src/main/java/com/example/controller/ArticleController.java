package com.example.controller;

import com.example.dto.ArticleDeleteReqDTO;
import com.example.dto.ArticleRegisterReqDTO;
import com.example.dto.ArticleSearchReqDTO;
import com.example.dto.ArticleUpdateReqDTO;
import com.example.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    /**
     * 게시글 등록
     * @param request 게시글 등록 정보
     * @return        게시글 ID
     */
    @PostMapping
    public ResponseEntity<?> registerArticle(@RequestBody ArticleRegisterReqDTO request) {
        var result = articleService.registerArticle(request);

        return ResponseEntity.ok().body(result);
    }

    /**
     * 게시글 상세 조회
     * @param articleId 게시글 ID
     * @return          게시글 정보
     */
    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticle(@PathVariable("articleId") Long articleId) {
        var result = articleService.getArticle(articleId);

        return ResponseEntity.ok().body(result);
    }

    /**
     * 게시글 목록 조회 및 검색
     * @param request 게시글 조회 및 검색 정보
     * @return        게시글 목록
     */
    @GetMapping
    public ResponseEntity<?> searchArticleList(@ModelAttribute ArticleSearchReqDTO request) {
        var result = articleService.searchArticleList(request);

        return ResponseEntity.ok().body(result);
    }

    /**
     * 게시글 수정
     * @param articleId 게시글 ID
     * @param request   게시글 수정 정보
     * @return          true : 성공 / false : 실패
     */
    @PatchMapping("/{articleId}")
    public ResponseEntity<?> updateArticle(@PathVariable("articleId") Long articleId,
                                           @RequestBody ArticleUpdateReqDTO request) {
        var result = articleService.updateArticle(articleId, request);

        return ResponseEntity.ok().body(result);
    }

    /**
     * 게시글 삭제
     * @param articleId 게시글 ID
     * @return          true : 성공 / false : 실패
     */
    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable("articleId") Long articleId,
                                           @RequestBody ArticleDeleteReqDTO request) {
        var result = articleService.deleteArticle(articleId, request);

        return ResponseEntity.ok().body(result);
    }
}