package com.web.blog.model.post;



import java.time.LocalDateTime;
import java.util.List;

import com.web.blog.model.comment.CommentRes;
import com.web.blog.model.participant.Participant;

import io.swagger.annotations.ApiModelProperty;


public class PostResponse {
     public PostResponse(){
          
     }
     public PostResponse(int articleId,int categoryId,int userId,String title,String address,
     int minPrice,int sumPrice,int likeNum, int commentNum, String description, String writer, String urlLink,String image,
     List<String> tags,   int temp,LocalDateTime endTime, String timeAgo){
          super();
          this.articleId = articleId;
          this.categoryId = categoryId;
          this.userId =userId;
          this.title = title;
          this.address = address;
          this.minPrice = minPrice;
          this.sumPrice=sumPrice;
          this.likeNum = likeNum;
          this.commentNum=commentNum;
          this.description = description;
          this.writer = writer;
          this.urlLink = urlLink;
          this.image = image;
          this.tags = tags;
          this.temp = temp;
          this.endTime = endTime;
          this.timeAgo = timeAgo;
     }
     // post
     @ApiModelProperty(value = "articleId", position = 1)
     public int articleId;
     @ApiModelProperty(value = "categoryId", position = 2)
     public int categoryId;
     @ApiModelProperty(value = "userId", position = 3)
     public int userId;
     @ApiModelProperty(value = "title", position = 4)
     public String title;
     @ApiModelProperty(value = "address", position = 5)
     public String address;
     @ApiModelProperty(value = "description", position = 5)
     public String description;
     @ApiModelProperty(value = "writer", position = 6)
     public String writer;
     @ApiModelProperty(value = "temp", position = 7)
     public int temp;
     @ApiModelProperty(value = "minPrice", position = 8)
     public int minPrice;
     @ApiModelProperty(value = "endTime", position = 9)
     public LocalDateTime endTime;
     @ApiModelProperty(value = "urlLink", position = 10)
     public String urlLink;
     @ApiModelProperty(value = "image", position = 11)
     public String image;
     @ApiModelProperty(value = "likeNum", position = 12)
     public int likeNum;
     @ApiModelProperty(value = "commentNum", position = 13)
     public int commentNum;
     @ApiModelProperty(value = "isLiked", position = 14)
     public boolean isLiked;
     @ApiModelProperty(value = "sumPrice", position = 15)
     public int sumPrice;
     @ApiModelProperty(value = "tags", position = 16)
     public List<String> tags;
     @ApiModelProperty(value = "commentList", position = 17)
     public List<CommentRes> commentList;
     @ApiModelProperty(value = "timeAgo", position = 18)
     public String timeAgo;

     @ApiModelProperty(value = "partList", position = 19)
     public List<Participant> partList;
     @ApiModelProperty(value = "status", position = 20)
     public int status;

}