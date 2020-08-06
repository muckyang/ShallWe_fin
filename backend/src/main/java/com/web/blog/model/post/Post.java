package com.web.blog.model.post;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Table(name = "article") // 이어줄 테이블명 지정
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int articleId;
    private int categoryId;
    private int userId;
    private String title;
    private String writer;
    private String address;
    private String description;
    private int minPrice;
    private int sumPrice;
    private int likeNum;
    private int commentNum;
    private String urlLink;
    private String image;
    private int temp;
    private LocalDateTime endTime;
    // 생성시간은 자동생성
    @Column(insertable = false, updatable = false)
    private LocalDateTime createTime;

    public Post(int cateId, int userId, String title, String writer, String addr, String des, int minPrice,
            int sumPrice, String image, int temp, LocalDateTime endTime) {
                this.categoryId = cateId;
                this.userId = userId;
                this.title = title;
                this.writer = writer;
                this.address = addr;
                this.description = des;
                this.minPrice = minPrice;
                this.sumPrice = sumPrice;
                
                this.image = image;
                this.temp = temp;
                this.endTime = endTime;
          

    }

}