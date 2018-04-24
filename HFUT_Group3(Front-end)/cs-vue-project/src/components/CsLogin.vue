<template>
  <div class="grid-content subrow">
    
    <el-form :model="csLoginForm" status-icon ref="csLoginForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="邮箱" prop="cs_email" :rules="[ { required: true, message: '请输入邮箱地址', trigger: 'blur' }, { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur,change' } ]">
        <el-input v-model="csLoginForm.cs_email"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="cs_pwd" :rules="{
          required: true, message: '密码不能为空', trigger: 'blur'
      }">
        <el-input type="password" v-model="csLoginForm.cs_pwd" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button class="subbtn" type="primary" @click="submitForm('csLoginForm')">提交</el-button>
        <el-button class="subbtn" @click="resetForm('csLoginForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      login_url: '/customerService/Login',
      csLoginForm: {
        cs_email: '',
        cs_pwd: ''
      }
    }
  },
  mounted() {},
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          var params = new URLSearchParams()
          let _this = this
          params.append('data', JSON.stringify(this.csLoginForm))
          this.$axios({
            method: 'post',
            url: this.rootUrl + _this.login_url,
            data: params
          }).then(res => {
            if (res.data === '客服账号未激活') {
              alert('客服账号未激活')
              //this.$router.push({ path: '/index' })
            } else if (res.data === '用户名或者密码错误') {
              // sessionStorage.setItem('company_id', '1')
              // this.$message({
              //   message: JSON.stringify(res.data),
              //   type: 'error'
              // })
              alert('用户名或者密码错误')
            } else {
              var d = res.data
              sessionStorage.setItem('company_id', d.company_id)
              sessionStorage.setItem('cs_pwd', d.cs_pwd)
              sessionStorage.setItem('cs_workId', d.cs_workId)
              sessionStorage.setItem('cs_register_status', d.cs_register_status)
              sessionStorage.setItem('cs_status', d.cs_status)
              sessionStorage.setItem(
                'cs_operating_number',
                d.cs_operating_number
              )
              sessionStorage.setItem('cs_waiting_number', d.cs_waiting_number)
              sessionStorage.setItem('cs_email', d.cs_email)
              sessionStorage.setItem('cs_nickName', d.cs_nickName)
              sessionStorage.setItem('cs_id', d.cs_id)
              sessionStorage.setItem('cs_score', d.cs_score)
              this.$router.push('/cs_dialogue')
            }
          })
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    resetForm(formName) {
      this.$refs[formName].resetFields()
    }
  }
}
</script>

<style>
.subbtn {
  width: 45%;
}
.subrow {
  position: absolute;
  left: 0;
  right: 0;
  width: 400px;
  padding: 35px 35px 15px;
  margin: 120px auto;
  background-color: #fff;
}
body {
  overflow: hidden;
}
</style>
