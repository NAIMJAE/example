package com.example.service;

import com.example.dto.*;
import com.example.entity.Article;
import com.example.entity.User;
import com.example.repository.ArticleRepository;
import com.example.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    /**
     * 게시글 등록
     * @param dto 게시글 등록 정보
     * @return    게시글 ID
     */
    @Transactional
    public Long registerArticle(ArticleRegisterReqDTO dto) {
        User userRef = userRepository.getReferenceById(dto.getUserId());
        Article article = Article.builder()
                .user(userRef)
                .title(dto.getTitle())
                .contents(dto.getContents())
                .build();

        Article saveArticle = articleRepository.save(article);

        return saveArticle.getArticleId();
    }

    /**
     * 게시글 상세 조회
     * @param articleId 게시글 ID
     * @return          게시글 정보
     */
    @Transactional(readOnly = true)
    public ArticleDTO getArticle(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new java.util.NoSuchElementException("해당 게시글을 찾을 수 없음"));

        User user = article.getUser();

        return ArticleDTO.builder()
                .articleId(article.getArticleId())
                .title(article.getTitle())
                .contents(article.getContents())
                .userInfo(new ArticleDTO.UserInfo(user.getUserId(), user.getName()))
                .createdAt(article.getCreatedAt())
                .updatedAt(article.getUpdatedAt())
                .build();
    }

    /**
     * 게시글 목록 조회 및 검색
     * @param dto 게시글 조회 및 검색 정보
     * @return    게시글 목록
     */
    @Transactional(readOnly = true)
    public ArticleSearchResDTO searchArticleList(ArticleSearchReqDTO dto) {
        Pageable pageable = dto.toPageable();
        Page<ArticleDTO> list = articleRepository.searchArticleList(dto.getSearchType(), dto.getSearchKeyword(), pageable);

        return new ArticleSearchResDTO(dto, list);
    }

    /**
     * 게시글 수정
     * @param articleId 게시글 ID
     * @return          true : 성공 / false : 실패
     */
    @Transactional
    public boolean updateArticle(Long articleId, ArticleUpdateReqDTO dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new java.util.NoSuchElementException("해당 게시글을 찾을 수 없음"));

        if (!article.getUser().getUserId().equals(dto.getUserId())) {
            throw new IllegalStateException("수정 권한이 없는 사용자");
        }

        article.updateInfo(dto.getTitle(), dto.getContents());
        return true;
    }

    /**
     * 게시글 삭제
     * @param articleId 게시글 ID
     * @return          true : 성공 / false : 실패
     */
    @Transactional
    public boolean deleteArticle(Long articleId, ArticleDeleteReqDTO dto) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new java.util.NoSuchElementException("해당 게시글을 찾을 수 없음"));

        if (!article.getUser().getUserId().equals(dto.getUserId())) {
            throw new IllegalStateException("삭제 권한이 없는 사용자");
        }

        articleRepository.deleteById(articleId);
        return true;
    }
}