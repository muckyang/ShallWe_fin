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
    <infinite-loading @infinite="infiniteHandler" :identifier="infiniteId" spinner="waveDots">
      <div slot="no-more" style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;">더이상 게시물이 존재하지 않습니다!</div>
    </infinite-loading>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import { mapState, mapActions } from "vuex";
import InfiniteLoading from 'vue-infinite-loading'
import cookies from "vue-cookies";
import axios from "axios";

export default {
  name: "reviewList",
  data() {
    return {
      reviews: [],
      onlyOne:true,
      categoryNum: 102,
      page : 0,
      infiniteId: +new Date(),
    };
  },
  components: {
    InfiniteLoading,
  },
  methods: {
    // getReviews() {
    //   const auth ={ token: cookies.get("auth-token") };
    //   axios
    //     .post(`${BACK_URL}/post/read/2/${this.categoryNum}/${this.page}`, auth )
    //     .then((response) => {
    //       this.reviews = response.data.postList;
    //     })
    //     .catch((err) => {
    //         console.error(err);
    //     });
    // },
    ...mapActions(["search"]),
    infiniteHandler($state) {
      const auth =  { token: cookies.get("auth-token") };
      axios.post(`${BACK_URL}/post/read/2/102/${this.page}`, auth)
        .then(res => {
          setTimeout(() => {
            if (res.data.postList.length) {
              this.reviews = this.reviews.concat(res.data.postList);
              this.page += 1
              $state.loaded();
            } else {
              $state.complete();
            }
          }, 1000)
        }) .catch(err => {
          console.log(err);
        })
    },
  },
  // computed: {},
  // created() {
  //   this.getReviews();
  // },
};
</script>

<style>
</style>