package com.example.dto;

import lombok.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Getter
@Setter
@NoArgsConstructor
public class ArticleSearchReqDTO {
    private int page = 1;
    private int size = 10;

    private String searchType;
    private String searchKeyword;

    public Pageable toPageable() {
        int p = Math.max(1, page);
        return PageRequest.of(p - 1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
    }
}