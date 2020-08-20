<template>
  <div class="post-container" style="margin-left: 10%; margin-right: 10%">
    <h5 class="pl-sm-2 pb-sm-1">
      <strong class="our-main-font">후기 쓰기</strong>
    </h5>
    <table class="table mt-1">
      <tbody>
        <tr>
          <th scope="row" class="our-main-font">제목<small style="font-size: 17px; color: red;">*</small></th>
          <td class="d-flex">
            <b-form-input type="text" v-model="articleData.title"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row" class="our-main-font">내용<small style="font-size: 17px; color: red;">*</small></th>
          <td>
            <div>
              <b-form-textarea id="textarea-rows" rows="8" v-model="articleData.description"></b-form-textarea>
            </div>
          </td>
        </tr>
        <tr>
          <th scope="row" class="our-main-font">사진첨부</th>
          <td>
            <b-form-file class="mt-3 our-main-font" @change="imageChange" plain></b-form-file>
          </td>
        </tr>
      </tbody>
    </table>
    <hr />
    <button class="temp-form our-main-font" @click="createArticle({articleData,temp:0})">임시저장</button>
    <button
      class="complete-form our-main-font"
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