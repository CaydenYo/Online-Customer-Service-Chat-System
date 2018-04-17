<template>
  <div class="mainset">
    <el-row :gutter="12">
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>个人设置</span>
          </div>
          <div class="setbody">
            <el-form ref="bsetForm" :model="bsetForm" :rules="brules" size="small" label-width="80px">
              <el-form-item label="昵称" prop="cs_nickName">
                <el-input v-model="bsetForm.cs_nickName"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('bsetForm',bset_url)">保存</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>头像设置</span>
          </div>
          <div class="setbody">
            <el-upload class="avatar-uploader" action="https://jsonplaceholder.typicode.com/posts/" :show-file-list="false" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
              <img v-if="imageUrl" :src="imageUrl" class="avatar">
              <i v-else class="el-icon-plus avatar-uploader-icon"></i>
            </el-upload>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="12">
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>服务设置</span>
          </div>
          <div class="setbody">
            <el-form ref="ssetForm" :model="ssetForm" :rules="srules" size="small">
              <el-form-item label="同时接入人数" prop="cs_operating_number">
                <el-select v-model="ssetForm.cs_operating_number" placeholder="请选择同时接入人数">
                  <el-option label="5" value="5"></el-option>
                  <el-option label="10" value="10"></el-option>
                  <el-option label="15" value="15"></el-option>
                  <el-option label="20" value="20"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="同时等待人数" prop="cs_waiting_number">
                <el-select v-model="ssetForm.cs_waiting_number" placeholder="请选择同时等待人数">
                  <el-option label="5" value="5"></el-option>
                  <el-option label="10" value="10"></el-option>
                  <el-option label="15" value="15"></el-option>
                  <el-option label="20" value="20"></el-option>
                </el-select>
              </el-form-item>
              </br>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ssetForm',sset_url)">保存</el-button>
              </el-form-item>
              </br>
            </el-form>
          </div>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>密码设置</span>
          </div>
          <div class="setbody">
            <el-form ref="psetForm" :model="psetForm" :rules="prules" size="small" label-width="80px">
              <el-form-item label="原密码" prop="cs_oldpwd">
                <el-input v-model="psetForm.cs_oldpwd"></el-input>
              </el-form-item>
              <el-form-item label="新密码" prop="cs_pwd">
                <el-input v-model="psetForm.cs_pwd"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="cs_checkpwd">
                <el-input v-model="psetForm.cs_checkpwd"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('psetForm',pset_url)">保存</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    // 原密码验证
    var check_old_pass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入原密码'))
      } else if (value != localStorage.getItem('cs_pwd')) {
        callback(new Error('原密码输入错误'))
      } else {
        callback()
      }
    }
    // 纯数字和长度验证
    var checkNull = (rule, value, callback) => {
      setTimeout(() => {
        if (Number.isInteger(value)) {
          callback(new Error('密码不能为纯数字'))
        } else {
          if (value.length < 8) {
            callback(new Error('长度必须大于等于8'))
          } else {
            callback()
          }
        }
      }, 1000)
    }
    // 新密码验证
    var validatePass = (rule, value, callback) => {
      setTimeout(() => {
        if (value === '') {
          callback(new Error('请输入密码'))
        } else {
          if (value.length < 8) {
            callback(new Error('长度必须大于等于8'))
          } else {
            // if (Number.isInteger(parseInt(value))) {
            //   callback(new Error('密码不能为纯数字'))
            // } else
            {
              if (this.psetForm.cs_checkpwd !== '') {
                this.$refs.psetForm.validateField('cs_checkpwd')
              }
              callback()
            }
          }
        }
      }, 500)
    }
    // 确认密码验证
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'))
      } else if (value !== this.psetForm.cs_pwd) {
        callback(new Error('两次输入密码不一致!'))
      } else {
        callback()
      }
    }
    return {
      bset_url: '',
      sset_url: '',
      pset_url: '',
      bsetForm: {
        cs_nickName: ''
      },
      imageUrl: '',
      ssetForm: {
        cs_operating_number: '',
        cs_waiting_number: ''
      },
      psetForm: {
        cs_oldpwd: '',
        cs_pwd: '',
        cs_checkpwd: ''
      },
      brules: {
        cs_nickName: [
          { required: true, message: '请填写昵称', trigger: 'blur' }
        ]
      },
      srules: {
        cs_operating_number: [
          { required: true, message: '请选择同时接入人数', trigger: 'change' }
        ],
        cs_waiting_number: [
          { required: true, message: '请选择同时等待人数', trigger: 'change' }
        ]
      },
      prules: {
        cs_oldpwd: [{ validator: check_old_pass, trigger: 'blur' }],
        cs_pwd: [{ validator: validatePass, trigger: 'blur' }],
        cs_checkpwd: [{ validator: validatePass2, trigger: 'blur' }]
      }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    submitForm(formName, url) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          var params = new URLSearchParams()
          let _this = this
          params.append('data', JSON.stringify(this.formName))
          this.$axios({
            method: 'post',
            url: this.rootUrl + url,
            data: params
          }).then(res => {
            if (res.data === 'UpdateSuccess') {
              this.$message({
                message: '成功',
                type: 'success'
              })
            } else {
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
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
      console.log(imageUrl)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    }
  }
}
</script>

<style>
.el-row {
  margin-bottom: 12px;
}
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
