package com.example.dto;

import lombok.*;
import org.springframework.data.domain.Page;
import java.util.List;

@Getter
public class ArticleSearchResDTO {
    private List<ArticleDTO> list;
    private int page;
    private int size;
    private int totalPages;
    private long totalElements;
    private boolean hasNext;
    private String searchType;
    private String searchKeyword;

    public ArticleSearchResDTO(ArticleSearchReqDTO dto, Page<ArticleDTO> list) {
        this.list = list.getContent();
        this.page = list.getNumber() + 1;
        this.size = list.getSize();
        this.totalPages = list.getTotalPages();
        this.totalElements = list.getTotalElements();
        this.hasNext = list.hasNext();
        this.searchType = dto.getSearchType();
        this.searchKeyword = dto.getSearchKeyword();
    }
}