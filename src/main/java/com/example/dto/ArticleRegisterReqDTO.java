package com.example.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class ArticleRegisterReqDTO {
    private Long userId;
    private String title;
    private String contents;
}