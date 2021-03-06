import Vue from 'vue'
import App from './App.vue'
import router from "./router"
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import './assets/icons/iconfont.css'
import echarts from 'echarts'
Vue.prototype.$echarts = echarts
import moment from 'moment'
Vue.prototype.$moment = moment

var axios = require('axios')
axios.defaults.baseURL = 'http://localhost:8848/api'
Vue.prototype.$axios = axios
Vue.config.productionTip = false

Vue.use(ElementUI)

new Vue({
  render: h => h(App),
  router,//使用路由配置
  //使用 Vuex 进行状态管理
}).$mount('#app')
