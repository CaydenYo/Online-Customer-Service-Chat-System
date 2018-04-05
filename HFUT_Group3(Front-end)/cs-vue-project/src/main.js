// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Vuex from 'vuex'
import store from './vuex/store.js'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import axios from 'axios'
import MintUI from 'mint-ui'
import  'mint-ui/lib/style.css'
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';

Vue.use(MintUI)
Vue.use(Vuex)
Vue.prototype.$axios = axios
Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.rootUrl = '/OCSSystem'

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
