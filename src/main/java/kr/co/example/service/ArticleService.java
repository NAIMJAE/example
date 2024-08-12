package kr.co.example.service;

import kr.co.example.Repository.ArticleRepository;
import kr.co.example.Repository.CommentRepository;
import kr.co.example.dto.ArticleDTO;
import kr.co.example.dto.CommentDTO;
import kr.co.example.dto.PageRequestDTO;
import kr.co.example.dto.PageResponseDTO;
import kr.co.example.entity.Article;
import kr.co.example.entity.Comment;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final CommentRepository commentRepository;

    // 게시글 조회 (전체)
    public ResponseEntity<?> articleList(PageRequestDTO pageRequestDTO) {
        Pageable pageable = pageRequestDTO.getPageable("articleNo");
        Page<Article> pageArticle = articleRepository.findByArticleCate(pageRequestDTO.getCate(), pageable);

        List<Article> articleList = pageArticle.getContent();
        int total = (int) pageArticle.getTotalElements();

        List<ArticleDTO> articleDTOs = new ArrayList<>();
        for (Article each : articleList) {
            articleDTOs.add(modelMapper.map(each, ArticleDTO.class));
        }

        PageResponseDTO pageResponseDTO = PageResponseDTO.builder()
                .pageRequestDTO(pageRequestDTO)
                .dtoList(articleDTOs)
                .total(total)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(pageResponseDTO);
    }

    // 게시글 조회 (1개)
    public ResponseEntity<?> articleView(int articleNo) {

        // 게시글 조회
        Optional<Article> optArticle = articleRepository.findById(articleNo);
        ArticleDTO articleDTO = optArticle
                .map(article -> modelMapper.map(article, ArticleDTO.class))
                .orElse(null);

        // 댓글 조회
        List<Comment> commentList = commentRepository.findByArticleNo(articleNo);
        if (!commentList.isEmpty()) {
            List<CommentDTO>  commentDTOList = commentList.stream()
                    .map(comment -> modelMapper.map(comment, CommentDTO.class))
                    .toList();
            articleDTO.setCommentList(commentDTOList);
        }

        // 게시글 조회수 +1 프로시저 호출
        articleRepository.incrementArticleHit(articleNo);

        return ResponseEntity.status(HttpStatus.OK).body(articleDTO);
    }

    // 게시글 작성
    public ResponseEntity<?> articleWrite(ArticleDTO articleDTO) {
        Article article = modelMapper.map(articleDTO, Article.class);
        Article savedArticle = articleRepository.save(article);

        if (savedArticle.getArticleContent() != null) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }
    }

    // 게시글 삭제
    public ResponseEntity<?> articleDelete(int articleNo) {
        articleRepository.deleteById(articleNo);
        Optional<Article> optArticle = articleRepository.findById(articleNo);

        if (optArticle.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body(1);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(0);
        }
    }

    // 댓글 작성
    public ResponseEntity<?> commentInsert(CommentDTO commentDTO) {

        Comment savedComment = commentRepository.save(modelMapper.map(commentDTO, Comment.class));
        if (savedComment.getCNo() != 0) {

            Optional<Article> optArticle = articleRepository.findById(commentDTO.getArticleNo());
            if (optArticle.isEmpty()) {
                optArticle.get().setCommentNum(optArticle.get().getCommentNum() + 1);
                articleRepository.save(optArticle.get());
            }

            return ResponseEntity.status(HttpStatus.OK).body(1);
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(0);
        }
    }
}
