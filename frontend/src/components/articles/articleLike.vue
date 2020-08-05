<template>
  <div class="like-content">
    <button v-if="!isLiked" @click="like" 
    class="like-review">
    <i class="fas fa-heart"></i> 찜</button>

    <button v-if="isLiked" @click="like" 
    class="like-review">
    <i class="fas fa-heart-broken"></i> 찜 취소</button>
  </div>
</template>

<script>

import axios from 'axios'
const BACK_URL = "http://127.0.0.1:8080"

export default {
    name: "articleLike",
    props: {
        article: Object,
        isLiked: Boolean,
    },
    data(){
      return{
        articleData:this.article,
      }
    },
    methods: {
        like(){
          const articleData=this.articleData
          articleData.token=this.$cookies.get('auth-token')
          axios.post(`${BACK_URL}/like/${this.articleData.articleId}`,articleData)
            .then(() => { 
              this.$emit('like-change')
            })
            .catch(err => console.log(err.response.data))
            },
    }
}
</script>

<style>
.like-content {
    font-size: 17px;
    text-align: center;
}
.like-content .like-review {
    display: block;
    /* margin: 40px auto 0px; */
    text-align: center;
    background: #ee6e9f;
    border-radius: 3px;
    box-shadow: 0 10px 20px -8px rgb(240, 75, 113);
    padding: 10px 12px;
    font-size: 17px;
    cursor: pointer;
    border: none;
    outline: none;
    color: #ffffff;
    text-decoration: none;
    -webkit-transition: 0.3s ease;
    transition: 0.3s ease;
    /* margin: 0 1% 0 1%; */
}
.like-content .like-review:hover {
      transform: translateY(-3px);
}
.like-content .like-review .fa {
      margin-right: 5px;
}
</style>