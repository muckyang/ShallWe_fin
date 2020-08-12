
package com.web.blog.dao;

import java.util.Optional;

import com.web.blog.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserDao extends JpaRepository<User, String> {
    User getUserByUserId(int userId);
    User getUserByEmail(String email);
    User getUserByNickname(String nickname);
    @Transactional(readOnly = true) 
    Optional<User> findUserByNickname(String nickname);
    Optional<User> findUserByEmail(String email);
}
