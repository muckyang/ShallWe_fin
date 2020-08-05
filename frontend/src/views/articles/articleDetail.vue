<template>
  <div class="mt-3">
    <div class="container detail">
      <!--Top 부분. 제목, 작성자, create time -->
      <div class="top">
        <div class="top-row">
          <div class="detail-title">{{ articleData.title }}</div>

          <div class="article-drop dropdown dropleft" v-if="articleData.userId === userData.userId">
            <button type="button" class="article-btn" data-toggle="dropdown">
                  <i class="fas fa-ellipsis-v"></i>
            </button>
            <div class="dropdown-menu">
                <a class="dropdown-item"><router-link class="articleUpdate" :to="{name:'articleUpdate',
                params: {ID:this.$route.params.ID}}">수정</router-link></a>
                <a class="dropdown-item" @click="del">삭제</a><!--다시 보기!!!!!!!1 -->
            </div>
          </div>
          
        <br>
        </div>
        <div class="in-the-top">
          <div class="writer">{{ articleData.writer }}<br></div>
          <div class="create-time">{{ articleData.createTime }}2020.08.04. 08:36</div>        
        </div>
      </div>
      <hr class="top-line">

      <!--중간 부분. 이미지, 주요 정보들 -->
      <div class="middle-row">
        <img class="MyImage" :src="articleData.image" alt="...">
        <div class="articleInfo">
          <div class="detail-info">
            <div class="detail-address">{{articleData.address}}<br></div>
            <div class="detail-price">{{articleData.sumPrice}}원 / {{articleData.minPrice}}원</div>
            <div class="detail-endTime">{{articleData.endTime}}까지{{articleData.endTime}}</div>
          </div>          
          <div class="detail-btns">
            <articleLike  @like-change="likeChange" 
            :isLiked="isLiked" :article="articleData"/>
              <!-- <button type="button">
                <a href="javascript:;" 
                  @click="shareContent" 
                  id="kakao-link">
                  <img src="../../assets/img/kakao_btn.png" class="kakao">
                </a>
              </button> -->
              <button @click="shareContent" 
              class="detail-share">
              <i class="fas fa-share-alt"></i> 공유</button>

              <b-button class="detail-join" v-if="articleData.userId != userData.userId" 
              v-b-modal.modal-prevent-closing>
              <i class="fas fa-user-plus"></i> 참여</b-button>

                <b-modal
                  id="modal-prevent-closing"
                  ref="modal"
                  title="참여하기"
                  @ok="sendJoinData"
                >
                  <form ref="form" @submit.stop.prevent="handleSubmit">
                    <b-form-group
                      label="제목"
                      label-for="title-input"
                    >
                      <b-form-input
                        id="title-input"
                        v-model="joinData.title"
                      ></b-form-input>
                    </b-form-group>

                    <b-form-group
                      label="url"
                      label-for="url-input"
                    >
                      <b-form-input
                        id="url-input"
                        v-model="joinData.url"
                      ></b-form-input>
                    </b-form-group>

                    <b-form-group
                      label="가격"
                      label-for="price-input"
                    >
                      <b-form-input
                        id="price-input"
                        v-model="joinData.price"
                      ></b-form-input>원
                    </b-form-group>

                    <b-form-group
                      label="요구사항"
                      label-for="order-input"
                    >
                      <b-form-input
                        id="order-input"
                        v-model="joinData.order"
                      ></b-form-input>
                    </b-form-group>

                  </form>
                </b-modal>

          </div>
          
        </div>
      </div>

      <!--하단 부분. 내용,(지도) -->
      <div class="detail-content" id="item-1">
        <p>{{ articleData.description }}</p>
      </div>
    </div>
    <commentList/>
  </div>
</template>

<script>
  const BACK_URL = "http://127.0.0.1:8080"
  import axios from "axios"
  import {mapState,mapActions} from 'vuex'
  import commentList from '@/components/comments/commentList'
  import articleLike from '@/components/articles/articleLike'
  
  export default {
    name:'articleDetail',
    components:{
      commentList,
      articleLike
    },
    data () {
      return {
        isLiked:false,
        joinData:{
          articleId:this.$route.params.ID,
          title: '',
          url: '',
          price: '',
          description: '',
          token:this.$cookies.get('auth-token')
        }
      }
    },
    computed:{
      ...mapState(['articleData','userData'])
    },
    methods: {
      ...mapActions(['getArticle', 'getUserData']),

      sendJoinData(){
        axios.post(`${BACK_URL}/participant/create`,this.joinData)
          .then((response)=>{
            console.log(response)
            alert('등록이 완료되었습니다!')
          })
          .catch((error)=>{
            console.log(error,"AAAA")
          })
      },

      shareContent(){
        Kakao.Link.createDefaultButton({
        container: '#kakao-link',
        objectType: 'feed',
        content: {
          title: '디저트 사진',
          description: '아메리카노, 빵, 케익',
          imageUrl:
            'http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg',
          link: {
            mobileWebUrl: 'https://developers.kakao.com',
            androidExecParams: 'test',
          },
        },
        social: {
          likeCount: 10,
          commentCount: 20,
          sharedCount: 30,
        },
        buttons: [
          {
            title: '웹으로 이동',
            link: {
              mobileWebUrl: 'https://developers.kakao.com',
            },
          },
          {
            title: '앱으로 이동',
            link: {
              mobileWebUrl: 'https://developers.kakao.com',
            },
          },
        ],
        success: function(response) {
          console.log(response);
        },
        fail: function(error) {
          console.log(error);
        }
      });
    },
      likeCheck(){
        const auth={token:this.$cookies.get('auth-token')}
        axios.post(BACK_URL + `/likedcheck/${this.$route.params.ID}`,auth)
        .then((response) => {
          this.isLiked=response.data.isLiked
        })
        .catch(err => console.log(err))
      },
      likeChange() {
        this.getArticle(this.$route.params.ID)
        this.likeCheck()
      }
    },
    created: function(){
      this.getArticle(this.$route.params.ID)
      this.getUserData()
      console.log(this.articleData, '나옴 ?')
      console.log(this.userData, '유저 나옴?')
    },

  }
