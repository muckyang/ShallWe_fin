<template>
<div class="mt-5">
  <div class="mb-4 container">
    <div class="row">
      <div class="col-6">
        <b-card no-body class="overflow-hidden">
          <b-row no-gutters>
            <b-col>
              <h5 class="mt-5">"{{ userData.nickname }}" 님의 프로필</h5>
              <hr style="width: 70%;">
              <h6 class="mt-0 mb-4">{{ userData.email }}</h6>
              <h6 class="mt-0">닉네임: {{ userData.nickname }}</h6>
              <h6 class="mt-0">지역: {{ userData.address }}</h6>
            </b-col>
            <b-col style="border-left: solid 1px lightgray">
              <b-card-body>
                <b-card-text>
                  <div class="text-left">
                    <h6 class="text-center mt-5">자기소개</h6>
                    <div class="mb-5" style="border: solid 1px lightgray; height: 100px;"></div>
                  </div>
                </b-card-text>
              </b-card-body>
            </b-col>
          </b-row>
        </b-card>
      </div>
        <div class="col-6">
          <b-card no-body class="overflow-hidden h-100">
            <b-row no-gutters>
              <b-col class="d-flex justify-content-center mt-5 mb-5">
                <img class="mt-4 mb-4" style="width: 120px; height: 120px" src="https://picsum.photos/1024/480/?image=52" alt="">
                <div class="ml-5 mt-4" style="border-left: solid 1px lightgray">
                  <h6 class="mb-5 mt-3 ml-5">등급: Level {{ userData.grade }}</h6>
                  <h6 class="ml-5">포인트: {{ userData.userPoint }}<small class="ml-1">Point</small></h6>
                </div>
              </b-col>
            </b-row>
          </b-card>
        </div>
      </div>
    </div>

    <div class="container">
      <div class="row">
        <div class="col-3">
          <div class="list-group flex-column" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="list-group-item list-group-item-action" id="v-pills-1-tab" data-toggle="pill" href="#v-pills-1" role="tab" aria-controls="v-pills-1" aria-selected="true">내가 쓴 게시물</a>
            <a class="list-group-item list-group-item-action" id="v-pills-2-tab" data-toggle="pill" href="#v-pills-2" role="tab" aria-controls="v-pills-2" aria-selected="true">참여한 게시물</a>
            <a class="list-group-item list-group-item-action" id="v-pills-3-tab" data-toggle="pill" href="#v-pills-3" role="tab" aria-controls="v-pills-3" aria-selected="false">거래 완료 게시물</a>
            <a class="list-group-item list-group-item-action" id="v-pills-4-tab" data-toggle="pill" href="#v-pills-4" role="tab" aria-controls="v-pills-4" aria-selected="false">내가 쓴 후기</a>
            <a class="list-group-item list-group-item-action" id="v-pills-5-tab" data-toggle="pill" href="#v-pills-5" role="tab" aria-controls="v-pills-5" aria-selected="false">내가 쓴 커뮤니티</a>
            <a class="list-group-item list-group-item-action" id="v-pills-6-tab" data-toggle="pill" href="#v-pills-6" role="tab" aria-controls="v-pills-6" aria-selected="false">찜 목록</a>
            <a class="list-group-item list-group-item-action" id="v-pills-7-tab" data-toggle="pill" href="#v-pills-7" role="tab" aria-controls="v-pills-7" aria-selected="false">임시 저장</a>
          </div>
        </div>
        <div class="col-9">
          <div class="tab-content" id="v-pills-tabContent">
            <div class="tab-pane fade show active text-dark" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-1-tab">
              내가 쓴 게시물 목록
              {{ userData.articleList }}
            </div>
            <div class="tab-pane fade text-dark" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-2-tab">
              참여한 게시물
              {{ userData.joinList }}
            </div>
            <div class="tab-pane fade text-dark" id="v-pills-3" role="tabpanel" aria-labelledby="v-pills-3-tab">
              거래 완료 게시물
              {{ userData.completeList }}
            </div>
            <div class="tab-pane fade text-dark" id="v-pills-4" role="tabpanel" aria-labelledby="v-pills-4-tab">
              내가 쓴 후기 목록
              {{ userData.reviewList }}
            </div>
            <div class="tab-pane fade text-dark" id="v-pills-5" role="tabpanel" aria-labelledby="v-pills-5-tab">
              내가 쓴 커뮤니티 목록
              {{ userData.freeList }}
            </div>
            <div class="tab-pane fade text-dark" id="v-pills-6" role="tabpanel" aria-labelledby="v-pills-6-tab">
              내가 찜한 목록
              {{ userData.likeList }}
            </div>
            <div class="tab-pane fade text-dark" id="v-pills-7" role="tabpanel" aria-labelledby="v-pills-7-tab">
              임시 저장 목록
              {{ userData.tempList }}
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
    #v-pills-5 {
      border: solid lightgray 1px;
      border-radius: 1%;
      height: 500px;
    }
    #v-pills-6 {
      border: solid lightgray 1px;
      border-radius: 1%;
      height: 500px;
    }
    #v-pills-7 {
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