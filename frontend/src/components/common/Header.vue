<template>
  <div>
    <!-- Navbar1 -->
    <nav class="navbar navbar-light d-flex justify-content-end navbar1 p-0 my-navbar">
      <ul class="navbar-nav d-flex flex-row">
        <!-- 로그인 X -->
        <li class="nav-item mr-1">
          <a
            v-if="!isLoggedin"
            v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}"
            class="nav-link navbar1-item loginBtn"
            data-toggle="modal"
            data-target="#Login"
          >로그인</a>
        </li>

        <div class="wall" v-show="!isLoggedin">|</div>

        <!--modal-->
        <div
          class="modal fade"
          id="Login"
          tabindex="-1"
          role="dialog"
          aria-labelledby="exampleModalLabel"
          aria-hidden="true"
        >
          <div class="modal-dialog modalsize">
            <div class="modal-content modalsize">
              <!-- <div class="modal-header">
              </div>-->
              <div class="modal-body">
                <div class="login-header">
                  <div>
                    <a type="button" class="close-btn" data-dismiss="modal" aria-label="Close">
                      <span class="close" aria-hidden="true">&times;</span>
                    </a>
                  </div>
                  <h5
                    class="modal-title text-center"
                    style="color: #ee6e9f; font-family: 'Lobster', cursive; 
                font-size: 35px"
                    id="exampleModalLabel"
                  >Shall we?</h5>
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
                          type="text"
                        />
                      </div>
                    </div>
                    <div class="form-group">
                      <div class="container">
                        <input
                          class="login-pw"
                          v-model="loginData.password"
                          type="password"
                          id="password"
                          style="font-family: FontAwesome;"
                          :placeholder="pw"
                          @keypress.enter="login(loginData)"
                        />
                      </div>
                    </div>
                  </form>
                </div>

                <div class="login-sumit">
                  <div class="container mb-3">
                    <button
                      type="submit"
                      class="submit-btn"
                      @click="login(loginData)"
                      data-dismiss="modal"
                    >로그인</button>

                    <div class="row-items d-flex justify-content-center">
                      <div class="bottom-line"></div>
                      <div class="or">또는</div>
                      <div class="bottom-line"></div>
                    </div>

                    <button class="kakao-btn">
                      <a
                        href="https://kauth.kakao.com/oauth/authorize?client_id=5a3a01519efdeba53b7a039bffafd62d&redirect_uri=http://localhost:8080/account/kakaoLogin&response_type=code"
                      >
                        <img src="../../assets/img/kakao_logo.jpg" class="kakao-img" />
                        카톡 로그인
                      </a>
                    </button>
                    <div class="blank"></div>
                    <button class="google-btn">
                      <img src="../../assets/img/google_logo4.png" class="google-img" />
                      구글 로그인
                    </button>
                  </div>
                </div>

                <div class="modal-footer d-flex justify-content-center">
                  <div class="signup d-flex justify-content-center">
                    <div class="ask">계정이 없으신가요?</div>
                    <div class="blank"></div>
                    <div class="click">
                      <router-link
                        v-bind:to="{name:constants.URL_TYPE.USER.JOIN}"
                        class="goJoin"
                        data-dismiss="modal"
                      >가입하기</router-link>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <li class="nav-item mr-2">
          <router-link
            v-if="!isLoggedin"
            v-bind:to="{name:constants.URL_TYPE.USER.JOIN}"
            class="nav-link navbar1-item"
          >회원가입</router-link>
        </li>
        <!-- 로그인 O -->
        <li class="nav-item mr-1">
          <a href="#" v-if="isLoggedin" @click="REMOVE_TOKEN" class="nav-link navbar1-item">로그아웃</a>
        </li>

        <div class="wall" v-show="isLoggedin">|</div>

        <li class="nav-item mr-1">
          <router-link
            v-if="isLoggedin"
            v-bind:to="{name:constants.URL_TYPE.USER.PROFILE}"
            class="nav-link navbar1-item"
          >마이페이지</router-link>
        </li>
      </ul>

      <div class="wall" v-show="isLoggedin">|</div>

      <!-- 유저관리 -->
      <div v-if="isLoggedin && userData.grade===0" class="d-flex align-items-starts mr-1">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a href="/user/userlist" class="nav-link navbar1-item">유저관리</a>
          </li>
        </ul>
      </div>

      <div class="wall" v-show="isLoggedin">|</div>

      <!-- 신고관리 -->
      <div v-if="isLoggedin && userData.grade===0" class="d-flex align-items-starts">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a href="/user/accuselist" class="nav-link navbar1-item">신고관리</a>
          </li>
        </ul>
      </div>
    </nav>
    <!-- Navbar2 -->
    <nav class="navbar navbar-light d-flex justify-content-center my-navbar p-1">
      <a
        class="navbar-brand mt-3"
        href="/"
        style="color: #ee6e9f; font-family: 'Lobster', cursive; 
      font-size: 35px"
      >Shall we?</a>
    </nav>
    <!-- Navbar3 -->
    <nav class="navbar navbar-expand-lg navbar-light p-0">
      <!--페이지 줄일때 네브바 햄버거로 만들어주는거-->
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item pb-0">
            <router-link to="#" class="nav-link mainMenu pb-0" id="aboutUs">쉘위 소개</router-link>
          </li>

          <li class="nav-item pb-0">
            <router-link to="/article" class="nav-link mainMenu pb-0" id="article">게시글</router-link>
          </li>

          <li class="nav-item pb-0">
            <router-link to="/reviews" class="nav-link mainMenu pb-0" id="community">후기</router-link>
          </li>

          <li class="nav-item pb-0">
            <router-link to="/posts" class="nav-link mainMenu pb-0" id="community">커뮤니티</router-link>
          </li>

          <li class="nav-item pb-0">
            <router-link to="/localList" class="nav-link mainMenu pb-0" id="community">우리 동네</router-link>
          </li>
        </ul>

        <!-- Search -->
        <input
          class="searchInput"
          type="search"
          style="font-family: FontAwesome;"
          placeholder="Search"
          v-model="searchData.searchDataForSend.word"
          @keypress.enter="search(searchData)"
        />
        <button class="searchBtn my-2 my-sm-0" type="submit" @click="search(searchData)">
          <i class="fas fa-search"></i>
        </button>
        <!-- </div> -->
      </div>
    </nav>
  </div>
