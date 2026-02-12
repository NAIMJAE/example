package com.example.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class ArticleUpdateReqDTO {
    private Long userId;
    private String title;
    private String contents;
}