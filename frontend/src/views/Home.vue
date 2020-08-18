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

		<!-- 첫번째 줄 -->
		<b-container>
			<b-row>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
			</b-row>
		</b-container>

		<!-- 두번째 줄 -->
		<b-container>
			<b-row>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
			</b-row>
		</b-container>

		<!-- 3. 마감 임박 게시물-->
		<hr>
		<div class="division-box homeMenu d-flex align-items-center justify-content-center ">
			<h2 style="color: #fec9c9;">마감 임박 게시물</h2>
		</div>
		<hr class="division-box-underline-2">
		
		<!-- 첫번째 줄 -->
		<b-container>
			<b-row>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
			</b-row>
		</b-container>

		<!-- 두번째 줄 -->
		<b-container>
			<b-row>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="m-4" align="left" img-width="100%" 
					img-height="60%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
						<b-card-text>
							<h5>제목: 제목입니다.</h5>
							<h6>지역: 대전</h6>
							<h6>가격: 50000원/20000원</h6>
						</b-card-text>
						<template v-slot:footer>
							<div class="d-flex justify-content-between">
								<small>
									<b-icon-heart></b-icon-heart> 0개
									<b-icon-chat-dots class="ml-1"></b-icon-chat-dots> 0개
								</small>
								<small class="text-muted">10분 전</small>
							</div>
						</template>
					</b-card>
				</b-col>
			</b-row>
		</b-container>
		
		<!-- 4. 베스트 후기 -->
		<hr>
		<div class="division-box homeMenu d-flex align-items-center justify-content-center ">
			<h2 style="color: #fec9c9;">베스트 후기</h2>
		</div>
		<hr class="division-box-underline-3">

		<!-- 첫번째 줄 -->
		<b-container>
			<b-row>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
			</b-row>
		</b-container>

		<!-- 두번째 줄 -->
		<b-container>
			<b-row>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
			</b-row>
		</b-container>

		<!-- 세번째 줄 -->
		<b-container>
			<b-row>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
				<b-col cols="12" sm="4">
					<b-card class="text-center mt-3">
						<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
					</b-card>
				</b-col>
			</b-row>
		</b-container>

		<hr>
	</div>
</template>

<script>
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