<template>
  <div>
    <nav class="mt-4 mb-3 ml-3">
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
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="4"
            v-for="article in articles" 
            :key="article.articleId">
            <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}">
                <b-card class="article-card m-4" align="left" img-width="100%" 
               img-height="60%" :img-src="article.image"
               img-alt="Image" img-top>
                  <b-card-text>
                     <h5 class="article-title">{{ article.title }}</h5>
                     <h6 class="article-area">지역: 대전</h6>
                     <h6 class="article-price">가격: 50000원/20000원</h6>
                  </b-card-text>
                  <template v-slot:footer>
                     <div class="d-flex justify-content-between">
                        <small>
                           <b-icon-heart></b-icon-heart> 0개
                           <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
                     </div>
                  </template>
               </b-card>
            </router-link>
                <!-- <b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
                  <b-card-text>
                      <h5>제목입니다.</h5>
                      <h6>대전</h6>
                      <h6>10000원/20000원</h6>
                  </b-card-text>
                  <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                        <small>
                            <b-icon-heart></b-icon-heart> 0개
                            <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
                      </div>
                  </template>
                </b-card> -->
            </b-col>
          </b-row>
        </b-container>        
        
        <!-- <div class="container">
          <div class="row">
            <div class="card col-3" style="width: 18rem;" 
            v-for="article in articles" 
            :key="article.articleId">
              <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}">
                <img :src="article.image" class="card-img-top" alt="..."></router-link>
              <div class="card-body">
                <h5 class="card-title">{{ article.title }}</h5>
                <p class="card-text">{{ article.description }}</p>
              </div>
            </div>
          </div>
        </div> -->
        
      </div>
      <div v-if="categoryNum === 1" class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="4"
            v-for="article in articles" 
            :key="article.articleId">
            <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}">
                <b-card class="article-card m-4" align="left" img-width="100%" 
               img-height="60%" :img-src="article.image"
               img-alt="Image" img-top>
                  <b-card-text>
                     <h5 class="article-title">{{ article.title }}</h5>
                     <h6 class="article-area">지역: 대전</h6>
                     <h6 class="article-price">가격: 50000원/20000원</h6>
                  </b-card-text>
                  <template v-slot:footer>
                     <div class="d-flex justify-content-between">
                        <small>
                           <b-icon-heart></b-icon-heart> 0개
                           <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
                     </div>
                  </template>
               </b-card>
            </router-link>
                <!-- <b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
                  <b-card-text>
                      <h5>제목입니다.</h5>
                      <h6>대전</h6>
                      <h6>10000원/20000원</h6>
                  </b-card-text>
                  <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                        <small>
                            <b-icon-heart></b-icon-heart> 0개
                            <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
                      </div>
                  </template>
                </b-card> -->
            </b-col>
          </b-row>

        </b-container>  
      </div>
      <div v-if="categoryNum === 2" class="tab-pane fade" id="nav-contact" role="tabpanel" aria-labelledby="nav-contact-tab">
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="4"
            v-for="article in articles" 
            :key="article.articleId">
            <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}">
                <b-card class="article-card m-4" align="left" img-width="100%" 
               img-height="60%" :img-src="article.image"
               img-alt="Image" img-top>
                  <b-card-text>
                     <h5 class="article-title">{{ article.title }}</h5>
                     <h6 class="article-area">지역: 대전</h6>
                     <h6 class="article-price">가격: 50000원/20000원</h6>
                  </b-card-text>
                  <template v-slot:footer>
                     <div class="d-flex justify-content-between">
                        <small>
                           <b-icon-heart></b-icon-heart> 0개
                           <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
                     </div>
                  </template>
               </b-card>
            </router-link>
                <!-- <b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
                  <b-card-text>
                      <h5>제목입니다.</h5>
                      <h6>대전</h6>
                      <h6>10000원/20000원</h6>
                  </b-card-text>
                  <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                        <small>
                            <b-icon-heart></b-icon-heart> 0개
                            <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
                      </div>
                  </template>
                </b-card> -->
            </b-col>
          </b-row>
        </b-container>  
      </div>
      <div v-if="categoryNum === 3" class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <!-- <div class="container">
          <div class="row">
            <div class="card col-3" style="width: 18rem;" v-for="article in articles" :key="article.articleId">
              <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}"><img :src="article.image" class="card-img-top" alt="..."></router-link>
              <div class="card-body">
                <h5 class="card-title">{{ article.title }}</h5>
                <p class="card-text">{{ article.description }}</p>
              </div>
            </div>
          </div>
        </div> -->
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="4"
            v-for="article in articles" 
            :key="article.articleId">
            <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}">
                <b-card class="article-card m-4" align="left" img-width="100%" 
               img-height="60%" :img-src="article.image"
               img-alt="Image" img-top>
                  <b-card-text>
                     <h5 class="article-title">{{ article.title }}</h5>
                     <h6 class="article-area">지역: 대전</h6>
                     <h6 class="article-price">가격: 50000원/20000원</h6>

                  </b-card-text>
                  <template v-slot:footer>
                     <div class="d-flex justify-content-between">
                        <small>
                           <b-icon-heart></b-icon-heart> 0개
                           <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
                     </div>
                  </template>
               </b-card>
            </router-link>
                <!-- <b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
                  <b-card-text>
                      <h5>제목입니다.</h5>
                      <h6>대전</h6>
                      <h6>10000원/20000원</h6>
                  </b-card-text>
                  <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                        <small>
                            <b-icon-heart></b-icon-heart> 0개
                            <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
                      </div>
                  </template>
                </b-card> -->
            </b-col>

          </b-row>
        </b-container>
      </div>
      
    </div>
  </div>
</template>

<script>
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
    }
  },
  computed:{
    ...mapState(['articles'])
  },
  created () {
    this.getArticles({temp:1,categoryId:this.categoryNum})
    console.log(this.articles, '아티클리스츠')
  },
}
</script>

<style>
.article-title{
  font-size: 17px;
  font-weight: 600;
  letter-spacing: -0.6px;
  color: #212529;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  margin-bottom: 10px;
  line-height: 1.2;
}
.article-area{
  margin-bottom: 10px;;
}
.article-card {
  height: 400px;
}
</style>