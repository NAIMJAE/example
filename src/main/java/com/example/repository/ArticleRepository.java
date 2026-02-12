package com.example.repository;

import com.example.dto.ArticleDTO;
import com.example.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(
        value = """
            SELECT new com.example.dto.ArticleDTO(
                a.articleId, a.title, a.contents, a.createdAt, a.updatedAt,
                u.userId, u.name
            )
            FROM Article a
            JOIN a.user u
            WHERE (:searchType IS NULL OR :searchKeyword IS NULL OR :searchKeyword = '')
               OR (:searchType = 'title' AND a.title LIKE CONCAT('%', :searchKeyword, '%'))
               OR (:searchType = 'contents' AND a.contents LIKE CONCAT('%', :searchKeyword, '%'))
        """,
        countQuery = """
            SELECT count(a)
            FROM Article a
            JOIN a.user u
            WHERE (:searchType IS NULL OR :searchKeyword IS NULL OR :searchKeyword = '')
               OR (:searchType = 'title' AND a.title LIKE CONCAT('%', :searchKeyword, '%'))
               OR (:searchType = 'contents' AND a.contents LIKE CONCAT('%', :searchKeyword, '%'))
        """
    )
    Page<ArticleDTO> searchArticleList(@Param("searchType") String searchType,
                                       @Param("searchKeyword") String searchKeyword,
                                       Pageable pageable);
}