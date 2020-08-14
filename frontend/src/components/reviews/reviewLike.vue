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
  },
  methods: {
    like() {
      const auth = this.$cookies.get("auth-token");
      axios
        .post(
          `${BACK_URL}/like/${this.articleData.articleId}`,
          this.articleData
        )
        .then(() => {
          this.$emit("like-change");
        })
        .catch((err) => console.log(err.response.data));
    },
  },
  computed: {
    ...mapState(["articleData"]),
  },
};
</script>

<style>
.review-like {
  border: none;
  outline: none;
  background-color: transparent;
}
</style>