package com.web.blog.dao;

import java.util.List;
import java.util.Optional;


import com.web.blog.model.like.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;



public interface LikeDao extends JpaRepository<Like, String> {
    
    List<Like> getLikeByArticleId(int articleId);  //게시물  좋아요 수 리턴 

    @Transactional(readOnly = true) 
    List<Like> findLikeByUserId(int userId);  //게시물  좋아요 수 리턴 
    @Transactional(readOnly = true) 
    Optional<Like> findLikeByUserIdAndArticleId(int userId, int articleId); // 계정 좋아요 여부 확인
}