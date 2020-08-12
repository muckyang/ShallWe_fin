<template>
  <div>
    <p>신고자</p>
    <input v-model="accuseData.reporter" type="text">
    <p>피신고자</p>
    <input v-model="accuseData.defendant" type="text">
    <p>신고종류</p>
    <input v-model="accuseData.accuseKind" type="text">
    <p>신고이유</p>
    <input v-model="accuseData.accuseReason" type="text">
    <p>신고게시물url</p>
    <input v-model="accuseData.accuseUrl" type="text">
    <p>내용</p>
    <input v-model="accuseData.accuseConfirm" type="text">
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