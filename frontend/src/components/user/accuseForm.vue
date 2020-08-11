<template>
  <div>
    <p>신고자</p>
    <input type="text">
    <p>피신고자</p>
    <input type="text">
    <p>신고종류</p>
    <input type="text">
    <p>신고이유</p>
    <input type="text">
    <p>신고게시물url</p>
    <input type="text">
    <button @click="createAccuse(accuseData)">제출</button>
  </div>
</template>

<script>
  import { mapActions } from "vuex";

  export default {
    name: 'accuseForm',
    data() {
      return {
        accuseData: {
          accuseId: '',
          reporter: '',
          defendant: '',
          accuseKind: '',
          accuseReason: '',
          accuseUrl: '',
          accuseConfirm: '',
        }
      }
    },
    methods: {
      ...mapActions(['createAccuse']),
    },
  }
</script>

<style>

</style>