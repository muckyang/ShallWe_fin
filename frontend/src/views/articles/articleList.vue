<template>
  <div class="article-list-box mt-5 mx-auto">
    <!-- <input class="searchInput" type="search" 
    v-model="searchData.searchDataForSend.word"
    @keypress.enter="search(searchData)"
    style="font-family: FontAwesome;" 
    placeholder="Search" 
    >
    <button class="searchBtn 
    my-2 my-sm-0" 
    type="submit" @click="search(searchData)">
    <i class="fas fa-search"></i></button>-->

    <nav class="mt-4 mb-3 mx-1">
      <div
        class="nav nav-tabs navs"
        id="nav-tab"
        role="tablist"
        style="font-family: 'Recipekorea', cursive; font-size:18px"
      >
        <a
          @click="changeCategory(0)"
          class="nav-item nav-link active text-dark"
          id="nav-home-tab"
          data-toggle="tab"
          href="#nav-home"
          role="tab"
          aria-controls="nav-home"
          aria-selected="true"
        >전체</a>
        <a
          @click="changeCategory(1)"
          class="nav-item nav-link text-dark"
          id="nav-profile-tab"
          data-toggle="tab"
          href="#nav-profile"
          role="tab"
          aria-controls="nav-profile"
          aria-selected="false"
        >쉘위배민</a>
        <a
          @click="changeCategory(2)"
          class="nav-item nav-link text-dark"
          id="nav-contact-tab"
          data-toggle="tab"
          href="#nav-contact"
          role="tab"
          aria-controls="nav-contact"
          aria-selected="false"
        >쉘위택배</a>
        <a
          @click="changeCategory(3)"
          class="nav-item nav-link text-dark"
          id="nav-profile-tab"
          data-toggle="tab"
          href="#nav-profile"
          role="tab"
          aria-controls="nav-profile"
          aria-selected="false"
        >쉘위N빵</a>
        <router-link :to="{ name: 'articleCreate' }" class="ml-auto">
          <button class="write-btn" style="font-size: 16px">글쓰기</button>
        </router-link>
      </div>
    </nav>
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
            <b-col cols="12" sm="6" lg="4" v-for="article in articles" :key="article.articleId">
              <router-link
                :to="{
                  name: 'articleDetail',
                  params: { ID: `${article.articleId}` },
                }"
                class="text-decoration-none text-dark"
              >
                <div class="type-one"></div>
                <b-card
                  :no-body="true"
                  class="article-card m-4 _card card__one"
                  align="left"
                  footer-bg-variant="#ee6e9f"
                  footer-class="card-end"
                >
                  <div
                    class="article-img-box m-0 p-0"
                    :style="{height: '100%',width:'100%',backgroundImage: 'url('+require('C:/Users/multicampus/Desktop/image/'+article.image)+')',backgroundSize: 'cover'}"
                  >
                    <img
                      v-if="article.categoryId==1"
                      src="@/assets/img/type2.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                    <img
                      v-if="article.categoryId==2"
                      src="@/assets/img/type1.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                    <img
                      v-if="article.categoryId==3"
                      src="@/assets/img/type3.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                  </div>
                  <b-card-text class="p-2">
                    <h5
                      class="article-title mt-3 pt-1"
                      style="font-family: 'Recipekorea', cursive; font-size:18px"
                    >{{ article.title }}</h5>
                    <h6
                      class="article-address py-1"
                      style="font-family: 'Recipekorea', cursive; font-size:16px"
                    >{{ article.address }}</h6>
                    <h6
                      class="article-price py-1"
                      style="font-family: 'Recipekorea', cursive; font-size:16px"
                    >
                      가격: {{ sumPrice(article.sumPrice) }}원 / {{
                      minPrice(article.minPrice)
                      }}원
                    </h6>
                  </b-card-text>
                  <template v-slot:footer>
                    <div
                      class="d-flex justify-content-between"
                      style="font-family: 'Recipekorea', cursive; font-size:14.5px"
                    >
                      <div class="articleList-subInfo">
                        <div class="articleList-heart">
                          <i class="far fa-heart" style="color: #ee6e9f; "></i>
                          {{ article.likeNum }}
                        </div>
                        <!-- <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> -->
                        <div class="articleList-chat ml-2">
                          <i class="far fa-comment-dots"></i>
                          {{ article.commentNum }}
                        </div>
                      </div>
                      <small class="text-muted">{{ article.timeAgo }}</small>
                    </div>
                  </template>
                </b-card>
              </router-link>
            </b-col>
          </b-row>
        </b-container>
      </div>
      <div
        v-if="categoryNum === 1"
        class="tab-pane fade"
        id="nav-profile"
        role="tabpanel"
        aria-labelledby="nav-profile-tab"
      >
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="12" sm="6" lg="4" v-for="article in articles" :key="article.articleId">
              <router-link
                :to="{
                  name: 'articleDetail',
                  params: { ID: `${article.articleId}` },
                }"
                class="text-decoration-none text-dark"
              >
                <b-card
                  :no-body="true"
                  class="article-card m-4 _card card__one"
                  align="left"
                  footer-bg-variant="#ee6e9f"
                  footer-class="card-end"
                >
                  <div
                    class="article-img-box"
                    :style="{height: '100%',width:'100%',backgroundImage: 'url('+require('C:/Users/multicampus/Desktop/image/'+article.image)+')',backgroundSize: 'cover'}"
                  >
                    <img
                      v-if="article.categoryId==1"
                      src="@/assets/img/type2.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                    <img
                      v-if="article.categoryId==2"
                      src="@/assets/img/type1.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                    <img
                      v-if="article.categoryId==3"
                      src="@/assets/img/type3.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                  </div>
                  <!-- <img src alt /> -->
                  <b-card-text class="p-2">
                    <h5
                      class="article-title mt-3 pt-1"
                      style="font-family: 'Recipekorea', cursive; font-size:18px"
                    >{{ article.title }}</h5>
                    <h6
                      class="article-address py-2"
                      style="font-family: 'Recipekorea', cursive; font-size:16px"
                    >{{ article.address }}</h6>
                    <h6
                      class="article-price py-2"
                      style="font-family: 'Recipekorea', cursive; font-size:16px"
                    >
                      가격: {{ sumPrice(article.sumPrice) }}원 / {{
                      minPrice(article.minPrice)
                      }}원
                    </h6>
                  </b-card-text>
                  <template v-slot:footer>
                    <div
                      class="d-flex justify-content-between"
                      style="font-family: 'Recipekorea', cursive; font-size:14.5px"
                    >
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
            </b-col>
          </b-row>
        </b-container>
      </div>
      <div
        v-if="categoryNum === 2"
        class="tab-pane fade"
        id="nav-contact"
        role="tabpanel"
        aria-labelledby="nav-contact-tab"
      >
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="12" sm="6" lg="4" v-for="article in articles" :key="article.articleId">
              <router-link
                :to="{
                  name: 'articleDetail',
                  params: { ID: `${article.articleId}` },
                }"
                class="text-decoration-none text-dark"
              >
                <b-card
                  :no-body="true"
                  class="article-card m-4 _card card__one"
                  align="left"
                  footer-bg-variant="#ee6e9f"
                  footer-class="card-end"
                >
                  <div
                    class="article-img-box"
                    :style="{height: '100%',width:'100%',backgroundImage: 'url('+require('C:/Users/multicampus/Desktop/image/'+article.image)+')',backgroundSize: 'cover'}"
                  >
                    <img
                      v-if="article.categoryId==1"
                      src="@/assets/img/type2.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                    <img
                      v-if="article.categoryId==2"
                      src="@/assets/img/type1.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                    <img
                      v-if="article.categoryId==3"
                      src="@/assets/img/type3.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                  </div>
                  <b-card-text class="p-2">
                    <h5
                      class="article-title mt-3 pt-1"
                      style="font-family: 'Recipekorea', cursive; font-size:18px"
                    >{{ article.title }}</h5>
                    <h6
                      class="article-address py-2"
                      style="font-family: 'Recipekorea', cursive; font-size:16px"
                    >{{ article.address }}</h6>
                    <h6
                      class="article-price py-2"
                      style="font-family: 'Recipekorea', cursive; font-size:16px"
                    >
                      가격: {{ sumPrice(article.sumPrice) }}원 / {{
                      minPrice(article.minPrice)
                      }}원
                    </h6>
                  </b-card-text>
                  <template v-slot:footer>
                    <div
                      class="d-flex justify-content-between"
                      style="font-family: 'Recipekorea', cursive; font-size:14.5px"
                    >
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
            </b-col>
          </b-row>
        </b-container>
      </div>
      <div
        v-if="categoryNum === 3"
        class="tab-pane fade"
        id="nav-profile"
        role="tabpanel"
        aria-labelledby="nav-profile-tab"
      >
        <b-container class="bv-example-row">
          <b-row align-h="start">
            <b-col cols="12" sm="6" lg="4" v-for="article in articles" :key="article.articleId">
              <router-link
                :to="{
                  name: 'articleDetail',
                  params: { ID: `${article.articleId}` },
                }"
                class="text-decoration-none text-dark"
              >
                <b-card
                  :no-body=true
                  class="article-card m-4 _card card__one"
                  align="left"
                  footer-bg-variant="#ee6e9f"
                  footer-class="card-end"
                >
                  <div
                    class="article-img-box"
                    :style="{height: '100%',width:'100%',backgroundImage: 'url('+require('C:/Users/multicampus/Desktop/image/'+article.image)+')',backgroundSize: 'cover'}"
                  >
                    <img
                      v-if="article.categoryId==1"
                      src="@/assets/img/type2.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                    <img
                      v-if="article.categoryId==2"
                      src="@/assets/img/type1.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                    <img
                      v-if="article.categoryId==3"
                      src="@/assets/img/type3.png"
                      class="article-type-img"
                      style="max-width: 100%; height: auto;"
                    />
                  </div>
                  <b-card-text class="p-2">
                    <h5
                      class="article-title mt-3 pt-1"
                      style="font-family: 'Recipekorea', cursive; font-size:18px"
                    >{{ article.title }}</h5>
                    <h6
                      class="article-address py-2"
                      style="font-family: 'Recipekorea', cursive; font-size:16px"
                    >{{ article.address }}</h6>
                    <h6
                      class="article-price py-2"
                      style="font-family: 'Recipekorea', cursive; font-size:16px"
                    >
                      가격: {{ sumPrice(article.sumPrice) }}원 / {{
                      minPrice(article.minPrice)
                      }}원
                    </h6>
                  </b-card-text>
                  <template v-slot:footer>
                    <div
                      class="d-flex justify-content-between"
                      style="font-family: 'Recipekorea', cursive; font-size:14.5px"
                    >
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
            </b-col>
          </b-row>
        </b-container>
      </div>
    </div>
    <infinite-loading @infinite="infiniteHandler" :identifier="infiniteId" spinner="waveDots">
      <div
        slot="no-more"
        style="color: rgb(102, 102, 102); font-size: 14px; padding: 10px 0px; font-family: 'Recipekorea', cursive; font-size:14.5px"
      >더이상 게시물이 존재하지 않습니다!</div>
    </infinite-loading>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import { mapState, mapActions } from "vuex";
