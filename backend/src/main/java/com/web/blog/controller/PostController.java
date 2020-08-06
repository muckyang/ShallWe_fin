package com.web.blog.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.StringTokenizer;

import javax.mail.MessagingException;
import javax.validation.Valid;

import com.web.blog.dao.ArticleTagDao;
import com.web.blog.dao.CommentDao;
import com.web.blog.dao.LikeDao;
import com.web.blog.dao.ParticipantDao;
// import com.web.blog.dao.LikeDao;
import com.web.blog.dao.PostDao;
import com.web.blog.dao.TagDao;
import com.web.blog.dao.UserDao;
import com.web.blog.model.post.PostListResponse;
import com.web.blog.model.post.PostResponse;
import com.web.blog.model.post.PostSearchRequest;
import com.web.blog.model.tag.Tag;
import com.web.blog.model.like.Like;
import com.web.blog.model.participant.Participant;
import com.web.blog.model.post.PostRequest;
import com.web.blog.model.post.Post;
import com.web.blog.model.user.TokenRequest;
import com.web.blog.model.user.User;
import com.web.blog.service.JwtService;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.beans.factory.annotation.Autowired;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiOperation;

import com.web.blog.model.comment.Comment;
import com.web.blog.model.comment.CommentRes;

@ApiResponses(value = { @ApiResponse(code = 401, message = "Unauthorized", response = PostResponse.class),
        @ApiResponse(code = 403, message = "Forbidden", response = PostResponse.class),
        @ApiResponse(code = 404, message = "Not Found", response = PostResponse.class),
        @ApiResponse(code = 500, message = "Failure", response = PostResponse.class) })

@CrossOrigin(origins = { "*" })
@RestController
public class PostController {
    @Autowired
    PostDao postDao;

    @Autowired
    UserDao userDao;

    @Autowired
    LikeDao likeDao;

    @Autowired
    CommentDao commentDao;

    @Autowired
    ArticleTagDao articleTagDao;

    @Autowired
    TagDao tagDao;

