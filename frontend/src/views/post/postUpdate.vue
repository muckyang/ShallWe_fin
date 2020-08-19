<template>
  <div>
    <div class="container m-5 mx-auto font-weight-bold">
      <div class="shadow border rounded m-5">
        <div class="form-group mb-5 w-75 mx-auto">
          <p class="align-self-center m-1 text-left">Title</p>
          <input
            id="title"
            type="text"
            class="form-control form-control-lg"
            v-model="articleUpdateData.title"
          />
        </div>
        <div class="form-group w-75 mx-auto">
          <p class="align-self-center m-1 text-left">Description</p>
          <textarea
            placeholder="내용을 입력해 주세요."
            class="form-control form-control-lg"
            v-model="articleUpdateData.description"
            id="content"
            cols="30"
            rows="10"
          ></textarea>
        </div>
        <div class="form-group mb-5 w-75 mx-auto">
          <p class="align-self-center m-1 mt-3 text-left">Image</p>
          <input type="file" id="file" name="file" ref="file" style="width: 100%;"/>
        </div>
      </div>
    </div>
    <button
      class="btn btn-secondary"
      type="submit"
      @click="postUpdate"
    >수정</button>
    <button
      class="ml-1 btn btn-danger"
      type="submit"
      @click="deleteArticle({ id: articleUpdateData.articleId, temp: 2 })"
    >삭제</button>
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
    fileUpload: function () {
      var formData = new FormData();
      this.file = this.$refs.file.files[0];
      formData.append("file", this.file);
      formData.append("uid", 10);
      axios.post(`${BACK_URL}/file`
          ,formData
          , {
              headers: {
                  'Content-Type': 'multipart/form-data'
              }
          })
        .then((response) => {
          this.path = response.data
          this.articleData.image = this.path
          var tmp=this.articleData.image.split('.')
          tmp[1]=tmp[1].toLowerCase()
          this.articleData.image = tmp[0]+'.'+tmp[1]
        })
        .catch((error) => {
          console.log(error);
        });
    },
    postUpdate(){
      this.fileUpload()
      setTimeout(() => {
        var articleUpdateData=this.articleUpdateData
        this.updateArticle({ articleUpdateData, temp: 2 })
      }, 300);
    },
  },
  created: function () {
    this.getArticle(this.$route.params.ID);
    this.articleUpdateData = this.articleData;
  },
  computed: {
    ...mapState(["articleData"]),
  },
};
</script>
<style></style>