</script>

<style>
.detail{
  border: 1px solid rgb(224, 221, 221);
  border-radius: 1.5%;
  width: 75%
}
.MyImage{
  width: 45%;
  height: 300px;
  margin: 10px;
}
.totheContent{
  background-color: #676d74;
  color: white;
  padding: 8px;
  border-radius: 5px;
  /* margin-bottom: */
}
a{
  justify-content: bottom;
  text-decoration: none;
  color: black;
}
.top{
  margin-bottom:2%;
  margin-top: 2%;
  text-align: left;
  /* grid-column: 1/15; */
}
.top-row{
  display: flex;
  justify-content: space-between;
}
.detail-title{
  font-size: x-large;
  font-weight:bold;
  /* display: inline; */
  width: 90%;
  /* justify-content: space-between; */
}
.create-time{
  font-size: small;
  font-weight:normal;
  opacity: 0.6;
}
.top-line{
  margin: 0% 0% 2% 0%; 
}
.middle-row{
  display: flex;
  /* border: 1px solid blue; */
}
.articleInfo{
  text-align: left;
  padding: 15px 35px 15px 40px;
  width: 65%;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  /* position: relative;
  height: 100%; */
  /* /* border-bottom: 1px solid #e9ecef; */
  /* border-top: 1px solid #e9ecef; */ 
}
.detail-address{
  font-size: large;
  font-weight: bold;
  margin-bottom: 5px;
}
.kakao{
  height: 25px;
  width: 25px;
}
.detail-content{
  /* border: 1px solid blue; */
  margin: 30px 0;
  padding: 0 15px;
  text-align: left;
}
.detail-btns{
  width: 100%;
  display: flex;
  justify-content: flex-end;
  font-size: 17px;
  text-align: center;
}
.detail-btns .detail-share{
    display: block;
    text-align: center;
    background: #f7e600;
    border-radius: 3px;
    box-shadow: 0 10px 20px -8px rgb(216, 203, 20);
    padding: 10px 12px;
    font-size: 17px;
    cursor: pointer;
    border: none;
    outline: none;
    color: #ffffff;
    text-decoration: none;
    -webkit-transition: 0.3s ease;
    transition: 0.3s ease;
    margin: 0 1% 0 2%;
}
.detail-btns .detail-share:hover {
      transform: translateY(-3px);
}
.detail-btns .detail-share .fa {
      margin-right: 5px;
}
.detail-btns .detail-join{
    display: block;
    text-align: center;
    background: #31312f;
    border-radius: 3px;
    box-shadow: 0 10px 20px -8px rgb(27, 27, 25);
    padding: 10px 12px;
    font-size: 17px;
    cursor: pointer;
    border: none;
    outline: none;
    color: #ffffff;
    text-decoration: none;
    -webkit-transition: 0.3s ease;
    transition: 0.3s ease;
    margin: 0 1% 0 1%;
}
.detail-btns .detail-join:hover {
      transform: translateY(-3px);
}
.detail-btns .detail-join .fa {
      margin-right: 5px;
}
.article-drop{
    display: flex;
    flex-direction: row;
    width: 3%;
}
.article-btn {
    background-color: transparent;
    color: rgb(182, 182, 182);
    padding:0;
    font-size: 15px;
    border: none;
    cursor: pointer;
    width: 100%;
    outline: none;
}
.article-drop:hover{
    border: none;
    outline: none;
}
.article-btn:hover{
    border: none;
    outline: none;
}
/* .article-drop:hover .article-btn {
    background-color: transparent;
    color: rgb(145, 141, 141);
    border: none;
    outline: none;
} */
.articleUpdate{
  text-decoration: none;;
}
.share{
  display: flex;
}
.dropdown-item{
  cursor:pointer;
  text-decoration: none;
}
</style>