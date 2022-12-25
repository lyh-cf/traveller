import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import router from './router'
import store from './store'
import Cookie from 'js-cookie'
//把axios实例化后引入main.js文件。挂载在axios原型链上全局使用。这样我们就可以直接this.$axios来发请求了
import http from './utils/http'
Vue.prototype.$axios = http
Vue.config.productionTip = false
Vue.use(ElementUI)

//添加全局前置导航守卫
router.beforeEach((to, from, next) => {
  let token = null;
  if (Cookie.get('user')) {
    //判断token存不存在
    token = JSON.parse(Cookie.get('user')).token;
  }
  //token不存在，说明当前用户是未登录，应该跳转至登录页
  if (!token && to.name !== 'login') {
    next({ name: 'login' })
  }
  else if (token && to.name === 'login') {//token存在，说明用户登录，此时跳转至首页,不能让用户重复登录
    next({ name: 'home' })
  } else {
    next()
  }
})

new Vue({
  store,
  router,
  render: h => h(App),
}).$mount('#app')
