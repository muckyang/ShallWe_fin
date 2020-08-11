import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import VueCookies from 'vue-cookies'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'
import InfiniteLoading from 'vue-infinite-loading';

Vue.use(VueCookies)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(InfiniteLoading, { /* options */ });

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
