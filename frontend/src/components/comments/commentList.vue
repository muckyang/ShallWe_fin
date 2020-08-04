<template>
  <div class="mt-4">
    <div class="comment-start">
      <i class="far fa-comment-dots"></i> 댓글 {{comments.length}}개
    </div>    
    <hr class="comment-start-line">

    <!--댓글 보여주는 공간-->
    <commentListItem v-for="comment in comments" 
    :key="comment.comment_id" :comment="comment" 
    @re-render="getComments" :user="comment.writer"/>

    <!--댓글 등록 공간-->
    <div class="comment-write">
      <div class="comment-text">
        <input class="comment-text" placeholder="댓글을 남겨보세요 :)" 
      type="text" v-model="commentData.content" @keypress.enter="createComment">
      </div>
      <div class="comment-btn">
        <button type="button" class="comment-btn" @click="createComment">등록</button>
      </div> 
    </div>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import axios from "axios"
import commentListItem from '@/components/comments/commentListItem'

export default {
  name:'commentList',
  components:{
    commentListItem,
  },
  data(){
    return{
      comments:[],
      commentData:{
        articleId: this.$route.params.ID,
        content:'',
        token:this.$cookies.get('auth-token'),
      },
    }
  },
  methods: {
      createComment(){
        axios.post( `${BACK_URL}/comment/create` , this.commentData)
          .then(()=>{
            this.commentData.content=''
            console.log('댓글 보내기 완료')
          })
          .catch((err)=>{
            console.error(err)
          })
      },
      getComments()
      { 
        axios.get(`${BACK_URL}/comment/read/${this.$route.params.ID}`)
          .then((response)=>{
            this.comments = response.data.commentList
            console.log(this.comments, '댓글 나옴?')
          })
          .catch((err)=>{
            console.error(err)
          })       
      },
  },
  created(){
    this.getComments()
  },
}
</script>

<style>
.comment-start{
  padding: 0% 13%;
  text-align: left;
}
.comment-start-line{
  width: 75%;
}
.comment-write{
  border:2px solid rgba(0,0,0,0.1);
  border-radius: 6px;
  width: 75%;
  margin: 0 auto;
  padding: 16px 10px 10px 18px;
  display: flex;
  flex-direction: column;
}
.comment-btn{
  font-weight: bold;
  border: none;
  outline: none;
  background-color: transparent;
  color: grey;
  display: flex;
  justify-content: flex-end;
}
.comment-text{
  border: none;
  outline: none;
  display: block;
  
}
</style>