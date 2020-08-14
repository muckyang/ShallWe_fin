<template>
  <div>
    <div class="d-flex">
      <div class="comment-box">
        <div class="comment-user">
          {{ comment.nickname }}
          <div
            class="comment-drop dropdown dropleft"
            v-if="comment.userId === userData.userId"
          >
            <button type="button" class="comment-btn" data-toggle="dropdown">
              <i class="fas fa-ellipsis-v"></i>
            </button>
            <div class="dropdown-menu">
              <a class="dropdown-item" @click="showInput" v-if="!flag">수정</a>
              <a class="dropdown-item" @click="deleteComment">삭제</a>
            </div>
          </div>

          <!-- 댓글 신고 -->
          <div v-else>
            <b-button v-b-modal.modal-1 class="btn btn-danger btn-sm"
              >신고</b-button
            >

            <b-modal id="modal-1" title="신고 접수">
              <h6>신고 사유</h6>
              <div class="dropdown">
                <button
                  class="btn btn-secondary dropdown-toggle"
                  type="button"
                  id="dropdownMenuButton"
                  data-toggle="dropdown"
                  aria-haspopup="true"
                  aria-expanded="false"
                >
                  선택
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(1)"
                    >욕설</a
                  >
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(2)"
                    >노쇼</a
                  >
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(3)"
                    >광고</a
                  >
                </div>
              </div>
              <h6>신고할 댓글의 게시물 URL</h6>
              <b-form-input
                id="type-url"
                type="url"
                v-model="accuseCommentData.accuseUrl"
              ></b-form-input>
              <h6>사유 상세</h6>
              <b-form-textarea
                id="textarea-rows"
                rows="8"
                v-model="accuseCommentData.accuseReason"
              ></b-form-textarea>
              <button @click="createCommentAccuse({ accuseCommentData })">
                신고접수
              </button>
            </b-modal>
          </div>
          <!-- 댓글 신고 -->
        </div>
        <div class="comment-content">{{ comment.content }}</div>
        <div class="comment-create-time">{{ cutDate(comment.createTime) }}</div>
      </div>
    </div>

    <!-- 댓글 수정 input 공간-->

    <div v-if="flag" class="comment-update-write">
      <div class="comment-update-text">
        <input class="comment-input" type="text" v-model="comment.content" />
      </div>
      <div class="comment-update-submit">
        <button
          type="button"
          v-if="flag"
          class="comment-update-btn"
          @click="updateCancel"
        >
          취소
        </button>
        <button
          type="button"
          v-if="flag"
          class="comment-update-btn"
          @click="updateComment"
        >
          수정
        </button>
      </div>
    </div>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import axios from "axios";
import { mapState, mapActions } from "vuex";

export default {
  name: "commentListItem",
  props: {
    comment: Object,
  },
  data() {
    return {
      watchFlag: false,
      flag: false,
      canceldata: {
        articleId: this.comment.articleId,
        commentId: this.comment.commentId,
        content: this.comment.content,
        userId: this.comment.userId,
        nickname: this.comment.nickname,
        createTime: this.comment.createTime,
        token: this.$cookies.get("auth-token"),
      },
      accuseCommentData: {
        reporter: "",
        defendant: this.comment.nickname,
        accuseIndex: 2,
        accuseValue: this.comment.commentId,
        accuseKind: 0,
        accuseReason: "",
        accuseUrl: "",
        accuseConfirm: 0,
        token: this.$cookies.get("auth-token"),
      },
    };
  },
  computed: {
    ...mapState(["userData"]),
  },
  methods: {
    ...mapActions(["createCommentAccuse"]),
    cutDate(date) {
      let CD = date + "";
      const year = CD.substring(0, 4) + ".";
      const month = CD.substring(5, 7) + ".";
      const day = CD.substring(8, 10) + ". ";
      const hour = CD.substring(11, 13) + ":";
      const minute = CD.substring(14, 16) + "";
      const res = year + month + day + hour + minute;
      return res;
    },
    linkCommentData() {
      this.accuseCommentData.accuseValue = this.comment.commentId
    },
    // 신고 유형 변경
    changeAccuseKind(kind) {
      this.accuseCommentData.accuseKind = kind;
      this.linkUserData();
      this.linkCommentData();
    },
    // 해당 userData 연결
    linkUserData() {
      this.accuseCommentData.reporter = this.userData.nickname;
    },

    deleteComment() {
      const auth = { token: this.$cookies.get("auth-token") };
      axios
        .post(BACK_URL + `/comment/delete/${this.comment.commentId}`, auth)
        .then(() => {
          this.watchFlag = true;
        })
        .catch((err) => {
          console.error(err);
        });
    },
    showInput() {
      this.flag = true;
    },
    updateComment() {
      axios
        .post(BACK_URL + "/comment/update", this.comment)
        .then((response) => {
          this.flag = false;
          //~~~~~~~~~~~~~~~~~~~~~~~~~중요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
          // this.commentData=response.data
          //다시 댓글을 전부 다시 받는 것이 아니라 수정 후 응답으로 수정된 댓글만 다시 받아온다.
          //~~~~~~~~~~~~~~~~~~~~~~~~~중요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        })
        .catch((err) => {
          console.error(err);
        });
    },
    updateCancel() {
      console.log(this.canceldata);
      console.log(this.comment, "바뀐 댓글데이타");
      this.comment = this.canceldata;
      console.log(this.comment, "취소 후");
      this.watchFlag = true;
      this.flag = false;
    },
  },
  watch: {
    watchFlag() {
      this.$emit("re-render");
    },
  },
  // created(){
  //     console.log(this.comment)
  // }
};
</script>

<style>
/* @import '../node_modules/bootstrap-vue/src/components/dropdown/_dropdown.scss'; */
/* @import url('node_modules/bootstrap-vue/src/components/dropdown/_dropdown.scss'); */
.comment-box {
  border-bottom: 1px solid rgb(237, 237, 240);
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 1%;
  padding: 0 0 0 0.3%;
}
.comment-user {
  text-align: left;
  font-weight: bold;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.comment-content {
  text-align: left;
}
.comment-create-time {
  text-align: left;
  color: rgb(145, 141, 141);
  font-size: small;
  margin-bottom: 1%;
}
.comment-drop {
  display: flex;
  flex-direction: row;
  width: 3%;
}
.comment-btn {
  background-color: transparent;
  color: rgb(182, 182, 182);
  padding: 0;
  font-size: 15px;
  border: none;
  cursor: pointer;
  width: 100%;
  outline: none;
}
.comment-drop:hover {
  border: none;
  outline: none;
}
.comment-btn:hover {
  border: none;
  outline: none;
}
.comment-drop:hover .comment-btn {
  background-color: transparent;
  color: rgb(145, 141, 141);
  border: none;
  outline: none;
}
.comment-update-write {
  border: 2px solid rgba(0, 0, 0, 0.1);
  border-radius: 6px;
  width: 100%;
  margin: 1% auto;
  padding: 16px 10px 10px 18px;
  display: flex;
  flex-direction: column;
}
.comment-update-submit {
  display: flex;
  justify-content: flex-end;
  margin: 0 4px 3px 0;
}
.comment-update-btn {
  font-weight: bold;
  border: none;
  outline: none;
  background-color: transparent;
  color: grey;
}
.comment-update-text {
  /* border: none;
  outline: none; */
  display: block;
}
.comment-update-input {
  border: none;
  outline: none;
  width: 100%;
}
</style>
