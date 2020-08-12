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
      <tbody v-for="accuse in accuses" :key="accuse.accuseId">
        <tr>
          <th scope="row">{{ accuse.accuseId }}</th>
          <td>{{ accuse.reporter }}</td>
          <td>{{ accuse.defendant }}</td>
          <td>{{ accuse.accuseIndex }}</td>
          <td>{{ accuse.accuseKind }}</td>
          <td>{{ accuse.accuseReason }}</td>
          <td>
            <button class="btn btn-info btn-sm"><a :href="accuse.accuseUrl" class="text-decoration-none text-white">신고상세</a></button>
            <button>신고접수</button>
            <button>허위신고</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  import { mapState, mapActions } from 'vuex'

  export default {
    name: 'accuseList',
    data() {
      return {
        updateAccuseData: {},
      }
    },
    methods: {
      ...mapActions([ 'getAccuses', 'updateAccuse' ]),

    },
    computed: {
      ...mapState([ 'accuses' ]),
    },
    created() {
      this.getAccuses()
    },
  }
</script>

<style>

</style>