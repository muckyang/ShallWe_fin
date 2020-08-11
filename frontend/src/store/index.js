import Vue from "vue";
import Vuex from "vuex";

import axios from "axios";
import cookies from "vue-cookies";
import router from "../router";

const BACK_URL = process.env.VUE_APP_BACK_URL;

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //사용자 인증
    authToken: cookies.get("auth-token"),
    isLoggedin: false,
    userData: {
      name: "",
      address: "",
      email: "",
      nickname: "",
      userId: "",
      password: "",
      birthday: "",
      userPoint: "",
      articleCount: "",
      reviewCount: "",
      likeCount: "",
      tempCount: "",
      articleList: [],
      reviewList: [],
      likeList: [],
      tempList: [],
      grade: "",
    },
    isTerm: false,
    articleData: {
      articleId: "",
      userId: "",
      categoryId: "",
      title: "",
      writer: "",
      address: "",
      description: "",
      minPrice: "",
      sumPrice: "",
      urlLink: "",
      image: "",
      temp: "",
      endTime: "",
      createTime: "",
      likeNum: "",
      commentNum: "",
      timeAgo: "",
      partList: [],
      tags: [],
    },

    isSended: false,

    //게시글
    articles: [],
    comments: [],
    users: [],
    accuseData: {
      accuseId: "",
      reporter: "",
      defendant: "",
      accuseKind: "",
      accuseReason: "",
      accuseUrl: "",
      accuseConfirm: "",
    },
  },

  getters: {},

  mutations: {
    //사용자 관리
    SET_TOKEN(state, token) {
      state.authToken = token;
      cookies.set("auth-token", token, 0);
      state.isLoggedin = true;
      router.push("/");
      router.go();
    },
    REMOVE_TOKEN(state) {
      state.authToken = null;
      cookies.remove("auth-token");
      state.isLoggedin = false;
      state.modal = true;
      router.push("/");
    },
    loginCheck(state) {
      if (!!state.authToken) {
        state.isLoggedin = true;
      } else {
        state.isLoggedin = false;
      }
    },
    sendCheck(state) {
      state.isSended = true;
    },
    CheckFalse(state) {
      state.isSended = false;
      state.isTerm = false;
    },
    termCheck(state) {
      if (state.isTerm) {
        state.isTerm = false;
      } else {
        state.isTerm = true;
      }
    },
    GET_USERDATA(state, userData) {
      state.userData.name = userData.name;
      state.userData.address = userData.address;
      state.userData.email = userData.email;
      state.userData.userPoint = userData.userPoint;
      state.userData.nickname = userData.nickname;
      state.userData.userId = userData.userId;
      state.userData.password = userData.password;
      state.userData.birthday = userData.birthday;
      state.userData.articleCount = userData.articleCount;
      state.userData.reviewCount = userData.reviewCount;
      state.userData.likeCount = userData.likeCount;
      state.userData.tempCount = userData.tempCount;
      state.userData.articleList = userData.articleList;
      state.userData.reviewList = userData.reviewList;
      state.userData.likeList = userData.likeList;
      state.userData.tempList = userData.tempList;
      state.userData.grade = userData.grade;
    },

    //게시글 관리
    GET_ARTICLES(state, articles) {
      state.articles = articles;
    },
    GET_ARTICLE(state, response) {
      (state.articleData.articleId = response.data.articleId),
        (state.articleData.userId = response.data.userId),
        (state.articleData.categoryId = response.data.categoryId),
        (state.articleData.title = response.data.title),
        (state.articleData.writer = response.data.writer),
        (state.articleData.address = response.data.address),
        (state.articleData.description = response.data.description),
        (state.articleData.minPrice = response.data.minPrice),
        (state.articleData.sumPrice = response.data.sumPrice),
        (state.articleData.likeNum = response.data.likeNum),
        (state.articleData.commentNum = response.data.commentNum),
        (state.articleData.urlLink = response.data.urlLink),
        (state.articleData.image = response.data.image),
        (state.articleData.temp = response.data.temp),
        (state.articleData.endTime = response.data.endTime),
        (state.articleData.createTime = response.data.createdTime); //안넘어옴
      state.articleData.timeAgo = response.data.timeAgo;
      state.articleData.partList = response.data.partList;
      state.articleData.tags = response.data.tags;
    },
    GET_COMMENTS(state, comments) {
      state.comments = comments;
    },
    GET_USERS(state, users) {
      state.users = users;
    },
  },

  actions: {
    //사용자 인증
    sendEmail({ state }, data) {
      if (Object.values(data.signUpDataForSend).indexOf("") === -1) {
        if (state.isTerm) {
          if (data.signUpDataForSend.password === data.password2) {
            alert("메일로 인증 코드가 발송되었습니다.");
            this.commit("sendCheck");
            axios
              .post(`${BACK_URL}/account/sendmail`, data.signUpDataForSend)
              .then((res) => {
                // this.commit('sendCheck')
              })
              .catch((err) => {
                console.log(err);
              });
          } else {
            alert("비밀번호를 다시 설정해주세요");
          }
        } else {
          alert("약관에 동의해주세요");
        }
      } else {
        alert("빈 칸을 채워 주세요");
      }
    },
    signUp({ commit }, signUpData) {
      axios
        .post(`${BACK_URL}/account/signup`, signUpData.signUpDataForSend)
        .then(() => {
          alert("회원가입이 완료되었습니다.");
          this.commit("CheckFalse");
          router.push("/");
        })
        .catch((err) => {
          console.log(err);
          alert("인증 코드를 다시 확인해주세요");
        });
    },
    login({ commit }, loginData) {
      axios
        .post(`${BACK_URL}/account/login`, loginData)
        .then((response) => {
          commit("SET_TOKEN", response.data);
          alert("환영합니다.");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //profile
    getUserData({ state, commit }) {
      const auth = { token: state.authToken };
      axios
        .post(`${BACK_URL}/account/read`, auth)
        .then((response) => {
          commit("GET_USERDATA", response.data);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    editUser({ state, commit }, editData) {
      if (editData.editDataForSend.password === editData.password2) {
        editData.editDataForSend.token = state.authToken;
        axios
          .post(`${BACK_URL}/account/update`, editData.editDataForSend)
          .then(() => {
            alert("수정이 완료되었습니다. 다시 로그인해 주세요");
            commit("REMOVE_TOKEN");
          })
          .catch((err) => {
            console.error(err);
          });
      } else {
        alert("비밀번호를 확인해 주세요");
      }
    },
    deleteUser({ state, commit }) {
      const auth = { token: state.authToken };
      axios
        .post(`${BACK_URL}/account/delete`, auth)
        .then(() => {
          commit("REMOVE_TOKEN");
          alert("회원 탈퇴가 완료되었습니다.");
          router.push("/");
        })
        .catch((err) => {
          console.error(err);
        });
    },

    //게시글 관리
    //전체 조회, 임시저장글 조회
    getArticles({ state, commit }, data) {
      const auth = { token: state.authToken };
      axios
        .post(`${BACK_URL}/post/read/${data.temp}/${data.categoryId}`, auth)
        .then((response) => {
          commit("GET_ARTICLES", response.data.postList);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    //단일 게시글 조회
    getArticle({ state, commit }, articleID) {
      const auth = { token: state.authToken };
      axios
        .post(`${BACK_URL}/post/detail/${articleID}`, auth)
        .then((response) => {
          console.log("ㄷㅇㅌㄷㅇㅌ", response);
          commit("GET_ARTICLE", response);
          commit("GET_COMMENTS", response.data.commentList);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    //게시글 생성
    createArticle(context, articleData) {
      if (articleData.temp === 1 || articleData.temp === 0) {
        if (articleData.articleData.endTime.length < 8) {
          articleData.articleData.endTime =
            articleData.articleData.endTime + ":00";
        }
      }
      axios
        .post(
          `${BACK_URL}/post/create/${articleData.temp}`,
          articleData.articleData
        )
        .then(() => {
          router.push("/posts");
        })
        .catch((err) => console.log(err));
    },
    //게시글 수정하기
    updateArticle({ state }, updateData) {
      if (updateData.articleUpdateData.endTime.length < 8) {
        updateData.articleUpdateData.endTime =
          updateData.articleUpdateData.endTime + ":00";
      }
      updateData.articleUpdateData.token = state.authToken;
      axios
        .post(
          `${BACK_URL}/post/update/${updateData.temp}`,
          updateData.articleUpdateData
        )
        .then(() => {
          router.push(`/detail/${updateData.articleUpdateData.articleId}`);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    //게시글 삭제하기
    deleteArticle({ state, dispatch }, article) {
      const auth = { token: state.authToken };
      axios
        .get(`${BACK_URL}/post/delete/${article.id}`, auth)
        .then(() => {
          if (article.temp === 1) {
            router.push("/article");
          } else {
            dispatch("getArticles", { temp: 0, categoryId: 0 });
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //게시글 검색
    search({ commit }, searchData) {
      cookies.set("searchData", searchData, 0);
      if (
        searchData.searchDataForSend.word &&
        searchData.searchDataForSend.subject &&
        searchData.categoryId
      ) {
        searchData.categoryId = 0;
        axios
          .post(
            `${BACK_URL}/post/search/1/${searchData.categoryId}`,
            searchData.searchDataForSend
          )
          .then((res) => {
            commit("GET_ARTICLES", res.data.postList);
            router.push({ name: "searchList" });
            searchData.categoryId = "기본값";
          })
          .catch((err) => {
            console.log(err);
            searchData.categoryId = "기본값";
          });
      } else if (!searchData.searchDataForSend.subject) {
        alert("분류를 입력하세요!");
      } else if (!searchData.categoryId) {
        alert("카테고리를 선택하세요!");
      } else if (!searchData.searchDataForSend.word) {
        alert("검색어를 입력해주세요!");
      }
    },

    // 관리자 페이지
    getUsers({ state, commit }, users) {
      const auth = { token: state.authToken };
      axios
        .post(`${BACK_URL}/account/readAll`, auth)
        .then((res) => {
          commit("GET_USERS", res.data);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    updateGrade({ state }, updateGrade) {
      changedObj.changedGrade.token = state.authToken;
      axios
        .post(`${BACK_URL}/`, changedObj.changedGrade)
        .then(() => {
          router.push("/user/admin");
        })
        .catch((err) => {
          console.error(err);
        });
    },
    // 신고양식
    createAccuse(context, accuseData) {
      axios
        .post(`${BACK_URL}/accuse/create`, accuseData)
        .then(() => {
          router.push("/");
        })
        .catch((err) => console.log(err));
    },
  },
  modules: {},
});
