<template>
  <div>
   <div class="container m-5 mx-auto font-weight-bold">
      <div class="shadow border rounded m-5">
          <div class="form-group mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Title</p>
            <input id="title" type="text" class="form-control form-control-lg" v-model="articleUpdateData.title"/>
          </div>
          <div class="form-group w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Description</p>
            <textarea placeholder="내용을 입력해 주세요." class="form-control form-control-lg" v-model="articleUpdateData.description" id="content" cols="30" rows="10"></textarea>
          </div>
          <div class="form-group mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 mt-3 text-left">Image</p>
            <input type="file" class="form-control form-control-lg" />
          </div>
      </div>
    </div>
    <button class="btn btn-secondary" type="submit" @click="updateArticle({articleUpdateData,temp:2})">수정</button>
    <button class="ml-1 btn btn-danger" type="submit" @click="deleteArticle({id:articleUpdateData.articleId,temp:2})">삭제</button>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL
import axios from "axios"
import {mapActions,mapState} from 'vuex'

export default {
    name:'articleUpdate',
    data () {
      return {
        articleUpdateData: {},
      }
    },
    methods: {
        ...mapActions(['getArticle','deleteArticle','updateArticle']),
    },
    created: function(){
      this.getArticle(this.$route.params.ID)
      this.articleUpdateData=this.articleData
    },
    computed:{
      ...mapState(['articleData'])
    }
}
</script>
<style>
</style>