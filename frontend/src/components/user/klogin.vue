<template>
  <div>
  </div>
</template>

<script>
import { mapMutations } from 'vuex'
const BACK_URL=process.env.VUE_APP_BACK_URL
export default {
    methods:{
        ...mapMutations(["SET_TOKEN"]),
    },
    created:function(){
        this.SET_TOKEN(this.$route.query.token)
    }
}
</script>

<style>

</style>