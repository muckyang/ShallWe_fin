
package com.web.blog.dao;

import java.util.Optional;

import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;



public interface UserDao extends JpaRepository<User, String> {
    @Transactional(readOnly = true) 
    Optional<User> findUserByEmailAndPassword(String email, String password);
    User getUserByUserId(int userId);
    User getUserByEmail(String email);
    @Transactional(readOnly = true) 
    Optional<User> findUserByNickname(String nickname);
    @Transactional(readOnly = true) 
    Optional<User> findUserByEmail(String email);
    Optional<User> getUserByEmailAndPassword(String email, String password);
}
