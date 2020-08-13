<template>
  <div class="mt-5">
    <div class="container detail">
      <!--Top 부분. 제목, 작성자, create time -->
      <div class="top">
        <div class="top-row">
          <div class="detail-title">{{ articleData.title }}</div>
          <div class="article-drop dropdown dropleft" v-if="articleData.userId === userData.userId">
            <button type="button" class="article-btn" data-toggle="dropdown">
              <i class="fas fa-ellipsis-v"></i>
            </button>
            <div class="dropdown-menu">
              <router-link
                class="articleUpdate"
                :to="{name:'articleUpdate',
                params: {ID:this.$route.params.ID}}"
              >
                <a class="dropdown-item articleUpdate">수정</a>
              </router-link>
              <a class="dropdown-item">삭제</a>
              <!--다시 보기!!!!!!!1 -->
            </div>
          </div>

          <!-- 게시물 신고 -->
          <div v-else>
            <b-button v-b-modal.modal-1 class="btn btn-danger btn-sm">신고</b-button>

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
                >선택</button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(1)">욕설</a>
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(2)">노쇼</a>
                  <a class="dropdown-item" href="#" @click="changeAccuseKind(3)">광고</a>
                </div>
              </div>
              <h6>신고할 게시물 URL</h6>
              <b-form-input id="type-url" type="url" v-model="accuseArticleData.accuseUrl"></b-form-input>
              <h6>사유 상세</h6>
              <b-form-textarea id="textarea-rows" rows="8" v-model="accuseArticleData.accuseReason"></b-form-textarea>
              <button @click="createArticleAccuse({accuseArticleData})">신고접수</button>
            </b-modal>
          </div>
          <!-- 게시물 신고 -->

          <br />
        </div>
        <div
          :class="{ tagList : tagsLength(articleData.tags) , nomargin : !tagsLength(articleData.tags)  }"
        >
          <button class="tag" v-for="tag in articleData.tags" :key="tag.no">#{{tag}}</button>
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

      <!--중간 부분. 이미지, 주요 정보들 -->
      <div class="middle-row">
        <img class="MyImage" :src="articleData.image" alt="..." />
        <div class="articleInfo">
          <div class="detail-info">
            <div class="detail-address">만남의 장소: {{articleData.address}}</div>
            <div class="detail-price">
              <div class="min-price">최소 주문 금액: {{articleData.minPrice}}원</div>
              <div class="min-price">모인 금액: {{articleData.sumPrice}}원</div>
            </div>
            <div class="detail-endTime">마감 시간: {{cutDate(articleData.endTime)}}까지</div>
          </div>
          <div class="detail-btns">
            <articleLike @like-change="likeChange" :isLiked="isLiked" />

            <button @click="shareContent" class="detail-share">
              <a href="javascript:;" class="kakao-share" @click="shareContent" id="kakao-link">
                <img src="../../assets/img/kakao_btn.png" class="kakao" alt="삭제" />
                공유
              </a>
            </button>
            <b-button
              id="show-btn"
              v-b-modal.join-modal
              class="detail-join"
              v-if="articleData.userId != userData.userId"
            >
              <i class="fas fa-user-plus"></i> 참여
            </b-button>
            <b-modal
              id="join-modal"
              size="xl"
              ref="modal"
              title="참여하기"
              class="form-input"
              @ok="sendJoinData"
            >
              <form ref="form" class="form-input" @submit.stop.prevent="handleSubmit">
                <b-form-group label="제목" label-for="title-input">
                  <b-form-input id="title-input" v-model="joinData.title" class="form-input"></b-form-input>
                </b-form-group>

                <b-form-group label="url" label-for="url-input">
                  <b-form-input id="url-input" v-model="joinData.url" class="form-input"></b-form-input>
                </b-form-group>

                <b-form-group label="가격(숫자만 입력하세요.)" label-for="price-input">
                  <b-form-input id="price-input" v-model="joinData.price" class="form-input"></b-form-input>
                </b-form-group>

                <b-form-group label="요구사항" label-for="order-input">
                  <b-form-input id="order-input" v-model="joinData.description" class="form-input"></b-form-input>
                </b-form-group>
              </form>
            </b-modal>

            <!--임시modal-->
            <b-modal id="update-modal" size="xl" ref="modal" title="수정하기" @ok="updateJoinData">
              <form ref="form" @submit.stop.prevent="handleSubmit">
                <b-form-group label="제목" label-for="title-input">
                  <b-form-input id="title-input" v-model="joinData.title"></b-form-input>
                </b-form-group>

                <b-form-group label="url" label-for="url-input">
                  <b-form-input id="url-input" v-model="joinData.url"></b-form-input>
                </b-form-group>

                <b-form-group label="가격" label-for="price-input">
                  <b-form-input id="price-input" v-model="joinData.price"></b-form-input>원
                </b-form-group>

                <b-form-group label="요구사항" label-for="order-input">
                  <b-form-input id="order-input" v-model="joinData.description"></b-form-input>
                </b-form-group>
              </form>
            </b-modal>
          </div>
        </div>
      </div>

      <!--하단 부분. 내용,(지도) -->
      <div class="detail-content" id="item-1">{{ articleData.description }}</div>
    </div>
    <div class="kakao-map">
      <kakaoMapForDetail />
    </div>
    <div class="members">
      <div class="members-start">
        <i class="fas fa-users"></i>
        참여 멤버 (총 {{articleData.partList.length}}명)
      </div>
      <div class>
        <div class="member-list" v-for="participant in articleData.partList" :key="participant.no">
          <div class="member">
            <div class="member-writer">
              {{participant.writer}}
              <i
                v-if="articleData.userId === participant.userId"
                class="fas fa-crown"
              ></i>
            </div>
            <div
              class="article-drop dropdown dropleft"
              v-if="participant.userId === userData.userId"
            >
              <button type="button" class="article-btn" data-toggle="dropdown">
                <i class="fas fa-ellipsis-v"></i>
              </button>
              <div class="dropdown-menu">
                <b-button
                  variant="light"
                  size="sm"
                  v-b-modal.update-modal
                  v-if="participant.userId === userData.userId"
                >수정</b-button>
                <b-button
                  variant="light"
                  size="sm"
                  v-if="participant.userId === userData.userId"
                  @click="cancel(participant.no)"
                >삭제</b-button>
              </div>
            </div>
          </div>
          <div
            v-if="articleData.userId !== participant.userId"
            class="member-title"
          >제목: {{participant.title}}</div>
          <div class="member-price">가격: {{participant.price}}</div>
          <div
            v-if="articleData.userId !== participant.userId"
            class="member-content"
          >요구사항: {{participant.description}}</div>
        </div>
      </div>
    </div>
    <commentList />
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import axios from "axios";
import { mapState, mapActions } from "vuex";
import commentList from "@/components/comments/commentList";
import articleLike from "@/components/articles/articleLike";
import kakaoMapForDetail from "@/components/articles/kakaoMapForDetail";

