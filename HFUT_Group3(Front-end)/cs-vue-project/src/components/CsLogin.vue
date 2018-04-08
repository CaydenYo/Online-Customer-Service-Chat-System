<template>
  <el-row :gutter="20">
    <el-col :span="6" :offset="9">
      <div class="grid-content subrow">
        <el-form :model="csLoginForm" status-icon ref="csLoginForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="邮箱" prop="cs_email" :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur,change' }
    ]">
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
    </el-col>
  </el-row>
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
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert(JSON.stringify(this.csLoginForm))
          var params = new URLSearchParams()
          let _this = this
          params.append('data', JSON.stringify(this.csLoginForm))
          this.$axios({
            method: 'post',
            url: this.rootUrl + _this.login_url,
            data: params
          }).then(res => {
            if (res.data === 'success') {
              alert('success')
              this.$router.push({ path: '/index' })
            } else {
              localStorage.setItem('company_id', '1')
              this.$message({
                message: JSON.stringify(res.data),
                type: 'error'
              })
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
  margin-top: 20%;
}
</style>
