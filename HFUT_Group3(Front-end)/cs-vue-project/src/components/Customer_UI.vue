<template>
  <div class="user_ui">
    <xfchat></xfchat>
    <el-container>
<el-container>
  <el-header class="customer-index">
    <div class="line"></div>
    <el-menu
    class="el-menu-demo"
    mode="horizontal"
    @select="handleSelect"
    background-color="#545c64"
    text-color="#fff"
    active-text-color="#ffd04b">
    <el-menu-item index="1">联系客服</el-menu-item>
    <el-submenu index="2">
      <template slot="title">我的工作台</template>
      <el-menu-item index="2-1">选项1</el-menu-item>
      <el-menu-item index="2-2">选项2</el-menu-item>
      <el-menu-item index="2-3">选项3</el-menu-item>
      <el-submenu index="2-4">
        <template slot="title">选项4</template>
        <el-menu-item index="2-4-1">选项1</el-menu-item>
        <el-menu-item index="2-4-2">选项2</el-menu-item>
        <el-menu-item index="2-4-3">选项3</el-menu-item>
    </el-submenu>
</el-submenu>
<el-submenu index="3">
  <template v-if="nickname == null" slot="title">账户</template>
  <template v-if="nickname !== null" slot="title">{{nickname}}</template>
  <el-menu-item index="3-1">登录</el-menu-item>
  <el-menu-item index="3-2">注册</el-menu-item>
  <el-menu-item index="3-3">个人信息</el-menu-item>
</el-submenu>
</el-menu>
</el-header>
<el-main>

  <el-dialog
  :visible.sync="dialogLoginVisible"
  width="50%"
  :before-close="handleClose">
  <el-tabs v-model="activeName" @tab-click="handleClick"> 
    <el-tab-pane label="登录" name="first">
      <label for="login_username">账号</label>
      <el-input type="text" v-model="login_info.customer_email" id="login_username" placeholder="请输入邮箱"
      class="info-input"></el-input>
      <br><br>
      <label for="login_password">密码</label>
      <el-input type="password" v-model="login_info.customer_pwd" id="login_password" placeholder="请输入密码"
      class="info-input"></el-input>
      <br><br>
      <el-button round v-on:click="login" class="login-button">登录</el-button>
  </el-tab-pane>
  <el-tab-pane label="注册" name="second">
      <label for="register_username">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
      <el-input type="text" v-model="register_info.customer_email" id="register_username" placeholder="请输入邮箱"
      class="info-input"></el-input>
      <br><br>
      <label for="register_password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
      <el-input type="password" v-model="register_info.customer_pwd" id="register_password" placeholder="请输入密码"
      class="info-input"></el-input>
      <br><br>
      <label for="register_name">确认密码</label>
      <el-input type="text" v-model="register_info.customer_repwd" id="register_repassword" placeholder="请输入密码"
      class="info-input"></el-input>
      <br><br>
      <label for="register_ID">家庭地址</label>
      <el-input type="text" v-model="register_info.customer_address" id="register_address" placeholder="请输入地址"
      class="info-input"></el-input>
      <br><br>
      <label for="register_nickname">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
      <el-input type="text" v-model="register_info.customer_nickname" id="register_nickname" placeholder="由13、15、18开头" class="info-input"></el-input>
      <br><br>
      <el-radio v-model="register_info.customer_gender" label="1">男</el-radio>
      <el-radio v-model="register_info.customer_gender" label="2">女</el-radio>
      <br><br>
      <el-button round v-on:click="register" class="register-button">注册</el-button>
  </el-tab-pane>
</el-tabs>
</el-dialog>

<el-dialog
:visible.sync="dialogRegisterVisible"
width="50%"
:before-close="handleClose">
<h1>账号注册</h1>
<label for="register_username">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label>
<el-input type="text" v-model="register_info.customer_email" id="register_username" placeholder="请输入邮箱"
class="info-input"></el-input>
<br><br>
<label for="register_password">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
<el-input type="password" v-model="register_info.customer_pwd" id="register_password" placeholder="请输入密码"
class="info-input"></el-input>
<br><br>
<label for="register_name">确认密码</label>
<el-input type="text" v-model="register_info.customer_repwd" id="register_repassword" placeholder="请输入密码"
class="info-input"></el-input>
<br><br>
<label for="register_ID">家庭地址</label>
<el-input type="text" v-model="register_info.customer_address" id="register_address" placeholder="请输入地址"
class="info-input"></el-input>
<br><br>
<label for="register_nickname">昵&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
<el-input type="text" v-model="register_info.customer_nickname" id="register_nickname" placeholder="由13、15、18开头"
class="info-input"></el-input>
<br><br>
<el-radio v-model="register_info.customer_gender" label="1">男</el-radio>
<el-radio v-model="register_info.customer_gender" label="2">女</el-radio>
<br><br>
<el-button round v-on:click="register" class="register-button">注册</el-button>
</el-dialog>


