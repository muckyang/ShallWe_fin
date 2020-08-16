<template>
  <div>
    <div>
      <b-card>
        <b-card-title># {{ accuse.accuseId }}. "{{ accuse.reporter }}" 님의 신고 내용</b-card-title>
        <b-card-sub-title>피신고자: {{ accuse.defendant }}</b-card-sub-title>

        <b-card-text v-if="accuse.accuseIndex === 1">
          <span class="badge badge-pill badge-light mr-2">게시물 신고</span>
          <span v-if="accuse.accuseKind == 1">욕설</span>
          <span v-if="accuse.accuseKind == 2">노쇼</span>
          <span v-if="accuse.accuseKind == 3">광고</span>
        </b-card-text>
        <b-card-text v-if="accuse.accuseIndex === 2">
          <span class="badge badge-pill badge-dark mr-2">댓글 신고</span>
          <span v-if="accuse.accuseKind == 1">욕설</span>
          <span v-if="accuse.accuseKind == 2">노쇼</span>
          <span v-if="accuse.accuseKind == 3">광고</span>
        </b-card-text>

        <b-card-text
          style="border: solid 0.5px gray; width: 70%; margin: auto;"
        >{{ accuse.accuseReason }}</b-card-text>
        <button class="btn btn-info btn-sm mt-3">
          <a :href="accuse.accuseUrl" class="text-decoration-none text-white">신고상세</a>
        </button>
        <button class="btn btn-danger btn-sm mt-3" @click="yesAccuse()">신고접수</button>
        <button class="btn btn-secondary btn-sm mt-3" @click="noAccuse()">허위신고</button>
      </b-card>
    </div>
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
      this.accuse.accuseConfirm = 1;
      this.decideAccuse();
      console.log(this.accuse, "BBBBB");
    },
  },
};
</script>

<style>
</style>