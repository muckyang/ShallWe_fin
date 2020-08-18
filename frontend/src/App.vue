<template>
  <div id="app">
    <!-- 헤더 -->
    <div class="container" id="Top">
      <Header :isHeader="isHeader" />
    </div>
    <!-- 메인 -->
    <div class="container">
      <router-view />
    </div>
    <div style="background-color: #f1bbba;">
      <div class="container">
        <Footer />
      </div>
    </div>
    <a href="#Top"
      ><button class="totheTop">
        <i class="fas fa-arrow-up"></i></button
    ></a>
  </div>
</template>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<script>
// $('.tothetop').click( function() {
//     // var htmloffset = $( 'app' ).offset();
//     $( 'html, body' ).animate( { scrollTop : '0' }, 800 );
// });
import Header from "./components/common/Header.vue";
import Footer from "./components/common/Footer.vue";
import constants from "./lib/constants";

export default {
  name: "App",
  components: {
    Header,
    Footer,
  },
  created() {
    let url = this.$route.name;
    this.checkUrl(url);
  },
  watch: {
    $route(to) {
      this.checkUrl(to.name);
    },
  },
  methods: {
    checkUrl(url) {
      let array = [constants.URL_TYPE.USER.LOGIN, constants.URL_TYPE.USER.JOIN];

      let isHeader = true;
      array.map((path) => {
        if (url === path) isHeader = false;
      });
      this.isHeader = isHeader;
    },
  },
  data: function() {
    return {
      isHeader: true,
      constants,
    };
  },
};
</script>

<style>
.totheTop {
  position: fixed;
  bottom: 1%;
  right: 8%;
  cursor: pointer;
  width: 35px;
  height: 35px;
  background-color: #ffffff;
  border: 0.5px solid #bbb3b3;
  border-radius: 20px;
  color: #6d6a6a;
  -webkit-transition: 0.3s ease;
  box-shadow: 0 10px 20px -8px #b2b0b3;
  font-weight: 100;
}

.totheTop:hover {
  /* background-color: #c0aeb5;
  color: white; */
  transform: translateY(-3px);
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
</style>
