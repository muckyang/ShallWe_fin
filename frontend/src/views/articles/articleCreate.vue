<template>
  <div class="mt-5 create-container">
    <div class="create-form">
      <!-- 지도 -->
      <div class="kakao-map-select">
        <kakaoMap :coNum="coNum" @setAddress="setAddress" />
      </div>
      <div class="right-items">
        <!--선택 장소-->
        <div class="selected-place">
          <label for>어디서 만날까?<small style="font-size: 17px; color: red;">*</small></label>
          <input
            type="text"
            v-model="articleData.address"
            :placeholder="address"
            style="font-family: FontAwesome;"
            readonly
          />
        </div>
        <!--제목-->
        <div class="title">
          <label for="title">제목<small style="font-size: 17px; color: red;">*</small></label>
          <div class="btn-group">
            <button
              type="button"
              class="btn btn-outline-secondary btn-sm dropdown-toggle"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >{{ selectedTBG }}</button>
            <div class="dropdown-menu">
              <a class="dropdown-item" @click="selectCategory(1)">쉘위배달</a>
              <a class="dropdown-item" @click="selectCategory(2)">쉘위택배</a>
              <a class="dropdown-item" @click="selectCategory(3)">쉘위공구</a>
            </div>
          </div>
          <input type="text" id="title" v-model="articleData.title" placeholder=" 제목을 입력하세요" />
        </div>
        <!--시작금액/전체금액-->
        <div class="price">
          <label for>시작금액<small style="font-size: 17px; color: red;">*</small>/전체금액<small style="font-size: 17px; color: red;">*</small></label>
          <input type="number" id="myPrice" v-model="articleData.myPrice" placeholder=" 시작금액" />
          <input type="number" id="minPrice" v-model="articleData.minPrice" placeholder=" 전체금액" />
        </div>
        <!--종료일시-->
        <div class="endTime">
          <label for>종료일자<small style="font-size: 17px; color: red;">*</small>/종료시간<small style="font-size: 17px; color: red;">*</small></label>
          <input type="date" v-model="articleData.endDate" />
          <input type="time" id="form-endTime" v-model="articleData.endTime" />
        </div>
        <!-- 파일 업로드 -->
        <div class="imageInsert d-flex">
          <label>사진 업로드</label>
          <div class="d-flex">
            <input type="file" id="file" name="file" ref="file" style="width: 100%;"/>
            <!-- <button v-on:click="fileUpload" class="mr-2 _temp-form text-white" style="font-size: 13px; width: 20%;">업로드</button> -->
          </div>
        </div>
        <!--url-->
        <div class="url">
          <label for="url">URL</label>
          <input type="url" id="url" v-model="articleData.urlLink" placeholder=" url을 입력하세요" required/>
        </div>
        <!--오픈톡방url-->
        <div class="url">
          <label for="url">오픈 카카오톡 URL</label>
          <input type="url" id="url" v-model="articleData.openLink" placeholder=" url을 입력하세요" />
        </div>
        <!--내용-->
        <div class="createContent">
          <label for="textarea-rows">내용<small style="font-size: 17px; color: red;">*</small></label>
          <b-form-textarea
              id="textarea-rows"
              v-model="articleData.description"
              placeholder="내용을 입력하세요..."
              rows="3"
              max-rows="6"
            ></b-form-textarea>
        </div>
      </div>
    </div>

    <!--태그-->
    <div class="tags">
      <label for>태그</label>
      <b-form-tags v-model="articleData.tags" no-outer-focus class="mb-2">
        <template v-slot="{ tags, inputAttrs, inputHandlers, tagVariant, addTag, removeTag }">
          <b-input-group class="mb-2">
            <b-form-input
              v-bind="inputAttrs"
              v-on="inputHandlers"
              placeholder="엔터를 누르면 태그가 추가돼요! :)"
              id="tag-input"
              class="form-control"
            ></b-form-input>
            <b-input-group-append>
              <div class="tag-btn">
                <b-button @click="addTag()" class="tag-btn" variant="dark">등록</b-button>
              </div>
            </b-input-group-append>
          </b-input-group>
          <div class="d-inline-block" style="font-size: 1.5rem;">
            <b-form-tag
              v-for="tag in tags"
              @remove="removeTag(tag)"
              :key="tag"
              :title="tag"
              :variant="tagVariant"
              class="mr-1"
            >{{ tag }}</b-form-tag>
          </div>
        </template>
      </b-form-tags>
    </div>

    <div class="create-submit">
      <button class="temp-form" @click="articleCreate(0)">임시저장</button>
      <button class="complete-form" @click="articleCreate(1)">
        <i class="fas fa-check"></i> 완료
      </button>
    </div>
  </div>
</template>

<script>
const BACK_URL = process.env.VUE_APP_BACK_URL;
import axios from "axios";
import { mapActions } from "vuex";
import kakaoMap from "@/components/articles/kakaoMap";

