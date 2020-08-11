<template>
  <div class="post-container">
    <h5 class="pl-sm-2 pb-sm-1">
      <strong>자유게시판 글쓰기</strong>
    </h5>
    <table class="table mt-1">
      <tbody>
        <tr>
          <th scope="row">제목</th>
          <td class="d-flex">
            <div class="btn-group">
              <button
                type="button"
                class="btn btn-outline-secondary btn-sm dropdown-toggle"
                data-toggle="dropdown"
                aria-haspopup="true"
                aria-expanded="false"
              >{{ selectedTBG }}</button>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="#" @click="selectCategory(101)">공지</a>
                <a class="dropdown-item" href="#" @click="selectCategory(102)">후기</a>
                <a class="dropdown-item" href="#" @click="selectCategory(103)">자유</a>
                <!-- 관리자라면 선택할 수 있음
                <a class="dropdown-item" href="#" @click="selectCategory(104)">공지사항</a>-->
              </div>
            </div>
            <b-form-input type="text" v-model="articleData.title"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">내용</th>
          <td>
            <div>
              <b-form-textarea id="textarea-rows" rows="8" v-model="articleData.description"></b-form-textarea>
            </div>
          </td>
        </tr>
        <tr>
          <th scope="row">사진첨부</th>
          <td>
            <b-form-file class="mt-3" @change="imageChange" plain></b-form-file>
          </td>
        </tr>
      </tbody>
    </table>
    <hr />
    <button class="temp-form" @click="createArticle({articleData,temp:0})">임시저장</button>
    <button
      class="complete-form"
      type="submit"
      @click="createArticle({articleData,temp:2})"
      value="Submit"
    >작성완료</button>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080";
import axios from "axios";
import { mapActions } from "vuex";

export default {
  name: "CreateView",
  data() {
    return {
      articleData: {
        categoryId: "카테고리",
        title: null,
        description: null,
        token: this.$cookies.get("auth-token"),
      },
      imageUrl: null, //다시 검토
      value: [],
      selectedTBG: "카테고리",
    };
  },
  methods: {
    ...mapActions(["createArticle", "tempSaveArticle"]),
    selectCategory(num) {
      this.articleData.categoryId = num;
      if (num === 101) {
        this.selectedTBG = "공지";
      } else if (num === 102) {
        this.selectedTBG = "후기";
      } else {
        this.selectedTBG = "자유";
      }
    },
    imageChange(e) {
      const selectedImage = e.target.files[0];
      this.createBase64Image(selectedImage);
    },
    createBase64Image(fileObject) {
      this.articleData.image = new Image();
      const reader = new FileReader();
      reader.onload = (e) => {
        this.articleData.image = e.target.result;
      };
      reader.readAsDataURL(fileObject);
    },
    imageUpload() {
      this.$refs.imageInput.click();
    },
  },
};
</script>

<style>
.post-container {
  margin: 7% 0 0 0;
  text-align: left;
}
</style>