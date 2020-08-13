<template>
  <div>후기 게시판</div>
</template>

<script>
import { mapState, mapActions } from "vuex";

export default {
  name: "reviewList",
  methods: {
    ...mapActions(["getArticles"]),
  },
  computed: {
    ...mapState(["articles"]),
  },
  created() {
    this.getArticles({ temp: 2, categoryId: 102 });
    console.log(this.articles, "후기 리스트");
  },
};
</script>

<style>
</style>