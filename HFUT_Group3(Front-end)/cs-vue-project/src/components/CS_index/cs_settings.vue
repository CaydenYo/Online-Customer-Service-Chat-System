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
                <el-button type="primary" @click="submitForm(bsetForm,'bsetForm',bset_url)">保存</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
      <!-- 头像设置 -->
      <el-col :span="12">
        <el-card shadow="hover">
          <div slot="header" class="clearfix">
            <span>头像设置</span>
          </div>
          <div class="setbody">
            <input type="file" name="avatar" accept="image/jpeg,image/jpg,image/png" ref="avatarInput" style="display:none;" @change="onFileSelected">
            <img :src="avatar" alt="" :onerror="default_cs_avatar" @click="$refs.avatarInput.click()" /></br>
            <el-button type="primary" @click="onUpload" icon="el-icon-upload" size="small">上传</el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row :gutter="12">
      <!-- 服务设置 -->
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
                  <el-option label="25" value="25"></el-option>
                  <el-option label="30" value="30"></el-option>
                </el-select>
              </el-form-item>
              <el-form-item label="同时等待人数" prop="cs_waiting_number">
                <el-select v-model="ssetForm.cs_waiting_number" placeholder="请选择同时等待人数">
                  <el-option label="5" value="5"></el-option>
                  <el-option label="10" value="10"></el-option>
                  <el-option label="15" value="15"></el-option>
                  <el-option label="20" value="20"></el-option>
                  <el-option label="25" value="25"></el-option>
                  <el-option label="30" value="30"></el-option>
                </el-select>
              </el-form-item>
              </br>
              <el-form-item>
                <el-button type="primary" @click="submitForm(ssetForm,'ssetForm',sset_url)">保存</el-button>
              </el-form-item>
              </br>
            </el-form>
          </div>
        </el-card>
      </el-col>
      <!-- 密码设置 -->
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
                <el-button type="primary" @click="submitForm(psetForm,'psetForm',pset_url)">保存</el-button>
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
      } else if (value != sessionStorage.getItem('cs_pwd')) {
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
      avatar: '',
      default_cs_avatar:
        'this.src="' + require('../../../static/images/cs_def.png') + '"',
      imageUrl: 'https://jsonplaceholder.typicode.com/posts/',
      bset_url: '/customerService/setName',
      sset_url: '/customerService/setNumber',
      pset_url: '/customerService/setPwd',
      bsetForm: {
        cs_email: sessionStorage.getItem('cs_email'),
        cs_nickName: sessionStorage.getItem('cs_nickName')
      },
      ssetForm: {
        company_id: sessionStorage.getItem('company_id'),
        cs_email: sessionStorage.getItem('cs_email'),
        cs_operating_number: sessionStorage.getItem('cs_operating_number'),
        cs_waiting_number: sessionStorage.getItem('cs_waiting_number')
      },
      psetForm: {
        cs_email: sessionStorage.getItem('cs_email'),
        cs_oldpwd: '',
        cs_pwd: '',
        cs_checkpwd: ''
      },
      upLoadData: {
        selectedFile: null,
        message: sessionStorage.getItem('cs_email')
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
    // if (this.$refs.avatarInput.files.length === 0){
    if (localStorage.getItem('cs_img') === null) {
      console.log('检测到未设置头像')
      this.avatar =
        'this.src="' + require('../../../static/images/cs_def.png') + '"'
    } else {
      this.avatar = localStorage.getItem('cs_img')
    }
  },
  methods: {
    onFileSelected(event) {
      console.log(event)
      this.upLoadData.selectedFile = event.target.files[0]
      this.upLoadData.message = event.target.files[0].name+' '+this.upLoadData.message
      console.log(this.upLoadData.message)
      var file = event.target.files[0]

      // 显示图片
      if (this.beforeAvatarUpload(file)) {
        console.log('true')
        var reader = new FileReader()
        var that = this
        reader.readAsDataURL(file)
        reader.onload = function(event) {
          console.log('this.result:' + this.result)
          that.avatar = this.result
          localStorage.setItem('cs_img', this.result)
        }
      }
    },
    // 上传图片按钮
    onUpload() {
      if (this.$refs.avatarInput.files.length !== 0) {
        this.upLoadData.selectedFile = this.$refs.avatarInput.files[0]
        console.log('是否有图片：' + (this.upLoadData.selectedFile !== null))
        const fr = new FormData()
        fr.append('file', this.upLoadData.selectedFile, this.upLoadData.message)

        let that = this
        this.$axios({
          method: 'post',
          url: '/OCSSystem/customerService/setImg',
          data: fr,
          processData: false, // 告诉jQuery不要去处理发送的数据
          contentType: false // 告诉jQuery不要去设置Content-Type请求头
        }).then(res => {
          console.log(res.data)
        })
      } else {
        this.$message({
          message: '您还没有选择新头像',
          type: 'error'
        })
      }
    },
    // 提交设置
    submitForm(form, formName, url) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          var params = new URLSearchParams()
          let _this = this
          params.append('data', JSON.stringify(form))
          this.$axios({
            method: 'post',
            url: this.rootUrl + url,
            data: params
          }).then(res => {
            if (res.data === 'success') {
              if (formName === 'bsetForm') {
                sessionStorage.setItem('cs_nickName', form.cs_nickName)
              } else if (formName === 'psetForm') {
                sessionStorage.setItem('cs_pwd', form.cs_pwd)
              } else if (formName === 'ssetForm') {
                sessionStorage.setItem(
                  'cs_operating_number',
                  form.cs_operating_number
                )
                sessionStorage.setItem(
                  'cs_waiting_number',
                  form.cs_waiting_number
                )
              }
              this.$message({
                message: '成功',
                type: 'success'
              })
            } else if (!isNaN(Number(res.data))) {
              console.log(Number(res.data))
              this.$message({
                message: '同时服务人数至少为：' + res.data,
                type: 'error'
              })
            } else {
              this.$message({
                message: '请稍后再试',
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
    // 上传前图片检测
    beforeAvatarUpload(file) {
      console.log('in this beforeAvatarUpload')
      const isJPG = file.type === 'image/jpeg' || file.type === 'image/jeg'
      const isPNG = file.type === 'image/png'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG && !isPNG) {
        this.$message.error('上传头像图片只能是 JPG 或 PNG 格式!')
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
.setbody img {
  width: 50px;
  height: 50px;
  margin-bottom: 15px;
}
</style>
