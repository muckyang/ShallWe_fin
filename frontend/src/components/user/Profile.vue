<template>
    <div class="mt-5">
      <!-- 1. 상단 -->
      <div class="mb-4 container">
        <div class="row">
          <b-card no-body class="overflow-hidden col-6">
            <b-row no-gutters>
              <b-col>
                <b-card-img src="https://picsum.photos/400/400/?image=20" alt="Image" class="rounded-0"></b-card-img>
              </b-col>
              <b-col>
                <b-card-body title="내 정보">
                  <b-card-text>
                    <h6 class="mt-0">이메일: {{ userData.email }}</h6>
                    <h6 class="mt-0">이름: {{ userData.name }}</h6>
                    <h6 class="mt-0">닉네임: {{ userData.nickname }}</h6>
                    <h6 class="mt-0">생일: {{ userData.birthday }}</h6>
                    <h6 class="mt-0">지역: {{ userData.address }}</h6>
                    <h6>자기소개</h6>
                    <div style="height: 50px; border: solid black 1px"></div>
                  </b-card-text>
                </b-card-body>
              </b-col>
            </b-row>
          </b-card>
          <b-card bg-variant="light" text-variant="dark" class="col-6">
            <b-card-text class="mt-4">
              <h3>나의 레벨</h3>
              <div class="progress">
                <div class="progress-bar progress-bar-striped bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
              </div>
              <p>{{ userData.userPoint }} Point</p>
              <div class="container mt-5">
                <div class="row">
                  <div class="col-4">
                    <h5>점수</h5>
                    <h2>100점</h2>
                  </div>
                  <div class="col-4">
                    <h5>점수</h5>
                    <h2>100점</h2>
                  </div>
                  <div class="col-4">
                    <h5>점수</h5>
                    <h2>100점</h2>
                  </div>
                </div>
              </div>
            </b-card-text>
          </b-card>
        </div>
      </div>

      <!-- 2. 중단 -->
      <b-card bg-variant="light" text-variant="dark" class="mb-4">
        <b-card-text>
          <div class="container">
                <div class="row">
                  <div class="col-4">
                    <b-icon icon="camera" font-scale="4"></b-icon>
                    <h2>100점</h2>
                  </div>
                  <div class="col-4">
                    <b-icon icon="camera" font-scale="4"></b-icon>
                    <h2>100점</h2>
                  </div>
                  <div class="col-4">
                    <b-icon icon="camera" font-scale="4"></b-icon>
                    <h2>100점</h2>
                  </div>
                </div>
              </div>
        </b-card-text>
      </b-card>

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
                  <div class="tab-pane fade show active text-dark" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-1-tab">내가 쓴 게시물 목록</div>
                  <div class="tab-pane fade text-dark" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-2-tab">내가 쓴 후기 목록</div>
                  <div class="tab-pane fade text-dark" id="v-pills-3" role="tabpanel" aria-labelledby="v-pills-3-tab">내가 찜한 목록</div>
                  <div class="tab-pane fade text-dark" id="v-pills-4" role="tabpanel" aria-labelledby="v-pills-4-tab">
                      <div v-for="article in articles" :key="article.articleId">
                          <!-- <tempList/> -->
                          <span>{{article.title}}</span>
                          <button class="btn btn-info"><router-link :to="{name:'articleUpdate', params: {ID:article.articleId}}">작성하기</router-link></button>
                          <button class="btn btn-danger" @click="deleteArticle({id:article.articleId,temp:0})">삭제하기</button>
                      </div>
                  </div>
              </div>
          </div>
        </div>
        <div class="mt-5 mb-5">
            <button class="btn btn-danger mr-1" @click="deleteUser">회원 탈퇴 하기</button>
            <router-link class="btn btn-info ml-1" v-bind:to="{name:constants.URL_TYPE.USER.EDIT}">회원 정보 수정하기</router-link>
        </div>
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
        ...mapActions(['getUserData','deleteUser','getArticles','deleteArticle']),
    },
    computed:{
        ...mapState(['userData','articles'])
    },
    created: function(){
        this.getUserData()
        this.getArticles({temp:0,categoryId:0})
        console.log("created")
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