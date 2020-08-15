<template>
  <div class="container">
    <div class="review-container">
      <div class="review-drop dropdown dropleft" v-if="review.userId === userData.userId">
        <button type="button" class="review-btn" data-toggle="dropdown">
          <i class="fas fa-ellipsis-h"></i>
        </button>
        <div class="dropdown-menu">
          <router-link
            class="reviewUpdate"
            :to="{
                      name: 'reviewUpdate',
                      params: { ID: this.review.articleId},
                    }"
          >
            <a class="dropdown-item">수정</a>
          </router-link>
          <a class="dropdown-item">삭제</a>
        </div>
      </div>
      <div class="review-top">
        <div class="review-info">
          <div class="review-title">{{review.title}}</div>
          <div class="review-writer">{{review.writer}}</div>
          <div class="review-time">{{review.timeAgo}}</div>
        </div>
        <div class="review-like-comment">
          <div class="like-container">
            <reviewLike @like-change="likeChange" :isLiked="isLiked" :review="review" />
            {{review.likeNum}}
          </div>
          <div class="review-comment">
            <div class="comment-icon">
              <i class="far fa-comment-dots"></i>
            </div>
            {{review.commentNum}}
          </div>
        </div>
      </div>
      <!--화살표 클릭하면 보일 내용들. 내용,이미지-->
      <div class="collapse multi-collapse review-bottom" :id="'review'+review.articleId">
        <div v-if="review.image">
          <b-img :src="review.image" fluid alt="Responsive image" class="review-img"></b-img>
        </div>
        <div class="review-content">{{review.description}}</div>
      </div>
      <div class="arrow">
        <a
          data-toggle="collapse"
          :href="'#'+toggleId(review.articleId)"
          role="button"
          aria-expanded="false"
          aria-controls="review11"
        >
          <i class="fas fa-angle-double-down"></i>
        </a>
      </div>
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
      mainProps: { width: 450, height: 450 },
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
    toggleId(num) {
      const result = "review" + num;
      return result;
    },
    created: function () {
      this.likeCheck();
    },
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
.review-drop {
  display: flex;
  justify-content: flex-end;
}
.review-btn {
  border: none;
  outline: none;
  background-color: transparent;
  color: rgb(197, 192, 192);
}
.review-info {
  display: flex;
  justify-content: space-between;
  padding: 10px;
}
.review-title {
  font-weight: bold;
  font-size: 20px;
}
.review-time {
  opacity: 0.7;
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
  vertical-align: middle;
}
.like-container,
.review-comment {
  display: flex;
}
.like-container {
  margin: 0 5px;
}
.comment-icon {
  margin: 0 3px;
}
.review-bottom {
  border: 1px solid red;
}
.review-img {
  border: 1px solid blue;
}
</style>