</template>

<script>
import constants from "../../lib/constants";
import { mapState, mapMutations, mapActions } from "vuex";
import router from "@/router";
import axios from "axios";
const BACK_URL = process.env.VUE_APP_BACK_URL;
const API_KEY = process.env.VUE_APP_KAKAO_API_KEY;

export default {
  name: "Header",
  components: {},
  props: ["isHeader"],
  data: function () {
    return {
      email: " \uf0e0" + "  이메일",
      pw: " \uf084" + "  비밀번호",
      constants,
      searchData: {
        searchDataForSend: {
          word: "",
          subject: "headertitle",
        },
      },
      loginData: {
        email: "",
        password: "",
      },
    };
  },
  methods: {
    ...mapMutations(["REMOVE_TOKEN", "loginCheck"]),
    ...mapActions(["search", "login", "getUserData"]),
  },
  computed: {
    ...mapState(["isLoggedin", "userData"]),
  },
  created: function () {
    this.loginCheck();
    if (this.$cookies.get("auth-token")) {
      this.getUserData();
    }
  },
};
</script>

<style>
@font-face {
  font-family: "Recipekorea";
  src: url("https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff")
    format("woff");
  font-weight: normal;
  font-style: normal;
}
.modal {
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
.close-btn {
  border: none;
  outline: none;
  background-color: transparent;
  width: 100%;
}
.close {
  float: right;
}
.login-input {
  margin: 7% 0 5% 0;
}
.login-email:focus::placeholder {
  color: transparent;
}
.login-pw:focus::placeholder {
  color: transparent;
}
.login-email,
.login-pw {
  width: 100%;
  border-radius: 2px;
}
.login-email {
  margin-bottom: 0.5%;
}
.modal-body .login-submit {
  margin: 0 0 10% 0;
}
.submit-btn,
.kakao-btn,
.google-btn {
  border-radius: 4px;
  border: none;
  font-weight: bold;
  height: 35px;
  text-align: center;
  vertical-align: middle; /* 적용 안 됨 */
}
.submit-btn {
  background-color: #ee6e9f;
  color: white;
  margin: 2% 0 0 0;
  width: 100%;
  opacity: 0.8;
}
.submit-btn:hover {
  transition-duration: 0.5s;
  opacity: 1;
}
.row-items {
  margin: 5% 0 3% 0;
  width: 100%;
  height: 40%;
}
.or {
  border: 0px solid grey;
  margin: 2px 1.5% 0 1.5%;
  font-size: small;
  color: rgb(153, 151, 151);
}
.bottom-line {
  margin: 7px 0 8px 0;
  width: 44%;
  border-bottom: 1px solid rgb(216, 211, 211);
}
.kakao-btn,
.google-btn {
  margin: 2% 0 0 0;
  width: 100%;
  background-color: transparent;
  color: rgb(26, 12, 12);
}
.kakao-img {
  height: 23px;
  width: 24px;
}
.google-img {
  height: 24px;
  width: 23px;
  /* margin: 0 0 0 5px; */
}

.blank {
  width: 10px;
}
.mainMenu {
  font-family: Recipekorea;
  font-weight: normal;
  font-style: normal;
}
#article {
  margin-left: 10px;
  color: #3a4049;
}
#community {
  margin-left: 10px;
  color: #3a4049;
}
#aboutUs {
  margin-left: -5px;
  color: #3a4049;
}
.navbar1 {
  height: 30px;
}
.navbar1-item {
  font-size: 12px;
}
.wall {
  padding: 0.5rem 0.2rem;
  font-size: 12px;
  margin-left: -4px;
  margin-right: 1px;
}
.down {
  padding-bottom: 3px;
  margin-bottom: 5px;
}
.downBtn {
  height: 35px;
  vertical-align: middle;
  background-color: #676d74;
}
.downBtn:hover {
  background-color: #3a4049;
}
.searchInput {
  height: 35px;
  width: 180px;
  border-radius: 4px 0px 0px 4px;
  /* border-top-left-radius: 2px; */
  border-style: solid;
  border-width: 1.5px;
  border-color: #3a4049;
  padding-left: 0.6%;
}
.searchInput:focus {
  outline: none;
}
.searchInput:focus::placeholder {
  color: transparent;
}
.searchBtn {
  height: 35px;
  border-style: solid;
  border-radius: 0px 4px 4px 0px;
  border-color: #3a4049;
  background-color: #3a4049;
  color: #faf9fa;
}
.loginBtn:hover {
  cursor: pointer;
}
.goJoin {
  font-weight: bold;
}
</style>