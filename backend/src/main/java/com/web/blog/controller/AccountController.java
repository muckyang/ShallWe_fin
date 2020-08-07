package com.web.blog.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;

import com.web.blog.dao.AuthDao;
import com.web.blog.dao.PostDao;
import com.web.blog.dao.CommentDao;
import com.web.blog.dao.LikeDao;
import com.web.blog.dao.UserDao;
import com.web.blog.model.user.UserResponse;
import com.web.blog.model.auth.Auth;
import com.web.blog.model.comment.Comment;
import com.web.blog.model.like.Like;
import com.web.blog.model.post.Post;
import com.web.blog.model.post.PostResponse;
import com.web.blog.model.user.AuthRequest;
import com.web.blog.model.user.LoginRequest;
import com.web.blog.model.user.SignupRequest;
import com.web.blog.model.user.TokenRequest;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;
import com.web.blog.service.KakaoService;

import org.springframework.web.bind.annotation.RestController;

import org.thymeleaf.spring5.SpringTemplateEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.context.Context;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = UserResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = UserResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = UserResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = UserResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class AccountController {

    @Autowired
    UserDao userDao;
    @Autowired
    PostDao postDao;
    @Autowired
    CommentDao commentDao;
    @Autowired
    LikeDao likeDao;
    @Autowired
    AuthDao authDao;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine TemplateEngine;
    @Autowired
    private JwtService jwtService;
    @Autowired
    KakaoService kakao;

    @RequestMapping("/account/kakaoLogin")
    @ApiOperation(value = "카카오 로그인") // SWAGGER UI에 보이는 이름
    public Object kakaoLogin(@RequestParam("code") String code) {
        String access_Token = kakao.getAccessToken(code);
        System.out.println("controller access_token : " + access_Token);

        System.out.println("카카오 로그인 체크 : " + code);
        return new ResponseEntity<>(code, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/account/login") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "로그인") // SWAGGER UI에 보이는 이름
    public Object login(@RequestBody LoginRequest req) {

        String email = req.getEmail();
        String password = req.getPassword();
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(email, password);

        if (userOpt.isPresent()) {
            System.out.println("로그인 성공  : " + email);
            User user = new User(email, password);
            String token = jwtService.createLoginToken(user);
            return new ResponseEntity<>(token, HttpStatus.OK);
        } else {
            System.out.println("로그인 실패");
            return new ResponseEntity<>("로그인 실패 ", HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/account/sendmail")
    @ApiOperation(value = "인증메일 발송")
    public Object sendmail(@Valid @RequestBody AuthRequest req) throws MessagingException, IOException {

        int authNumber = makeAuthNumber(req);

        try {
            System.out.println("메일 전송 완료!!");
            MimeMessage mailmessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mailmessage, true);
            // 메일 제목 설정
            helper.setSubject("SHALL WE 가입인증 메일 발송");
            // 수신자 설정
            helper.setTo(req.getEmail());
            // 템플릿에 전달할 데이터 설정
            Context context = new Context();
            context.setVariable("auth_number", authNumber);
            // 메일 내용 설정 : 템플릿 프로세스
            String html = TemplateEngine.process("mail-template", context);
            helper.setText(html, true);
            javaMailSender.send(mailmessage);

        } catch (Exception e) {
            System.out.println("메일 발송 실패!");
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
        System.out.println("메일 발송 성공!");
        return new ResponseEntity<>("메일 발송 성공", HttpStatus.OK);

    }

    @PostMapping("/account/signup")
    @ApiOperation(value = "회원가입")
    public Object signup(@Valid @RequestBody SignupRequest req) throws MessagingException, IOException {
        // 이메일, 닉네임 중복처리
        Optional<Auth> OptionalAuth = authDao.getAuthByEmailAndAuthNumber(req.getEmail(), req.getAuthNumber());
        if (OptionalAuth.isPresent()) {
            User user = new User(req.getEmail(), req.getPassword(), req.getName(), req.getNickname(), req.getAddress(),
                    req.getBirthday());
            userDao.save(user);
            System.out.println("가입하기 성공!");
            return new ResponseEntity<>("회원가입 성공 ", HttpStatus.OK);
        } else {
            System.out.println("인증번호 불일치");
            return new ResponseEntity<>("인증번호 불일치 ", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/account/read") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "프로필 조회")
    public Object info(@RequestBody TokenRequest req) {
        String token = req.getToken();
        ResponseEntity<Object> response = null;
        System.out.println("프로필 조회 ! ");
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        int userId = userOpt.get().getUserId();
        if (userOpt.isPresent()) {
            UserResponse result = getUserResponse(userOpt.get());
            result.userId = userOpt.get().getUserId();
            result.email = userOpt.get().getEmail();
            result.userPoint = userOpt.get().getUserPoint();

            result.articleList = new LinkedList<>();
            result.reviewList = new LinkedList<>();
            result.tempList = new LinkedList<>();

            List<Post> plist = postDao.findPostByUserId(userId);

            for (int i = 0; i < plist.size(); i++) {
                Post p = plist.get(i);
                if (p.getTemp() == 1) {
                    result.articleList.add(p);
                } else if (p.getTemp() == 102) {
                    result.reviewList.add(p);
                } else if (p.getTemp() == 0) {
                    result.tempList.add(p);
                }
            }
            List<Like> llist = likeDao.findLikeByUserId(userId);
            result.likeList = new LinkedList<>();
            for (int i = 0; i < llist.size(); i++) {
                result.likeList.add(postDao.getPostByArticleId(llist.get(i).getArticleId()));
            }

            result.articleCount = result.articleList.size();
            result.reviewCount = result.reviewList.size();
            result.likeCount = result.likeList.size();
            result.tempCount = result.tempList.size();
            System.out.println("리턴 !!! ");
            response = new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            response = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @PostMapping("/account/update")
    @ApiOperation(value = "수정하기")
    public Object update(@Valid @RequestBody SignupRequest req) {
        String token = req.getToken();

        // 복호화' ;
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        String message = "";
        System.out.println("수정하기 - token 검색");
        if (userOpt.isPresent()) {
            User user = userDao.getUserByEmail(jwtuser.getEmail());
            user.UserUpdate(req);

            if (user.getNickname() != req.getNickname()) {

                // 닉네임 유효성 검사 해야함

                List<Post> plist = postDao.findPostByUserId(user.getUserId());
                for (int i = 0; i < plist.size(); i++) {
                    Post p = plist.get(i);
                    p.setWriter(req.getNickname());
                    postDao.save(p);
                }
                List<Comment> clist = commentDao.findCommentByUserId(user.getUserId());
                for (int i = 0; i < clist.size(); i++) {
                    Comment c = clist.get(i);
                    c.setWriter(req.getNickname());
                    commentDao.save(c);
                }

            }
            userDao.save(user); // 수정내용 저장
            System.out.println("수정하기 완료!! ");
            User result = user;
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            message = "로그인 된 계정이 없습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/account/delete")
    @ApiOperation(value = "삭제하기")
    public Object delete(@RequestBody TokenRequest request) {
        String token = request.getToken();
        User jwtuser = jwtService.getUser(token);

        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        String message = "";
        if (userOpt.isPresent()) {
            User user = userDao.getUserByEmail(jwtuser.getEmail());

            // FK 연동된것 삭제 /// 완료테이블 있어야 할 것 같음
            // 1. 거래참가자 테이블에 없어야 됨 (존재한다면 alert 표시)
            // 2. like_tabel, comment 테이블에서 해당 id 삭제
            // 3. article 테이블에서 해당아이디 게시물 삭제

            userDao.delete(user);

            System.out.println("계정 삭제 완료 ");
            message = "삭제 완료되었습니다.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            message = "로그인 된 아이디가 없습니다.";
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

    private int makeAuthNumber(AuthRequest req) {
        int authNumber = (int) (Math.random() * 900000) + 100000; // 난수 생성
        Optional<Auth> OptionalAuth = authDao.getAuthByEmail(req.getEmail());
        if (OptionalAuth.isPresent()) {
            Auth auth = OptionalAuth.get();
            auth.setAuthNumber(authNumber);
            authDao.save(auth);
        } else {
            Auth auth = new Auth(req.getEmail(), authNumber);
            authDao.save(auth);
        }
        return authNumber;
    }

    private UserResponse getUserResponse(User user) {
        UserResponse result = new UserResponse(user.getPassword(), user.getName(), user.getNickname(),
                user.getAddress(), user.getBirthday());
        result.articleCount = postDao.findPostByUserIdAndTemp(user.getUserId(), 1).size();
        result.reviewCount = postDao.findPostByUserIdAndCategoryId(user.getUserId(), 102).size();
        result.likeCount = likeDao.findLikeByUserId(user.getUserId()).size();
        result.tempCount = postDao.findPostByUserIdAndTemp(user.getUserId(), 2).size();
        return result;
    }
}