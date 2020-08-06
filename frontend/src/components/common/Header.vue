<template>
  <div>
    <!-- Navbar1 -->
    <nav class="navbar navbar-light d-flex justify-content-end navbar1 p-0 my-navbar">
      <ul class="navbar-nav d-flex flex-row">
        <!-- 로그인 X -->
        <li class="nav-item mr-1"><a v-if="!isLoggedin" 
        v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="nav-link navbar1-item loginBtn" 
        data-toggle="modal" data-target="#Login">로그인</a></li>

        <div class="wall" v-show="!isLoggedin">|</div>

        <!--modal-->
        <div class="modal fade" v-if="modal" id="Login" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog modalsize">
            <div class="modal-content modalsize">
            <!-- <div class="modal-header">
            </div> -->
            <div class="modal-body">
              <div class="login-header">
                <div>
                  <a type="button" class="close-btn" data-dismiss="modal" 
                  aria-label="Close">
                  <span class="close" aria-hidden="true">&times;</span></a>
                </div>
                <h5 class="modal-title text-center" style="color: #ee6e9f; font-family: 'Lobster', cursive; 
                font-size: 35px" id="exampleModalLabel">Shall we?</h5>
              </div>
              <div class="login-input">
                <form>
                  <div class="form-group mb-1">
                      <div class="container">

                          <input 
                          class="login-email"
                          v-model="loginData.email"
                          id="email" 
                          style="font-family: FontAwesome;" 
                          :placeholder="email"
                          type="text"/>
                      </div>
                  </div>
                  <div class="form-group">
                      <div class="container">
                          <input 
                          class="login-pw"
                          v-model="loginData.password" type="password"
                          id="password"
                          style="font-family: FontAwesome;" 
                          :placeholder="pw"
                          @keypress.enter="login(loginData)"/>
                      </div>
                  </div>
                </form>
              </div>

              <div class="login-sumit">
                <div class="container mb-3">
                  <button type="submit" class="submit-btn" 
                  @click="login(loginData)" data-dismiss="modal">로그인</button>

                  <div class="row-items d-flex justify-content-center">
                    <div class="bottom-line"></div>
                    <div class="or">또는</div>
                    <div class="bottom-line"></div>
                  </div>

                  <button class="kakao-btn">
                    <img src="../../assets/img/kakao_logo.jpg" class="kakao-img">
                    카톡 로그인</button>
                    <div class="blank"></div>
                  <button class="google-btn">
                  <img src="../../assets/img/google_logo.png" class="google-img">
                  구글 로그인</button>
                </div>
              </div>

              <div class="modal-footer d-flex justify-content-center">
                <div class="signup d-flex justify-content-center">
                  <div class="ask">계정이 없으신가요?</div>
                  <div class="blank"></div>
                  <div class="click">
                      <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" 
                      class="goJoin" data-dismiss="modal">가입하기</router-link>
                  </div>  
                </div>
                          
              </div>

            </div>
          </div>
        </div>
        </div>

        <li class="nav-item mr-2"><router-link v-if="!isLoggedin"
        v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="nav-link navbar1-item">
        회원가입</router-link></li>
        <!-- 로그인 O -->
        <li class="nav-item mr-1"><a href="#" v-if="isLoggedin" 
        @click="REMOVE_TOKEN" class="nav-link navbar1-item">로그아웃</a></li>

        <div class="wall" v-show="isLoggedin">|</div>

        <li class="nav-item mr-2"><router-link v-if="isLoggedin" 
        v-bind:to="{name:constants.URL_TYPE.USER.PROFILE}" class="nav-link navbar1-item">
        마이페이지</router-link></li>
      </ul>
    </nav>
    <!-- Navbar2 -->
    <nav class="navbar navbar-light d-flex justify-content-center my-navbar p-1">
      <a class="navbar-brand mt-3" href="/" 
      style="color: #ee6e9f; font-family: 'Lobster', cursive; 
      font-size: 35px">Shall we?</a>
    </nav>
    <!-- Navbar3 -->
    <nav class="navbar navbar-expand-lg navbar-light p-0">
      <!--페이지 줄일때 네브바 햄버거로 만들어주는거-->
      <button class="navbar-toggler" 
      type="button" data-toggle="collapse" 
      data-target="#navbarSupportedContent" 
      aria-controls="navbarSupportedContent" 
      aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item pb-0">
            <router-link to="/article" 
            class="nav-link mainMenu pb-0" 
            id="article">게시글</router-link></li>

          <li class="nav-item pb-0">
            <router-link to="/posts" 
            class="nav-link mainMenu pb-0" 
            id="community">자유 게시판</router-link></li>

          <li class="nav-item pb-0">
            <router-link to="#" 
            class="nav-link mainMenu pb-0" 
            id="community">쉘위 소개</router-link></li>
        </ul>
        <!-- Search -->
        <!-- <div class="dropdown mr-1">
          <button class="downBtn btn btn-secondary" 
            type="button" id="dropdownMenuButton" 
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{item1 }}<i class="down fas fa-sort-down"></i> 
            </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="#" @click="selectSubject(0)">제목</a>
            <a class="dropdown-item" href="#" @click="selectSubject(1)">작성자</a>
            <a class="dropdown-item" href="#" @click="selectSubject(2)">태그</a>
          </div>
        </div> -->
        <!-- <div class="form-inline my-2 my-lg-0 mb-1"> -->
          <!-- <div class="dropdown mr-1">
            <button class="downBtn btn btn-secondary" 
            type="button" id="dropdownMenuButton" 
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{item2}}<i class="down fas fa-sort-down"></i> 
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#" @click="selectCategory(1)">배달</a>
              <a class="dropdown-item" href="#" @click="selectCategory(2)">최저주문</a>
              <a class="dropdown-item" href="#" @click="selectCategory(3)">공동구매</a>
          </div>
        </div> -->
          <input class="searchInput" type="search" 
          style="font-family: FontAwesome;" 
          placeholder="Search" 
          v-model="searchData.searchDataForSend.word" 
          @keypress.enter="search" 
          >
          <button class="searchBtn 
          my-2 my-sm-0" 
          type="submit" @click="search(searchData)">
          <i class="fas fa-search"></i></button>
        <!-- </div> -->
      </div>
    </nav>
  </div>
