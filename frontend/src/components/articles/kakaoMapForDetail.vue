<template>
  <div id="app">
    <div id="map"></div>
    <div>{{articleData.address}}</div>
    <!--삭제하면 update 실행  안된다.-->
  </div>
</template>

<script>
const APP_KEY = process.env.VUE_APP_KAKAO_APP_KEY;
import { mapState } from "vuex";
export default {
  computed: {
    ...mapState(["articleData"]),
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();

      // setTimeout(function() {
      //     map.relayout();
      //     map.setCenter(new kakao.maps.LatLng(lat, lng));
      //     // map.setLevel(2); 필요하면 레벨조정
      // }, 1000);
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=8500f9b4c8e3ef8075b8eeefaaae025f&libraries=services";
      document.head.appendChild(script);
    }
  },
  methods: {
    initMap() {
      var container = document.getElementById("map");
      var options = {
        center: new kakao.maps.LatLng(36.355539125856275, 127.29863289195224),
        level: 3,
      };

      var map = new kakao.maps.Map(container, options);
      var geocoder = new kakao.maps.services.Geocoder();
      geocoder.addressSearch(this.articleData.address, function (
        result,
        status
      ) {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);

          // 결과값으로 받은 위치를 마커로 표시합니다
          var marker = new kakao.maps.Marker({
            map: map,
            position: coords,
          });

          // 인포윈도우로 장소에 대한 설명을 표시합니다
          var infowindow = new kakao.maps.InfoWindow({
            content:
              '<div style="width:150px;text-align:center;padding:6px 0;">만남의 장소</div>',
          });
          infowindow.open(map, marker);

          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          map.setCenter(coords);
        }
      });
    },
  },
  updated: function () {
    this.initMap();
  },
};
</script>

<style>
#map {
  width: 400px;
  height: 300px;
}
</style>