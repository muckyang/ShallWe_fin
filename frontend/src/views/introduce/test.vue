<template>
  <div>
    <div class="col-4 col-6-medium col-12-small">
      <section class="box style1 main-point-box p-3 _card card__one">
        <div class="infinite">
          <img src="@/assets/img/truck1.png" class="infinite" />
        </div>
        <h3>쉘위택배</h3>
        <p>물건의 최저금액을 같이 맞춰</p>
        <p style="color:#ee6e9f">배송비를 절약</p>
        <p>해 보아요 :)</p>
      </section>
    </div>
  </div>
</template>

<script>
$(document).ready(function () {
  $("div.infinite").click(function () {
    $(this).css("-webkit-animation-name", "iteration-count");
  });
});
export default {
  name: "test",
};
</script>
    
<style>
.infinite {
  height: 100px;
  width: 100px;
  animation-iteration-count: infinite;
}
@-webkit-keyframes iteration-count {
  from {
    left: 0px;
  }
  to {
    left: 100px;
  }
}
div.infinite {
  position: relative;
  animation-timing-function: linear;
  animation-duration: 2s;
}
</style>