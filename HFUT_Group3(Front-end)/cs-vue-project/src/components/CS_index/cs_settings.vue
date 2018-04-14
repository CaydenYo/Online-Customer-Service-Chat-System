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
                            <el-form-item label="原密码" prop="cs_oldpwd">
                                <el-input v-model="bsetForm.cs_oldpwd"></el-input>
                            </el-form-item>
                            <el-form-item label="新密码" prop="cs_pwd">
                                <el-input v-model="bsetForm.cs_pwd"></el-input>
                            </el-form-item>
                            <el-form-item label="确认密码" prop="cs_checkpwd">
                                <el-input v-model="bsetForm.cs_checkpwd"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="submitForm('bsetForm')">保存</el-button>
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
                            <el-form-item>
                                <el-button type="primary" @click="submitForm('ssetForm')">保存</el-button>
                            </el-form-item>
                        </el-form>
                    </div>
                </el-card>
            </el-col>
            <el-col :span="12">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>状态设置</span>
                    </div>
                    <div class="setbody">
                        <el-form ref="csetForm" :model="csetForm" :rules="crules" size="small" label-width="80px">
                            <el-form-item label="状态">
                                <el-radio-group v-model="csetForm.cs_status">
                                    <el-radio label=0>在线</el-radio>
                                    <el-radio label=1>休息</el-radio>
                                    <el-radio label=2>离开</el-radio>
                                </el-radio-group>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click="submitForm('csetForm')">保存</el-button>
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
        return {
        imageUrl: '',
        csetForm: {
            cs_status: '0'
        },
        bsetForm: {
            cs_nickName: '',
            cs_oldpwd: '',
            cs_pwd: '',
            cs_checkpwd: ''
        },
        ssetForm: {
            cs_operating_number: '',
            cs_waiting_number: ''
        },
        brules: {
            cs_nickName: [
            { required: true, message: '请填写昵称', trigger: 'change' }
            ]
        },
        srules: {
            cs_operating_number: [
            { required: true, message: '请选择同时接入人数', trigger: 'change' }
            ],
            cs_waiting_number: [
            { required: true, message: '请选择同时等待人数', trigger: 'change' }
            ]
        }
        }
    },
  methods: {
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
