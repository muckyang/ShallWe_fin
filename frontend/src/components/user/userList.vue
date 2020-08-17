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
            <router-link :to="{name: 'userDetail', params: { ID: `${user.nickname}` }}"
            ><button class="info-btn">회원정보</button></router-link>
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
.info-btn {
  border: none;
  outline: none;
  border-radius: 3px;
  padding: 7px 7px;
  font-size: 14px;
  background-color: #ee6e9f;
  color: white;
  margin: 0 0 0;
  opacity: 0.8;
}
.info-btn:hover {
  transition-duration: 0.5s;
  opacity: 1;
}
</style>