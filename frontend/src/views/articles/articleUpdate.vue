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
            <b-form-input type="text" v-model="articleUpdateData.title"></b-form-input>
          </td>
        </tr>
      </tbody>
    </table>
    <hr>
    <div>
      <div class="bg-secondary text-light">
        <h3>어디서 만날까? - {{articleUpdateData.address}}</h3>
      </div>
      <kakaoMap @setAddress="setAddress"/>
    </div>
    <table class="table mt-5">
      <tbody>
        <tr>
          <th scope="row">시작금액/전체금액</th>
          <td class="d-flex">
            <b-form-input type="number" v-model="articleUpdateData.myPrice"></b-form-input>
            <b-form-input type="number" v-model="articleUpdateData.minPrice"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">URL</th>
          <td>
            <b-form-input type="url" v-model="articleUpdateData.urlLink"></b-form-input>
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
            <b-form-input type="date" v-model="articleUpdateData.endDate"></b-form-input>
            <b-form-input type="time" v-model="articleUpdateData.endTime"></b-form-input>
          </td>
        </tr>
        <tr>
          <th scope="row">내용</th>
          <td>
            <div>
              <b-form-textarea
                id="textarea-rows"
                rows="8"
                v-model="articleUpdateData.description"
              ></b-form-textarea>
            </div>
          </td>
        </tr>
        <tr>
          <th scope="row">태그</th>
          <td>
            <div>
              <b-form-tags input-id="tags-basic" v-model="articleUpdateData.tags" class="mb-2"></b-form-tags>
          </div>
          </td>
        </tr>
      </tbody>
    </table>
    <hr>
    <button class="btn btn-secondary" type="submit" @click="updateArticle({articleUpdateData,temp:1})">수정</button>
    <button class="ml-1 btn btn-danger" type="submit" @click="deleteArticle({id:articleUpdateData.articleId,temp:1})">삭제</button>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL
import axios from "axios"
import {mapActions,mapState} from 'vuex'
import kakaoMap from '@/components/articles/kakaoMap'

export default {
    name:'articleUpdate',
    components:{
      kakaoMap
    },
    data () {
      return {
        articleUpdateData: {},
        selectedTBG:"카테고리"
      }
    },
    methods: {
        ...mapActions(['getArticle','deleteArticle','updateArticle']),
        selectCategory(num){
          this.articleUpdateData.categoryId=num
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
      setAddress(address){
        this.articleData.address=address
      },
    },
    created: function(){
      this.getArticle(this.$route.params.ID)
      this.articleUpdateData=this.articleData
      if(this.articleUpdateData.categoryId===1){
        this.selectedTBG='쉘위배달'
      }else if(this.articleUpdateData.categoryId===2){
        this.selectedTBG='쉘위택배'
      }else{
        this.selectedTBG='쉘위공구'
      }
    },
    computed:{
      ...mapState(['articleData'])
    }
}
</script>
<style>
</style>