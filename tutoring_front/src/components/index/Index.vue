<template>
  <div id="index" style="height: 100%">
    <el-container style="height: 100%">
      <el-aside  style="height: 100%;width: 180px;">
        <el-row style="height: 100%;">
          <el-col :span="4"  style="height: 100%;width: 180px;">
            <el-menu style="height: 100%;background-color: #30465d;" router>
              <img class="logo" src="../../assets/logo1.png" @click="goHome()"/>
              <el-submenu index="2">
                <template slot="title"><i class="el-icon-document"></i>数据管理</template>
                <el-menu-item :default-active="defaultActive" index="/index/teacher">教师列表</el-menu-item>
                <el-menu-item index="/index/student">学生列表</el-menu-item>
                <el-menu-item index="foodList">搭档列表</el-menu-item>
                <el-menu-item index="orderList">配置列表</el-menu-item>
                <el-menu-item index="adminList">管理员列表</el-menu-item>
              </el-submenu>
              <el-submenu index="4">
                <template slot="title"><i class="el-icon-star-on"></i>图表</template>
                <el-menu-item index="visitor">用户分布</el-menu-item>
                <!-- <el-menu-item index="newMember">用户数据</el-menu-item> -->
              </el-submenu>
              <el-submenu index="5">
                <template slot="title"><i class="el-icon-edit"></i>编辑</template>
                <!-- <el-menu-item index="uploadImg">上传图片</el-menu-item> -->
                <el-menu-item index="vueEdit">文本编辑</el-menu-item>
              </el-submenu>
              <el-submenu index="6">
                <template slot="title"><i class="el-icon-setting"></i>设置</template>
                <el-menu-item index="adminSet">管理员设置</el-menu-item>
                <!-- <el-menu-item index="sendMessage">发送通知</el-menu-item> -->
              </el-submenu>
              <el-submenu index="7">
                <template slot="title"><i class="el-icon-warning"></i>说明</template>
                <el-menu-item index="explain">说明</el-menu-item>
              </el-submenu>
            </el-menu>
          </el-col>
        </el-row>
      </el-aside>
      <el-container>
        <el-header style="border: 0;background-color: #30465d;height: 50px">
                <div class="logout" @click="logout()">
                  <i class="el-icon-back"></i>退出
                </div>
        </el-header>
        <el-main>
          <router-view></router-view>
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
<script>
  import Vue from 'vue'
  import Element from '../../../node_modules/element-ui'
  import '../../../node_modules/element-ui/lib/theme-chalk/index.css'

  Vue.use(Element)
  export default {
    name: 'index', data: function () {
      return {active: true}
    },
    methods:{
      goHome(){
        this.$router.push("/index")
      },
      logout(){
        this.$confirm('确认是否退出系统?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$http.get('http://localhost:8091/api/m/logout/1').then((response)=>{
            if(response.body && response.body.status == 1){
              this.$message({
                message: '退出成功',
                type: 'success'
              });
              localStorage.removeItem('token')
              localStorage.removeItem('manager')
              this.$router.push("/login")
            }else{
              this.$message({
                message: response.body && response.body.status != 1002?response.body.message:'请求出错',
                type: 'warning'
              });
            }
          },(response)=>{
            this.$message.error('请求出错');
          });
        }).catch(() => {
          console.log('取消退出!')
        });
      }
    }
  }
</script>
<style>
  body {
    margin: 0;
  }

  #index {
    min-width: 1200px;
    margin: 0 auto;
    font-family: "Helvetica Neue", "PingFang SC", Arial, sans-serif;
  }
  .logo{
    width: 150px;
    height: 55px;
    margin-left: 10px;
  }
  .logo:hover{
    cursor: pointer;
  }
  .logout{
    position: absolute;
    right: 50px;
    color: #909399;
    line-height: 50px;
  }
  .logout:hover{
    color: #303133;
    cursor: pointer;
  }
  /* 头部导航 */
  header {
    z-index: 1000;
    min-width: 1200px;
    transition: all 0.5s ease;
    border-top: solid 4px #3091F2;
    background-color: #fff;
    box-shadow: 0 2px 4px 0 rgba(0, 0, 0, .12), 0 0 6px 0 rgba(0, 0, 0, .04);
  }

  header.header-fixed {
    position: fixed;
    top: 0;
    left: 0;
    right: 0;
  }

  header .el-menu-demo {
    padding-left: 300px !important;
  }
  .el-submenu__title,.el-menu-item{
    color: #ffffff;
  }
  .el-submenu__title:focus,.el-menu-item:focus{
    color: #30465d;
  }
  .el-menu{
    border: 0;
    background-color: #253951;
  }
  .el-submenu__title:hover,.el-menu-item:hover{
    color: #30465d;
  }
</style>
