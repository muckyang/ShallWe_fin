<template>
  <div class="mt-4">
    <hr>
    <div class="d-flex mb-2 justify-content-end">
      <button class="mr-2 view-all-btn" @click="resetArticles">전체 보기</button>
      <router-link type="button" :to="{ name: 'articleCreate' }">
        <button class="article-create-btn">글 쓰기</button>
      </router-link>
    </div>
    <kakaoMapForLocal :articles="articles" @setAddress="setAddress"/>
    <div class="tab-content" id="nav-tabContent">
      <div
        v-if="categoryNum === 0"
        class="tab-pane fade show active"
        id="nav-home"
        role="tabpanel"
        aria-labelledby="nav-home-tab"
      >
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="12" sm="4" v-for="article in articles" :key="article.articleId">
              <div v-if="article.address===address||!flag">
                <router-link
                  :to="{
                    name: 'articleDetail',
                    params: { ID: `${article.articleId}`},
                  }"
                  class="text-decoration-none text-dark"
                >
                  <b-card
                    class="article-card m-4 _card card__one"
                    align="left"
                    img-width="100%"
                    img-height="60%"
                    :img-src="imageUrl(article)"
                    img-alt="Image"
                    img-top
                    footer-bg-variant="#ee6e9f"
                    footer-class="card-end"
                  >
                    <b-card-text>
                      <h5 class="article-title">{{ article.title }}</h5>
                      <h6 class="article-address">{{ article.address }}</h6>
                      <br />
                      <h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
                    </b-card-text>
                    <template v-slot:footer>
                      <div class="d-flex justify-content-between">
                        <small>
                          <b-icon-heart></b-icon-heart>
                          {{ article.likeNum }}개
                          <b-icon-chat-dots class="ml-1"></b-icon-chat-dots>
                          {{ article.commentNum }}개
                        </small>
                        <small class="text-muted">{{ article.timeAgo }}</small>
                      </div>
                    </template>
                  </b-card>
                </router-link>
              </div>
            </b-col>
          </b-row>
        </b-container>
      </div>
    </div>
    <br />
    <infinite-loading @infinite="infiniteHandler" :identifier="infiniteId" spinner="waveDots">
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px;"
      >회원님의 동네에서 등록된 게시글이 더이상 존재하지 않습니다.</div>
    </infinite-loading>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import { mapState, mapActions } from "vuex";
import InfiniteLoading from "vue-infinite-loading";
import cookies from "vue-cookies";
import axios from "axios";
import kakaoMapForLocal from '@/components/articles/kakaoMapForLocal.vue'

export default {
  name: "articleList",
  data() {
    return {
      categoryNum: 0,
      page: 0,
      onlyOne: true,
      articles: [],
      duarticles:[],
      allArticles:[],
      address:'default',
      flag:false,
      infiniteId: +new Date(),
    };
  },
  components: {
    InfiniteLoading,
    kakaoMapForLocal
  },
  computed: {
    imageUrl(){
      return (article)=>{
        return require('C:/Users/multicampus/Desktop/image/'+`${article.image}`)
      }
    },
  },
  methods: {
    ...mapActions(["getArticles", "search"]),
    infiniteHandler($state) {
      const auth = { token: cookies.get("auth-token") };
      axios
        .post(`${BACK_URL}/post/read/3/${this.categoryNum}/${this.page}`, auth)
        .then((res) => {
          setTimeout(() => {
            if (res.data.postList.length) {
              this.allArticles = this.allArticles.concat(res.data.postList);
              this.articles = this.articles.concat(res.data.postList);
              this.page += 1;
              $state.loaded();
            } else {
              $state.complete();
            }
          }, 1000);
        })
        .catch((err) => {
          console.log(err);
        });
    },
    changeCategory(num) {
      this.categoryNum = num;
      this.page = 0;
      this.articles = [];
      this.onlyOne = true;
      this.infiniteId += 1;
    },
    setAddress(address){
      this.address=address
      this.flag=true
      var tempArticles=[]
      for(const a of this.articles){
        if(a.address===address){
          tempArticles.push(a)
        }
      }
      this.articles=tempArticles
    },
    resetArticles(){
      this.articles=this.allArticles
      this.flag=false
    },
  },
};
</script>

<style>
.article-create-btn {
  border: none;
  outline: none;
  border-radius: 4px;
  background-color: #ee6e9f;
  padding: 5px 10px;
  color: white;
  font-weight: bold;
}
.article-create-btn {
  border: none;
  outline: none;
  border-radius: 4px;
  background-color: #ee6e9f;
  padding: 5px 10px;
  color: white;
  font-weight: bold;
}
.view-all-btn {
  border: none;
  outline: none;
  border-radius: 4px;
  padding: 5px 10px;
  background-color: rgb(151, 151, 151);
  color: white;
  font-weight: bold;
}
/* .article-title {
  font-size: 17px;
  font-weight: 600;
  letter-spacing: -0.6px;
  color: #212529;
  margin-bottom: 10px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  line-height: 1.2;
}*/
/* .article-address {
  font-size: 13.5px;
  margin-bottom: 7px;
  margin-top: 15px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  line-height: 1.2;
} */
.article-card {
  height: 400px;
}
.article-price {
  font-size: 13.5px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  line-height: 1.2;
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
  transition: transform 0.3s;

  &::after {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    transition: opacity 2s cubic-bezier(0.165, 0.84, 0.44, 1);
    box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2),
      0 6px 20px 0 rgba(0, 0, 0, 0.15);
    content: "";
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
.disable{
  display:none
}
</style>
