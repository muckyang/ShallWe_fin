<template>
  <div>
    <!-- <input class="searchInput" type="search" 
    v-model="searchData.searchDataForSend.word"
    @keypress.enter="search(searchData)"
    style="font-family: FontAwesome;" 
    placeholder="Search" 
    >
    <button class="searchBtn 
    my-2 my-sm-0" 
    type="submit" @click="search(searchData)">
    <i class="fas fa-search"></i></button> -->

    <nav class="mt-4 mb-3 ml-3">
      <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a @click="changeCategory(0)" class="nav-item nav-link active text-dark" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">전체보기</a>
        <a @click="changeCategory(1)" class="nav-item nav-link text-dark" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">쉘위배달</a>
        <a @click="changeCategory(2)" class="nav-item nav-link text-dark" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">쉘위택배</a>
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
                <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}" class="text-decoration-none text-dark">
                  <b-card class="article-card m-4 _card card__one" align="left" img-width="100%" 
                img-height="60%" :img-src="article.image"
                img-alt="Image" img-top>
                    <b-card-text>
                      <h5 class="article-title">{{ article.title }}</h5>
                      <h6 class="article-area">{{ article.address }}</h6>
                      <h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
                    </b-card-text>
                    <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                          <small>
                            <b-icon-heart></b-icon-heart> {{article.likeNum}}개
                            <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> {{article.commentNum}}개
                          </small>
                          <small class="text-muted">{{ article.timeAgo }}</small>
                      </div>
                    </template>
                </b-card>
              </router-link>
            </b-col>
          </b-row>
        </b-container>        
      </div>
      <div v-if="categoryNum === 1" class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="4"
            v-for="article in articles" 
            :key="article.articleId">
                <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}" class="text-decoration-none text-dark">
                  <b-card class="article-card m-4 _card card__one" align="left" img-width="100%" 
                img-height="60%" :img-src="article.image"
                img-alt="Image" img-top>
                    <b-card-text>
                      <h5 class="article-title">{{ article.title }}</h5>
                      <h6 class="article-area">{{ article.address }}</h6>
                      <h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
                    </b-card-text>
                    <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                          <small>
                            <b-icon-heart></b-icon-heart> {{article.likeNum}}개
                            <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> {{article.commentNum}}개
                          </small>
                          <small class="text-muted">{{ article.timeAgo }}</small>
                      </div>
                    </template>
                </b-card>
              </router-link>
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
                <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}" class="text-decoration-none text-dark">
                  <b-card class="article-card m-4 _card card__one" align="left" img-width="100%" 
                img-height="60%" :img-src="article.image"
                img-alt="Image" img-top>
                    <b-card-text>
                      <h5 class="article-title">{{ article.title }}</h5>
                      <h6 class="article-area">{{ article.address }}</h6>
                      <h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
                    </b-card-text>
                    <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                          <small>
                            <b-icon-heart></b-icon-heart> {{article.likeNum}}개
                            <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> {{article.commentNum}}개
                          </small>
                          <small class="text-muted">{{ article.timeAgo }}</small>
                      </div>
                    </template>
                </b-card>
              </router-link>
            </b-col>
          </b-row>
        </b-container>  
      </div>
      <div v-if="categoryNum === 3" class="tab-pane fade" id="nav-profile" role="tabpanel" aria-labelledby="nav-profile-tab">
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="4"
            v-for="article in articles" 
            :key="article.articleId">
              <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}" class="text-decoration-none text-dark">
                  <b-card class="article-card m-4 _card card__one" align="left" img-width="100%" 
                img-height="60%" :img-src="article.image"
                img-alt="Image" img-top>
                    <b-card-text>
                      <h5 class="article-title">{{ article.title }}</h5>
                      <h6 class="article-area">{{ article.address }}</h6>
                      <h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
                    </b-card-text>
                    <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                          <small>
                            <b-icon-heart></b-icon-heart> {{article.likeNum}}개
                            <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> {{article.commentNum}}개
                          </small>
                          <small class="text-muted">{{ article.timeAgo }}</small>
                      </div>
                    </template>
                </b-card>
              </router-link>
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
      searchData:{
        searchDataForSend:{
          word:'',
          subject:'address',
        },
        categoryId:'기본값',
      },
    }
  },
  methods: {
    ...mapActions(['getArticles','search']),
    changeCategory(num){
      this.categoryNum = num
      this.getArticles({temp:1,categoryId:this.categoryNum})
    },
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

<style scoped lang="scss">
$color-bg: #f8f8f8;
$card-padding: 20px;
$grid-gutter: 31px;

// Media Queries breakpoints
$small: 480px;
$medium: 768px;
$large: 992px;
$x-large: 1200px;

._card {
  position: relative;
  flex: 1 1 100%;
  background: lighten($color-bg, 3%);

  @media screen and (min-width: $medium) {
    flex-basis: calc(33.33% - (#{$grid-gutter * 2} + #{$card-padding * 2}));
    margin: 0 $grid-gutter;
  }
}
.card__one {
  transition: transform .5s;

  &::after {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    transition: opacity 2s cubic-bezier(.165, .84, .44, 1);
    box-shadow: 0 8px 17px 0 rgba(0, 0, 0, .2), 0 6px 20px 0 rgba(0, 0, 0, .15);
    content: '';
    opacity: 0;
    z-index: -1;
  }

  &:hover,
  &:focus {
    transform: scale3d(1.006, 1.006, 1);

    &::after {
      opacity: 1;
    }
  }
}
</style>