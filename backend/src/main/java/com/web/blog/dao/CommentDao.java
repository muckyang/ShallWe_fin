package com.web.blog.dao;

import java.util.List;
import com.web.blog.model.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface CommentDao extends JpaRepository<Comment, String> {
    List<Comment> getCommentByArticleId(int articleId);
    @Transactional(readOnly = true) 
    List<Comment> findCommentByArticleId(int articleId);
    List<Comment> getCommentByUserId(int userId);
    @Transactional(readOnly = true) 
    List<Comment> findCommentByUserId(int userId);

    Comment getCommentByCommentId(int commentId);

}
