<template>
  <div>
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