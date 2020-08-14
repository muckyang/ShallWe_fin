<template>
  <div>
    <p>{{ userData.nickname }}</p>
    <button class="btn btn-danger btn-sm" @click="changeUserStatus()">회원삭제</button>
  </div>
</template>

<script>
  import { mapState, mapActions } from "vuex";
  import axios from "axios";
  import cookies from "vue-cookies";

  const BACK_URL = process.env.VUE_APP_BACK_URL;

  export default {
    name: 'userDetail',
    computed: {
      ...mapState([ "userData" ]),
    },
    methods: {
      ...mapActions([ "getUserDatailData" ]),
      changeUserStatus() {
        this.userData.status = 0
        this.sendChangedUserStatus();
      },
      sendChangedUserStatus() {
        this.userData.token = this.$cookies.get('admin-token')
        axios
          .post(`${BACK_URL}/accuse/disabled`, this.userData)
          .then(() => {
            console.log(this.userData, 'UUUUUUUUUUUUUUUUUUUU')
          })
          .catch(( err ) => {
            console.error(err);
          });
      },
    },
    created() {
      this.getUserDatailData(this.$route.params.ID);
    }
  }

</script>

<style>

</style>