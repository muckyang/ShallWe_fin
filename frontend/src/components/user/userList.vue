<template>
  <div class="mt-5">
    <h1>회원관리</h1>
    <table class="table">
      <thead class="thead-dark">
        <tr>
          <th scope="col">#</th>
          <th scope="col">이메일</th>
          <th scope="col">이름</th>
          <th scope="col">닉네임</th>
          <th scope="col">지역</th>
          <th scope="col">등급</th>
          <th scope="col">포인트</th>
          <th scope="col">회원관리</th>
        </tr>
      </thead>
      <tbody v-for="user in users" :key="user.userId">
        <tr>
          <th scope="row">{{ user.userId }}</th>
          <td>{{ user.email }}</td>
          <td>{{ user.name }}</td>
          <router-link :to="{name: 'userDetail', params: { ID: `${user.nickname}` }}"
          ><td>{{ user.nickname }}</td></router-link>
          <td>{{ user.address }}</td>
          <td>{{ user.grade }}</td>
          <td>{{ user.userPoint }}</td>
          <td class="d-flex justify-content-center">
            <button class="btn btn-info btn-sm">회원정보</button>
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