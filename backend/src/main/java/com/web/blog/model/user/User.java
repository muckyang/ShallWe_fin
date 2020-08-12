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
    private String nickname;
    private String address;
    @Column(name ="profile_image")
    private String profileImage;
    @Column(name = "introduce")
    private String introduce;
    private int grade;
    private int status;
    private int userPoint;
    private LocalDate birthday;
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    public User(String email, String nickname){
        this.email= email;
        this.nickname=nickname;
    }

}