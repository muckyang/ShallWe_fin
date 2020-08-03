<template>
  <div>
    <nav class="mt-5">
      <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a @click="changeCategory(0)" class="nav-item nav-link active text-dark" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">전체보기</a>
        <a @click="changeCategory(1)" class="nav-item nav-link text-dark" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">쉘위택배</a>
        <a @click="changeCategory(2)" class="nav-item nav-link text-dark" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">쉘위배달</a>
        <a @click="changeCategory(3)" class="nav-item nav-link text-dark" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">쉘위공구</a>
        <router-link class="nav-item nav-link" :to="{name:'articleCreate'}">글쓰기</router-link>
      </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
      <div v-if="categoryNum === 0" class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
        <div class="container">
          <div class="row">
            <div class="card col-3" style="width: 18rem;" v-for="article in articles" :key="article.articleId">
              <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}"><img :src="article.image" class="card-img-top" alt="..."></router-link>
              <div class="card-body">
                <h5 class="card-title">{{ article.title }}</h5>
                <p class="card-text">{{ article.description }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="categoryNum === 1" class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <div class="container">
          <div class="row">
            <div class="card col-3" style="width: 18rem;" v-for="article in articles" :key="article.articleId">
              <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}"><img :src="article.image" class="card-img-top" alt="..."></router-link>
              <div class="card-body">
                <h5 class="card-title">{{ article.title }}</h5>
                <p class="card-text">{{ article.description }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="categoryNum === 2" class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
        <div class="container">
          <div class="row">
            <div class="card col-3" style="width: 18rem;" v-for="article in articles" :key="article.articleId">
              <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}"><img :src="article.image" class="card-img-top" alt="..."></router-link>
              <div class="card-body">
                <h5 class="card-title">{{ article.title }}</h5>
                <p class="card-text">{{ article.description }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div v-if="categoryNum === 3" class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <div class="container">
          <div class="row">
            <div class="card col-3" style="width: 18rem;" v-for="article in articles" :key="article.articleId">
              <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}"><img :src="article.image" class="card-img-top" alt="..."></router-link>
              <div class="card-body">
                <h5 class="card-title">{{ article.title }}</h5>
                <p class="card-text">{{ article.description }}</p>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import {mapState,mapActions} from 'vuex'

export default {
  name: 'articleList',
  data(){
    return {
      categoryNum: 0,
    }
  },
  methods: {
    ...mapActions(['getArticles']),
    changeCategory(num){
      this.categoryNum = num
      this.getArticles({temp:1,categoryId:this.categoryNum})
      console.log('바뀐 카테고리 넘버',this.categoryNum);
    }
  },
  computed:{
    ...mapState(['articles'])
  },
  created () {
    this.getArticles({temp:1,categoryId:this.categoryNum})
  },
}
</script>

<style>

</style>