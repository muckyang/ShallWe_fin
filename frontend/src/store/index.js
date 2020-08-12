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
    adminToken: cookies.get("admin-token"),
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
      router.push("/");
      state.authToken = token;
      cookies.set("auth-token", token, 0);
      state.isLoggedin = true;
      setTimeout(function () { alert("환영합니다.") }, 50)
    },
    SET_ADMIN_TOKEN(state,token){
      router.push("/");
      state.authToken = token;
      cookies.set("admin-token", token, 0);
      state.isLoggedin = true;
      setTimeout(function () { alert("환영합니다.") }, 50)
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
    termCheck(state) {
      if (state.isTerm) {
        state.isTerm = false;
      } else {
        state.isTerm = true;
      }
    },
    GET_USERDATA(state, userData) {
      state.userData = userData
    },

    //게시글 관리
    GET_ARTICLES(state, articles) {
      state.articles = articles;
    },
    GET_ARTICLE(state, response) {
      state.articleData = response.data
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
    // sendEmail({ state }, data) {
    //   if (Object.values(data.signUpDataForSend).indexOf("") === -1) {
    //     if (state.isTerm) {
    //       if (data.signUpDataForSend.password === data.password2) {
    //         alert("메일로 인증 코드가 발송되었습니다.");
    //         this.commit("sendCheck");
    //         axios
    //           .post(`${BACK_URL}/account/sendmail`, data.signUpDataForSend)
    //           .then((res) => {
    //             // this.commit('sendCheck')
    //           })
    //           .catch((err) => {
    //             console.log(err);
    //           });
    //       } else {
    //         alert("비밀번호를 다시 설정해주세요");
    //       }
    //     } else {
    //       alert("약관에 동의해주세요");
    //     }
    //   } else {
    //     alert("빈 칸을 채워 주세요");
    //   }
    // },
    duCheck(context, nickname) {
      axios.get(`${BACK_URL}/account/nicknamecheck/${nickname}`)
        .then((response) => {
          alert(response.data)
        })
        .catch((error) => {
          alert(error.data)
        })
    },
    signUp({ state, commit }, signUpData) {
      if (state.isTerm) {
        axios.post(`${BACK_URL}/account/signup`, signUpData)
          .then(() => {
            alert("회원가입이 완료되었습니다.");
            commit("SET_TOKEN", response.data);
            this.commit("termCheck");
            router.push("/");
          })
          .catch((err) => {
            console.log(err);
            alert("빈 칸을 채워 주세요");
          });
      } else {
        alert("약관에 동의해 주세요")
      }
    },
    // login({ commit }, loginData) {
      // axios
      //   .post(`${BACK_URL}/account/login`, loginData)
      //   .then((response) => {
      //     commit("SET_TOKEN", response.data);
      //     alert("환영합니다.");
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //   });
    // },
    adminLogin({ commit }, loginData){
      console.log(loginData)
      axios
      .post(`${BACK_URL}/admin/login`, loginData)
      .then((response) => {
        commit("SET_ADMIN_TOKEN", response.data);
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
      editData.token = state.authToken;
      axios
        .post(`${BACK_URL}/account/update`, editData)
        .then(() => {
          alert("수정이 완료되었습니다. 다시 로그인해 주세요");
          commit("REMOVE_TOKEN");
        })
        .catch((err) => {
          console.error(err);
        });
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
      const auth = { token: state.adminToken ,status : 1};
      axios
        .post(`${BACK_URL}/post/detail/${articleID}`, auth)
        .then((response) => {
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

    // 게시글 신고 접수
    createArticleAccuse(context, accuseArticleData) {
      axios
        .post(
          `${BACK_URL}/accuse/create`,
          accuseArticleData.accuseArticleData
        )
        .then(() => {
          router.push('/');
        })
        .catch((err) => console.log(err))
    },
    // 댓글 신고 접수
    createCommentAccuse(context, accuseCommentData) {
      axios
        .post(
          `${BACK_URL}/accuse/create`,
          accuseCommentData.accuseCommentData
        )
        .then(() => {
          router.push('/');
        })
        .catch((err) => console.log(err))
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
    // 신고 리스트
    getAccuses({ state, commit }, users) {
      const auth = { token: state.authToken };
      axios
        .post(`${BACK_URL}/accuse/read`, auth)
        .then((res) => {
          commit("GET_ACCUSES", res.data.accuseList);
        })
        .catch((err) => {
          console.log(err);
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
