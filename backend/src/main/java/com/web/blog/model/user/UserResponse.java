package com.web.blog.model.user;

import java.time.LocalDate;
import java.util.List;

import com.web.blog.model.post.Post;

import io.swagger.annotations.ApiModelProperty;

public class UserResponse {
    // user

    @ApiModelProperty(value = "userId", position = 1)
    public int userId;
    @ApiModelProperty(value = "password", position = 2)
    public String password;
    @ApiModelProperty(value = "email", position = 3)
    public String email;
    @ApiModelProperty(value = "name", position = 4)
    public String name;
    @ApiModelProperty(value = "nickname", position = 5)
    public String nickname;
    @ApiModelProperty(value = "address", position = 6)
    public String address;
    @ApiModelProperty(value = "birthday", position = 7)
    public LocalDate birthday;
    @ApiModelProperty(value = "userPoint", position = 8)
    public int userPoint;

    @ApiModelProperty(value = "articleCount", position = 9)
    public int articleCount;

    @ApiModelProperty(value = "reviewCount", position = 10)
    public int reviewCount;

    @ApiModelProperty(value = "likeCount", position = 11)
    public int likeCount;

    @ApiModelProperty(value = "tempCount", position = 12)
    public int tempCount;


    @ApiModelProperty(value = "articleList", position = 13)
    public List<Post> articleList;

    @ApiModelProperty(value = "reviewList", position = 14)
    public List<Post> reviewList;

    @ApiModelProperty(value = "likeList", position = 15)
    public List<Post> likeList;

    @ApiModelProperty(value = "tempList", position = 16)
    public List<Post> tempList;
    @ApiModelProperty(value = "grade", position = 17)
    public int grade;
    public UserResponse(String password, String name, String nickname, String address, LocalDate birthday) {
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.address = address;
        this.birthday = birthday;
    }

    public UserResponse(int userId ,String password,String email,  String name, String nickname, String address, LocalDate birthday,
    int userPoint) {
        this.userId = userId;
        this.password = password;
        this.name = name;
        this.nickname = nickname;
        this.address = address;
        this.userPoint = userPoint;
        this.birthday = birthday;
    }

}