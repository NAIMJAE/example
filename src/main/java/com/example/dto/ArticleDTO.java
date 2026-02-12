package com.example.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO {
    private Long articleId;
    private String title;
    private String contents;
    private UserInfo userInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Getter
    @AllArgsConstructor
    public static class UserInfo {
        private Long userId;
        private String name;
    }

    public ArticleDTO(Long articleId, String title, String contents,
                      LocalDateTime createdAt, LocalDateTime updatedAt,
                      Long userId, String name) {
        this.articleId = articleId;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.userInfo = new UserInfo(userId, name);
    }
}