<template>
  <div class="mt-5">
    <h3 class="mb-3">유저관리</h3>
    <table class="table">
      <thead class="">
        <tr>
          <th scope="col">#</th>
          <th scope="col">이메일</th>
          <th scope="col">닉네임</th>
          <th scope="col">지역</th>
          <th scope="col">등급</th>
          <th scope="col">포인트</th>
          <th scope="col">회원관리</th>
        </tr>
      </thead>
      <tbody v-for="user in users" :key="user.userId">
        <tr>
          <td>{{ user.userId }}</td>
          <td>{{ user.email }}</td>
          <td>{{ user.nickname }}</td>
          <td>{{ user.address }}</td>
          <td>{{ user.grade }}</td>
          <td>{{ user.userPoint }}</td>
          <td>
            <router-link class="btn btn-info btn-sm" :to="{name: 'userDetail', params: { ID: `${user.nickname}` }}"
            >회원정보</router-link>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
  import { mapState, mapActions } from 'vuex'

  export default {
    name: 'userList',
    data() {
      return {
      }
    },
    methods: {
      ...mapActions([ 'getUsers', 'getArticles' ]),
    },
    computed: {
      ...mapState([ 'users', 'articles' ]),
    },
    created() {
      this.getUsers()
    },
  }
</script> 

<style>

</style>