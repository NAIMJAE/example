package kr.co.example.dto;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CommentDTO {
    private int cNo;
    private int articleNo;
    private String userId;
    private String content;
}