<el-carousel :interval="5000" arrow="always">
    <el-carousel-item v-for="item in his" :key="item">
      <a href=""><img alt="" v-bind:src="item.message"/></a>
  </el-carousel-item>
</el-carousel>

</el-main>
</el-container>
</el-container>
</div>
</template>

<script>
import {mapState} from 'vuex'
import xfchat from './xfchat/chat'
export default {
  data (){
    return{
      login_url:'/login.action',
      register_url:'/register.action',
      activeName: 'first',
      nickname: null,
      dialogLoginVisible:false,
      dialogRegisterVisible:false,
      login_info:{
        customer_email:'',
        customer_pwd:''
    },
    register_info:{
        customer_email:'',
        customer_pwd:'',
        customer_repwd:'',
        customer_address:'',
        customer_nickname:'',
        customer_gender:''
    },
    his:[
    {message: require('../assets/logo.png')},
    {message: require('../assets/logo.png')},
    {message: require('../assets/logo.png')},
    {message: require('../assets/logo.png') },
    ],
};
},
components: {
  xfchat
},
methods: {
    handleClose(done) {
      this.$confirm('确认关闭？')
      .then(_ => {
        done();
    })
      .catch(_ => {});
  },
  show: function(event){
      this.show2 = !this.show2;
  },
  handleSelect(key,keypath){
      if(key == '1'){
        this.$router.push({path: '/show_embedded_cs'})
    }else if(key == '3-1'){
        this.dialogLoginVisible = true;
        this.activeName = 'first';
    }else if(key == '3-2'){
        this.dialogRegisterVisible = true;
    }
},
login : function(event){
              //获取值
              var username = this.login_info.customer_email;
              var password = this.login_info.customer_pwd;
              if(username == '' || password == ''){
                this.$message({
                  message : '账号或密码为空！',
                  type : 'error'
              })
                return;
            }
            let _this = this
            var params = new URLSearchParams();
            params.append('data',JSON.stringify(this.login_info));
            this.$axios({
                method: 'post',
                url:this.rootUrl + _this.login_url,
                data: params
            }).then((res)=>{
                if(res.data.customer_email == this.login_info.customer_email){
                  alert("success")
                  _this.dialogLoginVisible = false
                  var d = res.data
                  alert("登录成功！准备往store中写数据")
                  this.$store.commit('clientLoginSuccess', {
                    senderId: d.customer_id,
                    nickname: d.customer_nickname,
                    img: d.customer_img
                  })
                  alert("储存用户信息结束")
                  //sessionStorage.setItem('customer_id', d.customer_id)
                  //sessionStorage.setItem('customer_nickname', d.customer_nickname)
                  //sessionStorage.setItem('customer_img', d.customer_img)
              }else{
                  this.$message({
                    message:JSON.stringify(res.data),
                    type:'error'
                })
              }
          })
        },
        register :function(even){
          var username = this.register_info.customer_email;
          var password = this.register_info.customer_pwd;
          var repassword = this.register_info.customer_repwd;
          var address = this.register_info.customer_address;
          var nickname = this.register_info.customer_nickname;
          var gender = this.register_info.customer_gender;
          if(username == '' || password == ''|| repassword == ''
             || address == '' || nickname == ''){
            this.$message({
              message : '信息不完全！',
              type : 'error'
          })
        return;
    }else if(repassword != password){
      this.$message({
        message : '两次密码不一致！',
        type : 'error'
    })
      return
  }
  let _this = this
  var params = new URLSearchParams();
  params.append('data',JSON.stringify(this.register_info));
  this.$axios({
    method: 'post',
    url:this.rootUrl + _this.register_url,
    data: params
}).then((res)=>{
    if(res.data === "success"){
      alert("success")
      _this.dialogRegisterVisible = false
      this.$router.push({path: '/success'})
  }else{
      this.$message({
        message:JSON.stringify(res.data),
        type:'error'
    })
  }
})
}
}

}
</script>

<style scoped>
.customer-index {
   background-color:#545c64
}
.info-input {
  width: 50%;
}
.login-button {
  width: 50%;
}
.register-button {
  width: 50%;
}
.el-header, .el-footer {
    background-color:none;
    color: #333;
    text-align: center;
    line-height: 60px;
}

.el-aside {
    background-color: #D3DCE6;
    color: #333;
    text-align: center;
}

.el-main {
    background-color: #E9EEF3;
    color: #333;
    text-align: center;
    line-height: 160px;
    background-image: url("../img/user_logo.jpg");
}

body > .el-container {
    margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
    line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
    line-height: 320px;
}

.user_ui{
    width: 100%;
    height: 100%;
}

.user_ui > .el-container{
    height: 100%;
}

.el-carousel__item h3 {
    color: #475669;
    font-size: 18px;
    opacity: 0.75;
    line-height: 300px;
    margin: 0;
}

.el-carousel__item:nth-child(2n) {
    background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}
</style>