<template>
    <div class="mt-5">
        <div class="mypage-mypage mb-5 container">
            <div class="row">
                <div class="col-6">
                    <div class="media mypage1">
                        <img src="https://source.unsplash.com/random" class="align-self-center mr-3 profile-photo" alt="...">
                        <div class="media-body">
                            <h1 class="mt-2">내 정보</h1>
                            <h6 class="mt-0">이메일: {{ userData.email }}</h6>
                            <h6 class="mt-0">이름: {{ userData.name }}</h6>
                            <h6 class="mt-0">닉네임: {{ userData.nickname }}</h6>
                            <h6 class="mt-0">생일: {{ userData.birthday }}</h6>
                            <h6 class="mt-0">지역: {{ userData.address }}</h6>
                            <div>
                                <h6>자기소개</h6>
                                <div style="height: 50px; border: solid black 1px"></div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-6">
                    <div class="mypage2 d-flex flex-column align-items-center justify-content-center" style="border-left: solid black 1px;">
                        <h1>나의 레벨</h1>
                        <p>포인트: {{ userData.userPoint }}</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
        <div class="col-3">
            <div class="nav flex-column nav-side" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active text-dark" id="v-pills-1-tab" data-toggle="pill" href="#v-pills-1" role="tab" aria-controls="v-pills-1" aria-selected="true">내가 쓴 게시물</a>
                <a class="nav-link text-dark" id="v-pills-2-tab" data-toggle="pill" href="#v-pills-2" role="tab" aria-controls="v-pills-2" aria-selected="false">내가 쓴 후기</a>
                <a class="nav-link text-dark" id="v-pills-3-tab" data-toggle="pill" href="#v-pills-3" role="tab" aria-controls="v-pills-3" aria-selected="false">찜 목록</a>
                <a class="nav-link text-dark" id="v-pills-4-tab" data-toggle="pill" href="#v-pills-4" role="tab" aria-controls="v-pills-4" aria-selected="false">임시 저장</a>
            </div>
        </div>
        <div class="col-9">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active text-dark" id="v-pills-1" role="tabpanel" aria-labelledby="v-pills-1-tab">내가 쓴 게시물 목록</div>
                <div class="tab-pane fade text-dark" id="v-pills-2" role="tabpanel" aria-labelledby="v-pills-2-tab">내가 쓴 후기 목록</div>
                <div class="tab-pane fade text-dark" id="v-pills-3" role="tabpanel" aria-labelledby="v-pills-3-tab">내가 찜한 목록</div>
                <div class="tab-pane fade text-dark" id="v-pills-4" role="tabpanel" aria-labelledby="v-pills-4-tab">
                    <div v-for="article in articles" :key="article.articleId">
                        <span>{{article.title}}</span>
                        <router-link :to="{name:'articleUpdate', params: {ID:article.articleId}}">작성하기</router-link>
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
</template>

<script>
const BACK_URL = 'http://127.0.0.1:8080'
import constants from '../../lib/constants'
import {mapState, mapActions} from 'vuex'

export default {
    name:'Profile',
    data: function () {
        return {
            constants,
        }
    },
    methods: {
        ...mapActions(['getUserData','deleteUser','getArticles']),
    },
    computed:{
        ...mapState(['userData','articles'])
    },
    created: function(){
        this.getUserData()
        this.getArticles({temp:0,categoryId:0})
    }
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
        border: solid black 1px;
    }
    #v-pills-1 {
        border: solid black 1px;
        height: 500px;
    }
    #v-pills-2 {
        border: solid black 1px;
        height: 500px;
    }
    #v-pills-3 {
        border: solid black 1px;
        height: 500px;
    }
    #v-pills-4 {
        border: solid black 1px;
        height: 500px;
    }
    .nav-side {
        border: solid black 1px;
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