export default {
  name: "articleDetail",
  components: {
    commentList,
    articleLike,
    kakaoMapForDetail,
  },
  data() {
    return {
      isLiked: false,
      joinData: {
        articleId: this.$route.params.ID,
        title: "",
        url: "",
        price: "",
        description: "",
        token: this.$cookies.get("auth-token"),
      },
      participants: {},
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
    };
  },
  computed: {
    ...mapState(["articleData", "userData"]),
  },
  methods: {
    ...mapActions(["getArticle", "getUserData", "createArticleAccuse"]),

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

    tagsLength(tagList) {
      if (tagList.length > 0) return true;
      else false;
    },

    cutDate(date) {
      let CD = date + "";
      const year = CD.substring(0, 4) + "년 ";
      const month = CD.substring(5, 7) + "월";
      const day = CD.substring(8, 10) + "일 ";
      const hour = CD.substring(11, 13) + "시";
      const minute = CD.substring(14, 16) + "분";
      const res = year + month + day + hour + minute;
      return res;
    },
    sendJoinData() {
      axios
        .post(`${BACK_URL}/participant/create`, this.joinData)
        .then((response) => {
          this.getparticipantData();
          alert("등록이 완료되었습니다!");
        })
        .catch((error) => {
          console.log(error);
          alert("이미 참가한 게시글 입니다.");
        });
    },
    updateJoinData() {
      this.joinData.articleId = this.articleData.articleId;
      axios
        .post(`${BACK_URL}/participant/update`, this.joinData)
        .then((response) => {
          this.getparticipantData();
          alert("수정이 완료되었습니다.");
        })
        .catch((err) => {
          console.log(err);
        });
    },
    getparticipantData() {
      axios
        .post(`${BACK_URL}/participant/read/${this.$route.params.ID}`)
        .then((response) => {
          this.articleData.partList = response.data.participantList;
        })
        .catch((err) => {
          console.log(err);
        });
    },
    cancel(no) {
      axios
        .post(`${BACK_URL}/participant/delete/${no}`)
        .then((response) => {
          this.getparticipantData();
          console.log("삭제가 완료되었습니다.");
        })
        .catch((err) => {
          console.log(err);
        });
    },

    shareContent() {
      Kakao.Link.createDefaultButton({
        container: "#kakao-link",
        objectType: "feed",
        content: {
          title: "디저트 사진",
          description: "아메리카노, 빵, 케익",
          imageUrl:
            "http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg",
          link: {
            mobileWebUrl: "https://developers.kakao.com",
            androidExecParams: "test",
          },
        },
        social: {
          likeCount: 10,
          commentCount: 20,
          sharedCount: 30,
        },
        buttons: [
          {
            title: "웹으로 이동",
            link: {
              mobileWebUrl: "https://developers.kakao.com",
            },
          },
          {
            title: "앱으로 이동",
            link: {
              mobileWebUrl: "https://developers.kakao.com",
            },
          },
        ],
        success: function (response) {
          console.log(response);
        },
        fail: function (error) {
          console.log(error);
        },
      });
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
  },
  created: function () {
    this.getArticle(this.$route.params.ID);
    this.getUserData();
    this.likeCheck();
  },
};
</script>

<style>
.kakao-map {
  display: flex;
  width: 100%;
  margin: 3% 0 0 0;
  padding: auto;
  justify-content: center;
  text-align: center;
}
.member-content,
.member-title,
.member-price,
.member-create-time {
  text-align: left;
}
.member-create-time {
  color: rgb(145, 141, 141);
  font-size: small;
  margin-bottom: 1%;
}
.member-list {
  border-bottom: 1px solid rgb(237, 237, 240);
  display: flex;
  flex-direction: column;
  width: 100%;
  margin-bottom: 1.5%;
  padding: 0 0 1.5% 0.3%;
}
.member {
  text-align: left;
  font-weight: bold;
  display: flex;
  flex-direction: row;
  justify-content: space-between;
}
.members {
  width: 75%;
  margin: 3% auto;
}
.fa-crown {
  color: rgb(245, 230, 24);
  vertical-align: text-top;
}
.members-start {
  width: 100%;
  padding: 0 0 0.5% 0.5%;
  text-align: left;
  border-bottom: 0.5px solid rgb(218, 215, 215);
  margin: 0 0 2% 0;
}
@media screen and (max-width: 768px) {
  b-modal:before {
    display: inline-block;
    vertical-align: middle;
    content: " ";
    height: 100%;
  }
}
b-modal {
  display: inline-block;
  text-align: left;
  vertical-align: middle;
}
b-modal.form-input {
  width: 27%;
  height: 85%;
  margin: 0;
  padding: 0;
}
.detail {
  border: 1px solid rgb(224, 221, 221);
  border-radius: 1.5%;
  width: 75%;
}
.MyImage {
  width: 45%;
  height: 300px;
  margin: 10px;
}
.totheContent {
  background-color: #676d74;
  color: white;
  padding: 8px;
  border-radius: 5px;
}
a {
  justify-content: bottom;
  text-decoration: none;
  color: black;
}

.top {
  margin-bottom: 2%;
  margin-top: 2%;
  text-align: left;
}
.top-row {
  display: flex;
  justify-content: space-between;
}
.detail-title {
  font-size: x-large;
  font-weight: bold;
  width: 90%;
}
.create-time {
  font-size: small;
  font-weight: normal;
  opacity: 0.6;
}
.top-line {
  margin: 0% 0% 2% 0%;
}
.map-btn {
  font-size: medium;
  color: white;
  border: none;
  background-color: rgb(15, 190, 182);
  border-radius: 3px;
  padding: 1% 2% 1% 2%;
}
.middle-row {
  display: flex;
}
.articleInfo {
  text-align: left;
  padding: 15px 35px 35px 40px;
  width: 65%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
}
@media screen and (max-width: 768px) {
  .middle-row {
    display: flex;
    flex-direction: column;
    text-align: left;
  }
  .articleInfo {
    margin: 0;
    padding: 0;
    width: 100%;
  }
  .detail-content {
    margin: 0;
    padding: 0;
    width: 100%;
  }
}
.detail-address {
  font-size: large;
  font-weight: bold;
  margin-bottom: 5px;
}
.detail-price {
  margin: 5% 0 0 0;
}
.detail-endTime {
  margin: 5% 0 0 0;
  font-size: medium;
}
.kakao {
  height: 25px;
  width: 25px;
}
.kakao-share {
  text-decoration: none;
  color: white;
  font-weight: normal;
  text-align: center;
  vertical-align: middle;
}
.kakao-share:hover {
  text-decoration: none;
  color: white;
}
.detail-content {
  margin: 30px 0;
  padding: 0 15px;
  text-align: left;
}
.detail-btns {
  width: 100%;
  display: flex;
  justify-content: flex-end;
  font-size: 17px;
  text-align: center;
}
.detail-btns .detail-share {
  display: block;
  text-align: center;
  background: rgb(250, 227, 1);
  border-radius: 3px;
  box-shadow: 0 10px 20px -8px rgb(202, 190, 21);
  padding: 10px 12px;
  font-size: 17px;
  cursor: pointer;
  border: none;
  outline: none;
  color: #ffffff;
  text-decoration: none;
  -webkit-transition: 0.3s ease;
  transition: 0.3s ease;
  margin: 0 1% 0 2%;
}
.detail-btns .detail-share:hover {
  transform: translateY(-3px);
}
.detail-btns .detail-share .fa {
  margin-right: 5px;
}
.detail-btns .detail-join {
  display: block;
  text-align: center;
  background: #31312f;
  border-radius: 3px;
  box-shadow: 0 10px 20px -8px rgb(27, 27, 25);
  padding: 10px 12px;
  font-size: 17px;
  cursor: pointer;
  border: none;
  outline: none;
  color: #ffffff;
  text-decoration: none;
  -webkit-transition: 0.3s ease;
  transition: 0.3s ease;
  margin: 0 1% 0 1%;
}
.detail-btns .detail-join:hover {
  transform: translateY(-3px);
}
.detail-btns .detail-join .fa {
  margin-right: 5px;
}
.article-drop {
  display: flex;
  flex-direction: row;
  width: 3%;
}
.article-btn {
  background-color: transparent;
  color: rgb(182, 182, 182);
  padding: 0;
  font-size: 15px;
  border: none;
  cursor: pointer;
  width: 100%;
  outline: none;
}
.article-drop:hover {
  border: none;
  outline: none;
}
.article-btn:hover {
  border: none;
  outline: none;
}
.router-link-active {
  color: white;
}
.router-link-exact-active a {
  color: white;
}
.articleUpdate {
  text-decoration: none;
  width: 100%;
}
.article:hover {
  text-decoration: none;
}
.share {
  display: flex;
}
.dropdown-item {
  cursor: pointer;
  text-decoration: none;
}
.participants {
  width: 75%;
  margin: auto;
  text-align: left;
  margin-top: 3%;
  border: 1px solid rgb(179, 175, 175);
  border-radius: 3px;
}
.form-input {
  width: 150%;
}
.tagList {
  display: flex;
  flex-direction: row;
  justify-content: flex-start;
  margin: 0.5% 0 1% 0;
}
.nomargin {
  margin: 0;
}
.tag {
  margin-right: 1%;
  border: none;
  outline: none;
  border-radius: 3px;
}
</style>