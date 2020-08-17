<template>
  <div class="review-comment-box">
    <div class="review-comment-start">
      <i class="far fa-comment-dots"></i>
      댓글 {{commentList.length}}
    </div>

    <!--댓글 보여주는 공간-->
    <reviewCommentListItem
      v-for="comment in commentList"
      :key="comment.commentId"
      :comment="comment"
      @re-render="getArticle(reviewId)"
    />
    <!--댓글 등록 공간-->
    <div class="review-comment-write">
      <div class="review-comment-text">
        <input
          class="review-comment-input"
          placeholder="댓글을 남겨보세요 :)"
          type="text"
          v-model="commentData.content"
        />
      </div>
      <div class="review-comment-submit">
        <button type="button" class="comment-submit-btn" @click="createComment">등록</button>
      </div>
    </div>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import axios from "axios";
import { mapState, mapActions, mapMutations } from "vuex";
import reviewCommentListItem from "@/components/reviews/reviewCommentListItem";

export default {
  name: "commentList",
  components: {
    reviewCommentListItem,
  },
  props: {
    reviewId: Number,
    commentList: Array,
  },
  data() {
    return {
      commentData: {
        articleId: this.reviewId,
        content: "",
        token: this.$cookies.get("auth-token"),
      },
    };
  },
  computed: {
    ...mapState(["comments"]),
  },
  methods: {
    ...mapActions(["getArticle"]),
    ...mapMutations(["GET_COMMENTS"]),
    createComment() {
      if (this.commentData.content) {
        axios
          .post(`${BACK_URL}/comment/create`, this.commentData)
          .then(() => {
            console.log("댓글 등록 완료");
            this.commentData.content = "";
            this.getArticle(this.reviewId); //다시 보기
          })
          .catch((err) => {
            console.error(err);
          });
      } else {
        alert("댓글을 써주세요!");
      }
    },
    // getComments(){
    //   axios.get(`${BACK_URL}/comment/read/${this.$route.params.ID}`)
    //     .then((response)=>{
    //       this.GET_COMMENTS(response.data.commentList)
    //     })
    //     .catch((err)=>{
    //       console.error(err)
    //     })
    // },
  },
  created() {
    this.getArticle(this.reviewId);
  },
};
</script>

<style>
.review-comment-box {
  /* border: 1px solid red; */
  width: 100%;
  margin: 0 auto;
}
.review-comment-start {
  width: 95%;
  padding: 0 0 0.5% 0.5%;
  text-align: left;
  border-bottom: 0.5px solid rgb(218, 215, 215);
  margin: 0 auto;
}
.review-comment-start-line {
  width: 85%;
}
.review-comment-write {
  border: 2px solid rgba(0, 0, 0, 0.1);
  border-radius: 6px;
  width: 95%;
  margin: 1% auto;
  padding: 16px 10px 10px 18px;
  display: flex;
  flex-direction: column;
}
.review-comment-submit {
  display: flex;
  justify-content: flex-end;
  margin: 0 4px 3px 0;
}
.review-comment-submit-btn {
  font-weight: bold;
  border: none;
  outline: none;
  background-color: transparent;
  color: grey;
}
.review-comment-text {
  /* border: none;
  outline: none; */
  display: block;
}
.review-comment-input {
  border: none;
  outline: none;
  width: 100%;
}
.review-comment-input:focus::placeholder {
  color: transparent;
}
</style>