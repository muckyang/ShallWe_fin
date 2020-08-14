<template>
  <div class="container">
    <div class="review-container">
      <div class="review-info">
        <div class="review-title">{{review.title}}</div>
        <div class="review-writer">{{review.writer}}</div>
        <div class="review-time">{{review.timeAgo}}</div>
        <div class="comment-drop dropdown dropleft" v-if="review.userId === userData.userId">
          <button type="button" class="comment-btn" data-toggle="dropdown">
            <i class="fas fa-ellipsis-v"></i>
          </button>
          <div class="dropdown-menu">
            <router-link
              class="reviewUpdate"
              :to="{
                  name: 'postUpdate',
                  params: { ID: this.review.articleId},
                }"
            >
              <a class="dropdown-item">수정</a>
            </router-link>
            <a class="dropdown-item">삭제</a>
          </div>
        </div>
      </div>
      <div class="review-like-comment">
        <div class="review-like">
          <reviewLike @like-change="likeChange" :isLiked="isLiked" :review="review" />
          {{review.likeNum}}
        </div>
        <div class="review-comment">
          <i class="far fa-comment-dots"></i>
          {{review.commentNum}}
        </div>
      </div>
      <div class="arrow">
        <i class="fas fa-angle-double-down"></i>
      </div>
      <!-- <img class="review-img" :src="review.image" alt="..." />
      {{review.description}}-->
    </div>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import axios from "axios";
import { mapState, mapActions } from "vuex";
import commentList from "@/components/comments/commentList";
import reviewLike from "@/components/reviews/reviewLike";

export default {
  name: "reviewDetail",
  components: {
    commentList,
    reviewLike,
  },
  props: {
    review: Object,
  },
  data() {
    return {
      isLiked: false,
      likeFlag: false,
    };
  },
  computed: {
    ...mapState(["articleData", "userData"]),
  },
  methods: {
    ...mapActions(["getArticle", "getUserData", "createArticleAccuse"]),
    likeCheck() {
      const auth = { token: this.$cookies.get("auth-token") };
      axios
        .post(BACK_URL + `/likedcheck/${this.review.articleId}`, auth)
        .then((response) => {
          this.isLiked = response.data.isLiked;
        })
        .catch((err) => console.log(err));
    },
    likeChange() {
      this.likeCheck();
    },
  },
  created: function () {
    this.likeCheck();
  },
};
</script>

<style>
.review-container {
  border: 1px solid rgb(225, 225, 233);
  border-radius: 5px;
  margin-bottom: 15px;
  display: flex;
  flex-direction: column;
}
.review-time {
  /* display: flex; */
  /* justify-content: flex-end; */
  /* margin: 10px 8px 0 8px; */
}
.review-info {
  display: flex;
  justify-content: space-between;
  /* border: 1px solid red; */
  padding: 10px;
}
.review-title {
  font-weight: bold;
  font-size: 20px;
}
.review-title,
.review-writer,
.review-time {
  letter-spacing: -0.6px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  line-height: 1.2;
}
.review-like-comment {
  display: flex;
  justify-content: flex-end;
}
.review-like,
.review-comment {
  display: flex;
  margin: 0 8px;
}
</style>