export default {
  name: "CreateView",
  components: {
    kakaoMap,
  },
  data() {
    return {
      articleData: {
        categoryId: null,
        title: null,
        address: null,
        description: null,
        minPrice: null,
        myPrice: null,
        urlLink: null,
        openLink:null,
        endDate: null,
        endTime: null,
        image: "",
        tags: [],
        token: this.$cookies.get("auth-token"),
      },
      imageUrl: null, //다시 검토
      selectedTBG: "카테고리",
      coNum: "1",
      address: " \uf060" + " 지도에서 만남의 장소를 설정하세요",
      file: '',
      path: '',
      uid: '',
    };
  },
  methods: {
    ...mapActions(["createArticle", "tempSaveArticle"]),
    articleCreate(num){
      this.fileUpload()
      setTimeout(() => {
        var articleData=this.articleData 
        this.createArticle({articleData,temp:num})
      }, 300);
    },
    fileUpload: function () {
      var formData = new FormData();
      this.file = this.$refs.file.files[0];
      console.log(this.file);
      formData.append("file", this.file);
       axios.post(`${BACK_URL}/file`
          ,formData
          , {
              headers: {
                  'Content-Type': 'multipart/form-data'
              }
          })
        .then((response) => {
          this.path = response.data
          this.articleData.image = this.path
          var tmp=this.articleData.image.split('.')
          tmp[1]=tmp[1].toLowerCase()
          this.articleData.image = tmp[0]+'.'+tmp[1]
        })
        .catch((error) => {
          console.log(error);
        });
    },
    selectCategory(num) {
      this.articleData.categoryId = num;
      if (num === 1) {
        this.selectedTBG = "쉘위배달";
      } else if (num === 2) {
        this.selectedTBG = "쉘위택배";
      } else {
        this.selectedTBG = "쉘위공구";
      }
    },
    imageChange(e) {
      const selectedImage = e.target.files[0];
      this.createBase64Image(selectedImage);
    },
    createBase64Image(fileObject) {
      this.articleData.image = new Image();
      const reader = new FileReader();
      reader.onload = (e) => {
        this.articleData.image = e.target.result;
      };
      reader.readAsDataURL(fileObject);
    },
    imageUpload() {
      this.$refs.imageInput.click();
    },
    setAddress(address) {
      this.articleData.address = address;
    },
  },
  watch: {
    articleData:{
      deep: true,
      handler(){
        try{
          if(this.articleData.minPrice<0){
            alert("1이상의 정수만 입력이 가능합니다.")
            this.articleData.minPrice=null
          }
          if(this.articleData.myPrice<0){
            alert("1이상의 정수만 입력이 가능합니다.")
            this.articleData.myPrice=null
          }

        }catch{

        }
      }
    }
  }
};
</script>

<style>
.create-container {
  padding-left: 8px;
}
.create-form {
  display: flex;
  flex-direction: row;
}
.right-items {
  text-align: left;
  margin: 0 0 0 1.5vw;
  width: 55vw;
  /* border: 1px solid rgb(201, 201, 201); */
  border-radius: 4px;
}
.right-items > div {
  margin: 0 0 2vh 0;
}
.right-items > div > label {
  margin-right: 1vw;
}
.selected-place > input,
.imageInsert > input,
.url > input {
  width: 21vw;
}
.price > input {
  width: 8vw;
}
.title > input {
  width: 14.9vw;
  margin-left: 0.5vw;
}
.title > .btn-group > button {
  height: 5.3vh;
  text-align: center;
}
.price > input,
.endTime > input {
  width: 10.25vw;
}
#minPrice,
#form-endTime {
  margin-left: 0.5vw;
}
.tag {
  display: flex;
}
.tag > .tags {
  width: 21vw;
}
.createContent > textarea {
  vertical-align: top;
}
.tags {
  text-align: left;
  display: flex;
  align-items: center;
  margin: 3% 0;
  padding: 0 15vw;
}
.complete-form,
.temp-form {
  border: none;
  outline: none;
  margin: 0 0.5vw;
  padding: 10px;
  border-radius: 4px;
  opacity: 0.85;
  transition: 0.4s;
}
.temp-form {
  background-color: rgb(151, 151, 151);
}
._temp-form {
  border: none;
  outline: none;
  margin: 0 0.5vw;
  padding: 7px;
  border-radius: 4px;
  opacity: 0.85;
  transition: 0.4s;
  background-color: rgb(151, 151, 151);
}
.complete-form {
  background-color: #ee6e9f;
}
.temp-form:hover,
.complete-form:hover {
  opacity: 1;
}
input:focus::placeholder {
  color: transparent;
}
.tag-btn {
  border: none;
  outline: none;
  border-top-left-radius: 0%;
  border-bottom-left-radius: 0%;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  background-color: rgb(151, 151, 151);
  opacity: 0.85;
  transition: 0.3s;
}
.tag-btn:hover {
  opacity: 1;
}
#tag-input {
  outline: none;
}
#tag-input:focus {
  outline: none;
  outline-style: none;
  -webkit-tap-highlight-color: transparent;
}
/* .{
  content: '';
  position: absolute;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: 1;
  opacity: 0;
  -webkit-transition: all 0.3s;
  -moz-transition: all 0.3s;
  -o-transition: all 0.3s;
  transition: all 0.3s;
  border-top-width: 1px;
  border-bottom-width: 1px;
  border-top-style: solid;
  border-bottom-style: solid;
  border-top-color: rgba(255,255,255,0.5);
  border-bottom-color: rgba(255,255,255,0.5);
  -webkit-transform: scale(0.1, 1);
  transform: scale(0.1, 1);
}
.:after {
    content: '';
    position: absolute;
    bottom: 0;
    left: 0;
    width: 100%;
    height: 100%;
    z-index: 1;
    -webkit-transition: all 0.3s;
    -moz-transition: all 0.3s;
    -o-transition: all 0.3s;
    transition: all 0.3s;
    background-color: rgba(255,255,255,0.25);
} */
</style>