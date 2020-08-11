package com.web.blog.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.web.blog.dao.AccuseDao;
import com.web.blog.dao.UserDao;
import com.web.blog.model.accuse.Accuse;
import com.web.blog.model.accuse.AccuseListResponse;
import com.web.blog.model.accuse.AccuseRequest;
import com.web.blog.model.accuse.AccuseResponse;
import com.web.blog.model.user.TokenRequest;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import io.swagger.annotations.ApiOperation;

public class AdminController {
    @Autowired
    AccuseDao accuseDao;

    @Autowired
    UserDao userDao;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/accuse/create")
    @ApiOperation(value = "신고글 등록")
    public Object create(@RequestBody AccuseRequest req, @PathVariable int temp)
            throws MessagingException, IOException {
        String token = req.getToken();
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        if (userOpt.isPresent()) {
            Accuse accuse = new Accuse(req.getReporter(), req.getDefendant(), req.getAccuseKind(),
                    req.getAccuseReason(), req.getAccuseUrl(), req.getAccuseConfirm());

            accuseDao.save(accuse);

            System.out.println("신고글 등록!!");
  
            return new ResponseEntity<>(accuse, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("로그인 상태를 확인하세요 ", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/accuse/read")
    @ApiOperation(value = "신고글 목록")
    public AccuseListResponse read(@RequestBody TokenRequest req) throws MessagingException, IOException {
        System.out.println("신고글 목록 출력!!");
        String token = req.getToken();
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        // if (userOpt.isPresent() && userOpt.get().getNickname().equals("admin")) {
            if (userOpt.isPresent()) {
            List<Accuse> alist = accuseDao.findAll();
            AccuseListResponse result = new AccuseListResponse();

            result.accuseList = new LinkedList<>();
            for (int i = 0; i < alist.size(); i++) {
                Accuse a = alist.get(i);

                result.accuseList.add(new AccuseResponse(a.getAccuseId(), a.getReporter(), a.getDefendant(),
                        a.getAccuseKind(), a.getAccuseReason(), a.getAccuseUrl(), a.getAccuseConfirm()));
            }
            return result;
        } else {
            return null;
        }
    }


    @PostMapping("/accuse/applyto")
    @ApiOperation(value = "신고 적용하기")
    public Object applyto(@Valid @RequestBody AccuseRequest request) {
        String nickname = request.getDefendant();
        Optional<User> userOpt = userDao.findUserByNickname(nickname);
        User user = userOpt.get();
        
        if(request.getAccuseKind() == 1){ // 1. 허위로 기재된 게시글 -20
            user.setUserPoint(user.getUserPoint()-20);
        }
        else if(request.getAccuseKind() == 2){ // 2. 욕설, 비방, 도배 -50
            user.setUserPoint(user.getUserPoint()-50);
        }
        else if(request.getAccuseKind() == 3){ // 3. 광고 글입니다. -30
            user.setUserPoint(user.getUserPoint()-30);
        }
        
        userDao.save(user);

        System.out.println("점수(user point) 수정");
        
        return new ResponseEntity<>("신고 사항을 접수하였습니다.", HttpStatus.OK);
    }

    @PostMapping("/accuse/deleteuser")
    @ApiOperation(value = "탈퇴시키기")
    public Object deleteuser(@Valid @RequestBody AccuseRequest request) {
        String nickname = request.getDefendant();
        Optional<User> userOpt = userDao.findUserByNickname(nickname);
        User user = userOpt.get();
        userDao.delete(user);

        System.out.println("user 탈퇴시켜버리기!!!");
       
        return new ResponseEntity<>("유저를 틸퇴시켰습니다.", HttpStatus.OK);
    }

}