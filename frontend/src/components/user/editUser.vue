<template>
  <div class="user" id="join">
        <div class="wrapC table">
            <div class="middle mt-4">
                <h2 class="edit">개인정보 수정</h2>
                <hr class="line">
                <div class="form-wrap">                                    

                    <!-- <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <label for="password">비밀번호</label>
                        </div>
                        <input v-model="editData.editDataForSend.password"
                            id="password" 
                            class="editInput"
                            type="password"
                            name="password"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : passwordType==='text'}">
                            </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <label for="password-confirm">비밀번호 확인</label>
                        </div>
                        <input v-model="editData.password2" 
                            id="password-confirm"
                            class="editInput"
                            name="password-confirm"
                            type="password"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : passwordConfirmType==='text'}">
                            </span>
                    </div>

                    <div class="input-wrap name-warp">
                        <div class="p-2">
                            <label for="name">이름</label>
                        </div>
                        <input v-model="editData.editDataForSend.name" 
                            id="name"
                            class="editInput"
                            name="name"
                            placeholder="이름을 입력해주세요"
                            type="text"/>
                    </div> -->

                    <div class="input-wrap">
                        <div class="p-2">
                            <label for="nickname">닉네임</label>
                        </div>
                        <input v-model="editData.nickname"
                            id="nickname"
                            class="editInput"
                            name="nickname"
                            placeholder="닉네임을 입력해주세요" 
                            type="text"/>
                    </div>                

                    <div class="input-wrap address-warp">
                        <div class="p-2">
                            <label for="address">주소</label>
                        </div>
                        <input v-model="editData.address" 
                            id="address"
                            class="editInput"
                            name="address"
                            placeholder="주소를 입력해주세요"
                            type="text"/>
                    </div>
                    <kakaoMap @setAddress="setAddress"/>

                    <div class="input-wrap birthday-warp">
                        <div class="p-2">
                            <label for="birthday">생일</label>
                        </div>
                        <input v-model="editData.birthday" 
                            id="birthday"
                            class="editInput"
                            name="birthday"
                            placeholder="YYYY-MM-DD"
                            type="text"/>
                    </div>

                </div>
                <hr class="line">
                <button @click="editUser(editData)" class="submitButton">
                    <span>
                        작성완료
                    </span>
                </button>
            </div>
        </div> 
    </div>
</template>

<script>
const BACK_URL = 'http://127.0.0.1:8080'
import kakaoMap from '@/components/articles/kakaoMap'
import axios from 'axios'
import { mapState, mapActions } from 'vuex'

export default {
    name:"editUser",
    components: {
        kakaoMap,
    },
    data: function () {
        return {
            editData:{
                address:'',
                nickname:'',
                birthday:'',
            },
        }
    },
    methods:{
        ...mapActions(['editUser','getUserData']),
        setAddress(address){
            this.editData.address=address
        },
    },
    created: function(){
        this.getUserData()
        this.editData.editDataForSend=this.userData
    },
    computed:{
        ...mapState(["userData"]),
    },
}
</script>

<style>
 @font-face { 
     font-family: 'GmarketSansBold'; 
    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansBold.woff') format('woff'); 
    font-weight: normal; 
    font-style: normal; 
}
.edit{
    /* font-family: GmarketSansBold; */
    font-weight: 900;
    font-style: normal;
    color:#3a4049;
}
.editInput{
    width: 250px;
    height: 40px;
    border: 1px solid grey;
    border-radius: 5px;
    opacity: 0.5;
}
.form-wrap{
    justify-content: center;
    align-items: center;
    margin-left: 31.5%;
    margin-top: 30px;
    margin-bottom: 20px;
}
.input-wrap{
    display: flex;
    /* justify-content: flex-start; */
    margin-bottom: 10px;
}
.line{
    width:50%;
    margin-bottom: 20px;
    height:3px;
    border-color: black;
}
label{
    width: 150px;
    text-align: left;
    font-weight: 550;
    color: black;
}
input{
    width: 30%;
    height: 40px;
    border: 1px solid grey;
    border-radius: 5px;
    opacity: 0.5;
}
.submitButton{
    color: white;
    font-weight: bold;
    background: #ee6e9f;
    border: 3px solid #ee6e9f;
    opacity: 0.75;
    border-radius: 5px;
    -webkit-transition: all .5s ease;
    transition: all .5s ease;
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0);
    width:18%;
    height: 42px;
    margin-top: 10px;
}
.submitButton:hover{
    background-color: #ee6e9f;
    opacity: 1;
    color: white ;
    text-decoration: solid;
    border: 3px solid #ee6e9f;
    -webkit-transition: all .35s ease;
    transition: all .35s ease;
    /* >a
        color aqur-bg
        -webkit-transition all .35s ease
        transition all .35s ease */
}
    
</style>