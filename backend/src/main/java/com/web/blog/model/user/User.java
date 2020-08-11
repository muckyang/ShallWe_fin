package com.web.blog.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "user") // 이어줄 테이블명 지정
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    private String email;
    private String password;
    private String name;
    private String nickname;
    private String address;
    private int grade;
    private int userPoint;
    private LocalDate birthday;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
    public void UserUpdate(SignupRequest req) {
        this.password = req.getPassword();
        this.grade= req.getGrade();
        this.nickname = req.getNickname();
        this.address= req.getAddress();
        this.birthday = req.getBirthday();
    }
}