package kr.co.example.Repository;

import kr.co.example.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {
    // 게시글 조회
    public Page<Article> findByArticleCate(String cate, Pageable pageable);

    // 게시글 조회수 +1 프로시저 정의
    @Procedure(name = "incrementArticleHit")
    void incrementArticleHit(@Param("articleNo") int articleNo);
}
