<template>
  <div id="home">主页
  </div>
</template>
<script>
  import Vue from 'vue'
  import Element from '../../../node_modules/element-ui'
  import '../../../node_modules/element-ui/lib/theme-chalk/index.css'

  Vue.use(Element)
  export default {
    name: 'home',
    data: function () {
      return {active: true}
    }
  }
</script>
<style>
  body {
    margin: 0;
  }


</style>
