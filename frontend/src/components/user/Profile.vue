<template>
    <div class="mt-5">
      <!-- 1. 상단 -->
      <div class="mb-4 container">
        <div class="row">
          <div class="col-5">
            <b-card no-body class="overflow-hidden">
              <b-row no-gutters>
                <b-col>
                  <b-card-img height="50%" src="https://picsum.photos/400/400/?image=20" alt="Image" class="rounded-0 mt-3" style="width: 50%;"></b-card-img>
                  <hr style="width: 70%" class="mb-1">
                  <h5>{{ userData.name }} 님의 프로필</h5>
                  <h6 class="mt-0" style="font-size: 70%;">{{ userData.email }}</h6>
                </b-col>
                <b-col style="border-left: solid 1px lightgray">
                  <b-card-body>
                    <b-card-text>
                      <div class="text-left">
                        <h6 class="mt-0">닉네임: {{ userData.nickname }}</h6>
                        <h6 class="mt-0">생일: {{ userData.birthday }}</h6>
                        <h6 class="mt-0">지역: {{ userData.address }}</h6>
                        <hr style="width: 90%">
                        <h6>자기소개</h6>
                        <div style="border: solid 1px lightgray; height: 70px;"></div>
                      </div>
                    </b-card-text>
                  </b-card-body>
                </b-col>
              </b-row>
            </b-card>
          </div>
          <div class="col-7">
            <b-card bg-variant="light" text-variant="dark" >
              <b-card-text class="mt-2">
                <h3>나의 레벨</h3>
                <div class="progress">
                  <div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
                </div>
                <p>{{ userData.userPoint }} Point</p>
                <div class="container mt-4">
                  <div class="row">
                    <div class="col-4">
                      <i class="fas fa-balance-scale" style="font-size: 40px;"></i><br>
                      <small>신뢰점수</small>
                      <h3>100 점</h3>
                    </div>
                    <div class="col-4">
                      <i class="fas fa-star" style="font-size: 40px;"></i><br>
                      <small>활동점수</small>
                      <h3>100 점</h3>
                    </div>
                    <div class="col-4">
                      <i class="fas fa-heart" style="font-size: 40px;"></i><br>
                      <small>인기점수</small>
                      <h3>100 점</h3>
                    </div>
                  </div>
                </div>
              </b-card-text>
            </b-card>
          </div>
        </div>
      </div>

      <!-- 2. 중단 -->
      <div class="container">
        <div class="row">
          <div class="col-12">
            <b-card bg-variant="light" text-variant="dark" class="mb-4">
              <b-card-text>
                <div class="container">
                  <div class="row">
                    <div class="col-3">
                      <h2>{{ userData.articleCount }} 개</h2>
                      <small>내가 쓴 게시물</small>
                    </div>
                    <div class="col-3">
                      <h2>{{ userData.reviewCount }} 개</h2>
                      <small>내가 쓴 후기</small>
                    </div>
                    <div class="col-3">
                      <h2>{{ userData.likeCount }} 개</h2>
                      <small>찜 목록</small>
                    </div>
                    <div class="col-3">
                      <h2>{{ userData.tempCount }} 개</h2>
                      <small>임시 저장</small>
                    </div>
                  </div>
                </div>
              </b-card-text>
            </b-card>
          </div>
        </div>
      </div>

      <!-- 3. 하단 -->
      <div class="container">
        <div class="row">
          <div class="col-3">
              <div class="list-group flex-column" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                  <a class="list-group-item list-group-item-action" id="v-pills-1-tab" data-toggle="pill" href="#v-pills-1" role="tab" aria-controls="v-pills-1" aria-selected="true">내가 쓴 게시물</a>
                  <a class="list-group-item list-group-item-action" id="v-pills-2-tab" data-toggle="pill" href="#v-pills-2" role="tab" aria-controls="v-pills-2" aria-selected="false">내가 쓴 후기</a>
                  <a class="list-group-item list-group-item-action" id="v-pills-3-tab" data-toggle="pill" href="#v-pills-3" role="tab" aria-controls="v-pills-3" aria-selected="false">찜 목록</a>
                  <a class="list-group-item list-group-item-action" id="v-pills-4-tab" data-toggle="pill" href="#v-pills-4" role="tab" aria-controls="v-pills-4" aria-selected="false">임시 저장</a>
              </div>
          </div>
          <div class="col-9">
              <div class="tab-content" id="v-pills-tabContent">
                  <div class="tab-pane fade show active text-dark" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-1-tab">내가 쓴 게시물 목록
                    {{ userData.articleList }}</div>
                  <div class="tab-pane fade text-dark" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-2-tab">내가 쓴 후기 목록</div>
                  <div class="tab-pane fade text-dark" id="v-pills-3" role="tabpanel" aria-labelledby="v-pills-3-tab">내가 찜한 목록</div>
                  <div class="tab-pane fade text-dark" id="v-pills-4" role="tabpanel" aria-labelledby="v-pills-4-tab">
                      <div v-for="article in articles" :key="article.articleId">
                          <!-- <tempList/> -->
                       <router-link :to="{name:'articleUpdate', params: {ID:article.articleId}}" class="text-decoration-none">
                          <b-card class="">
                            <b-card-text class="d-flex justify-content-between">
                              <h6>제목: {{article.title}}</h6>
                              <div>
                          
                                <button class="btn btn-danger btn-sm" @click="deleteArticle({id:article.articleId,temp:0})">삭제하기</button>
                              </div>
                            </b-card-text>
                           
                          </b-card>
                          </router-link>
              
                      </div>
                  </div>
              </div>
          </div>
        </div>
    </div>
    <hr>
    <div class="mb-5">
        <router-link class="btn btn-info ml-1" v-bind:to="{name:constants.URL_TYPE.USER.EDIT}">회원수정</router-link>
        <button class="btn btn-danger mr-1" @click="deleteUser">회원탈퇴</button>
    </div>
  </div>
</template>

<script>
const BACK_URL = 'http://127.0.0.1:8080'
import constants from '../../lib/constants'
import {mapState, mapActions} from 'vuex'
// import tempList from '@/views/articles/tempList'

export default {
    name:'Profile',
    components:{
        // tempList
    },
    data: function () {
        return {
            constants,
        }
    },
    methods: {
        ...mapActions(['deleteUser','getArticles','deleteArticle']),
    },
    computed:{
        ...mapState(['userData','articles'])
    },
    created: function(){
        // this.getArticles({temp:0,categoryId:0})
    },
}
</script>

<style>
    .profile-block {
        height: 50px;
        background-color: lightgrey;
    }
    .intro {
        height: 100px;
        border: solid black;
    }
    .mypage-mypage {
        height: 300px;

    }
    #v-pills-1 {
        border: solid lightgray 1px;
        border-radius: 1%;
        height: 500px;
    }
    #v-pills-2 {
        border: solid lightgray 1px;
        border-radius: 1%;
        height: 500px;
    }
    #v-pills-3 {
        border: solid lightgray 1px;
        border-radius: 1%;
        height: 500px;
    }
    #v-pills-4 {
        border: solid lightgray 1px;
        border-radius: 1%;
        height: 500px;
    }
    .profile-photo {
        height: 100px;
        width: 100px;
    }
    .mypage1 {
        height: 300px;
    }
    .mypage2 {
        height: 300px;
    }
</style>