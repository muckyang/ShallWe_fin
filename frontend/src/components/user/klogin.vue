<template>
  <div>
  </div>
</template>

<script>
const BACK_URL=process.env.VUE_APP_BACK_URL
import axios from 'axios'
export default {
    methods:{
        kakaoLogin(){
            this.$cookies.set('auth-token',this.$route.query.token)
        },
    },
    created:function(){
        this.kakaoLogin()
    }
}
</script>

<style>

</style>