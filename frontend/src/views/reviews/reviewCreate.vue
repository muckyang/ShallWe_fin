<template>
  <div class="post-container">
    <h5 class="pl-sm-2 pb-sm-1">
      <strong>후기 쓰기</strong>
    </h5>
    <table class="table mt-1">
      <tbody>
        <tr>
          <th scope="row">제목<small style="font-size: 17px; color: red;">*</small></th>
          <td class="d-flex">
            <b-form-input type="text" v-model="articleData.title"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">내용<small style="font-size: 17px; color: red;">*</small></th>
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
    >
      <i class="fas fa-check"></i> 완료
    </button>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080";
import axios from "axios";
import { mapActions } from "vuex";

export default {
  name: "reviewCreate",
  data() {
    return {
      articleData: {
        categoryId: 102,
        title: null,
        description: null,
        token: this.$cookies.get("auth-token"),
      },
      imageUrl: null, //다시 검토
      value: [],
    };
  },
  methods: {
    ...mapActions(["createArticle", "tempSaveArticle"]),
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