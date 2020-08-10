<template>
  <div>
    <nav class="mt-4 mb-3 ml-3">
      <div class="nav nav-tabs d-flex" id="nav-tab" role="tablist">
        <a @click="changeCategory(100)" class="nav-item nav-link active text-dark" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">전체</a>
        <a @click="changeCategory(101)" class="nav-item nav-link text-dark" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">공지</a>
        <a @click="changeCategory(102)" class="nav-item nav-link text-dark" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">후기</a>
        <a @click="changeCategory(103)" class="nav-item nav-link text-dark" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">자유</a>
        <router-link :to="{name:'postCreate'}" class="ml-auto">
          <button class="post-write-btn">글쓰기</button>
        </router-link>
      </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <div v-if="categoryNum === 100" class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <table class="table">
            <thead>
                <tr>
                  <th scope="col">카테고리</th>
                  <th scope="col">제목</th>
                  <th scope="col">글쓴이</th>
                  <th scope="col">내용</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="article in articles" :key="article.articleId">
                    <th scope="row" v-if="article.categoryId === 101"><h5><b-badge  variant="warning">공지</b-badge></h5></th>
                    <th scope="row" v-else-if="article.categoryId === 102"><h5><b-badge  variant="light">후기</b-badge></h5></th>
                    <th scope="row" v-else-if="article.categoryId === 103"><h5><b-badge  variant="success">자유</b-badge></h5></th>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.title }}</router-link></td>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.writer }}</router-link></td>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.description }}</router-link></td>
                </tr>
            </tbody>
            </table>
        </div>
        <div v-if="categoryNum === 101" class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <table class="table">
            <thead>
                <tr>
                  <th scope="col">카테고리</th>
                  <th scope="col">제목</th>
                  <th scope="col">글쓴이</th>
                  <th scope="col">내용</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="article in articles" :key="article.articleId">
                    <th scope="row" v-if="article.categoryId === 101"><h5><b-badge  variant="warning">공지</b-badge></h5></th>
                    <th scope="row" v-else-if="article.categoryId === 102"><h5><b-badge  variant="light">후기</b-badge></h5></th>
                    <th scope="row" v-else-if="article.categoryId === 103"><h5><b-badge  variant="success">자유</b-badge></h5></th>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.title }}</router-link></td>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.writer }}</router-link></td>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.description }}</router-link></td>
                </tr>
            </tbody>
            </table>
        </div>
        <div v-if="categoryNum === 102" class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <table class="table">
            <thead>
                <tr>
                  <th scope="col">카테고리</th>
                  <th scope="col">제목</th>
                  <th scope="col">글쓴이</th>
                  <th scope="col">내용</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="article in articles" :key="article.articleId">
                    <th scope="row" v-if="article.categoryId === 101"><h5><b-badge  variant="warning">공지</b-badge></h5></th>
                    <th scope="row" v-else-if="article.categoryId === 102"><h5><b-badge  variant="light">후기</b-badge></h5></th>
                    <th scope="row" v-else-if="article.categoryId === 103"><h5><b-badge  variant="success">자유</b-badge></h5></th>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.title }}</router-link></td>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.writer }}</router-link></td>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.description }}</router-link></td>
                </tr>
            </tbody>
            </table>
        </div>
        <div v-if="categoryNum === 103" class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            <table class="table">
            <thead>
                <tr>
                  <th scope="col">카테고리</th>
                  <th scope="col">제목</th>
                  <th scope="col">글쓴이</th>
                  <th scope="col">내용</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="article in articles" :key="article.articleId">
                    <th scope="row" v-if="article.categoryId === 101"><h5><b-badge  variant="warning">공지</b-badge></h5></th>
                    <th scope="row" v-else-if="article.categoryId === 102"><h5><b-badge  variant="light">후기</b-badge></h5></th>
                    <th scope="row" v-else-if="article.categoryId === 103"><h5><b-badge  variant="success">자유</b-badge></h5></th>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.title }}</router-link></td>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.writer }}</router-link></td>
                    <td><router-link :to="{name:'postDetail',params:{ID:`${article.articleId}`}}">{{ article.description }}</router-link></td>
                </tr>
            </tbody>
            </table>
        </div>
    </div>
  </div>
</template>

<script>
import {mapState,mapActions} from 'vuex'

export default {
    name:'postList',
    data(){
        return{
            categoryNum:100,
        }
    },
    methods:{
        ...mapActions(['getArticles']),
        changeCategory(num){
            this.categoryNum = num
            this.getArticles({temp:2,categoryId:this.categoryNum})
        }
    },
    computed:{
        ...mapState(['articles']),
    },  
    created(){
        this.getArticles({temp:2,categoryId:this.categoryNum})
        console.log(this.articles, '아티클리스츠')
    }

}
</script>

<style>
  .post-write-btn {
    border: none;
    outline: none;
    border-radius: 4px;
    background-color: #ee6e9f;
    padding: 5px 10px;
    color: white;
    font-weight: bold;
  }
</style>