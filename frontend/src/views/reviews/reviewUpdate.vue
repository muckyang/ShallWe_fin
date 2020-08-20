<template>
  <div class="post-container" style="margin-left: 10%; margin-right: 10%">
    <h5 class="pl-sm-2 pb-sm-1">
      <strong class="our-main-font">후기 수정</strong>
    </h5>
    <!--선택 장소-->
      <div class="create-alert-msg">
        <small style="font-size: 15.5px; color: #ee6e9f;" class="our-main-font">*</small>
        <small style="font-size: 15.5px;" class="our-main-font">표시는 필수값입니다.</small>
      </div>
    <table class="table mt-1">
      <tbody>
        <tr>
          <th scope="row" class="our-main-font">제목<small style="font-size: 15.5px; color: #ee6e9f;" class="our-main-font">*</small></th>
          <td class="d-flex">
            <b-form-input class="our-main-font" type="text" v-model="articleData.title"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row" class="our-main-font">내용<small style="font-size: 15.5px; color: #ee6e9f;" class="our-main-font">*</small></th>
          <td>
            <div class="our-main-font">
              <b-form-textarea class="our-main-font" id="textarea-rows" rows="8" v-model="articleData.description"></b-form-textarea>
            </div>
          </td>
        </tr>
        <tr>
          <th scope="row" class="our-main-font">사진첨부</th>
          <td class="our-main-font">
            <b-form-file class="mt-3 our-main-font" @change="imageChange" plain></b-form-file>
          </td>
        </tr>
      </tbody>
    </table>
    <hr />
    <div class="d-flex justify-content-center">
    <button
      style="font-size: 15px;"
      class="complete-form our-main-font"
      type="submit"
      @click="updateArticle({ articleUpdateData, temp: 2 })"
    >수정</button>
    <button
      style="font-size: 15px;"
      class="temp-form our-main-font"
      
      type="submit"
      @click="deleteArticle({ id: articleUpdateData.articleId, temp: 2 })"
    >삭제</button>
    </div>
  </div>
</template>
<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import axios from "axios";
import { mapActions, mapState } from "vuex";

export default {
  name: "articleUpdate",
  data() {
    return {
      articleUpdateData: {},
    };
  },
  computed: {
    ...mapState(["articleData"]),
  },
  methods: {
    ...mapActions(["getArticle", "deleteArticle", "updateArticle"]),
    imageChange(e) {
      const selectedImage = e.target.files[0];
      this.createBase64Image(selectedImage);
    },
    createBase64Image(fileObject) {
      this.articleUpdateData.image = new Image();
      const reader = new FileReader();
      reader.onload = (e) => {
        this.articleUpdateData.image = e.target.result;
      };
      reader.readAsDataURL(fileObject);
    },
    imageUpload() {
      this.$refs.imageInput.click();
    },
  },
  created: function () {
    this.getArticle(this.$route.params.ID);
    this.articleUpdateData = this.articleData;
  },
};
</script>

<style>
</style>