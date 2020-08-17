<template>
	<div>
		<hr>
		<!-- 1. Carousel -->
		<div>
			<b-carousel
				id="carousel-1"
				v-model="slide"
				:interval="4000"
				controls
				indicators
				background="#ababab"
				img-width="1024"
				img-height="480"
				style="text-shadow: 1px 1px 2px #333;"
				@sliding-start="onSlideStart"
				@sliding-end="onSlideEnd"
			>
				<!-- Page1 -->
				<b-carousel-slide
					caption="First slide"
					text="Nulla vitae elit libero, a pharetra augue mollis interdum."
					img-src="https://picsum.photos/1024/480/?image=52"
				></b-carousel-slide>

				<!-- Page2 -->
				<b-carousel-slide img-src="https://picsum.photos/1024/480/?image=54">
					<h1>Hello world!</h1>
				</b-carousel-slide>

				<!-- Page3 -->
				<b-carousel-slide img-src="https://picsum.photos/1024/480/?image=58"></b-carousel-slide>
			</b-carousel>
		</div>

		<!-- 2. 실시간 게시물-->
		<hr>
		<div class="division-box homeMenu d-flex align-items-center justify-content-center ">
			<h2 style="color: #fec9c9;">실시간 게시물</h2>
		</div>
		<hr class="division-box-underline-1">

		<b-container class="bv-example-row">
		<b-row align-h="start">
			<b-col cols="12" sm="6" lg="4" v-for="article in recentList" :key="article.articleId">
			<router-link
				:to="{
				name: 'articleDetail',
				params: { ID: `${article.articleId}` },
				}"
				class="text-decoration-none text-dark"
			>
				<b-card
				class="article-card m-4 _card card__one"
				align="left"
				img-width="100%"
				img-height="60%"
				:img-src="article.image"
				img-alt="Image"
				img-top
				footer-bg-variant="#ee6e9f"
				footer-class="card-end"
				>
				<b-card-text>
					<h5 class="article-title">{{ article.title }}</h5>
					<h6 class="article-address">{{ article.address }}</h6>
					<h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
				</b-card-text>
				<template v-slot:footer>
					<div class="d-flex justify-content-between">
					<small>
						<b-icon-heart></b-icon-heart>
						{{ article.likeNum }}개
						<b-icon-chat-dots class="ml-1"></b-icon-chat-dots>
						{{ article.commentNum }}개
					</small>
					<small class="text-muted">{{ article.timeAgo }}</small>
					</div>
				</template>
				</b-card>
			</router-link>
			</b-col>
		</b-row>
		</b-container>

		<!-- 3. 마감 임박 게시물-->
		<hr>
		<div class="division-box homeMenu d-flex align-items-center justify-content-center ">
			<h2 style="color: #fec9c9;">마감 임박 게시물</h2>
		</div>
		<hr class="division-box-underline-2">
		
		<b-container class="bv-example-row">
		<b-row align-h="start">
			<b-col cols="12" sm="6" lg="4" v-for="article in deadLineList" :key="article.articleId">
			<router-link
				:to="{
				name: 'articleDetail',
				params: { ID: `${article.articleId}` },
				}"
				class="text-decoration-none text-dark"
			>
				<b-card
				class="article-card m-4 _card card__one"
				align="left"
				img-width="100%"
				img-height="60%"
				:img-src="article.image"
				img-alt="Image"
				img-top
				footer-bg-variant="#ee6e9f"
				footer-class="card-end"
				>
				<b-card-text>
					<h5 class="article-title">{{ article.title }}</h5>
					<h6 class="article-address">{{ article.address }}</h6>
					<h6 class="article-price">가격: {{ article.sumPrice }}원/{{ article.minPrice }}원</h6>
				</b-card-text>
				<template v-slot:footer>
					<div class="d-flex justify-content-between">
					<small>
						<b-icon-heart></b-icon-heart>
						{{ article.likeNum }}개
						<b-icon-chat-dots class="ml-1"></b-icon-chat-dots>
						{{ article.commentNum }}개
					</small>
					<small class="text-muted">{{ article.timeAgo }}</small>
					</div>
				</template>
				</b-card>
			</router-link>
			</b-col>
		</b-row>
		</b-container>
		
		<!-- 4. 베스트 후기 -->
		<hr>
		<div class="division-box homeMenu d-flex align-items-center justify-content-center ">
			<h2 style="color: #fec9c9;">베스트 후기</h2>
		</div>
		<hr class="division-box-underline-3">

			<b-container>
				<b-row>
					<b-col cols="12" sm="4" v-for="review in bestReview" :key="review.articleId">
						<b-card class="text-center mt-3">
							<b-card-text>{{review.title}}</b-card-text>
							<b-card-text>{{review.description}}</b-card-text>
						</b-card>
					</b-col>
				</b-row>
			</b-container>
		<hr>
	</div>
</template>

<script>
	const BACK_URL = process.env.VUE_APP_BACK_URL
	import axios from 'axios'

	export default {
		name:'Home',
		data() {
			return {
				slide: 0,
				sliding: null,
				recentList:[],
				deadLineList:[],
				bestReview:[],
			}
		},
		methods: {
			onSlideStart(slide) {
				this.sliding = true
			},
			onSlideEnd(slide) {
				this.sliding = false
			},
			getMainArticle(){
				const auth = { token: this.$cookies.get("auth-token") };
				axios.post(`${BACK_URL}/main/post`,auth)
					.then((response)=>{
						for (let i=0;i<18;i++){
							if(i<6){
								this.recentList.push(response.data.postList[i])
							}else if(i<12){
								this.deadLineList.push(response.data.postList[i])
							}else{
								this.bestReview.push(response.data.postList[i])
							}
						}
					})
					.catch((error)=>{
						console.log(error)
					})
			}
		},
		created(){
			this.getMainArticle()
		}
	}
</script>

<style>
	.division-box {
			height: 10%;
			margin-top:4%;
	}
	.division-box-small {
		height: 30px;
	}
	.division-box-underline-1 {
		margin-top:0.4%;
		margin-bottom: 0;
		width: 13%;
		height: 1px;
		background-color: #f0cbcb;
	}
	.division-box-underline-2 {
		margin-top:0.4%;
		margin-bottom: 0;
		width: 15%;
		height: 1px;
		background-color: #f0cbcb;
	}
	.division-box-underline-3 {
		margin-top:0.4%;
		margin-bottom: 0;
		width: 10%;
		height: 1px;
		background-color: #f0cbcb;
	}
@font-face { font-family: 'BMJUA'; 
src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_one@1.0/BMJUA.woff') format('woff'); 
font-weight: normal; 
font-style: normal; }
.homeMenu{
font-family: BMJUA;
font-weight: normal;
font-style: normal;
font-size: 1.2em;
}
</style>