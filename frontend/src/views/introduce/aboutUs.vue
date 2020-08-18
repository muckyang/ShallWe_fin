<template>
  <div class="introduce-list-box mt-5 mx-auto">
    <div class="a_layer">
      <div class="a_layer_inner">
        <div class="a_content">
          <div class="shall-we">
            <div id="pink">
              Sh
              <div id="black">are</div>
            </div>
            <div id="pink">
              a
              <div id="black">t</div>
            </div>
            <div id="pink">
              l
              <div id="black">oca</div>l
              <div id="black">area</div>
            </div>
            <div id="pink">
              w
              <div id="black">ith</div>
            </div>
            <div id="pink">
              e
              <div id="black">njoy</div>
            </div>
            <div>
              <h5>- 같은 동네에서 즐겁게 공유하세요 -</h5>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div class="row aln-center">
      <div class="col-4 col-6-medium col-12-small main-point">
        <section class="box style1 main-point-box p-3 _card card__one">
          <img src="@/assets/img/food1.png" class="foodIcon" />
          <h3>쉘위배민</h3>
          <p>배달음식의 최저금액을 같이 맞춰</p>
          <p style="color:#ee6e9f">최소의 금액으로 최대의 맛있는 음식</p>
          <p>을 나눠 보아요 :)</p>
        </section>
      </div>
      <div class="col-4 col-6-medium col-12-small">
        <section class="box style1 main-point-box p-3 _card card__one">
          <img src="@/assets/img/truck1.png" class="truckIcon" />
          <h3>쉘위택배</h3>
          <p>물건의 최저금액을 같이 맞춰</p>
          <p style="color:#ee6e9f">배송비를 절약</p>
          <p>해 보아요 :)</p>
        </section>
      </div>
      <div class="col-4 col-6-medium col-12-small">
        <section class="box style1 main-point-box p-3 _card card__one">
          <img src="@/assets/img/N1.png" class="nIcon" />
          <h3>쉘위공구</h3>
          <p>같은 물건을 대량구매하여 확</p>
          <p style="color:#ee6e9f">저렴해진 가격</p>
          <p>으로 구매해 보아요 :)</p>
        </section>
      </div>
    </div>

    <section class="row aln-center" id="point">
      <div class="col-2-small">
        <section class="box style1">
          <h2>신뢰성</h2>
          <p>
            회원관리에 있어 점수 또는 등급을 부여
            <br />카카오 로그인으로 1인 1계정으로 신뢰점수 ↑
          </p>
          <img src="@/assets/img/trust.png" class="trustIcon" />
        </section>
      </div>
      <div class="col-2-small">
        <section class="box style1">
          <h2>안정성</h2>
          <p>
            주변 CCTV가 많은 편의점 지도 API를 통해
            <br />사용자가 서로의 위치의 중간지점 편의점 지정
          </p>
          <img src="@/assets/img/Convenience.png" class="ConIcon" />
        </section>
      </div>
    </section>

    <section class="row aln-center">
      <div class="col-2-small">
        <section class="box style1">
          <h2>나의 동네에서 싸게 구매하자!!</h2>
          <p>회원가입 때 등록한 가장 가까운 편의점 주소로 같은 동네의 거래 상황을 알 수 있습니다.</p>
        </section>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: "aboutUs",
  data() {
    return {
      categoryNum: 0,
    };
  },
  methods: {
    changeCategory(num) {
      this.categoryNum = num;
    },
  },
};
</script>

<style>
.main-point {
  /* border: 1px solid red; */
}
.main-point-box {
  /* border: 1px solid blue; */
}
.main-point-box:hover {
  /* border: 1px solid red; */
}
.a_layer {
  display: table;
  margin: 10px auto;
  background: white;
}
.a_layer_inner {
  display: table-cell;
  text-align: center;
  vertical-align: middle;
}
#pink {
  display: inline;
  text-align: center;
  color: #ee6e9f;
  font-family: "Lobster", "cursive";
  font-size: 80px;
}
#black {
  text-align: center;
  display: inline;
  color: black;
  font-family: "Lobster", "cursive";
  font-size: 70px;
}
.foodIcon {
  height: 100px;
  width: 100px;
  /* padding: 20px 20px; */
  margin: 20px 20px;
}
.truckIcon {
  height: 100px;
  width: 100px;
  /* padding: 20px 20px; */
  margin: 20px 20px;
}
.nIcon {
  height: 100px;
  width: 100px;
  /* padding: 20px 20px; */
  margin: 20px 20px;
}
.trustIcon {
  height: 250px;
  width: 250px;
  /* padding: 20px 20px; */
  margin: 15px 10px;
}
.ConIcon {
  height: 300px;
  width: 300px;
  /* padding: 20px 20px; */
  margin: 15px 10px;
}
h3 {
  margin: 20px 20px;
}

#point {
  background: #fec9c9;
  margin: 30px;
  padding: 40px;
}
.col-2-small {
  width: 50%;
  margin: 10px auto;
}
</style>

<style scoped lang="scss">
$color-bg: #f8f8f8;
$card-padding: 20px;
$grid-gutter: 31px;

// Media Queries breakpoints
$small: 480px;
$medium: 768px;
$large: 992px;
$x-large: 1200px;

._card {
  position: relative;
  flex: 1 1 100%;
  background: lighten($color-bg, 3%);
  border-radius: 3px;

  @media screen and (min-width: $medium) {
    flex-basis: calc(33.33% - (#{$grid-gutter * 2} + #{$card-padding * 2}));
    margin: 0 $grid-gutter;
  }
}
.card__one {
  border-radius: 3px;
  transition: transform 0.3s;

  &::after {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    transition: opacity 2s cubic-bezier(0.165, 0.84, 0.44, 1);
    box-shadow: 0 8px 17px 0 rgba(0, 0, 0, 0.2),
      0 6px 20px 0 rgba(0, 0, 0, 0.15);
    content: "";
    opacity: 0;
    border-radius: 4px;
    z-index: -1;
  }

  &:hover,
  &:focus {
    transform: scale3d(1.006, 1.006, 1);

    &::after {
      opacity: 1;
    }
  }
}
</style>