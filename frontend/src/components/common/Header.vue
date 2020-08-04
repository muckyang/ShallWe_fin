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
        <div class="modal fade" id="Login" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
        <div class="modal-dialog">
            <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <div class="container">
                            <label for="" class="mr-3 col-3">이메일</label>
                            <input 
                            class="col-6"
                            v-model="loginData.email"
                            id="email" 
                            placeholder="아이디를 입력해주세요"
                            type="text"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="container">
                            <label for="" class="mr-3 col-3">패스워드</label>
                            <input 
                            class="col-6"
                            v-model="loginData.password" type="password"
                            id="password"
                            placeholder="영문, 숫자 혼용 8자 이상"
                            @keypress.enter="login"/>
                        </div>
                    </div>
                    
                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary" @click="login(loginData)" data-dismiss="modal">로그인</button>
                        <button class="btn btn-warning text-white">카카오 로그인</button>
                        <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn btn-secondary" data-dismiss="modal">회원가입</router-link>
                    </div>
                </form>
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
      constants,
      // icon:'\uf002',
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
    ...mapState(['isLoggedin'])
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

/* .dropdown-toggle {
  background-color: white;
  border: white;
} */
</style>