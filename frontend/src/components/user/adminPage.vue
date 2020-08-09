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
      <tbody>
        <tr>
          <th scope="row">1</th>
          <td>이메일</td>
          <td>이름</td>
          <td>닉네임</td>
          <td>지역</td>
          <td>
            <!-- 만약 관리자 라면 -->
            <!-- <p>관리자</p> -->
            <select name="level">
              <option value="1">Level 1</option>
              <option value="2">Level 2</option>
              <option value="3">Level 3</option>
              <option value="4">Level 4</option>
              <option value="5">Level 5</option>
            </select>
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
            <button class="btn btn-danger btn-sm">회원삭제</button>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
export default {
  name: 'adminPage',
  data() {
    return {
      
    }
  },
  methods: {
    
  },
}
</script>

<style>

</style>