<template>
  <div class="user_ui">
    <el-container>
      <transition name="el-zoom-in-top">
        <el-aside width="30%" v-if="show_cs">
          <div style="float:left;width:100%;height:100%">
            <div style="float:left;width:100%;height:10%">
             <el-row>
              <el-button type="info" icon="el-icon-caret-left"circle
              style="float:left;margin-top:1%;margin-left:1%" 
              @click="show_cs=false"></el-button>
            </el-row>
          </div>
        </div>
      </el-aside>
    </transition>
    <el-container>
      <el-header style="background-color:#545c64">
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
        <el-menu-item index="3" disabled>消息中心</el-menu-item>
        <el-submenu index="4">
          <template slot="title">账户</template>
          <el-menu-item index="4-1">登录</el-menu-item>
          <el-menu-item index="4-2">注册</el-menu-item>
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
          <el-input type="text" v-model="login_username" id="login_username" placeholder="请输入邮箱"
          style="width:50%;"></el-input>
          <br><br>
          <label for="login_password">密码</label>
          <el-input type="password" v-model="login_password" id="login_password" placeholder="请输入密码"
          style="width:50%;"></el-input>
          <br><br>
          <el-button round v-on:click="login" style="width:50%;">登录</el-button>
        </el-tab-pane>
        <el-tab-pane label="注册" name="second">
          <label for="register_username">账号</label>
          <el-input type="text" v-model="register_username" id="register_username" placeholder="请输入邮箱"
          style="width:50%;"></el-input>
          <br><br>
          <label for="register_password">密码</label>
          <el-input type="password" v-model="register_password" id="register_password" placeholder="请输入密码"
          style="width:50%;"></el-input>
          <br><br>
          <label for="register_name">姓名</label>
          <el-input type="text" v-model="register_name" id="register_name" placeholder="请输入姓名"
          style="width:50%;"></el-input>
          <br><br>
          <label for="register_ID">身份证</label>
          <el-input type="text" v-model="register_ID" id="register_ID" placeholder="15或18位身份证号码" pattern="\d{15}(\d\d[0-9xX])?"
          style="width:50%;"></el-input>
          <br><br>
          <label for="register_tel">手机</label>
          <el-input type="text" v-model="register_tel" id="register_tel" placeholder="由13、15、18开头" pattern="^((13[0-9])|(15[0-9])|(18[0,5-9]))\d{8}$"
          style="width:50%;"></el-input>
          <br><br>
          <el-radio v-model="gender" label="1">男</el-radio>
          <el-radio v-model="gender" label="2">女</el-radio>
          <br><br>
          <el-button round v-on:click="register" style="width:50%;">注册</el-button>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>

    <el-dialog
    :visible.sync="dialogRegisterVisible"
    width="50%"
    :before-close="handleClose">
    <h1>账号注册</h1>
    <label for="register_username">账号</label>
    <el-input type="text" v-model="register_username" id="register_username" placeholder="请输入邮箱"
    style="width:50%;"></el-input>
    <br><br>
    <label for="register_password">密码</label>
    <el-input type="password" v-model="register_password" id="register_password" placeholder="请输入密码"
    style="width:50%;"></el-input>
    <br><br>
    <label for="register_name">姓名</label>
    <el-input type="text" v-model="register_name" id="register_name" placeholder="请输入姓名"
    style="width:50%;"></el-input>
    <br><br>
    <label for="register_ID">身份证</label>
    <el-input type="text" v-model="register_ID" id="register_ID" placeholder="15或18位身份证号码" pattern="\d{15}(\d\d[0-9xX])?"
    style="width:50%;"></el-input>
    <br><br>
    <label for="register_tel">手机</label>
    <el-input type="text" v-model="register_tel" id="register_tel" placeholder="由13、15、18开头" pattern="^((13[0-9])|(15[0-9])|(18[0,5-9]))\d{8}$"
    style="width:50%;"></el-input>
    <br><br>
    <el-radio v-model="gender" label="1">男</el-radio>
    <el-radio v-model="gender" label="2">女</el-radio>
    <br><br>
    <el-button round v-on:click="register" style="width:50%;">注册</el-button>
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
export default {
  data (){
    return{
      show_cs: false,
      activeName: 'first',
      dialogLoginVisible:false,
      dialogRegisterVisible:false,
      login_username:'',
      login_password:'',
      register_username:'',
      register_password:'',
      register_name:'',
      register_ID:'',
      register_tel:'',
      gender:'',
      his:[
        {message: require('../assets/logo.png')},
        {message: require('../assets/logo.png')},
        {message: require('../assets/logo.png')},
        {message: require('../assets/logo.png') },
      ]
    };
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
        this.show_cs = !this.show_cs;
      }else if(key == '4-1'){
        this.dialogLoginVisible = true;
        this.activeName = 'first';
      }else if(key == '4-2'){
        this.dialogRegisterVisible = true;
      }
    },
    login : function(event){
              //获取值
              var username = this.login_username;
              var password = this.login_password;
              if(username == '' || password == ''){
                this.$message({
                  message : '账号或密码为空！',
                  type : 'error'
                })
                return;
              }
              $.ajax({
                url : 'login',
                type : 'post',
                data : {
                  username : login_username,
                  password : login_password
                },
                success : function(data) {
                  var result = data.result;
                  if(result == 'true' || result == true){
                    alert("登录成功");
                  }else {
                    alert("登录失败");
                  }
                },
                error : function(data) {
                  alert(data);
                },
                dataType : 'json',
              })
            },
            register :function(even){
              var username = this.register_username;
              var password = this.register_password;
              var name = this.register_name;
              var ID = this.register_ID;
              var tel = this.register_tel;
              var gender = this.gender;
              if(username == '' || password == ''|| name == ''
               || ID == '' || tel == ''){
                this.$message({
                  message : '信息不完全！',
                  type : 'error'
                })
              return;
            }
            $.ajax({
              url : 'register',
              type : 'post',
              data : {
                username : register_username,
                password : register_password,
                name: register_name,
                ID: register_ID,
                tel: register_tel,
                gender: gender
              },
              success : function(data) {
                var result = data.result;
                if(result == 'true' || result == true){
                  alert("注册成功");
                }else {
                  alert("注册失败");
                }
              },
              error : function(data) {
                alert(data);
              },
              dataType : 'json',
            })
          }
        }

      }
      </script>

      <style scoped>
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