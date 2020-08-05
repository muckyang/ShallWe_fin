package com.web.blog.model.comment;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.Valid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Valid
@NoArgsConstructor
@AllArgsConstructor
public class CommentRes {
    private int commentId;
    private int articleId;
    private int userId;
    private String nickname;
    private String content;
    private LocalDateTime createTime;
    
}