    @Autowired
    ParticipantDao participantDao;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/post/create/{temp}")
    @ApiOperation(value = "게시글 및 임시글 등록")
    public Object create(@Valid @RequestBody PostRequest req, @PathVariable int temp)
            throws MessagingException, IOException {
        String token = req.getToken();
        String endDate = req.getEndDate();
        String endT = req.getEndTime();
        LocalDateTime endTime=null; 
        if(endDate!=null||endT!=null){
            endTime = LocalDateTime.parse(endDate + " " + endT,
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        System.out.println(token);
        User jwtuser = jwtService.getUser(token);
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        if (userOpt.isPresent()) {
            if (temp == 0) {// 임시저장

                Post post = new Post(req.getCategoryId(), userOpt.get().getUserId(), req.getTitle(),
                        userOpt.get().getNickname(), req.getAddress(), req.getDescription(), req.getMinPrice(),
                        req.getMyPrice(), req.getImage(), temp, endTime);
                // post.setUrlLink(req.getUrlLink());
                postDao.save(post);
                System.out.println("임시저장!!");

                return new ResponseEntity<>("임시저장 완료", HttpStatus.OK);
            } else if (temp == 1) {
                Post post = new Post(req.getCategoryId(), userOpt.get().getUserId(), req.getTitle(),
                        userOpt.get().getNickname(), req.getAddress(), req.getDescription(), req.getMinPrice(),
                        req.getMyPrice(), req.getImage(), temp, endTime);
                System.out.println("Min" + req.getMinPrice());
                System.out.println("My" + req.getMyPrice());

                postDao.save(post);
                int artiId = post.getArticleId();
                System.out.println("게시물 등록!!");

                // 게시물 등록과 동시에 참가자 등록하기
                int myPrice = req.getMyPrice();
                if (myPrice <= 0) {
                    String message = "price 값이 유효하지 않습니다.";
                    return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
                }
                String def_mes = "게시자 본인입니다.";
                Participant participant = new Participant();
                participant.setUserId(userOpt.get().getUserId()); // token값으로 id 받아옴
                participant.setArticleId(artiId);
                System.out.println(post.getTitle() + " " + artiId);
                participant.setTitle(def_mes);
                participant.setPrice(myPrice);
                participant.setDescription(def_mes);
                participantDao.save(participant);// 참가자 DB에 등록 완료

                // 게시물 sum_price에 더하기
                post = postDao.getPostByArticleId(artiId);// 해당 구매게시물을 얻어옴
                post.setSumPrice(myPrice);
                postDao.save(post);// 다시 DB에 넣어줌

                System.out.println("참가자 등록!!");

                // 태그 등록
                String[] tags = req.getTags();// 태그 내용
                // artiId 게시물 PK
                for (int i = 0; i < tags.length; i++) {
                    Tag tag = new Tag();
                    tag.setName(tags[i]);
                    tag.setArticleId(artiId);
                    tagDao.save(tag);
                }

                return new ResponseEntity<>("태그 등록 및 참가자 등록 및 게시물 등록", HttpStatus.OK);

            } else if (temp == 2) { // 자유게시물
                Post post=new Post();
                post.setUserId(userOpt.get().getUserId());
                post.setCategoryId(req.getCategoryId());
                post.setTitle(req.getTitle());
                post.setWriter(userOpt.get().getNickname());
                post.setDescription(req.getDescription());
                post.setImage(req.getImage());
                post.setTemp(temp);
                postDao.save(post);

                int artiId = post.getArticleId();
                // 태그 등록
                String[] tags = req.getTags();// 태그 내용
                // artiId 게시물 PK
                for (int i = 0; i < tags.length; i++) {
                    Tag tag = new Tag();
                    tag.setName(tags[i]);
                    tag.setArticleId(artiId);
                    tagDao.save(tag);
                }
                return new ResponseEntity<>("자유게시물 등록", HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>("로그인 상태를 확인하세요 ", HttpStatus.BAD_REQUEST);

        }
    }

    @PostMapping("/post/read/{temp}/{categoryId}") // temp 값 int 로 변경예정
    @ApiOperation(value = "게시글 및 임시글 목록")
    public Object read(@RequestBody TokenRequest request, @PathVariable int temp, @PathVariable int categoryId)
            throws MessagingException, IOException {
        if (temp == 0) {
            System.out.println("임시글 목록 출력!!");
            String token = request.getToken();
            User jwtuser = jwtService.getUser(token);
            Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());

            String writer = userOpt.get().getName();
            List<Post> plist = postDao.findPostByTempAndWriter(temp, writer);

            PostListResponse result = new PostListResponse();
            result.postList = new LinkedList<>();
            for (int i = 0; i < plist.size(); i++) {
                Post p = plist.get(i);

                result.postList.add(new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                        p.getAddress(), p.getMinPrice(), p.getSumPrice(), p.getDescription(), p.getWriter(),
                        p.getUrlLink(), p.getImage(), temp, p.getEndTime() , BeforeCreateTime(p.getCreateTime())));
                        

            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (temp == 1) {
            List<Post> plist;
            System.out.println("게시물 목록 출력!!");
            if (categoryId == 0)// 전체 게시물 출력
                plist = postDao.findPostByTemp(temp);
            else
                plist = postDao.findPostByTempAndCategoryId(temp, categoryId);

            PostListResponse result = new PostListResponse();
            result.postList = getPostList(plist, temp); //게시물 목록 및 각 게시물의 좋아요 댓글 수

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (temp == 2) { // 자유게시판
            List<Post> plist;
            if (categoryId == 100)// 전체 게시물 출력
                plist = postDao.findPostByTemp(temp);
            else
                plist = postDao.findPostByTempAndCategoryId(temp, categoryId);
            
            PostListResponse result = new PostListResponse();
            result.postList = getPostList(plist, temp); //게시물 목록 및 각 게시물의 좋아요 댓글 수

            System.out.println("자유글 목록 출력!!");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post/search/{temp}/{categoryId}")
    @ApiOperation(value = "구매게시판 검색 목록")
    public Object search(@RequestBody PostSearchRequest request, @PathVariable int temp, @PathVariable int categoryId)
            throws MessagingException, IOException {

        String subject = request.getSubject();
        String word = request.getWord();
        List<Post> plist = new LinkedList<>();
        if (subject.equals("title")) {
            word = "%" + word + "%";
            System.out.println("title로 검색");
           
            if (categoryId == 0)
                plist = postDao.findPostByTempAndTitleLike(temp, word);
            else
                plist = postDao.findPostByTempAndCategoryIdAndTitleLike(temp, categoryId, word);
            PostListResponse result = new PostListResponse();
            result.postList = getPostList(plist, temp);

            System.out.println("title로 검색 확인");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (subject.equals("writer")) {
            word = "%" + word + "%";
           
            if (categoryId == 0)
                plist = postDao.findPostByTempAndWriterLike(temp, word);
            else
                plist = postDao.findPostByTempAndCategoryIdAndWriterLike(temp, categoryId, word);
            PostListResponse result = new PostListResponse();
            result.postList = getPostList(plist, temp);

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else if (subject.equals("tag")) {
            List<Tag> taglist = tagDao.findTagByName(word);// 해당 태그의 tagId가져옴
            PostListResponse result = new PostListResponse();
            for (int i = 0; i < taglist.size(); i++) {
                int aId = taglist.get(i).getArticleId();
                Optional<Post> article = postDao.findPostByArticleIdAndTempAndCategoryId(aId, temp, categoryId);
                plist.add(article.get());
                // result.postList.add(new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                // p.getAddress(), p.getMinPrice(), p.getSumPrice(), p.getDescription(), p.getWriter(),
                // p.getUrlLink(), p.getImage(), p.getTemp(), p.getEndTime()));
                
                // List<Like> llist = likeDao.findLikeByArticleId(p.getArticleId());
                // List<Comment> clist = commentDao.findCommentByArticleId(p.getArticleId());
                // int likenum = llist.size();
                // int commentnum = clist.size();
                // System.out.println("list return success");
                // System.out.println(likenum);
                
                // result.postList.get(i).likenum = likenum;
                // result.postList.get(i).commentnum = commentnum;
            }


            result.postList = getPostList(plist,temp);
            

            return new ResponseEntity<>(result, HttpStatus.OK);
        }else if(subject.equals("address")){
            
            System.out.println("address로 검색");
            List<String> addList = new ArrayList<>();
            String pAddress = request.getAddress();
            StringTokenizer st = new StringTokenizer(pAddress);
            while(st.hasMoreTokens()){
                addList.add(st.nextToken());
            }
            

            if (categoryId == 0)
                if(addList.size() == 1){
                    plist = postDao.findPostByAddressLike(addList.get(0));
                }
                else if(addList.size() == 2){
                    plist = postDao.findPostByAddressLikeAndAddressLike(addList.get(0), addList.get(1));
                }
                else if(addList.size() == 3){
                    plist = postDao.findPostByAddressLikeAndAddressLikeAndAddressLike(addList.get(0), addList.get(1), addList.get(2));
                }
                else if(addList.size() == 4){
                    plist = postDao.findPostByAddressLikeAndAddressLikeAndAddressLikeAndAddressLike(addList.get(0), addList.get(1), addList.get(2), addList.get(3));
                }

                
            else{
                if(addList.size()==1){
                    plist = postDao.findPostByTempAndCategoryIdAndAddressLike(temp, categoryId, addList.get(0));
                }
                else if(addList.size() == 2){
                    plist = postDao.findPostByTempAndCategoryIdAndAddressLikeAndAddressLike(temp, categoryId, addList.get(0), addList.get(1));
                }
                else if(addList.size() == 3){
                    plist = postDao.findPostByTempAndCategoryIdAndAddressLikeAndAddressLikeAndAddressLike(temp, categoryId, addList.get(0), addList.get(1), addList.get(2));
                }
                else if(addList.size() == 4){
                    plist = postDao.findPostByTempAndCategoryIdAndAddressLikeAndAddressLikeAndAddressLikeAndAddressLike(temp, categoryId, addList.get(0), addList.get(1), addList.get(2), addList.get(3));
                }
            }
            PostListResponse result = new PostListResponse();
            result.postList = getPostList(plist, temp);

            System.out.println("address로 검색 확인");
            return new ResponseEntity<>(result, HttpStatus.OK);
        } 
        else {
            return new ResponseEntity<>("temp 해당 없음", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/post/detail/{articleId}") // SWAGGER UI에 보이는 REQUEST명
    @ApiOperation(value = "게시물상세보기") // SWAGGER UI에 보이는 이름
    public Object detail(@PathVariable int articleId, @RequestBody TokenRequest request) {
        // 토큰 받아오면 그 토큰으로 유효성 검사 후 uid 받아와서 좋아요 한지 여부 확인

        System.out.println("상세보기 들어옴 "  +articleId);
        Optional<Post> postOpt = postDao.findPostByArticleId(articleId);
        Post p = postOpt.get();

        if (postOpt.isPresent()) {
            String token = request.getToken();
            User jwtuser = jwtService.getUser(token);
      
            Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
            PostResponse result = new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                    p.getAddress(), p.getMinPrice(), p.getSumPrice(), p.getDescription(), p.getWriter(), p.getUrlLink(),
                    p.getImage(), p.getTemp(), p.getEndTime(), BeforeCreateTime(p.getCreateTime()));
      
                    List<Like> llist = likeDao.findLikeByArticleId(p.getArticleId());
                    List<Comment> clist = commentDao.findCommentByArticleId(p.getArticleId());
                    int likenum = llist.size();
                    int commentnum = clist.size();
                    System.out.println("list return success");
                    System.out.println(likenum);
        
                    result.likenum = likenum;
                    result.commentnum = commentnum;
        
      
                    // 이 게시물에 해당되는 태그는 다 보내기
                    List<Tag> tlist = tagDao.findTagByArticleId(articleId);
                    String[] tags = new String[tlist.size()];
                    for (int i = 0; i < tags.length; i++) {
                        tags[i] = tlist.get(i).getName();
                    }
                    result.tags = tags;
                    
      
            if (userOpt.isPresent()) {// 로그인 상태일때

                Optional<Like> isILiked = likeDao.findLikeByUserIdAndArticleId(userOpt.get().getUserId(), articleId);
                if (isILiked.isPresent()) // 좋아요 한 경우
                    result.isLiked = true;
                else // 좋아요 하지 않은경우
                    result.isLiked = false;

                System.out.println("게시물 상세보기 !!!");
            } else {
                System.out.println("비로그인 / 로그인 여부 확인 !!!");
                result.isLiked = false;
            }              

            result.commentList = new LinkedList<>(); // 댓글 리스트 가져옴
            for (int i = 0; i < clist.size(); i++) {
                User user = userDao.getUserByUserId(clist.get(i).getUserId());
                String nickname = user.getNickname();

                result.commentList.add(new CommentRes(clist.get(i).getCommentId(), clist.get(i).getArticleId(),
                        clist.get(i).getUserId(), nickname, clist.get(i).getContent(), clist.get(i).getCreateTime()));

                System.out.println(nickname);
            }

            return new ResponseEntity<>(result, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/post/update/{temp}")
    @ApiOperation(value = "게시글 및 임시글 수정")
    public Object update(@Valid @RequestBody PostRequest req, @PathVariable int temp) {
        Optional<Post> postOpt = postDao.findPostByArticleId(req.getArticleId());
        Post p = postOpt.get();
        String token = req.getToken();
        User jwtuser = jwtService.getUser(token);
        int userId;
        Optional<User> userOpt = userDao.findUserByEmailAndPassword(jwtuser.getEmail(), jwtuser.getPassword());
        if (userOpt.isPresent()) {
            userId = userOpt.get().getUserId();
        } else {
            return new ResponseEntity<>("로그인 상태를 확인하세요(token값 유효하지 않음)", HttpStatus.BAD_REQUEST);
        }

        if (userOpt.get().getUserId() != p.getUserId()) {
            return new ResponseEntity<>("로그인한 회원과 게시자가 일치하지 않습니다.", HttpStatus.NOT_FOUND);
        }

        String endDate = req.getEndDate();
        String endT = req.getEndTime();
        LocalDateTime endTime=null; 
        if(endDate!=null||endT!=null){
        endTime = LocalDateTime.parse(endDate + " " + endT,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }

        if (temp == 0) {
            Post post = postDao.getPostByArticleId(req.getArticleId());
            post.setCategoryId(req.getCategoryId());
            post.setTitle(req.getTitle());
            post.setAddress(req.getAddress());
            post.setDescription(req.getDescription());
            post.setMinPrice(req.getMinPrice());
            post.setUrlLink(req.getUrlLink());
            post.setImage(req.getImage());
            post.setTemp(temp);
            post.setEndTime(endTime);
            // post.setBillImage(request.getBillImage());

            System.out.println(post.getArticleId());
            System.out.println();

            postDao.save(post);

            System.out.println("임시글 수정");

            return new ResponseEntity<>("임시글 수정 완료", HttpStatus.OK);
        } else if (temp == 1) {

            Post post = postDao.getPostByArticleId(req.getArticleId());

            post.setCategoryId(req.getCategoryId());
            post.setTitle(req.getTitle());
            post.setAddress(req.getAddress());
            post.setMinPrice(req.getMinPrice());
            post.setDescription(req.getDescription());
            post.setUrlLink(req.getUrlLink());
            post.setImage(req.getImage());
            post.setTemp(temp);
            post.setEndTime(endTime);
            postDao.save(post);

            // 게시물 등록과 동시에 참가자 등록하기
            int myPrice = req.getMyPrice();
            if (myPrice < 0) {
                String message = "0원보다 값이 작습니다.";
                return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
            }

            Participant participant = new Participant();

            Optional<Participant> partOpt = participantDao.getParticipantByUserIdAndArticleId(userId,
                    req.getArticleId());
            if (partOpt.isPresent()) {// 이미 게시된 글이라면
                participant = partOpt.get();
            }

            int old_price = participant.getPrice();// 원래 참가자의 가격정보

            participant.setArticleId(req.getArticleId());
            participant.setPrice(myPrice);
            participantDao.save(participant);// 참가자 DB에 등록 완료

            // 게시물 sum_price에 더하기
            post = postDao.getPostByArticleId(req.getArticleId());// 해당 구매게시물을 얻어옴
            int sumPrice = post.getSumPrice();// sumPrice를 얻어옴
            sumPrice = sumPrice + (myPrice - old_price);// 참가자의 가격을 더해줌
            post.setSumPrice(sumPrice);
            postDao.save(post);// 다시 DB에 넣어줌

            // 태그 삭제
            List<Tag> tList = tagDao.findTagByArticleId(req.getArticleId());
            int atsize = tList.size();
            for (int i = 0; i < atsize; i++) {
                Tag l = tList.get(i);
                tagDao.delete(l);
            }
            // 태그 수정
            String[] tags = req.getTags();// 태그 내용
            for (int i = 0; i < tags.length; i++) {
                Tag tag = new Tag();
                tag.setName(tags[i]);
                tag.setArticleId(req.getArticleId());
                tagDao.save(tag);
            }

            System.out.println(post.getArticleId() + "번째 게시물 수정 완료 ");
            return new ResponseEntity<>("게시물 수정 완료 ", HttpStatus.OK);
        } else if (temp == 2) { // 자유게시판

            Post post = postDao.getPostByArticleId(req.getArticleId());
            post.setUserId(userOpt.get().getUserId());
            post.setCategoryId(req.getCategoryId());
            post.setTitle(req.getTitle());
            post.setWriter(userOpt.get().getNickname());
            post.setDescription(req.getDescription());
            post.setImage(req.getImage());
            post.setTemp(temp);
            postDao.save(post);
            // 태그 삭제
            List<Tag> tList = tagDao.findTagByArticleId(req.getArticleId());
            int atsize = tList.size();
            for (int i = 0; i < atsize; i++) {
                Tag l = tList.get(i);
                tagDao.delete(l);
            }
            // 태그 수정
            String[] tags = req.getTags();// 태그 내용
            for (int i = 0; i < tags.length; i++) {
                Tag tag = new Tag();
                tag.setName(tags[i]);
                tag.setArticleId(req.getArticleId());
                tagDao.save(tag);
            }

            System.out.println(post.getArticleId() + "번째 자유글 수정 완료 ");
            return new ResponseEntity<>("자유글 수정 완료 ", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/post/delete/{articleId}")
    @ApiOperation(value = "삭제하기")
    public Object delete(@Valid @PathVariable int articleId) {

        Post post = postDao.getPostByArticleId(articleId);
        // Optional<Post> postOpt = postDao.findPostByArticleId(articleId);
        // Post post = postOpt.get();
        // String token = req.getToken();
        // User jwtuser = jwtService.getUser(token);
        // int userId;

        // Optional<User> userOpt =
        // userDao.findUserByEmailAndPassword(jwtuser.getEmail(),
        // jwtuser.getPassword());
        // if (userOpt.isPresent()) {
        // userId = userOpt.get().getUserId();
        // } else {
        // return new ResponseEntity<>("로그인 상태를 확인하세요(token값 유효하지 않음)",
        // HttpStatus.BAD_REQUEST);
        // }

        // if(userOpt.get().getUserId() != post.getUserId()){
        // return new ResponseEntity<>("로그인한 회원과 게시자가 일치하지 않습니다.",
        // HttpStatus.NOT_FOUND);
        // }

        // 댓글 삭제
        List<Comment> commentList = commentDao.findCommentByArticleId(articleId);
        int csize = commentList.size();
        for (int i = 0; i < csize; i++) {
            Comment c = commentList.get(i);
            commentDao.delete(c);
        }
        /// 좋아요 삭제
        List<Like> likeList = likeDao.findLikeByArticleId(articleId);
        int lsize = likeList.size();
        for (int i = 0; i < lsize; i++) {
            Like l = likeList.get(i);
            likeDao.delete(l);
        }
        // 태그 삭제
        List<Tag> tList = tagDao.findTagByArticleId(articleId);
        int atsize = tList.size();
        for (int i = 0; i < atsize; i++) {
            Tag l = tList.get(i);
            tagDao.delete(l);
        }

        postDao.delete(post);
        System.out.println("삭제하기!! ");
        PostResponse result = new PostResponse();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    private List<PostResponse> getPostList(List<Post> plist, int temp) {
        List<PostResponse> result = new LinkedList<>();

        for (int i = 0; i < plist.size(); i++) { // 각 게시물 마다 좋아요 수 가져오기
            Post p = plist.get(i);

            result.add(new PostResponse(p.getArticleId(), p.getCategoryId(), p.getUserId(), p.getTitle(),
                    p.getAddress(), p.getMinPrice(), p.getSumPrice(), p.getDescription(), p.getWriter(), p.getUrlLink(),
                    p.getImage(), temp, p.getEndTime(),BeforeCreateTime(p.getCreateTime())));

            // Optional<Like> llist = likeDao.findLikeByArticleno(articleno);
            List<Like> llist = likeDao.findLikeByArticleId(p.getArticleId());
            List<Comment> clist = commentDao.findCommentByArticleId(p.getArticleId());
            int likenum = llist.size();
            int commentnum = clist.size();
            System.out.println("list return success");
            System.out.println(likenum);

            result.get(i).likenum = likenum;
            result.get(i).commentnum = commentnum;

        }

        return result;
    }

    
    private static String BeforeCreateTime(LocalDateTime createTime) {
        String result = "";
        int before = 0;
        LocalDateTime nowTime = LocalDateTime.now();
        if (createTime.getYear() == nowTime.getYear()) {
            if (createTime.getMonth() == nowTime.getMonth()) {
                if (createTime.getDayOfMonth() == nowTime.getDayOfMonth()) {
                    if (createTime.getHour() == nowTime.getHour()) {
                        if (createTime.getMinute() == nowTime.getMinute()) {
                            result = "1분 전";
                        } else {
                            before = createTime.getMinute() -  nowTime.getMinute() ;
                            if (before < 0)
                                before = 60 + before;
                            result = before + "분 전";
                        }
                    } else {
                        before = createTime.getHour() - nowTime.getHour();
                       
                        if (before < 0)
                            before = before + 24;
                        result = before + "시간 전";
                    }
                } else {
                    before = createTime.getDayOfYear() - nowTime.getDayOfYear();
                    if (before < 0)
                        before = 365 + before;
                    if (before < 0) {
                        result = "1달 전";
                        return result;
                    }
                    result = before + "일 전";
                }
            } else {
                before = createTime.getMonthValue()-  nowTime.getMonthValue() ;
                if (before < 0)
                    before = 12 + before;
                result = before + "달 전";
            }
        } else {
            before = createTime.getYear() - nowTime.getYear() ;
       
            result = before + "달 전";
        }
        return result;

    }
}