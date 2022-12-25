import Vue from 'vue'
import VueRouter from 'vue-router'

//1.创建路由组件
import Home from '../views/Home'
import User from '../views/User'
import Main from '../views/Main'
import Ticket from '../views/Ticket'
import Sights from '../views/Sights'
import Chat from '../views/Chat'
import Order from '../views/Order'
import Login from '../views/Login'
Vue.use(VueRouter)
//2.将路由与组件进行映射
const routes = [
  //主路由
  {
    path: '/',
    component: Main,
    redirect: '/home',//重定向
    //子路由
    children: [
      {
        path: 'home',
        name: 'home',
        component: Home
      },//首页 
      { path: 'user', component: User },//用户管理
      { path: 'ticket', component: Ticket },//车票管理
      { path: 'sights', component: Sights },//景点管理
      { path: 'chat',component: Chat},
      { path: 'order', component: Order },
    ]
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  // {
  //   path: '*',
  //   redirect: '/home'
  // }

]
//3.创建router实例  
//我这里用Vue.createRouter错误
const router = new VueRouter({
  routes// `routes: routes` 的缩写
})

export default router
