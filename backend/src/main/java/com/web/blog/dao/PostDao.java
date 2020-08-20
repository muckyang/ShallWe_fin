package com.web.blog.dao;

import java.util.List;
import java.util.Optional;

import com.web.blog.model.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostDao extends JpaRepository<Post, String> {
    // Post getUserByEmail(String email);
    // Post getUserByNickname(String nickname);

    
    Post getPostByArticleId(int articleId);

    Post findPostByArticleId(int articleId);
    
    List<Post> getPostByUserId(int userId);
    List<Post> getPostByUserIdAndTemp(int userId,int temp);
    List<Post> getPostByUserIdAndCategoryId(int userId,int categoryId);
    List<Post> getPostByTempAndCategoryIdNotOrderByCategoryIdAscCreateTimeDesc(int userId,int categoryId);
    List<Post> findPostByTempAndStatusNotOrderByCreateTimeDesc(int temp,int status);
    List<Post> findPostByTempAndCategoryIdAndStatusNotOrderByCreateTimeDesc(int temp,int categoryId,int status);
    List<Post> getPostByTempAndCategoryIdNotAndStatusNotOrderByCategoryIdAscCreateTimeDesc(int temp,int categoryId,int status);

    Post getPostByArticleIdAndStatus(int articleId,int status);
    Post getPostByArticleIdAndStatusIn(int articleId,List<Integer> numlist);
    List<Post> findPostByTempAndWriterOrderByCreateTimeDesc(int temp, String writer);


    Optional<Post> findPostByStatusNotArticleIdAndTempOrderByCreateTimeDesc(int status, int articleId,int temp);

    Optional<Post> findPostByArticleIdAndTempAndCategoryIdOrderByCreateTimeDesc(int articleId,int temp,int categoryId);

    List<Post> findPostByTitleLikeOrderByCreateTimeDesc(String word);

    List<Post> findPostByStatusNotTempAndTitleLikeOrderByCreateTimeDesc(int status,int temp , String word);

    List<Post> findPostByStatusNotTempAndCategoryIdAndTitleLikeOrderByCreateTimeDesc(int status,int temp ,int categoryId, String word);
   List<Post> findPostByStatusNotTempAndWriterLikeOrderByCreateTimeDesc(int status, int writer , String word);

    List<Post> findPostByStatusNotTempAndCategoryIdAndWriterLikeOrderByCreateTimeDesc(int status , int temp ,int categoryId, String word);

    // 리스트 출력 
    List<Post> findPostByStatusNotTempOrderByCreateTimeDesc(int status , int temp);
    
    List<Post> findPostByTempAndCategoryIdOrderByCreateTimeDesc(int temp,int categoryId);
    
    
    List<Post> findPostByUserId(int userId);
    
    // 스트링 토크나이저로 잘라서 한 개 입력했을 때 부터 네 개 입력했을 때 (전체)
    
    List<Post> findPostByStatusNotAddressLikeOrderByCreateTimeDesc(int status, String st1);
    List<Post> findPostByStatusNotAddressLikeAndAddressLikeOrderByCreateTimeDesc(int status, String st1, String st2);
    List<Post> findPostByStatusNotAddressLikeAndAddressLikeAndAddressLikeOrderByCreateTimeDesc(int status, String st1, String st2, String st3);
    List<Post> findPostByStatusNotAndAddressLikeAndAddressLikeAndAddressLikeOrderByCreateTimeDesc(int status,String st1, String st2, String st3);
    List<Post> findPostByStatusNotAddressLikeAndAddressLikeAndAddressLikeAndAddressLikeOrderByCreateTimeDesc(int status, String st1, String st2, String st3, String st4);
    // 스트링 토크나이저로 잘라서 한 개 입력했을 때 부터 네 개 입력했을 때 (카테고리별)
    
    List<Post> findPostByStatusNotTempAndCategoryIdAndAddressLikeOrderByCreateTimeDesc(int status, int temp, int categoryId, String st1);
    List<Post> findPostByStatusNotTempAndCategoryIdAndAddressLikeAndAddressLikeOrderByCreateTimeDesc(int status, int temp, int categoryId, String st1, String st2);
    List<Post> findPostByStatusNotTempAndCategoryIdAndAddressLikeAndAddressLikeAndAddressLikeOrderByCreateTimeDesc(int status, int temp, int categoryId, String st1, String st2, String st3);
    List<Post> findPostByStatusNotTempAndCategoryIdAndAddressLikeAndAddressLikeAndAddressLikeAndAddressLikeOrderByCreateTimeDesc(int status, int temp, int categoryId, String st1, String st2, String st3, String st4);
    
    // 메인
    // List<Post> findPostByTempOrderByCreateTimeDesc(int temp);
    List<Post> findPostByStatusNotTitleLikeOrderByCreateTimeDesc(int status,String title);
    List<Post> findPostByStatusNotTempOrderByEndTimeAsc(int status, int temp);
    List<Post> findPostByStatusNotTempAndCategoryIdOrderByLikeNumDesc(int status , int temp, int categoryId);;
}
