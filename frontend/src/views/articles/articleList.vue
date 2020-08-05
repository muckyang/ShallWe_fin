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
            <router-link :to="{name:'articleDetail',params:{ID:`${article.articleId}`}}" class="text-decoration-none text-dark">
                <b-card class="article-card m-4 _container" align="left" img-width="100%" 
               img-height="60%" :img-src="article.image"
               img-alt="Image" img-top>
                <div class="_overlay">
                  <div class="_text d-flex justify-content-center align-items-center" style="border: solid 3px white; height: 40px; width: 120px"><h4 class="text-center">Shall we?</h4></div>
                </div>
                  <b-card-text>
                     <h5 class="article-title">{{ article.title }}</h5>
                     <h6 class="article-area">{{ article.address }}</h6>
                     <h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
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
                <b-card class="article-card m-4 mycard" align="left" img-width="100%" 
               img-height="60%" 
               img-alt="Image" img-top>
              <div class="image">
                <img :src="article.image" style="height: 100%;">
              </div>
                <div class="details">
                  <div class="center">
                    <h1>{{ article.title }}<br><span>{{ article.address }}</span></h1>
                    <p>가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</p>
                    <ul>
                      <li><a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a></li>
                      <li><a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a></li>
                      <li><a href="#"><i class="fa fa-google-plus" aria-hidden="true"></i></a></li>
                      <li><a href="#"><i class="fa fa-linkedin" aria-hidden="true"></i></a></li>
                    </ul>
                  </div>
                </div>
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
                <b-card class="article-card m-4 _container" align="left" img-width="100%" 
               img-height="60%" :img-src="article.image"
               img-alt="Image" img-top>
                <div class="_overlay2">
                  <div class="_text d-flex justify-content-center align-items-center" style="border: solid 3px white; height: 40px; width: 120px"><h4 class="text-center">Shall we?</h4></div>
                </div>
                  <b-card-text>
                     <h5 class="article-title">{{ article.title }}</h5>
                     <h6 class="article-area">{{ article.address }}</h6>
                     <h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
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
                <b-card class="article-card m-4" align="left" img-width="100%" 
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
                           <b-icon-heart></b-icon-heart> 0개
                           <b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
                        </small>
                        <small class="text-muted">10분 전</small>
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
._overlay {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: .5s ease;
  background-color: lightgray;
}
._container:hover ._overlay {
  opacity: 0.8;
  box-shadow: 5px 5px 5px lightgray;
}
._text {
  color: white;
  font-size: 20px;
  position: absolute;
  top: 50%;
  left: 50%;
  -webkit-transform: translate(-50%, -50%);
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
  text-align: center;
}

._overlay2 {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  height: 100%;
  width: 100%;
  opacity: 0;
  transition: .5s ease;
  /* background-color: lightgray; */
}
._container:hover ._overlay2 {
  opacity: 0.8;
  box-shadow: 10px 10px 10px lightgray;
}




.mycard {
  
}
.mycard .image {
    width: 100%;
    height: 100%;
    overflow: hidden;
}
.mycard .image img {
    width: 100%;
    transition: .5s;
}
.mycard:hover .image img {
    opacity: .5;
    transform: translateX(30%);
}
.mycard .details {
    position: absolute;
    top: 0;
    left: 0;
    width: 70%;/*100%*/
    height: 100%;
    background: lightgray;
    transition: .5s;
    transform-origin: left;
    transform: perspective(2000px) rotateY(-90deg);
}
.mycard:hover .details {
    transform: perspective(2000px) rotateY(0deg);
}
.mycard .details .center {
    padding: 20px;
    text-align: center;
    background: #fff;
    position: absolute;
    top: 50%;
    transform: translateY(-50%);
}
.mycard .details .center h1 {
    margin: 0;
    padding: 0;
    color: #ff3636;
    line-height: 20px;
    font-size: 20px;
    text-transform: uppercase;
}
.mycard .details .center h1 span {
    font-size: 14px;
}
.mycard .details .center p {
    margin: 10px 0;
    padding: 0;
}
.mycard .details .center ul {
    margin: 10px auto 0;
    padding: 0;
    display: table;
}
.mycard .details .center ul li {
    list-style: none;
    margin: 0 5px;
    float: left;
}
.mycard .details .center ul li a {
    display: block;
    background: #262626;
    color: #fff;
    width: 30px;
    height: 30px;
    line-height: 30px;
    text-align: center;
    transform: .5s;
}
.mycard .details .center ul li a:hover {
    background: #ff3636;
}
</style>