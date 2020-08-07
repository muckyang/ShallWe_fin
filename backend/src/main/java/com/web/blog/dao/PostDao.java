package com.web.blog.dao;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostDao extends JpaRepository<Post, String> {
    // Post getUserByEmail(String email);
    // Post getUserByNickname(String nickname);
    Post getPostByArticleId(int articleId);
    
    List<Post> findPostByUserId(int userId);
    List<Post> findPostByUserIdAndTemp(int userId,int temp);
    List<Post> findPostByUserIdAndCategoryId(int userId,int categoryId);

    List<Post> findPostByTemp(int temp);
    List<Post> findPostByTempAndCategoryId(int temp,int categoryId);
    List<Post> findPostByTempAndWriter(int temp, String writer);
    Optional<Post> findPostByArticleId(int articleId);

    Optional<Post> findPostByArticleIdAndTemp(int articleId,int temp);
    Optional<Post> findPostByArticleIdAndTempAndCategoryId(int articleId,int temp,int categoryId);
    
    List<Post> findPostByTempAndTitleLike(int temp , String word);
    List<Post> findPostByTempAndCategoryIdAndTitleLike(int temp ,int categoryId, String word);
    List<Post> findPostByTempAndWriterLike(int writer , String word);
    List<Post> findPostByTempAndCategoryIdAndWriterLike(int temp ,int categoryId, String word);
    // 스트링 토크나이저로 잘라서 한 개 입력했을 때 부터 네 개 입력했을 때 (전체)
    List<Post> findPostByAddressLike(String st1);
    List<Post> findPostByAddressLikeAndAddressLike(String st1, String st2);
    List<Post> findPostByAddressLikeAndAddressLikeAndAddressLike(String st1, String st2, String st3);
    List<Post> findPostByAddressLikeAndAddressLikeAndAddressLikeAndAddressLike(String st1, String st2, String st3, String st4);
    // 스트링 토크나이저로 잘라서 한 개 입력했을 때 부터 네 개 입력했을 때 (카테고리별)
    List<Post> findPostByTempAndCategoryIdAndAddressLike(int temp, int categoryId, String st1);
    List<Post> findPostByTempAndCategoryIdAndAddressLikeAndAddressLike(int temp, int categoryId, String st1, String st2);
    List<Post> findPostByTempAndCategoryIdAndAddressLikeAndAddressLikeAndAddressLike(int temp, int categoryId, String st1, String st2, String st3);
    List<Post> findPostByTempAndCategoryIdAndAddressLikeAndAddressLikeAndAddressLikeAndAddressLike(int temp, int categoryId, String st1, String st2, String st3, String st4);

}
