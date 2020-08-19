<template>
  <div class="mt-5">
    <div class="container detail" style="font-family: 'Recipekorea', cursive; font-size:16.5px">
      <!-- <div>{{ articleData.description }}</div>
      <commentList />-->
      <!--Top 부분. 제목, 작성자, create time -->
      <div class="top">
        <div class="top-row">
          <div class="detail-title">{{ articleData.title }}</div>
          <!--수정,삭제,신고 버튼-->
          <div class="article-drop dropdown dropleft" v-if="articleData.userId === userData.userId">
            <button type="button" class="article-btn" data-toggle="dropdown">
              <i class="fas fa-ellipsis-v"></i>
            </button>
            <div class="dropdown-menu">
              <router-link
                class="postUpdate"
                style="text-decoration: none; color: black"
                :to="{
                  name: 'postUpdate',
                  params: { ID: this.$route.params.ID },
                }"
                v-if="articleData.status === 1"
              >
                <a class="dropdown-item articleUpdate">수정</a>
              </router-link>
              <a
                class="dropdown-item"
                @click="deleteArticle({articleId: articleData.articleId, categoryId: articleData.categoryId})"
              >삭제</a>
              <!--다시 보기!!!!!!!1 -->
            </div>
          </div>

          <!-- 게시물 신고 -->
          <div v-else>
            <b-button v-b-modal.modal-1 class="siren-btn">신고</b-button>

            <b-modal hide-footer="true" id="modal-1" title="신고 접수">
              <h6>신고 사유</h6>
              <div class="dropdown">
                <button
                  class="btn btn-secondary btn-sm dropdown-toggle"
                  type="button"
                  id="dropdownMenuButton"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >선택</button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(1)">욕설, 비난을 했어요!</a>
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(2)">약속 장소에 나오지 않았어요!</a>
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(3)">광고를 하고 있어요!</a>
                </div>
              </div>
              <h6 class="mt-3">사유 상세</h6>
              <b-form-textarea id="textarea-rows" rows="8" v-model="accuseArticleData.accuseReason"></b-form-textarea>
              <h6 class="mt-3">신고할 게시물 URL</h6>
              <b-form-input
                style="width: 400px"
                id="type-url"
                type="url"
                v-model="accuseArticleData.accuseUrl"
              ></b-form-input>
              <hr />
              <button
                @click="createArticleAccuse({ accuseArticleData })"
                class="btn btn-danger btn-sm"
              >신고접수</button>
            </b-modal>
          </div>
          <!-- 게시물 신고 -->

          <br />
        </div>

        <div class="in-the-top">
          <div class="writer">
            {{ articleData.writer }}
            <br />
          </div>
          <div class="create-time">{{ articleData.timeAgo }}</div>
        </div>
      </div>
      <hr class="top-line" />
      <!-- {{isImage}}{{noImage}} -->
      <!--이미지-->
      <div v-if="articleData.image" class="middle-row">
        <img class="post-img" :src="articleData.image" alt="..." />
      </div>

      <!--내용, 댓글-->
      <div class="detail-content" id="item-1">{{ articleData.description }}</div>
    </div>
    <commentList />
    <hr />
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import axios from "axios";
import { mapState, mapActions } from "vuex";
import commentList from "@/components/comments/commentList";
import articleLike from "@/components/articles/articleLike";

export default {
  name: "postDetail",
  components: {
    commentList,
    articleLike,
  },

  data() {
    return {
      isLiked: false,
      accuseArticleData: {
        reporter: "",
        defendant: "",
        accuseIndex: "",
        accuseValue: "",
        accuseKind: 0,
        accuseReason: "",
        accuseUrl: "",
        accuseConfirm: 0,
        token: this.$cookies.get("auth-token"),
      },
      isImage: false,
      noImage: false,
    };
  },

  computed: {
    ...mapState(["articleData", "userData"]),
  },

  methods: {
    ...mapActions([
      "getArticle",
      "getUserData",
      "createArticleAccuse",
      "deleteArticle",
    ]),
    getImg() {
      if (this.articleData.image) {
        this.isImage = true;
      } else {
        this.noImage = true;
      }
    },
    likeCheck() {
      const auth = { token: this.$cookies.get("auth-token") };
      axios
        .post(BACK_URL + `/likedcheck/${this.$route.params.ID}`, auth)
        .then((response) => {
          this.isLiked = response.data.isLiked;
        })
        .catch((err) => console.log(err));
    },
    likeChange() {
      this.getArticle(this.$route.params.ID);
      this.likeCheck();
    },
    cutDate(date) {
      let CD = date + "";
      const year = CD.substring(0, 4) + ".";
      const month = CD.substring(5, 7) + ".";
      const day = CD.substring(8, 10) + ". ";
      const hour = CD.substring(11, 13) + ".";
      const minute = CD.substring(14, 16) + ".";
      const res = year + month + day + hour + minute;
      return res;
    },
    // 신고 유형 변경
    changeAccuseKind(kind) {
      this.accuseArticleData.accuseKind = kind;
      this.linkArticleData();
      this.linkUserData();
    },
    // 해당 articleData 연결
    linkArticleData() {
      this.accuseArticleData.accuseIndex = 1;
      this.accuseArticleData.accuseValue = this.articleData.articleId;
      this.accuseArticleData.defendant = this.articleData.writer;
    },
    // 해당 userData 연결
    linkUserData() {
      this.accuseArticleData.reporter = this.userData.nickname;
    },
  },

  created: function () {
    this.getArticle(this.$route.params.ID);
    // this.getImg();
    this.getUserData();
    this.likeCheck();
  },
};
</script>

<style>
.post-img {
  width: 100%;
  height: 450px;
  margin: 10px;
}
.no-middle {
  display: none;
}
</style>
