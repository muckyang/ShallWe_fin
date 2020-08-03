<template>
  <div>
    <div class="container">
      <div class="row">
        <div class="card col-3" style="width: 18rem;" v-for="article in articles" :key="article.articleId">
          <img src="https://source.unsplash.com/random" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">{{ article.title }}</h5>
            <p class="card-text">{{ article.description }}</p>
            <router-link class="btn btn-primary" :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}">보러가기</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from 'vuex'
export default {
  name:'searchList',
  methods:{
    ...mapActions(['search'])
  },
  computed:{
    ...mapState(['articles'])
  },
  created:function(){
    this.search(this.$cookies.get('searchData'))
  }
}
</script>

<style>

</style>