<template>
  <div>
    <div class="login-input">
      <form>
        <div class="form-group mb-1">
          <div class="container">
            <input
              class="login-email"
              v-model="loginData.adminId"
              id="adminEmail"
              style="font-family: FontAwesome;"
              :placeholder="id"
              type="text"
            />
          </div>
        </div>
        <div class="form-group">
          <div class="container">
            <input
              class="login-pw"
              v-model="loginData.password"
              type="password"
              id="adminPassword"
              style="font-family: FontAwesome;"
              :placeholder="pw"
              @keypress.enter="adminLogin(loginData)"
            />
          </div>
        </div>
      </form>
      <div class="login-sumit">
        <div class="container mb-3">
          <button
            type="submit"
            class="submit-btn"
            @click="adminLogin(loginData)"
            data-dismiss="modal"
          >로그인</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapActions } from 'vuex';
export default {
  data() {
    return {
      id: " \uf0e0" + "  ID",
      pw: " \uf084" + "  비밀번호",
      loginData: {
        adminId: "",
        password: "",
      },
    }
  },
  methods:{
    ...mapActions(['adminLogin'])
  }
};
</script>

<style>
</style>