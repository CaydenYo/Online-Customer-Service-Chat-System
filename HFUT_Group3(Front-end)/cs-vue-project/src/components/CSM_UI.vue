<template>
  <div class="csm_login">
    <el-container style="padding-top:100px;width:100%;">
      <el-header class="header">
        <el-button-group style="float:right;margin-top:10px;">
          <el-button @click="login_tag">登录<i class="el-icon-caret-right"></i></el-button>
          <el-button @click="dialogRegisterVisible = true">注册<i class="el-icon-circle-plus"></i></el-button>
        </el-button-group>
      </el-header>
      <el-main class="main">
        <h1>pentaKill客服管理人员后台</h1>
        <el-dialog
        :visible.sync="dialogLoginVisible"
        width="50%"
        :before-close="handleClose">
        <el-tabs v-model="activeName" @tab-click="handleClick"> 
          <el-tab-pane label="登录" name="first">
            <label for="login_username">账号</label>
            <el-input type="text" v-model="login_info.csm_email" id="login_username" placeholder="请输入邮箱"
            style="width:50%;" props="csm_email"></el-input>
            <br><br>
            <label for="login_password">密码</label>
            <el-input type="password" v-model="login_info.csm_pwd" id="login_password" placeholder="请输入密码"
            style="width:50%;" props="csm_pwd"></el-input>
            <br><br>
            <el-button round @click="login" style="width:50%;">登录</el-button>
          </el-tab-pane>
          <el-tab-pane label="注册" name="second">
            <label for="register_username">公司</label>
            <el-input type="text" v-model="register_info.csm_company" id="register_username" placeholder="请输入公司ID"
            style="width:50%;"></el-input>
            <br><br>
            <label for="register_password">账号</label>
            <el-input type="password" v-model="register_info.csm_email" id="register_password" placeholder="请输入邮箱"
            style="width:50%;"></el-input>
            <br><br>
            <label for="register_name">密码</label>
            <el-input type="text" v-model="register_info.csm_pwd" id="register_name" placeholder="请输入密码"
            style="width:50%;"></el-input>
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
      <label for="register_username">公司</label>
      <el-input type="text" v-model="register_info.cs_company" id="register_username" placeholder="请输入公司ID"
      style="width:50%;"></el-input>
      <br><br>
      <label for="register_password">账号</label>
      <el-input type="password" v-model="register_info.cs_email" id="register_password" placeholder="请输入邮箱"
      style="width:50%;"></el-input>
      <br><br>
      <label for="register_name">密码</label>
      <el-input type="text" v-model="register_info.cs_pwd" id="register_name" placeholder="请输入密码"
      style="width:50%;"></el-input>
      <br><br>
      <el-button round v-on:click="register" style="width:50%;">注册</el-button>
    </el-dialog>
  </el-main>
</el-container>
</div>
</template>

<script>
export default {
  data() {
    return {
      login_url: '/Login',
      register_url: '/Register',
      activeName: 'first',
      dialogLoginVisible: false,
      dialogRegisterVisible: false,
      login_info:{
        csm_email:'',
        csm_pwd:''
      },
      register_info:{
        csm_company:'',
        csm_email:'',
        csm_pwd:''
      }
    };
  },
  methods: {
    handleClick(tab, event) {
      console.log(tab, event);
    },
    handleClose(done) {
      this.$confirm('确认关闭？')
      .then(_ => {
        done();
      })
      .catch(_ => {});
    },
    login_tag:function(event){
      this.dialogLoginVisible = true;
      this.activeName = 'first';
    },
    login : function(event){
              //获取值
              var username = this.login_info.cs_email;
              var password = this.login_info.cs_pwd;
              if(username == '' || password == ''){
                this.$message({
                  message : '账号或密码为空！',
                  type : 'error'
                })
                return;
              }
              let _this = this
              alert(JSON.stringify(this.login_info))
              var params = new URLSearchParams();
              params.append('data',JSON.stringify(this.login_info));
              this.$axios({
                method: 'post',
                url:this.rootUrl + _this.login_url,
                data: params
              }).then((res)=>{
                if(res.data === "success"){
                  alert("success")
                  this.$router.push({path: '/success'})
                }else{
                  this.$message({
                    message:JSON.stringify(res.data),
                    type:'error'
                  })
                }
              })
            },
            register :function(even){
              var username = this.register_info.csm_email;
              var password = this.register_info.csm_pwd;
              var ID = this.register_info.csm_company;
              if(username == '' || password == '' || ID == ''){
                this.$message({
                  message : '信息不完全！',
                  type : 'error'
                })
                return;
              }
              var params = new URLSearchParams();
              params.append('data',JSON.stringify(this.register_info));
              this.$axios({
                method: 'post',
                url:this.rootUrl + _this.register_url,
                data: params
              }).then((res)=>{
                if(res.data === "success"){
                  this.$router.push({path: '/success'})
                }else{
                  this.$message(JSON.stringify(res.data))
                }
              })
            }
          }
        };
        </script>

        <style scoped>

        .el-row {
          margin-bottom: 20px;
          &:last-child {
            margin-bottom: 0;
          }
        }

        .csm_login{
          background-image: url("../img/csm_background.jpg");
          height: 100%;
        }

        .header{
          background-color: #fff;
          color: #333;
          text-align: center;
          line-height: 60px;
        }

        .main {
          background-color: #E9EEF3;
          color: #333;
          text-align: center;
          line-height: 160px;
          height: 300px;
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
        </style>