</template>

<script>
import constants from "../../lib/constants";
import {mapState, mapMutations,mapActions} from 'vuex'
import router from '@/router'
import axios from "axios"
const BACK_URL = "http://127.0.0.1:8080"

export default {
  name: "Header",
  components: {},
  props: ["isHeader"],
  data: function() {
    return {
      email:' \uf0e0'+ '  이메일',
      pw: ' \uf084'+ '  비밀번호',
      constants,
      item1:'',
      item2:'',
      searchData:{
        searchDataForSend:{
          subject:'',
          word:'',
        },
        categoryId:'',
      },
      loginData:{
        email: '',
        password: '',
      },
    }
  },
  methods: {
    ...mapMutations(['REMOVE_TOKEN','loginCheck']),
    ...mapActions(['search','login']),


    //검색
    // selectSubject(num){
    //   if(num==0){
    //   this.searchData.searchDataForSend.subject='title'
    //   this.item1 = '제목'
    //   }else if(num==1){
    //     this.searchData.searchDataForSend.subject = 'writer'
    //     this.item1 = '작성자'
    //   }else{
    //     this.searchData.searchDataForSend.subject = 'tag'
    //     this.item1 = '태그'
    //   }
    // },
    // selectCategory(num){
    //   if(num==1){
    //   this.searchData.categoryId=1
    //   this.item2="배달"
    //   }else if(num==2){
    //     this.searchData.categoryId = 2
    //     this.item2="최저주문"
    //   }else{
    //     this.searchData.categoryId = 3
    //     this.item2="공동구매"
    //   }
    // },


  },
  computed:{
    ...mapState(['isLoggedin','modal'])
  },
  created:function(){
    this.loginCheck()
  },
};
</script>

