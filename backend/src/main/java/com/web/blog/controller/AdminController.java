package com.web.blog.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.web.blog.dao.AccuseDao;
import com.web.blog.dao.CommentDao;
import com.web.blog.dao.PostDao;
import com.web.blog.dao.UserDao;
import com.web.blog.model.accuse.Accuse;
import com.web.blog.model.accuse.AccuseListResponse;
import com.web.blog.model.accuse.AccuseRequest;
import com.web.blog.model.accuse.AccuseResponse;
import com.web.blog.model.comment.Comment;
import com.web.blog.model.post.Post;
import com.web.blog.model.user.User;
import com.web.blog.model.user.UserResponse;
import com.web.blog.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = UserResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = UserResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = UserResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = UserResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class AdminController {
    @Autowired
    AccuseDao accuseDao;

    @Autowired
    UserDao userDao;

    @Autowired
    PostDao postDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/accuse/create")
    @ApiOperation(value = "신고글 등록")
    public Object create(@RequestBody AccuseRequest req) throws MessagingException, IOException {

        String token = req.getToken();
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmail(jwtuser.getEmail());
        if (userOpt.isPresent()) {
            Accuse accuse = new Accuse(req.getReporter(), req.getDefendant(), req.getAccuseIndex(),
                    req.getAccuseValue(), req.getAccuseKind(), req.getAccuseReason(), req.getAccuseUrl(),
                    req.getAccuseConfirm());

            System.out.println(accuse);
            accuseDao.save(accuse);
            System.out.println("신고글 등록!!");

            return new ResponseEntity<>("신고글 등록", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("로그인 상태를 확인하세요", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/accuse/read")
    @ApiOperation(value = "신고글 목록")
    public Object read(@RequestBody AccuseRequest req) throws MessagingException, IOException {
        String token = req.getToken();
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        if (userOpt.isPresent() && userOpt.get().getGrade() == 0) {
            System.out.println("신고글 목록 출력!!");

            List<Accuse> alist = accuseDao.findAccuseByAccuseConfirm(0);
            AccuseListResponse result = new AccuseListResponse();

            result.accuseList = new LinkedList<>();
            for (int i = 0; i < alist.size(); i++) {
                Accuse a = alist.get(i);
                result.accuseList.add(new AccuseResponse(a.getAccuseId(), a.getReporter(), a.getDefendant(),
                        a.getAccuseIndex(), a.getAccuseValue(), a.getAccuseKind(), a.getAccuseReason(),
                        a.getAccuseUrl(), a.getAccuseConfirm()));
            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("관리자가 아닙니다.", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/accuse/applyto")
    @ApiOperation(value = "신고 적용하기")
    public Object applyto(@Valid @RequestBody AccuseRequest req) {
        String token = req.getToken();
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());

        if (userOpt.isPresent() && userOpt.get().getGrade() == 0) {

            String nickname = req.getDefendant();
            User user = userDao.getUserByNickname(nickname);
            // 신고를 확인하고 접수 완료하면서 점수 깎고 상태를 1로 만들어주어 디비에는 남기되 화면에는 띄우지 않음
            if (req.getAccuseConfirm() == 1) {
                if (req.getAccuseKind() == 1) { // 1. 허위로 기재된 게시글 -20
                    user.setUserPoint(user.getUserPoint() - 20);
                } else if (req.getAccuseKind() == 2) { // 2. 욕설, 비방, 도배 -50
                    user.setUserPoint(user.getUserPoint() - 50);
                } else if (req.getAccuseKind() == 3) { // 3. 광고 글입니다. -30
                    user.setUserPoint(user.getUserPoint() - 30);
                } else if (req.getAccuseKind() == 4) { // 4. 기타 - 10
                    user.setUserPoint(user.getUserPoint() - 10);
                }
                userDao.save(user);
                System.out.println("점수(user point) 수정");
                Accuse accuse = accuseDao.findAccuseByAccuseId(req.getAccuseId());
                accuse.setAccuseConfirm(1);
                accuseDao.save(accuse);
                System.out.println("신고 게시물 상태 수정");
                // 게시물에 대한 신고를 확실하게 판단하여 완료하면
                if (req.getAccuseIndex() == 1) {
                    Post post = postDao.getPostByArticleId(req.getAccuseValue());
                    post.setCategoryId(99); // 99로 빼준다.
                    postDao.save(post);
                } else if (req.getAccuseIndex() == 2) {
                    Comment comment = commentDao.getCommentByCommentId(req.getAccuseValue()); // 댓글은 바로 삭제한다.
                    // comment.setStatus(1);
                    commentDao.save(comment);
                }

            }
            // 신고가 허위일 경우
            else if (req.getAccuseConfirm() == 2) {
                Accuse accuse = accuseDao.findAccuseByAccuseId(req.getAccuseId());
                accuseDao.delete(accuse);
            }
            // 유저를 비활성화할 경우
            else if (req.getAccuseConfirm() == 3) {
                // user.setStatus(1);
                userDao.save(user);
    
                System.out.println("user 비활성화시켜버리기!!!");
            }
            return new ResponseEntity<>("신고 사항을 접수하였습니다.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("관리자가 아닙니다.", HttpStatus.BAD_REQUEST);
        }
    }

    // @PostMapping("/account/disabled")
    // @ApiOperation(value = "비활성화시키기")
    // public Object disabled(@Valid @RequestBody AccuseRequest req) {
    //     String token = req.getToken();
    //     User jwtuser = jwtService.getUser(token);
    //     Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());

    //     if (userOpt.isPresent() && userOpt.get().getGrade() == 0) {
            // String nickname = req.getDefendant();
            // User user = userDao.getUserByNickname(nickname);
            // user.setStatus(1);
            // userDao.save(user);

            // System.out.println("user 비활성화시켜버리기!!!");

            // return new ResponseEntity<>("유저를 비활성화했습니다.", HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>("관리자가 아닙니다.", HttpStatus.BAD_REQUEST);
    //     }
    // }

}