import InfiniteLoading from "vue-infinite-loading";
import cookies from "vue-cookies";
import axios from "axios";

export default {
  name: "articleList",
  data() {
    return {
      articleImg: {
        backgroundImage: `url(${require("@/assets/img/food1.png")})`,
      },
      categoryNum: 0,
      page: 0,
      onlyOne: true,
      articles: [],
      searchData: {
        searchDataForSend: {
          word: "",
          subject: "address",
        },
        categoryId: "기본값",
      },
      infiniteId: +new Date(),
    };
  },
  components: {
    InfiniteLoading,
  },
  methods: {
    ...mapActions(["getArticles", "search"]),
    cutPrice(article) {
      let CD = date + "";
      const year = CD.substring(0, 4) + "년 ";
      const month = CD.substring(5, 7) + "월";
      const day = CD.substring(8, 10) + "일 ";
      const hour = CD.substring(11, 13) + "시";
      const minute = CD.substring(14, 16) + "분";
      const res = year + month + day + hour + minute;
      return res;
    },
    LabelImg(article) {
      if (article.categoryId == 1) {
        var imgHome = "@/assets/img/food1.png";
        console.log(imgHome);
        return imgHome;
      }
    },
    infiniteHandler($state) {
      const auth = { token: cookies.get("auth-token") };
      axios
        .post(`${BACK_URL}/post/read/1/${this.categoryNum}/${this.page}`, auth)
        .then((res) => {
          setTimeout(() => {
            if (res.data.postList.length) {
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
      // this.getArticles({ temp: 1, categoryId: this.categoryNum });
    },
  },
  computed: {
    imageUrl() {
      return (article) => {
        var arImg = article.image;
        var result = "C:/Users/multicampus/Desktop/image/" + arImg;
        console.log(result);
        return {
          background: "url(" + result + ")",
        };
        ("{backgroundImage: 'url('+require('C:/Users/multicampus/Desktop/image/')+')'}");
        // require("C:/Users/multicampus/Desktop/image/" +
        //   `${article.image}`);
      };
    },

    minPrice() {
      return (price) => {
        var res = new Intl.NumberFormat("ko-KR", {
          style: "currency",
          currency: "KRW",
        }).format(price);
        let result = res + "";
        const real = result.substring(1, res.length);
        return real;
      };
    },
    sumPrice() {
      return (price) => {
        var res = new Intl.NumberFormat("ko-KR", {
          style: "currency",
          currency: "KRW",
        }).format(price);
        let result = res + "";
        const real = result.substring(1, res.length);
        return real;
      };
    },
  },
};
</script>

<style>
/* .article-img-box {
  width: 100%;
  height: 60%;
  border: 1px solid red;
  background-image: url("imageUrl(article)");
} */
.articleList-subInfo {
  display: flex;
  vertical-align: middle;
  font-size: 13px;
}
.article-background-img {
  width: 100%;
  height: 60%;
  margin: 0 0 0 0;
}
.article-type-img {
  width: 40%;
  height: 40%;
  position: relative;
  top: 0px;
  left: 0px;
}
.type-one {
}
.article-list-box {
  width: 95%;
}
.article-title {
  font-size: 17px;
  font-weight: 600;
  letter-spacing: -0.6px;
  color: #212529;
  margin-bottom: 10px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  line-height: 1.2;
}
.article-address {
  font-size: 13.5px;
  margin-bottom: 7px;
  margin-top: 15px;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
  line-height: 1.2;
}
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
// $color-bg: #572e2e;
$card-padding: 20px;
$grid-gutter: 30px;

// Media Queries breakpoints
$small: 480px;
$medium: 768px;
$large: 992px;
$x-large: 1200px;

._card {
  position: relative;
  flex: 1 1 100%;
  background: lighten($color-bg, 3%);
  // background: #ee6e9f;

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
// .card-end {
// background-color: #FFCBDB;
// opacity: 0.7;
// }
.navs {
  // border: 1px solid red;
  display: flex;
  justify-content: space-between;
}
.main-tabs {
  display: flex;
}
.write-btn {
  border: none;
  outline: none;
  border-radius: 4px;
  background-color: #ee6e9f;
  padding: 7px 8px 3px 8px;
  color: white;
}
</style>
