package com.web.blog.dao;

import java.util.Optional;
import com.web.blog.model.auth.Auth;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthDao extends JpaRepository<Auth, String> {

    Optional<Auth> getAuthByEmail(String email);
    Optional<Auth> getAuthByEmailAndAuthNumber(String email,int auth);


}
