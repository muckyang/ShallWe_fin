package com.web.blog.dao;

import java.util.List;

import com.web.blog.model.accuse.Accuse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


public interface AccuseDao extends JpaRepository<Accuse, String>{
    @Transactional(readOnly = true) 
    List<Accuse> findAll();
    
}