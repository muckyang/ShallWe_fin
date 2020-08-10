<template>
  <div>
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
                img-alt="Image" img-top footer-bg-variant="#ee6e9f" footer-class="card-end" >
                    <b-card-text>
                      <h5 class="article-title">{{ article.title }}</h5>
                      <small class="article-address" style="font-size: 13.5px;">{{ article.address }}</small><br>
                      <small class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</small>
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
    }
  },
  methods: {
    ...mapActions(['getArticles','search']),
  },
  computed:{
    ...mapState(['articles'])
  },
  created () {
    this.getArticles({temp:3,categoryId:this.categoryNum})
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
.article-address{
  margin-bottom: 10px;
  overflow:hidden;
  white-space:nowrap;
  text-overflow:ellipsis;
  line-height:1.2;
  font-size: 13.5px;
}
.article-card {
  height: 400px;
}
.article-price {
  font-size: 13.5px;
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
  transition: transform .3s;

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
.card-end {
  // background-color: #FFCBDB;
  // opacity: 0.7;
}
</style>