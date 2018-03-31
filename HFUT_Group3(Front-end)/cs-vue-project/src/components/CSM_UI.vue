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
            <el-input type="text" v-model="login_info.cs_email" id="login_username" placeholder="请输入邮箱"
            style="width:50%;" props="cs_email"></el-input>
            <br><br>
            <label for="login_password">密码</label>
            <el-input type="password" v-model="login_info.cs_pwd" id="login_password" placeholder="请输入密码"
            style="width:50%;" props="cs_pwd"></el-input>
            <br><br>
            <el-button round @click="login" style="width:50%;">登录</el-button>
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
    </el-main>
  </el-container>
</div>
</template>

<script>
export default {
  data() {
    return {
      url: '/customerService/Login',
      activeName: 'first',
      dialogLoginVisible: false,
      dialogRegisterVisible: false,
      login_info:{
        cs_email:'',
        cs_pwd:''
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
              alert(username + " " + password)
              let _this = this
              alert(this.rootUrl + _this.url)
              alert(JSON.stringify(this.login_info))
              this.$ajax.post(this.rootUrl + _this.url, JSON.stringify(_this.login_info))
              .then((response) => {
//                console.log(response.data.num)
                // if (response.data.num === 1) {
                //   this.$message.success('登陆成功！')
                //   //  跳转到登录成功页
                //   this.$router.push({path: '/success'})
                // } else if (response.data.num === 0) {
                //   this.$message.success('用户名，密码错误！')
                // }else {
                //   this.$message.success('系统错误！')
                // }
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