package com.web.blog.dao;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface PostDao extends JpaRepository<Post, String> {
    // Post getUserByEmail(String email);
    // Post getUserByNickname(String nickname);

    
    Post getPostByArticleId(int articleId);
    @Transactional(readOnly = true) 
    Post findPostByArticleId(int articleId);
    
    List<Post> getPostByUserId(int userId);
    List<Post> getPostByUserIdAndTemp(int userId,int temp);
    List<Post> getPostByUserIdAndCategoryId(int userId,int categoryId);

    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndWriter(int temp, String writer);

    @Transactional(readOnly = true) 
    Optional<Post> findPostByArticleIdAndTemp(int articleId,int temp);
    @Transactional(readOnly = true) 
    Optional<Post> findPostByArticleIdAndTempAndCategoryId(int articleId,int temp,int categoryId);
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndTitleLike(int temp , String word);
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndCategoryIdAndTitleLike(int temp ,int categoryId, String word);
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndWriterLike(int writer , String word);
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndCategoryIdAndWriterLike(int temp ,int categoryId, String word);

    // 리스트 출력 
    @Transactional(readOnly = true) 
    List<Post> findPostByTemp(int temp);
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndCategoryId(int temp,int categoryId);

    @Transactional(readOnly = true) 
    List<Post> findPostByUserId(int userId);

    // 스트링 토크나이저로 잘라서 한 개 입력했을 때 부터 네 개 입력했을 때 (전체)
    @Transactional(readOnly = true) 
    List<Post> findPostByAddressLike(String st1);
    @Transactional(readOnly = true) 
    List<Post> findPostByAddressLikeAndAddressLike(String st1, String st2);
    @Transactional(readOnly = true) 
    List<Post> findPostByAddressLikeAndAddressLikeAndAddressLike(String st1, String st2, String st3);
    @Transactional(readOnly = true) 
    List<Post> findPostByAddressLikeAndAddressLikeAndAddressLikeAndAddressLike(String st1, String st2, String st3, String st4);
    // 스트링 토크나이저로 잘라서 한 개 입력했을 때 부터 네 개 입력했을 때 (카테고리별)
    
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndCategoryIdAndAddressLike(int temp, int categoryId, String st1);
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndCategoryIdAndAddressLikeAndAddressLike(int temp, int categoryId, String st1, String st2);
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndCategoryIdAndAddressLikeAndAddressLikeAndAddressLike(int temp, int categoryId, String st1, String st2, String st3);
    @Transactional(readOnly = true) 
    List<Post> findPostByTempAndCategoryIdAndAddressLikeAndAddressLikeAndAddressLikeAndAddressLike(int temp, int categoryId, String st1, String st2, String st3, String st4);

}
