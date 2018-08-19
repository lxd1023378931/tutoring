import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/Login'
import Index from '@/components/index/Index'
import Home from '@/components/home/Home'
import Teacher from '@/components/teacher/Teacher'
Vue.use(Router)

export default new Router({
  routes: [
    {
      path:'/index',
      name:'Index',
      component:Index,
      children:[
        {
          path: '/',
          name: 'Home',
          component: Home
        },{
          path: '/index/teacher',
          name: 'Teacher',
          component: Teacher
        }
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    }
  ]
})
