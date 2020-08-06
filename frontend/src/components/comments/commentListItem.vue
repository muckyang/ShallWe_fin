<template>
  <div>
    <div class="d-flex ">
        <div class="comment-box">
            <div class="comment-user">
                {{comment.nickname}}

                <div class="comment-drop dropdown dropleft" v-if="checkAuth">
                    <button type="button" class="comment-btn" data-toggle="dropdown">
                        <i class="fas fa-ellipsis-v"></i>
                    </button>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" @click="showInput" v-if="!flag">수정</a>
                        <a class="dropdown-item" @click="deleteComment">삭제</a>
                    </div>
                </div>

            </div>
            <div class="comment-content">{{comment.content}}</div>
            <div class="comment-create-time">{{comment.createTime}}</div>
        </div>
    </div>

    <!-- 댓글 수정 -->

    <div v-if="flag" class="comment-update-write">
      <div class="comment-update-text">
        <input class="comment-input" type="text" v-model="commentData.content">
      </div>
      <div class="comment-update-submit">
        <button type="button" v-if="flag" class="comment-update-btn" @click="updateCancel">취소</button>
        <button type="button" v-if="flag" class="comment-update-btn" @click="updateComment">수정</button>
      </div> 
    </div>

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
    },
    data(){
        return{
            watchFlag:false,
            flag:false,
            usersameflag:false,
            commentData:{
                commentId:this.comment.commentId,
                content:this.comment.content,
                user: this.comment.userId,
                createTime: this.comment.createTime,
                token:this.$cookies.get('auth-token')
            },
            userIdForCheck:'',
            canceldata:{
                commentId:this.comment.commentId,
                content:this.comment.content,
                userId: this.comment.userId,
                createTime: this.comment.createTime,
                token:this.$cookies.get('auth-token')
            },
        }
    },
    computed:{
        checkAuth(){
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
                    //~~~~~~~~~~~~~~~~~~~~~~~~~중요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                    // this.commentData=response.data
                    //다시 댓글을 전부 다시 받는 것이 아니라 수정 후 응답으로 수정된 댓글만 다시 받아온다.
                    //~~~~~~~~~~~~~~~~~~~~~~~~~중요~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                })
                .catch((err)=>{
                    console.error(err)
                })
        },
        updateCancel() {
            console.log(this.canceldata)
            console.log(this.commentData, '바뀐 댓글데이타')
            this.commentData = this.canceldata
            console.log(this.commentData, '취소 후')
            this.watchFlag=true
        }
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
    padding: 0 0 0 0.3%;
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
    text-align: left;
    color:rgb(145, 141, 141);
    font-size: small;
    margin-bottom: 1%;
}
.comment-drop{
    display: flex;
    flex-direction: row;
    width: 3%;

}
.comment-btn {
    background-color: transparent;
    color: rgb(182, 182, 182);
    padding:0;
    font-size: 15px;
    border: none;
    cursor: pointer;
    width: 100%;
    outline: none;
}
.comment-drop:hover{
    border: none;
    outline: none;
}
.comment-btn:hover{
    border: none;
    outline: none;
}
.comment-drop:hover .comment-btn {
    background-color: transparent;
    color: rgb(145, 141, 141);
    border: none;
    outline: none;

}
.comment-update-write{
  border:2px solid rgba(0,0,0,0.1);
  border-radius: 6px;
  width: 100%;
  margin: 1% auto;
  padding: 16px 10px 10px 18px;
  display: flex;
  flex-direction: column;
}
.comment-update-submit{
  display: flex;
  justify-content: flex-end;
  margin: 0 4px 3px 0; 
}
.comment-update-btn{
  font-weight: bold;
  border: none;
  outline: none;
  background-color: transparent;
  color: grey;
}
.comment-update-text{
  /* border: none;
  outline: none; */
  display: block;
}
.comment-update-input{
  border: none;
  outline: none;
  width: 100%;
}

</style>