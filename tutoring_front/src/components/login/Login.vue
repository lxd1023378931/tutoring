<template>
  <div align="center" style="background-color: #f8f9fc">
    <div style="width: 100%;height: 360px;background-color: #66b1ff"></div>
    <div style="margin-top: -250px;background-color: #fff;width: 500px;height: 330px;padding: 30px">
      <img src="./logo.png" width='500px'/>
      <div class="login_body">
        <el-form ref="formItem" :model="formItem">
          <el-form-item>
            <el-input v-model="formItem.name"></el-input>
          </el-form-item>
          <el-form-item>
            <el-input type="password" v-model="formItem.password"></el-input>
          </el-form-item>
          <el-form-item align="left">
            记住密码<el-switch style="padding-left: 10px" v-model="formItem.remember"></el-switch>
            <div style="float: right;color: #66b1ff;cursor: pointer;" @click="onFindUser">忘记密码？</div>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" style="width: 100%" @click="onSubmit">立即登录</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
  import Vue from 'vue'
  import Element from '../../../node_modules/element-ui'
  import '../../../node_modules/element-ui/lib/theme-chalk/index.css'
  Vue.use(Element)
  console.log(localStorage.getItem('rememberPassword'))
  export default {
    name: 'Login',
    data () {
      return {
        formItem: {
          name: localStorage.getItem('name') || '',
          password: localStorage.getItem('password') || '',
          remember:localStorage.getItem('rememberPassword') == 'true'?true : false
        }
      }
    },
    methods: {
      onSubmit() {
        this.$http.post('http://localhost:8091/api/m/login',this.formItem).then((response)=>{
          if(response.body && response.body.status == 1){
            this.$message({
              message: '登录成功',
              type: 'success'
            });
            localStorage.setItem('token',response.body.object.token)
            var manager = response.body.object.manager
            localStorage.setItem('manager',manager)
            if(this.formItem.remember){
              localStorage.setItem('rememberPassword',this.formItem.remember)
              localStorage.setItem('name',this.formItem.name)
              localStorage.setItem('password',this.formItem.password)
            }else{
              localStorage.removeItem('rememberPassword')
              localStorage.removeItem('name')
              localStorage.removeItem('password')
            }
            console.log(localStorage.getItem('rememberPassword'))
            this.$router.push("/index")
          }else{
            this.$message({
              message: response.body && response.body.status != 1002?response.body.message:'请求出错',
              type: 'warning'
            });
          }
        },(response)=>{
          this.$message.error('请求出错');
        });
      },
      onFindUser(){
        this.$alert('管理系统暂不提供找回密码功能，请联系管理员重置密码！', '忘记密码？', {
          confirmButtonText: '确定',
          callback: action => {
          }
        });
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
.login_body{
  width: 500px;
}
el-button{
  width: 100%;
}
</style>
