<template>
  <router-view></router-view>
</template>
<script>
  import Vue from 'vue'
  import router from './router/router'
  import VueResource from '../node_modules/vue-resource'
  Vue.use(router)
  Vue.use(VueResource)
  Vue.http.headers.common['token'] = localStorage.getItem('token');
  export default {
    name: 'app',
    beforeCreate:function () {
        this.$http.get('http://localhost:8091/api/m/logged?type=token&token='+(localStorage.getItem('token') || '')).then((response)=>{
          if(response.body && response.body.status == 1){
            router.push("/index")
          }else{
            router.push("/login")
          }
        },(response)=>{
          router.push("/login")
        });
    }
  }
</script>
