import Vue from "vue";
import Vuex from "vuex";

import axios from "axios";
import cookies from "vue-cookies";
import router from "../router";

// CommonJS

const BACK_URL = process.env.VUE_APP_BACK_URL;

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    //사용자 인증
    authToken: cookies.get("auth-token"),
    adminToken: cookies.get("admin-token"),
    isLoggedin: false,
    isTerm: false,
    isChecked: false,
    // isSended: false,

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
    articles: [],
    comments: [],
    users: [],
    accuseData: {
      reporter: "",
      defendant: "",
      accuseIndex: "",
      accuseValue: "",
      accuseKind: 0,
      accuseReason: "",
      accuseUrl: "",
      accuseConfirm: 0,
    },
    accuses: [],
  },

  getters: {},

  mutations: {
    //사용자 관리
    SET_TOKEN(state, token) {
      router.push("/");
      state.authToken = token;
      cookies.set("auth-token", token, 0);
      state.isLoggedin = true;
    
        Swal.fire({
          icon: 'success',
          height: 400,
          width: 300,
          title : '로그인 되셨습니다.',
          text : '환영합니다.'
        })
     
    },
    SET_ADMIN_TOKEN(state, token) {
      state.adminToken = token;
      cookies.set("admin-token", token, 0);
    },
    REMOVE_TOKEN(state) {
      if (state.adminToken) {
        cookies.remove("admin-token");
        Swal.fire(
          '로그아웃 되었습니다.',
          '',
          'success'
        )
        state.adminToken = null;
      }
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
    duCheck(state, bool) {
      state.isChecked = bool;
    },
    GET_USERDATA(state, userData) {
      state.userData = userData;
    },
    GET_ARTICLES(state, articles) {
      state.articles = articles;
    },
    GET_ARTICLE(state, response) {
      state.articleData = response.data;
    },
    GET_COMMENTS(state, comments) {
      state.comments = comments;
    },
    GET_USERS(state, users) {
      state.users = users;
    },
    GET_USER(state, res) {
      state.userData = res.data;
    },
    GET_ACCUSES(state, accuses) {
      state.accuses = accuses;
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
    duCheck({ commit }, nickname) {
      axios
        .get(`${BACK_URL}/account/nicknamecheck/${nickname}`)
        .then((response) => {
          if (response.data === "사용가능한 닉네임입니다.") {
            commit("duCheck", true);
          } else {
            commit("duCheck", false);
          }
        })
        .catch((error) => {
          console.log(error);
        });
    },
    signUp({ state, commit }, signUpData) {
      if (signUpData.address && signUpData.nickname) {
        if (state.isChecked) {
          if (state.isTerm) {
            axios
              .post(`${BACK_URL}/account/signup`, signUpData)
              .then((response) => {
                Swal.fire({
                  height: 400,
                  width: 300,
                  title: '회원가입이 완료되었습니다.',
                  text:'Shall we 에 가입되신걸 축하합니다',
                  icon : 'success'
                 } )
                commit("SET_TOKEN", response.data);
                this.commit("termCheck");
                router.push("/");
              })
              .catch((err) => {
                console.log(err);
              });
          } else {
            swal.fire({
              height: 400,
              width: 300,
              icon : "error",
              title: '약관에 동의해 주세요',
              text: '',
            })
            // alert("약관에 동의해 주세요");
          }
        } else {
          swal.fire({
            height: 400,
            width: 300,
            icon : "error",
            title: '닉네임을 입력해 주세요',
            text: '',
          })
        }
      } else {
        swal.fire({
          height: 400,
          width: 300,
          icon : "error",
          title: '빈 칸을 모두 채워주세요',
          text: '',
        })
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
    adminLogin({ commit }, loginData) {
      console.log(loginData);
      axios
        .post(`${BACK_URL}/admin/login`, loginData)
        .then((response) => {
          console.log(response.data);
          commit("SET_ADMIN_TOKEN", response.data.adminToken);
          commit("SET_TOKEN", response.data.token);
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

    // 이한솔 시작
    getUserDatailData({ state, commit }, userNickname) {
      const auth = { token: state.authToken };
      axios
        .post(`${BACK_URL}/account/read/${userNickname}`, auth)
        .then((res) => {
          commit("GET_USER", res);
        })
        .catch((err) => {
          console.error(err);
        });
    },
    // 이한솔 끝

    editUser({ state, commit }, editData) {
      editData.token = state.authToken;
      axios
        .post(`${BACK_URL}/account/update`, editData)
        .then(() => {

          swal.fire({
            icon : "success",
            title: '수정이 완료되었습니다.',
            text: '다시 로그인 해주세요!!',
          })
          // alert("수정이 완료되었습니다. 다시 로그인해 주세요");
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
          console.log(response, ",ssdfs");
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
          console.log(response, "디테일");
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
          if (articleData.temp === 1 || articleData.temp === 0) {
            router.push("/article");
          } else {
            if (articleData.articleData.categoryId === 102) {
              router.push("/reviews");
            } else {
              router.push("/posts");
            }
          }
        })
        .catch((err) => console.log(err));
    },
    //게시글 수정하기
    updateArticle({ state }, updateData) {
      if (updateData.articleUpdateData.endTime) {
        if (updateData.articleUpdateData.endTime.length < 8) {
          updateData.articleUpdateData.endTime =
            updateData.articleUpdateData.endTime + ":00";
        }
      }
      updateData.articleUpdateData.token = state.authToken;
      axios
        .post(
          `${BACK_URL}/post/update/${updateData.temp}`,
          updateData.articleUpdateData
        )
        .then(() => {
          if (updateData.temp === 2) {
            if (updateData.articleUpdateData.categoryId === 102) {
              router.push(`/reviews`);
            } else {
              router.push(`/pdetail/${updateData.articleUpdateData.articleId}`);
            }
          } else {
            router.push(`/detail/${updateData.articleUpdateData.articleId}`);
          }
        })
        .catch((err) => {
          console.error(err);
        });
    },
    //게시글 삭제하기
    deleteArticle({ state, dispatch }, data) {
      const auth = { token: state.authToken };
      axios
        .get(`${BACK_URL}/post/delete/${data.articleId}`)
        .then(() => {
          if (data.categoryId < 10) {
            router.push(`/article`);
          } else {
            router.push(`/posts`);
          }
        })
        .catch((err) => {
          console.log(err);
        });
    },
    //전체 게시글 검색
    search({ commit }, searchData) {
      cookies.set("searchData", searchData, 0);
      searchData.categoryId = 0;
      axios
        .post(
          `${BACK_URL}/post/search/1/${searchData.categoryId}`,
          searchData.searchDataForSend
        )
        .then((res) => {
          commit("GET_ARTICLES", res.data.postList);
          router.push({ name: "searchList" });
        })
        .catch((err) => {
          console.log(err);
        });
    },
    detailSearch({ commit }, searchData) {
      cookies.set("searchData", searchData, 0);
      if (searchData.categoryId === 0) {
        searchData.categoryId = "temp";
      }
      if (
        searchData.searchDataForSend.word &&
        searchData.searchDataForSend.subject &&
        searchData.categoryId &&
        searchData.temp
      ) {
        if (searchData.categoryId === "temp") {
          searchData.categoryId = 0;
        }
        console.log(searchData);
        axios
          .post(
            `${BACK_URL}/post/search/${searchData.temp}/${searchData.categoryId}`,
            searchData.searchDataForSend
          )
          .then((res) => {
            commit("GET_ARTICLES", res.data.postList);
            router.push("/searchlist");
          })
          .catch((err) => {
            console.log(err);
          });
      } else if (!searchData.temp) {
        alert("게시글 종류를 선택해 주세요!");
      } else if (!searchData.categoryId) {
        alert("카테고리를 선택해 주세요!");
      } else if (!searchData.searchDataForSend.subject) {
        alert("분류를 선택해 주세요!");
      } else if (!searchData.searchDataForSend.word) {
        alert("검색어를 입력해 주세요!");
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
    // createArticleAccuse(context, accuseArticleData) {
    //   axios
    //     .post(`${BACK_URL}/accuse/create`, accuseArticleData.accuseArticleData)
    //     .then(() => {
    //       console.log(accuseArticleData, "하하하");
    //       router.push("/posts");
    //     })
    //     .catch((err) => console.log(err));
    // },
    // // 댓글 신고 접수
    // createCommentAccuse(context, accuseCommentData) {
    //   axios
    //     .post(`${BACK_URL}/accuse/create`, accuseCommentData.accuseCommentData)
    //     .then(() => {
    //       router.push("/");
    //     })
    //     .catch((err) => console.log(err));
    // },
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
    createArticleAccuse(context, accuseArticleData) {
      axios
        .post(`${BACK_URL}/accuse/create`, accuseArticleData)
        .then(() => {
          router.push("/");
        })
        .catch((err) => console.log(err));
    },
    createCommentAccuse(context, accuseCommentData) {
      axios
        .post(`${BACK_URL}/accuse/create`, accuseCommentData.accuseCommentData)
        .then(() => {
          router.push("/");
          console.log(accuseCommentData, "AAA");
        })
        .catch((err) => console.log(err));
    },
    getAccuses({ state, commit }) {
      const admin = { token: state.adminToken };
      axios
        .post(`${BACK_URL}/accuse/read`, admin)
        .then((res) => {
          commit("GET_ACCUSES", res.data.accuseList);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    decideAccuse({ state }, decisionData) {
      const admin = { token: state.adminToken };
      axios
        .post(`${BACK_URL}/accuse/applyto`, decisionData)
        .then(() => {
          router.push("/user/accuselist");
        })
        .catch((err) => {
          console.log(err);
        });
    },
  },
  modules: {},
});
