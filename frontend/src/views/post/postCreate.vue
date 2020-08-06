<template>
  <div>
    <table class="table mt-5">
      <tbody>
        <tr>
          <th scope="row">제목</th>
          <td class="d-flex">
            <div class="btn-group">
              <button type="button" class="btn btn-outline-secondary btn-sm dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                {{ selectedTBG }}
              </button>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="#" @click="selectCategory(101)">질문</a>
                <a class="dropdown-item" href="#" @click="selectCategory(102)">후기</a>
                <a class="dropdown-item" href="#" @click="selectCategory(103)">자유</a>
              </div>
            </div>
            <b-form-input type="text" v-model="articleData.title"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">내용</th>
          <td>
            <div>
              <b-form-textarea
                id="textarea-rows"
                rows="8"
                v-model="articleData.description"
              ></b-form-textarea>
            </div>
          </td>
        </tr>
        <tr>
          <th scope="row">사진첨부</th>
          <td>
            <b-form-file class="mt-3" @change="imageChange" plain></b-form-file>
          </td>
        </tr>
        <tr>
          <th scope="row">태그</th>
          <td>
            <div>
              <b-form-tags input-id="tags-basic" v-model="value" class="mb-2"></b-form-tags>
          </div>
          </td>
        </tr>
      </tbody>
    </table>
    <hr>
    <button class="btn btn-primary" type="submit" @click="createArticle({articleData,temp:2})" value="Submit">작성완료</button>
    <button class="btn btn-info" @click="createArticle({articleData,temp:0})">임시저장</button>
  </div>
</template>

<script>
  const BACK_URL = 'http://127.0.0.1:8080'
  import axios from 'axios'
  import {mapActions} from 'vuex'


  export default {
    name: "CreateView",
    data() {
      return {
        articleData: {
          categoryId : '카테고리',
          title: null,
          description: null,
          token:this.$cookies.get('auth-token')
        },
        imageUrl: null, //다시 검토
        value: [],
        selectedTBG:'카테고리'
      };
    },
    methods: {
      ...mapActions(['createArticle','tempSaveArticle']),
      selectCategory(num){
        this.articleData.categoryId=num
        if(num===101){
          this.selectedTBG='질문'
        }else if (num===102) {
          this.selectedTBG='후기'
        }else {
          this.selectedTBG='자유'
        }
      },
      imageChange(e){
        const selectedImage = e.target.files[0]
        this.createBase64Image(selectedImage);
      },
      createBase64Image(fileObject){
        this.articleData.image = new Image();
        const reader = new FileReader();
        reader.onload = (e) => {
          this.articleData.image = e.target.result;
        };
        reader.readAsDataURL(fileObject);
      },
      imageUpload(){
        this.$refs.imageInput.click()
      },
    }
  }
</script>

<style>

</style>