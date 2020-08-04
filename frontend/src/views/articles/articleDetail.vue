<template>
  <div class="mt-3">
    <div class="container detail">
      <!--Top 부분. 제목, 작성자, create time -->
      <div class="top">
        <div class="top-row">
          <div class="detail-title">{{ articleData.title }}</div>
          <div class="detail-dropbtn">
            <div class="dropdown" v-if="articleData.userId === userData.userId">
              <button class="dropbtn btn-dark">
                <i class="fas fa-ellipsis-v"></i><!-- <i class="fas fa-ellipsis-h"></i> -->
              </button>
              <div class="dropdown-content">
                  <router-link :to="{name:'articleUpdate',
                  params: {ID:this.$route.params.ID}}">수정
                  </router-link>
                  <a href="#">삭제</a><!--다시 보기!!!!!!!1 -->
                  <!-- <div class="share"> -->
                    <!-- <img src="../../assets/img/kakao_btn.png" class="kakao"> -->
                    <!-- <a href="javascript:;" 
                      @click="shareContent" 
                      id="kakao-link">공유</a> -->
                  <!-- </div> -->
              </div>
            </div>
          </div>
          
        <br>
        </div>
        <div class="in-the-top">
          <div class="writer">{{ articleData.writer }}<br></div>
          <div class="create-time">{{ articleData.createTime }}2020.08.04. 08:36</div>        </div>
      </div>
      <hr class="top-line">

      <!-- <a href="#item-1" class="totheContent mb-3">내용으로</a> -->

      <!--중간 부분. 이미지, 주요 정보들 -->
      <div class="middle-row">
        <img class="MyImage" :src="articleData.image" alt="...">
        <div class="articleInfo">
          <div class="detail-info">
            <div class="detail-address">{{articleData.address}}<br></div>
            <div class="detail-price">{{articleData.minPrice}}원 / {{articleData.sumPrice}}원</div>
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
              class="btn-secondary detail-share">
              <i class="fas fa-share-alt"></i> 공유</button>

              <button @click="shareContent" 
              class="btn-secondary detail-join">
              <i class="fas fa-user-plus"></i> 참여</button>
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
      }
    },
    computed:{
      ...mapState(['articleData','userData'])
    },
    methods: {
      ...mapActions(['getArticle', 'getUserData']),
      
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
/* .row-item{
  display: grid; */
  /* grid-template-columns: repeat(15, 1fr);
  grid-gap: 10px;
  grid-auto-rows: minmax(100px, auto);
} */
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
  padding: 15px 40px;
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
  display: flex;
  justify-content: space-between;
}
.detail-share{
  border: none;
  outline:none;
  height: 43px;
  border-radius: 3px;
  padding: 10px 12px;
  text-align: center;
  background: #f7e600;
  font-size: 17px;
}
.detail-join{
  border: none;
  outline:none;
  height: 43px;
  border-radius: 3px;
  padding: 10px 12px;
  text-align: center;
  background: #252522;
  font-size: 17px;
}
.detail-dropbtn{
  width: 10%;
  padding-right: 10px;
}
.dropbtn {
  /* background-color: #676d74; */
  color: white;
  padding-top: 7px;
  padding-bottom: 7px;
  padding-left: 8.5px;
  padding-right: 8.5px;;
  font-size: 13px;
  border: none;
  cursor: pointer;
  border-radius: 5px;
  /* height: 50px; */
}
.dropdown {
  position: relative;
  display: inline-block;
  float: right;
}
.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f9f9f9;
  min-width: 90px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  border-radius: 5px;
}
.dropdown-content a {
  color: black;
  font-size:medium;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}
.dropdown-content a:hover {background-color: #f1f1f1}

.dropdown:hover .dropdown-content {
  display: block;
}
.dropdown:hover .dropbtn {
  background-color: #3a4049;
}
.share{
  display: flex;
}
</style>