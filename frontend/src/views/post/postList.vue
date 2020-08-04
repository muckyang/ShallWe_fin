<template>
  <div>
    <nav class="mt-4 mb-3 ml-3">
      <div class="nav nav-tabs" id="nav-tab" role="tablist">
        <a @click="changeCategory(0)" class="nav-item nav-link active text-dark" id="nav-home-tab" data-toggle="tab" href="#nav-home" role="tab" aria-controls="nav-home" aria-selected="true">전체보기</a>
        <a @click="changeCategory(1)" class="nav-item nav-link text-dark" id="nav-profile-tab" data-toggle="tab" href="#nav-profile" role="tab" aria-controls="nav-profile" aria-selected="false">후기</a>
        <a @click="changeCategory(2)" class="nav-item nav-link text-dark" id="nav-contact-tab" data-toggle="tab" href="#nav-contact" role="tab" aria-controls="nav-contact" aria-selected="false">자유</a>
        <router-link class="nav-item nav-link" :to="{name:'postCreate'}">글쓰기</router-link>
      </div>
    </nav>
    <div class="tab-content" id="nav-tabContent">
        <div v-if="categoryNum === 0" class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            전체
        </div>
        <div v-if="categoryNum === 1" class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            후기
        </div>
        <div v-if="categoryNum === 2" class="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
            자유
        </div>
    </div>
  </div>
</template>

<script>
import {mapState,mapActions} from 'vuex'

export default {
    name:'postList',
    data(){
        return{
            categoryNum:0,
        }
    },
    methods:{
        ...mapActions(['getArticles']),
        changeCategory(num){
            this.categoryNum=num
            this.getArticles({temp:2,categoryId:this.categoryNum})
        }
    },
    computed:{
        ...mapState(['articles']),
    },  
    created(){
        this.getArticles({temp:2,categoryId:this.categoryNum})
    }

}
</script>

<style>

</style>