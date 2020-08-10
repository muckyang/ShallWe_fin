<template>
  <div class="mt-5">
    <h1>관리자페이지</h1>
    <table class="table">
      <thead class="thead-dark">
        <tr>
          <th scope="col">#</th>
          <th scope="col">이메일</th>
          <th scope="col">이름</th>
          <th scope="col">닉네임</th>
          <th scope="col">지역</th>
          <th scope="col">등급</th>
          <th scope="col">회원관리</th>
        </tr>
      </thead>
      <tbody v-for="user in users" :key="user.userId">
        <tr>
          <th scope="row">{{ user.userId }}</th>
          <td>{{ user.email }}</td>
          <td>{{ user.name }}</td>
          <td>{{ user.nickname }}</td>
          <td>{{ user.address }}</td>
          <td>
            <!-- 회원등급 Select -->
            <p v-if="user.grade===0" name="level">
              관리자
            </p>
            <div class="dropdown" v-else>
              <a class="btn btn-info dropdown-toggle btn-sm" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                Level {{ user.grade }}
              </a>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                <a class="dropdown-item" href="#" @click="changeGrade({ changedGrade:user, grade: 1 })">Level 1</a>
                <a class="dropdown-item" href="#" @click="changeGrade({ changedGrade:user, grade: 2 })">Level 2</a>
                <a class="dropdown-item" href="#" @click="changeGrade({ changedGrade:user, grade: 3 })">Level 3</a>
                <a class="dropdown-item" href="#" @click="changeGrade({ changedGrade:user, grade: 4 })">Level 4</a>
                <a class="dropdown-item" href="#" @click="changeGrade({ changedGrade:user, grade: 5 })">Level 5</a>
              </div>
            </div>
          </td>
          <td class="d-flex justify-content-center">
            <b-button v-b-modal.modal-scrollable class="btn btn-warning btn-sm text-white">신고내역</b-button>
            <div>
              <b-modal id="modal-scrollable" scrollable title="신고내역">
                <p class="my-4" v-for="i in 20" :key="i">
                  <b-card title="신고제목" sub-title="작성자">
                    <b-card-text>
                      신고내용신고내용신고내용신고내용신고내용신고내용신고내용
                    </b-card-text>
                    <div class="d-flex justify-content-end">
                      <a href="#" class="card-link btn btn-secondary btn-sm m-0">신고취소</a>
                      <a href="#" class="card-link btn btn-danger btn-sm m-0">신고접수</a>
                    </div>
                  </b-card>
                </p>
              </b-modal>
            </div>
            <button class="btn btn-danger btn-sm" @click="deleteUser">회원삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
    <button class="btn btn-primary" @click="updateGrade({ updatedGrade })">수정완료</button>
  </div>
</template>

<script>
  import { mapState, mapActions } from 'vuex'

  export default {
    name: 'adminPage',
    data() {
      return {
      }
    },
    methods: {
      ...mapActions([ 'getUsers', 'updateGrade', 'getArticles', 'deleteUser', 'deleteArticle' ]),
      changeGrade(changedObj) {
        changedObj.changedGrade.grade = changedObj.grade
      }
    },
    computed: {
      ...mapState([ 'users', 'articles' ]),
    },
    created() {
      this.getUsers()
    },
  }
</script> 

<style>

</style>