<style>
@font-face { font-family: 'Recipekorea'; 
src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff') format('woff'); 
font-weight: normal; 
font-style: normal;
 }
 .modal{
   text-align: center;
 }
 @media screen and (min-width: 768px) { 
  .modal:before {
          display: inline-block;
          vertical-align: middle;
          content: " ";
          height: 100%;
  }
}
.modal-dialog {
  display: inline-block;
  text-align: left;
  vertical-align: middle;
}
.modal-dialog.modalsize {
  width: 27%;
  height: 85%;
  margin: 0;
  padding: 0;
}
.modal-content.modalsize {
  height: auto;
  min-width: 10%;
}
.close-btn{
  border: none;
  outline: none;
  background-color: transparent;
  width: 100%;
}
.close{
  float: right;
}
.login-input{
  margin: 7% 0 5% 0;
}
.login-email:focus::placeholder{
  color: transparent;
}
.login-pw:focus::placeholder{
  color: transparent;
}
.login-email, .login-pw {
  width: 100%;
  border-radius: 2px;
}
.modal-body .login-submit{
  margin: 0 0 10% 0;
}
.submit-btn, .kakao-btn, .google-btn{
  border-radius: 4px;
  border: none;
  font-weight: bold;
  height: 35px;
  text-align: center;
  vertical-align: middle; /* 적용 안 됨 */
}
.submit-btn{
  background-color: #ee6e9f;
  color: white;
  margin: 2% 0 0 0;
  width: 100%;
  opacity: 0.8;
}
.submit-btn:hover{
  transition-duration: 0.3s;
  opacity: 1;
}
.row-items{
  margin: 5% 0 3% 0 ;
  width: 100%;
  height: 40%;
}
.or{
  border: 0px solid grey;
  margin: 2px 1.5% 0 1.5%;
  font-size: small;
  color: rgb(153, 151, 151);
}
.bottom-line{
  margin: 7px 0 8px 0;
  width: 44%;
  border-bottom: 1px solid rgb(216, 211, 211);
}
.kakao-btn, .google-btn{
  margin: 2% 0 0 0;
  width: 100%;
  background-color: transparent;
  color: rgb(26, 12, 12);
}
.kakao-img{
  height: 25px;
  width: 25px;
}
.google-img{
  height: 20px;
  width: 20px;
  margin: 0 0 0 5px;
}

.blank{
  width: 10px;
}
 .mainMenu{
   font-family: Recipekorea;
   font-weight: normal;
   font-style: normal;
 }
 #article{
   margin-left:-5px;
   color: #3a4049;
 }
 #community{
   margin-left: 10px;
   color: #3a4049;

 }
.navbar1 {
  height: 30px; 
  
}
.navbar1-item {
  font-size: 12px;
}
.wall{
  padding: .5rem 0.2rem;
  font-size: 12px;
  margin-left: -4px;
  margin-right: 1px;
}
.down{
  padding-bottom:3px;
  margin-bottom: 5px;
}
.downBtn{
  height: 35px;
  vertical-align: middle;
  background-color: #676d74;
}
.downBtn:hover{
  background-color: #3a4049;
}
.searchInput{
  height: 35px;
  width: 180px;
  border-radius: 4px 0px 0px 4px; 
  /* border-top-left-radius: 2px; */
  border-style: solid;
  border-width: 1.5px;
  border-color: #3a4049;
  padding-left: 0.6%;
}
.searchInput:focus{
  outline: none;
}
.searchInput:focus::placeholder{
  color:transparent;
}
.searchBtn{
  height: 35px;
  border-style: solid;
  border-radius: 0px 4px 4px 0px;
  border-color: #3a4049;
  background-color: #3a4049;
  color: #faf9fa;
}
.loginBtn:hover{
  cursor: pointer;
}
.goJoin{
  font-weight: bold;
}

</style>