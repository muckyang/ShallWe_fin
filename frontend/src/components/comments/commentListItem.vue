<template>
  <div>
    <div class="d-flex ">
        <div class="comment-box">
            <div class="comment-user">
                {{commentData.userId}}
                <!-- <div>
                    <b-dropdown size="lg"  variant="link" toggle-class="text-decoration-none" no-caret>
                        <template v-slot:button-content>
                        <i class="fas fa-ellipsis-v"></i>
                        </template>
                        <b-dropdown-item href="#">수정</b-dropdown-item>
                        <b-dropdown-item href="#">삭제</b-dropdown-item>
                    </b-dropdown>
                </div> -->

                <div class="comment-drop" v-if="checkAuth">
                    <button class="comment-btn">
                        <i class="fas fa-ellipsis-v"></i>
                    </button>
                    <div class="dropcontent">
                        <button class="comment-ud" @click="showInput" v-if="!flag">수정</button> 
                        <button class="comment-del" @click="deleteComment">삭제</button>
                    </div>
                </div> 
            </div>
            <div class="comment-content">{{commentData.content}}</div>
            <div class="comment-create-time">{{commentData.create_time}}</div>
        </div>
    </div>
    <!-- v-for="comment in comments" 
    :key="comment.comment_id" :comment="comment" 
    @re-render="getComments" :user="comment.writer" -->

    <!-- 댓글 수정 -->
    <input v-if="flag" type="text" v-model="commentData.content">
    <button v-if="flag" @click="updateComment">댓글 수정</button>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"                
import axios from "axios"
import {mapState,mapActions} from 'vuex'

export default {
    name:'commentListItem',
    props:{
        comment:Object,
        user:String,      
    },
    data(){
        return{
            watchFlag:false,
            flag:false,
            usersameflag:false,
            commentData:{
                commentId:this.comment.commentId,
                content:this.comment.content,
                userId: this.comment.userId,
                create_time: this.comment.create_time,
                token:this.$cookies.get('auth-token')
            },
            userIdForCheck:''
        }
    },
    computed:{
        checkAuth(){
            console.log(this.commentData,'아이디')
            console.log(this.comment, '댓글')
            console.log(this.user, '유저')
            return this.comment.userId===this.userIdForCheck.userId
        },
        ...mapState(['userData'])
    },
    methods:{
        ...mapActions(['getUserData']),
        deleteComment(){
            const auth = {token:this.$cookies.get('auth-token')}
            axios.post(BACK_URL+`/comment/delete/${this.commentData.commentId}`,auth)
                .then(()=>{
                    this.watchFlag=true
                })
                .catch((err)=>{
                    console.error(err)
                })
        },
        showInput(){
            console.log(this.flag, '수정전')
            this.flag=true
            console.log(this.flag, '수정후')
        },
        updateComment(){
            axios.post(BACK_URL+'/comment/update',this.commentData)
                .then((response)=>{
                    this.flag=false
                    console.log(this.flag, '수정gngngngngn')
                    //~~~~~~~~~~~~~~~~~~~~~~~~~중요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    // this.commentData=response.data
                    //다시 댓글을 전부 다시 받는 것이 아니라 수정 후 응답으로 수정된 댓글만 다시 받아온다.
                    //~~~~~~~~~~~~~~~~~~~~~~~~~중요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                })
                .catch((err)=>{
                    console.error(err)
                })
        },
    },
    watch:{
        watchFlag(){
            this.$emit('re-render')
        }
    },
    created(){
        this.getUserData()
        this.userIdForCheck=this.userData
        this.checkAuth
    }
}
</script>

<style>
/* @import '../node_modules/bootstrap-vue/src/components/dropdown/_dropdown.scss'; */
/* @import url('node_modules/bootstrap-vue/src/components/dropdown/_dropdown.scss'); */
.comment-box{
    border-bottom: 1px solid rgb(237, 237, 240);
    display: flex;
    flex-direction: column;
    width: 100%;
    margin-bottom: 1%;
}
.comment-user{
    text-align: left;
    font-weight: bold;
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}
.comment-content{
    text-align: left;
}
.comment-create-time{

}
.comment-drop{
    display: flex;
    flex-direction: row;

    /* position: relative;
  display: inline-block; */
}
.comment-btn {
  background-color: transparent;
  color: rgb(182, 182, 182);
  padding:0;
  font-size: 15px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  /* height: 50px; */
}

.dropcontent {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 90px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  border-radius: 5px;
}
.dropcontent button {
    border: none;
    outline: none;
    background-color: transparent;
    color: black;
    font-size:medium;
    padding: 10;
    text-decoration: none;
    display: block;
    
}
.dropcontent button:hover {
    background-color: #f1f1f1
}
.comment-drop:hover .dropcontent {
  display: block;
}
.comment-drop:hover .comment-btn {
  background-color: #3a4049;
}
</style>