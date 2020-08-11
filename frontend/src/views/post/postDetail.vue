<template>
  <div>
    <div>{{articleData.title}}</div>
    <div>{{articleData.description}}</div>
    <a href="/user/accuseform" class="btn btn-primary">{{articleData.writer}}</a>
    <div>{{articleData.createTime}}</div>
    <commentList />
    <router-link :to="{name:'postUpdate', params: {ID:this.$route.params.ID}}">수정</router-link>
    <button>삭제</button>
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
    };
  },

  computed: {
    ...mapState(["articleData", "userData"]),
  },

  methods: {
    ...mapActions(["getArticle", "getUserData"]),
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
  },
};
</script>

<style>
</style>