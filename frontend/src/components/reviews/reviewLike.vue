<template>
  <div>
    <button v-if="!isLiked" @click="like" class="review-like">
      <i class="far fa-heart"></i>
    </button>

    <button v-if="isLiked" @click="like" class="review-like">
      <i class="fas fa-heart"></i>
    </button>
  </div>
</template>

<script>
import axios from "axios";
import { mapState } from "vuex";
const BACK_URL = process.env.VUE_APP_BACK_URL;

export default {
  name: "reviewLike",
  props: {
    isLiked: Boolean,
    review: Object,
  },
  methods: {
    like() {
      this.review.token = this.$cookies.get("auth-token");
      axios
        .post(`${BACK_URL}/like/${this.review.articleId}`, this.review)
        .then(() => {
          this.$emit("like-change");
        })
        .catch((err) => console.log(err));
    },
  },
};
</script>

<style>
.review-like {
  border: none;
  outline: none;
  background-color: transparent;
}
.review-like:focus {
  border: none;
  outline: none;
}
</style>