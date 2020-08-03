<template>
  <div>
    <div class="d-flex bd-highlight mb-3">
        <div class="ml-auto bd-highlight">
        <span v-if="checkAuth">
            <span class="coutline">{{commentData.content}}</span>
            <span>{{commentData.userId}}</span>
            <button class="btn btn-warning" @click="deleteComment">삭제</button> |
            <button class="btn btn-info" @click="showInput" v-if="!flag">수정</button>
        </span>
        </div>
    </div>
    
    <!-- 댓글 수정 -->
    <input v-if="flag" type="text" v-model="commentData.content">
    <button v-if="flag" @click="updateComment">댓글 수정</button>
    <hr>
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
                token:this.$cookies.get('auth-token')
            },
            userIdForCheck:''
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
            this.flag=true
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

</style>