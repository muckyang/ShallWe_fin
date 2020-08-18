<template>
	<div>
		<hr>
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img class="carousel-img" src="@/assets/img/shallwe.png" alt="Chania">
      <div class="caption">
		<h5>지금 바로 쉘위 소개를 확인해보세요!</h5>
		<p><router-link to="/introduce" class="ml-0" id="introduce"><button class="carouselBtn">확인 하러가기</button></router-link></p>
      </div>
    </div>

    <div class="item">
      <img class="carousel-img" src="@/assets/img/forNbbang.png" alt="Chicago">
      <div class="carousel-caption">
        <h3 style="font-color:black;">필요한 만큼만 사고 싶은데...</h3>
        <p><router-link to="/introduce" class="ml-0" id="introduce"><button class="carouselBtn">N빵 하러가기</button></router-link></p>
      </div>
    </div>

    <div class="item">
      <img class="carousel-img" src="@/assets/img/forNbbang.png" alt="Chicago">
      <div class="carousel-caption">
        <h3 style="font-color:black;">필요한 만큼만 사고 싶은데...</h3>
        <p><router-link to="/introduce" class="ml-0" id="introduce"><button class="carouselBtn">N빵 하러가기</button></router-link></p>
      </div>
    </div>
	<div class="item">
      <img class="carousel-img" src="@/assets/img/forNbbang.png" alt="Chicago">
      <div class="carousel-caption">
        <h3 style="font-color:black;">필요한 만큼만 사고 싶은데...</h3>
        <p><router-link to="/introduce" class="ml-0" id="introduce"><button class="carouselBtn">N빵 하러가기</button></router-link></p>
      </div>
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
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
		
	}
	
</script>

<style>
.carouselBtn{
	border: none;
  outline: none;
  border-radius: 4px;
  background-color: #ee6e9f;
  padding: 5px 10px;
  color: white;
  font-weight: bold;
}
.carousel-img{
	display: flex;
          
            width: 100%;
            
}
.carousel-inner{
	height: 100%;
	width: 50%;
}
.caption{
  position: absolute;
  bottom: 8px;
  right: 100px;
  font-size: 18px;
}
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