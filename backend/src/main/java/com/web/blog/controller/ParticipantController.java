package com.web.blog.controller;

import java.io.IOException;
import java.util.List;

import java.util.Optional;
import javax.mail.MessagingException;
import javax.validation.Valid;
import com.web.blog.dao.ParticipantDao;
import com.web.blog.dao.PostDao;
import com.web.blog.dao.UserDao;
import com.web.blog.model.comment.CommentResponse;
import com.web.blog.model.participant.ParticipantRequest;
import com.web.blog.model.participant.ParticipantListResponse;
import com.web.blog.model.post.Post;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;
import com.web.blog.model.participant.Participant;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = CommentResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = CommentResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = CommentResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = CommentResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class ParticipantController {

    @Autowired
    PostDao postDao;

    @Autowired
    ParticipantDao participantDao;

    @Autowired
    UserDao userDao;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/participant/create")
    @ApiOperation(value = "참가자등록")
    public Object create(@Valid @RequestBody ParticipantRequest request) throws MessagingException, IOException {
        System.out.println(request.getArticleId());
        String token = request.getToken();

        // 게시자는 바로 참가자로 등록됨
        // 만약 게시자가 참가자 버튼을 눌렀을 때 안되도록 하기
        Optional<Participant> partiOpt = participantDao.getParticipantByUserIdAndArticleId(request.getUserId(),request.getArticleId());
        if(partiOpt.isPresent()){//이 게시물(ArticleId)에 해당하는 유저아이디(UserId)가 Participant DB에 있으면
            String message = "이미 참가되셨습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        String title = request.getTitle();
        int price = request.getPrice();
        if (price < 0) {
            String message = "0원보다 값이 작습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        String description = request.getDescription();
        User jwtuser = jwtService.getUser(token);

        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        if (userOpt.isPresent()) {
            Participant participant = new Participant();
            participant.setUserId(userOpt.get().getUserId()); // token값으로 id 받아옴
            participant.setArticleId(request.getArticleId());
            participant.setTitle(title);
            participant.setPrice(price);
            participant.setWriter(userOpt.get().getNickname());
            participant.setDescription(description);
            participantDao.save(participant);// 참가자 DB에 등록 완료

            // 게시물 sum_price에 더하기
            Post post = postDao.getPostByArticleId(request.getArticleId());// 해당 구매게시물을 얻어옴
            int sumPrice = post.getSumPrice();// sumPrice를 얻어옴
            sumPrice = sumPrice + price;// 참가자의 가격을 더해줌
            post.setSumPrice(sumPrice);
            postDao.save(post);// 다시 DB에 넣어줌

            return new ResponseEntity<>("참가자 등록", HttpStatus.OK);
        } else {
            String message = "로그인 상태를 확인하세요";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/participant/read/{articleId}")
    @ApiOperation(value = "참가자 목록")
    public Object read(@PathVariable int articleId) throws MessagingException, IOException {

        System.out.println("참가자 목록 출력!!");

        List<Participant> plist;

        plist = participantDao.findParticipantByArticleId(articleId);
        ParticipantListResponse result = new ParticipantListResponse();
        result.participantList = plist;

        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @PostMapping("/participant/update")
    @ApiOperation(value = "참가자 수정")
    public Object update(@Valid @RequestBody ParticipantRequest request) {

        int articleId = request.getArticleId();// 해당 클릭의 No를 가져와서
        int userId = request.getUserId();// 해당 클릭의 No를 가져와서
        Optional<Participant> partOpt = participantDao.getParticipantByUserIdAndArticleId(userId,articleId);// 참가자의 해당 정보를 가져옴
        int old_price = partOpt.get().getPrice();// 원래 참가자의 가격정보
        int new_price = request.getPrice();// 새로운 참가자의 가격정보

        if (new_price < 0) {
            String message = "수정할 값이 0원보다 값이 작습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
        int difference = (new_price - old_price);// article가격 추가될 것

        // 게시물 sum_price에 수정
        Post post = postDao.getPostByArticleId(request.getArticleId());// 해당 구매게시물을 얻어옴
        int sumPrice = post.getSumPrice();// sumPrice를 얻어옴
        sumPrice = (sumPrice + difference);// 참가자의 수정 가격을 더해줌
        post.setSumPrice(sumPrice);
        postDao.save(post);// 다시 DB에 넣어줌

        partOpt.get().setTitle(request.getTitle());
        partOpt.get().setPrice(new_price);
        partOpt.get().setDescription(request.getDescription());
        participantDao.save(partOpt.get());// 다시 세팅해서 참가자 DB에 넣어줌

        System.out.println(partOpt.get().getNo()+"번째 참가자 수정 완료");
        
        return new ResponseEntity<>("참가자 수정 완료", HttpStatus.OK);
    }

    @PostMapping("/participant/delete/{no}")
    @ApiOperation(value = "참가자삭제하기")
    public Object delete(@Valid @PathVariable int no) {
        Participant participant = participantDao.getParticipantByNo(no);// 참가자의 해당 정보를 가져옴
        int price = participant.getPrice();

        // 게시물 sum_price에 수정
        Post post = postDao.getPostByArticleId(participant.getArticleId());// 해당 구매게시물을 얻어옴
        int sumPrice = post.getSumPrice();// sumPrice를 얻어옴
        sumPrice = (sumPrice - price);// 참가자의 가격을 빼줌
        post.setSumPrice(sumPrice);
        postDao.save(post);// 다시 DB에 넣어줌

        participantDao.delete(participant);
        System.out.println("참가자 삭제하기!!");
        CommentResponse result = new CommentResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}