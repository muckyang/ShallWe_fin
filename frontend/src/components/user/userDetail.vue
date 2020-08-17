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
    <hr>
    <button class="btn btn-danger" @click="changeUserStatus()">회원삭제</button>
  </div>
</template>

<script>
  import { mapState, mapActions } from "vuex";
  import axios from "axios";
  import cookies from "vue-cookies";

  const BACK_URL = process.env.VUE_APP_BACK_URL;

  export default {
    name: 'userDetail',
    computed: {
      ...mapState([ "userData" ]),
    },
    methods: {
      ...mapActions([ "getUserDatailData" ]),
      changeUserStatus() {
        this.userData.status = 0
        this.sendChangedUserStatus();
      },
      sendChangedUserStatus() {
        this.userData.token = this.$cookies.get('admin-token')
        axios
          .post(`${BACK_URL}/accuse/disabled`, this.userData)
          .then(() => {
            console.log(this.userData, 'UUUUUUUUUUUUUUUUUUUU')
          })
          .catch(( err ) => {
            console.error(err);
          });
      },
    },
    created() {
      this.getUserDatailData(this.$route.params.ID);
    }
  }

</script>

<style>

</style>