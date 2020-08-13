<template>
  <div>
    <div>
      <b-card>
        <b-card-title># {{ accuse.accuseId }}. "{{ accuse.reporter }}" 님의 신고 내용</b-card-title>
        <b-card-sub->피신고자: {{ accuse.defendant }}</b-card-sub->
        <b-card-text>신고 유형: [{{ accuse.accuseIndex }}] {{ accuse.accuseKind }}</b-card-text>
        <b-card-text>{{ accuse.accuseReason }}</b-card-text>

        <button class="btn btn-info btn-sm"><a :href="accuse.accuseUrl" class="text-decoration-none text-white">신고상세</a></button>
        <button class="btn btn-danger btn-sm" @click="yesAccuse()">신고접수</button>
        <button class="btn btn-secondary btn-sm" @click="noAccuse()">허위신고</button>
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
    return {
    };
  },
  methods: {
    decideAccuse() {
      this.accuse.token=this.$cookies.get('admin-token')
      console.log(this.accuse)
      axios
        .post(`${BACK_URL}/accuse/applyto`, this.accuse)
        .then(() => {
          console.log(this.accuse, 'AAAAAAAAAAAAAAA')
        })
        .catch((err) => {
          console.error(err);
        });
    },
    yesAccuse() {
      this.accuse.accuseConfirm = 1
      this.decideAccuse();
      console.log(this.accuse, 'BBBBB')
    },
    noAccuse() {
      this.accuse.accuseConfirm = 1
      this.decideAccuse();
      console.log(this.accuse, 'BBBBB') 
    },
  }
};
</script>

<style>

</style>