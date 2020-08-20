<template>
  <div>
    <b-media tag="li" class="text-left">

      <div class="d-flex justify-content-between">
        <small v-if="accuse.accuseIndex === 1">
          <span class="badge badge-pill badge-dark mr-2">게시물 신고</span>
          <span v-if="accuse.accuseKind == 1">욕설</span>
          <span v-if="accuse.accuseKind == 2">노쇼</span>
          <span v-if="accuse.accuseKind == 3">광고</span>
        </small>
        <small v-if="accuse.accuseIndex === 2">
          <span class="badge badge-pill badge-dark mr-2">댓글 신고</span>
          <span v-if="accuse.accuseKind == 1">욕설</span>
          <span v-if="accuse.accuseKind == 2">노쇼</span>
          <span v-if="accuse.accuseKind == 3">광고</span>
        </small>
        <div>
          <div class="article-drop dropdown dropleft">
            <button type="button" class="article-btn" data-toggle="dropdown">
              <i class="fas fa-ellipsis-v"></i>
            </button>
            <div class="dropdown-menu">
              <a :href="accuse.accuseUrl" class="dropdown-item">신고상세</a>
              <a  @click="yesAccuse()" class="dropdown-item">신고접수</a>
              <a  @click="noAccuse()" class="dropdown-item">허위신고</a>
            </div>
          </div>
        </div>
      </div>
      <h5 class="mt-0 mb-2 mt-1"># {{ accuse.accuseId }}. "{{ accuse.reporter }}" 님의 신고 내용</h5>
      <h6>피신고자: {{ accuse.defendant }}</h6>
      <p class="mb-0">{{ accuse.accuseReason }}</p>
    </b-media>
    <hr>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import axios from "axios";
import cookies from "vue-cookies";

export default {
  name: "accuseListItem",
  props: {
    accuse: Object,
  },
  data() {
    return {};
  },
  methods: {
    decideAccuse() {
      this.accuse.token = this.$cookies.get("admin-token");
      console.log(this.accuse);
      axios
        .post(`${BACK_URL}/accuse/applyto`, this.accuse)
        .then(() => {
          console.log(this.accuse, "AAAAAAAAAAAAAAA");
        })
        .catch((err) => {
          console.error(err);
        });
    },
    yesAccuse() {
      this.accuse.accuseConfirm = 1;
      this.decideAccuse();
      console.log(this.accuse, "BBBBB");
    },
    noAccuse() {
      this.accuse.accuseConfirm = 2;
      this.decideAccuse();
      console.log(this.accuse, "BBBBB");
    },
  },
};
</script>

<style>
</style>