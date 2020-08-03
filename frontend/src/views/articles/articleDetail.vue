<template>
  <div class="mt-3">
    <div class="container">
      <a href="#item-1" class="totheContent mb-3">내용으로</a>
      <div data-spy="scroll" data-target="#navbar-example3" data-offset="0">
        <img class="MyImage" :src="articleData.image" alt="...">
        <div class="articleInfo">
          <!-- <hr> -->

          <div class="row-item">
            <div class="title"><p>{{ articleData.title }}asdfads</p></div>
            <!-- <div class="update">
              <router-link :to="{name:'articleUpdate',
              params: {ID:this.$route.params.ID}}">수정
              </router-link>
            </div> -->
            <div class="dropdown">
              <button class="dropbtn btn-dark">
                <i class="fas fa-ellipsis-v"></i>
                <!-- <i class="fas fa-ellipsis-h"></i> -->
              </button>
              <div class="dropdown-content">
                  <router-link :to="{name:'articleUpdate',
                  params: {ID:this.$route.params.ID}}">수정
                  </router-link>
                
                  <a href="#">삭제</a>

                  <div class="share">
                    <!-- <img src="../../assets/img/kakao_btn.png" class="kakao"> -->
                    <a href="javascript:;" 
                      @click="shareContent" 
                      id="kakao-link">공유</a>
                  </div>
              </div>
            </div>


          </div>
          <!-- <div class="title"><p>{{ articleData.title }}</p></div> -->
          <!-- <div><p>가격 들어갈 자리</p></div> -->
          <div class="minPrice"><p>{{ articleData.minPrice }}10000원</p></div>
          <div class="description" id="item-1">
            <p>{{ articleData.description }}</p>
          </div>

          <div>
            <articleLike  @like-change="likeChange" 
            :isLiked="isLiked" :article="articleData"/>
          </div>

          <!-- <div class="share">
            <a href="javascript:;" 
              @click="shareContent" 
              id="kakao-link">
                <img src="../../assets/img/kakao_btn.png" class="kakao">
            </a>
          </div> -->
         
        </div> 

        <!-- <div class="update">
          <router-link :to="{name:'articleUpdate',
           params: {ID:this.$route.params.ID}}">수정
           </router-link>
        </div> -->
        <!-- <a href="javascript:;" 
        @click="shareContent" 
        id="kakao-link">
          <img src="../../assets/img/kakao_btn.png" class="kakao">
        </a> -->

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
    methods: {
      ...mapActions(['getArticle']),
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
      console.log(this.articleData, '나옴 ?')
    },
    computed:{
      ...mapState(['articleData'])
      
    }
  }
</script>

<style>
.MyImage{
  width: 50%;
  height: 350px;
  margin-bottom: 25px;
}
.totheContent{
  background-color: #676d74;
  color: white;
  padding: 8px;
  border-radius: 5px;
  /* margin-bottom: */
}
.articleInfo{
  margin-left: 15px;
  text-align: left;
}
.row-item{
  display: grid;
  grid-template-columns: repeat(15, 1fr);
  grid-gap: 10px;
  grid-auto-rows: minmax(100px, auto);
}
.update{
  float:right;
}
a{
  justify-content: bottom;
  text-decoration: none;
  color: black;
}
.title{
  font-size: x-large;
  font-weight:bold;
  grid-column: 1/15;;
}
.articleInfo{
  padding: 30px 0;
    width: 677px;
    margin: 0 auto;
    border-bottom: 1px solid #e9ecef;
    border-top: 1px solid #e9ecef;
}
.kakao{
  height: 25px;
  width: 25px;
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