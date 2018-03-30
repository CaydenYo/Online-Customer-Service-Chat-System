<template>
  <el-row :gutter="20">
    <el-col :span="6" :offset="9">
      <div class="grid-content subrow">
        <el-form :model="csRegisterForm" ref="csRegisterForm" :rules="csRF" status-icon label-width="100px" class="demo-ruleForm">
          <el-form-item label="工号" prop="cs_id" :rules="{
        required: true, message: '工号不能为空', trigger: 'blur'
      }">
            <el-input v-model="csRegisterForm.cs_id" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="姓名" prop="cs_name" :rules="{
        required: true, message: '姓名不能为空', trigger: 'blur'
      }">
            <el-input v-model="csRegisterForm.cs_name" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="昵称" prop="cs_nickName" :rules="{
        required: true, message: '昵称不能为空', trigger: 'blur'
      }">
            <el-input v-model="csRegisterForm.cs_nickName" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="公司" prop="company_id" :rules="{
        required: true, message: '公司不能为空', trigger: 'blur'
      }">
            <el-input v-model="csRegisterForm.company_id" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="邮箱" prop="cs_email" :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur,change' }
    ]">
            <el-input v-model="csRegisterForm.cs_email" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="cs_pwd">
            <el-input type="password" v-model="csRegisterForm.cs_pwd" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="cs_pwd1">
            <el-input type="password" v-model="csRegisterForm.cs_pwd1" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button class="subbtn" type="primary" @click="submitForm('csRegisterForm')">提交</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'))
      } else {
        if (this.csRegisterForm.cs_pwd1 !== '') {
          this.$refs.csRegisterForm.validateField('cs_pwd1')
        }
        callback()
      }
    }
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.csRegisterForm.cs_pwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      csRegisterForm: {
        cs_id: '',
        cs_name: '',
        cs_nickName: '',
        company_id: '',
        cs_email: '',
        cs_pwd: '',
        cs_pwd1: ''
      },
      csRF: {
        cs_pwd: [{ validator: validatePass, trigger: 'blur' }],
        cs_pwd1: [{ validator: validatePass2, trigger: 'blur' }]
      }
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style>
.subbtn {
  width: 100%;
}
.subrow {
  margin-top: 20%;
}
</style>
