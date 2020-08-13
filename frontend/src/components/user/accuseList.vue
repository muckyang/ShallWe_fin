<template>
  <div class="mt-5">
    <h1>신고 관리</h1>
    
    <table class="table">
      <thead class="thead-dark">
        <tr>
          <th scope="col">#</th>
          <th scope="col">신고자</th>
          <th scope="col">피신고자</th>
          <th scope="col">신고종류</th>
          <th scope="col">신고유형</th>
          <th scope="col">신고이유</th>
          <th scope="col">신고처리</th>
        </tr>
      </thead>

      <tbody>
        <accuseListItem v-for="accuse in accuses" :key="accuse.accuseId" :accuse="accuse" />
      </tbody>

    </table>
  </div>
</template>

<script>
  const BACK_URL = process.env.VUE_APP_BACK_URL;
  import { mapState, mapActions } from 'vuex'
  import axios from "axios";
  import accuseListItem from '@/components/user/accuseListItem'

  export default {
    name: 'accuseList',
    components:{
      accuseListItem,
  },
    data() {
      return {
      }
    },
    methods: {
      ...mapActions([ 'getAccuses' ]),
    },
    created() {
      this.getAccuses()
    },
    computed: {
      ...mapState([ 'accuses' ]),
    },
  }
</script>

<style>

</style>