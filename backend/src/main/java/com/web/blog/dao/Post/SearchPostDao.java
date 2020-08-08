package com.web.blog.dao.Post;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import com.web.blog.model.post.Post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional(readOnly = true) 
public interface SearchPostDao extends JpaRepository<Post, String>{
    List<Post> findPostByTemp(int temp);
    List<Post> findPostByTempAndCategoryId(int temp,int categoryId);
}