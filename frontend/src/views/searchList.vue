<template>
  <div class="mt-5">
    <div class="dropdown mr-1">
      <!-- <button
        class="downBtn btn btn-secondary"
        type="button"
        id="dropdownMenuButton"
        data-toggle="dropdown"
        aria-haspopup="true"
        aria-expanded="false"
      >
        {{ item3 }}
      </button> -->
      <!-- <div class="dropdown-menu" aria-labelledby="dropdownMenuButton"> -->
      <div class="mainSearch">
        <a
          :class="{
            beforeSelect: searchData.temp != 1,
            colorChange: searchData.temp == 1,
          }"
          href="#"
          @click="selectTemp(1)"
          >게시글</a
        >
        <a
          :class="{
            beforeSelect: searchData.temp != 2,
            colorChange: searchData.temp == 2,
          }"
          href="#"
          @click="selectTemp(2)"
          >커뮤니티</a
        >
      </div>
      <!-- </div> -->

      <div v-if="searchData.temp !== 0" class="select-item-row mt-2">
        <div v-if="searchData.temp === 1">
          <a
            :class="{
              beforeSelect: searchData.categoryId != 1,
              colorChange: searchData.categoryId == 1,
            }"
            href="#"
            @click="selectCategory(1)"
            >배달</a
          >
          <a
            :class="{
              beforeSelect: searchData.categoryId != 2,
              colorChange: searchData.categoryId == 2,
            }"
            href="#"
            @click="selectCategory(2)"
            >최저주문</a
          >
          <a
            :class="{
              beforeSelect: searchData.categoryId != 3,
              colorChange: searchData.categoryId == 3,
            }"
            href="#"
            @click="selectCategory(3)"
            >공동구매</a
          >
        </div>
        <div v-if="searchData.temp === 2">
          <a
            :class="{
              beforeSelect: searchData.categoryId != 101,
              colorChange: searchData.categoryId == 101,
            }"
            href="#"
            @click="selectCategory(101)"
            >공지</a
          >
          <a
            :class="{
              beforeSelect: searchData.categoryId != 102,
              colorChange: searchData.categoryId == 102,
            }"
            href="#"
            @click="selectCategory(102)"
            >후기</a
          >
          <a
            :class="{
              beforeSelect: searchData.categoryId != 103,
              colorChange: searchData.categoryId == 103,
            }"
            href="#"
            @click="selectCategory(103)"
            >자유</a
          >
        </div>
        <div>|</div>
        <div v-if="searchData.temp !== 0" class="dropdown mr-1">
          <a
            :class="{
              beforeSelect: searchData.searchDataForSend.subject != 'title',
              colorChange: searchData.searchDataForSend.subject == 'title',
            }"
            href="#"
            @click="selectSubject(0)"
            >제목</a
          >
          <a
            :class="{
              beforeSelect: searchData.searchDataForSend.subject != 'writer',
              colorChange: searchData.searchDataForSend.subject == 'writer',
            }"
            href="#"
            @click="selectSubject(1)"
            >작성자</a
          >
          <a
            v-if="searchData.temp === 1"
            :class="{
              beforeSelect: searchData.searchDataForSend.subject != 'address',
              colorChange: searchData.searchDataForSend.subject == 'address',
            }"
            href="#"
            @click="selectSubject(2)"
            >주소</a
          >
        </div>
      </div>
    </div>

    <input
      class="detail-search mt-4"
      style="font-family: FontAwesome;"
      placeholder="Search"
      v-model="searchData.searchDataForSend.word"
      @keypress.enter="detailSearch(searchData)"
    />
    <button
      class="detail-search-btn"
      type="submit"
      @click="detailSearch(searchData)"
    >
      <i class="fas fa-search"></i>
    </button>

    <b-container class="bv-example-row">
      <b-row align-h="start">
        <b-col
          cols="12"
          sm="4"
          v-for="article in articles"
          :key="article.articleId"
        >
          <router-link
            :to="{
              name: 'articleDetail',
              params: { ID: `${article.articleId}` },
            }"
            class="text-decoration-none text-dark"
          >
            <b-card
              class="article-card m-4 _card card__one"
              align="left"
              img-width="100%"
              img-height="60%"
              :img-src="article.image"
              img-alt="Image"
              img-top
            >
              <b-card-text>
                <h5 class="article-title">{{ article.title }}</h5>
                <h6 class="article-area">{{ article.address }}</h6>
                <h6 class="article-price">
                  가격: {{ article.sumPrice }}원/{{ article.minPrice }}원
                </h6>
              </b-card-text>
              <template v-slot:footer>
                <div class="d-flex justify-content-between">
                  <small>
                    <b-icon-heart></b-icon-heart> {{ article.likeNum }}개
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
</template>

<script>
import { mapState, mapActions } from "vuex";
export default {
  name: "searchList",
  data() {
    return {
      searchData: {
        searchDataForSend: {
          subject: "",
          word: "",
        },
        categoryId: "",
        temp: 0,
      },
      item1: "",
      item2: "",
      item3: "",
    };
  },
  methods: {
    ...mapActions(["detailSearch"]),
    selectTemp(num) {
      if (num === 1) {
        this.searchData.temp = 1;
        this.item3 = "게시글";
      } else {
        this.searchData.temp = 2;
        this.item3 = "커뮤니티";
      }
      this.item1 = "";
      this.item2 = "";
    },
    selectSubject(num) {
      if (num == 0) {
        this.searchData.searchDataForSend.subject = "title";
        this.item1 = "제목";
      } else if (num == 1) {
        this.searchData.searchDataForSend.subject = "writer";
        this.item1 = "작성자";
      } else {
        this.searchData.searchDataForSend.subject = "address";
        this.item1 = "주소";
      }
    },
    selectCategory(num) {
      if (num == 1) {
        this.searchData.categoryId = 1;
        this.item2 = "배달";
      } else if (num == 2) {
        this.searchData.categoryId = 2;
        this.item2 = "최저주문";
      } else if (num == 3) {
        this.searchData.categoryId = 3;
        this.item2 = "공동구매";
      } else if (num == 101) {
        this.searchData.categoryId = 101;
        this.item2 = "공지";
      } else if (num == 102) {
        this.searchData.categoryId = 102;
        this.item2 = "후기";
      } else if (num == 103) {
        this.searchData.categoryId = 103;
        this.item2 = "자유";
      }
    },
  },
  computed: {
    ...mapState(["articles"]),
  },
  created: function() {
    // this.search(this.$cookies.get('searchData'))
  },
};
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
.select-item-row {
  display: flex;
  justify-content: center;
}
.mainSearch {
  // border: 1px solid blue;
}
.beforeSelect {
  text-decoration: none;
  margin: 0 5px;
}
.beforeSelect:hover {
  color: black;
  cursor: pointer;
}
.colorChange {
  color: #ee6e9f;
  text-decoration: none;
  font-weight: bold;
  margin: 0 5px;
}
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
  transition: transform 0.5s;

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
.search-row {
  display: flex;
  justify-content: center;
}
.main-select {
  margin: 0 10px;
}
.second-select,
.third-select {
  margin: 0 5px;
}
.detail-search {
  border-top: 0px;
  border-left: 0px;
  border-right: 0px;
  border-radius: 0;
  width: 250px;
}
.detail-search:focus {
  border-top: 0px;
  border-left: 0px;
  border-right: 0px;
  border-radius: 0;
  outline: none;
}
.detail-search-btn {
  border: none;
  outline: none;
  background-color: transparent;
  color: #ee6e9f;
}
</style>
