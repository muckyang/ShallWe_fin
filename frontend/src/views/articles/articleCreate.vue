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
                <a class="dropdown-item" href="#" @click="selectCategory(1)">쉘위배달</a>
                <a class="dropdown-item" href="#" @click="selectCategory(2)">쉘위택배</a>
                <a class="dropdown-item" href="#" @click="selectCategory(3)">쉘위공구</a>
              </div>
            </div>
            <b-form-input type="text" v-model="articleData.title"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">지역</th>
          <td>
            <b-form-input type="text" v-model="articleData.address"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">시작금액/전체금액</th>
          <td class="d-flex">
            <b-form-input type="number" v-model="articleData.myPrice"></b-form-input>
            <b-form-input type="number" v-model="articleData.minPrice"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">URL</th>
          <td>
            <b-form-input type="url" v-model="articleData.urlLink"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">사진첨부</th>
          <td>
            <b-form-file class="mt-3" @change="imageChange" plain></b-form-file>
          </td>
        </tr>
        <tr>
          <th scope="row">종료일자/종료시간</th>
          <td class="d-flex">
            <b-form-input type="date" v-model="articleData.endDate"></b-form-input>
            <b-form-input type="time" v-model="articleData.endTime"></b-form-input>
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
    <button class="btn btn-primary" type="submit" @click="createArticle({articleData,temp:1})" value="Submit">작성완료</button>
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
          address: null,
          description: null,
          minPrice: null,
          myPrice: null,
          urlLink: null,
          imgae: null,
          endDate:null,
          endTime:null,
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
        if(num===1){
          this.selectedTBG='쉘위배달'
        }else if(num===2){
          this.selectedTBG='쉘위택배'
        }else{
          this.selectedTBG='쉘위공구'
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