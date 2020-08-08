package com.web.blog.dao;
import java.util.List;
import com.web.blog.model.article_tag.ArticleTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface ArticleTagDao extends JpaRepository<ArticleTag, String>{
    @Transactional(readOnly = true) 
    List<ArticleTag> findArticleTagByArticleId(int articleId);
    @Transactional(readOnly = true) 
    List<ArticleTag> findArticleTagByTagId(int tagId);
}