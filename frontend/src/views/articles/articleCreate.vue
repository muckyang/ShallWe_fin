<template>
  <div>
    <hr>
    <div class="btn-group">
      <button type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
        {{ selectedTBG }}
      </button>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="#" @click="selectT1">쉘위택배</a>
        <a class="dropdown-item" href="#" @click="selectB2">쉘위배달</a>
        <a class="dropdown-item" href="#" @click="selectG3">쉘위공구</a>
      </div>
    </div>
    <hr>
    <label for="">제목</label>
    <input type="text" v-model="articleData.title">
    <hr>
    <label for="">address</label>
    <input type="text" v-model="articleData.address">
    <hr>
    <label for="">내용</label>
    <input type="text" v-model="articleData.description">
    <hr>
    <label for="">최소주문금액</label>
    <input type="number" v-model="articleData.minPrice">
    <hr>
    <label for="">urlLink</label>
    <input type="text" v-model="articleData.urlLink">
    <hr>
    <label for="">Image</label>
    <input type="file" @change="imageChange">
    <hr>
    <button class="font-weight-bold btn btn-dark" type="submit" @click="createArticle({articleData,temp:1})" value="Submit">Submit</button>
    <button @click="createArticle({articleData,temp:0})">임시저장</button>
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
          categoryId : 1,
          title: null,
          address: null,
          description: null,
          minPrice: null, 
          sumPrice: null,
          urlLink: null,
          imgae: null,
          token:this.$cookies.get('auth-token')
        },
        imageUrl: null, //다시 검토
        selectedTBG: '카테고리',
      };
    },
    methods: {
      ...mapActions(['createArticle','tempSaveArticle']),
      selectT1 () {
        this.articleData.categoryId = 1
        this.selectedTBG = '쉘위택배'
        console.log(this.articleData.categoryId)
      },
      selectB2 () {
        this.articleData.categoryId = 2
        this.selectedTBG = '쉘위배달'
        console.log(this.articleData.categoryId)
      },
      selectG3 () {
        this.articleData.categoryId = 3
        this.selectedTBG = '쉘위공구'
        console.log(this.articleData.categoryId)
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

</style>s