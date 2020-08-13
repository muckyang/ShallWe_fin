<template>
  <div class="review-list mt-5">
    <h5>쉘위 후기들</h5>
    <div v-for="review in reviews" :key="review.articleId">
      <div class="review-container">
        {{review.title}}
        {{review.timeAgo}}
        {{review.writer}}
        <img
          class="review-img"
          :src="review.image"
          alt="..."
        />
        {{review.description}}
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import axios from "axios";
const BACK_URL = process.env.VUE_APP_BACK_URL;

export default {
  name: "reviewList",
  data() {
    return {
      reviews: [],
      token: this.$cookies.get("auth-token"),
    };
  },
  methods: {
    getReviews() {
      const auth = this.token;
      axios
        .post(`${BACK_URL}/post/read/2/102`, { auth })
        .then((response) => {
          this.reviews = response.data.postList;
          console.log(this.reviews);
        })
        .catch((err) => {
          console.error(err);
        });
    },
  },
  computed: {},
  created() {
    this.getReviews();
  },
};
</script>

<style>
</style>