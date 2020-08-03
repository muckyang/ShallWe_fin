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
		<hr>

		<!-- 첫번째 줄 -->
		<div>
			<b-card-group deck>
				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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

				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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

				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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
			</b-card-group>
		</div>
		<div class="division-box-small"></div>

		<!-- 두번째 줄 -->
		<div>
			<b-card-group deck>
				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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

				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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

				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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
			</b-card-group>
		</div>

		<!-- 3. 마감 임박 게시물-->
		<hr>
		<div class="division-box homeMenu d-flex align-items-center justify-content-center">
			<h2 style="color: #6d9d88;">마감 임박 게시물</h2>
		</div>
		<hr>

		<div>
			<b-card-group deck>
				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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

				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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

				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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
			</b-card-group>
		</div>
		<div class="division-box-small"></div>

		<!-- 두번째 줄 -->
		<div>
			<b-card-group deck>
				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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

				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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

				<b-card align="left" img-width="100%" img-height="69%" img-src="https://picsum.photos/300/300/?image=41" img-alt="Image" img-top>
					<b-card-text>
						<h5>제목: 제목입니다.</h5>
						<h6>지역: 대전</h6>
						<h6>가격: 10000원/20000원</h6>
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
			</b-card-group>
		</div>
		
		<!-- 4. 베스트 후기 -->
		<hr>
		<div class="division-box homeMenu d-flex align-items-center justify-content-center ">
			<h2 style="color: #fec9c9;">베스트 후기</h2>
		</div>
		<hr>

		<!-- 첫번째 줄 -->
		<div class="mt-3">
			<b-card-group deck>
				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>

				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>

				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>
			</b-card-group>
		</div>

		<!-- 두번째 줄 -->
		<div class="mt-3">
			<b-card-group deck>
				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>

				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>

				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>
			</b-card-group>
		</div>

		<!-- 세번째 줄 -->
		<div class="mt-3">
			<b-card-group deck>
				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>

				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>

				<b-card class="text-center">
					<b-card-text>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</b-card-text>
				</b-card>
			</b-card-group>
		</div>

		<hr>
	</div>
</template>

<script>
	export default {
		name:'Home',
		data() {
			return {
				slide: 0,
				sliding: null
			}
		},
		methods: {
			onSlideStart(slide) {
				this.sliding = true
			},
			onSlideEnd(slide) {
				this.sliding = false
			}
		}
	}
</script>

<style>
	.division-box {
			height: 130px;
	}
	.division-box-small {
		height: 30px;
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