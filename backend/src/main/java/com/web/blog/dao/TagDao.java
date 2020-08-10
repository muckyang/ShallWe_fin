package com.web.blog.dao;

import java.util.List;
import com.web.blog.model.tag.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface TagDao extends JpaRepository<Tag, String>{
    @Transactional(readOnly = true) 
    List<Tag> findTagByName(String name);
    List<Tag> getTagByArticleId(int articleId);
    @Transactional(readOnly = true) 
    List<Tag> findTagByArticleId(int articleId);
}