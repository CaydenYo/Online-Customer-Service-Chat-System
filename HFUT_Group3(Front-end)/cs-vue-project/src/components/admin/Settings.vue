<template>
  <div id="sets">
    <el-card class="scard">
      <div slot="header">接入方式</div>
      <el-switch @change="jrfun" v-model="form1.access_type" active-color="#13ce66" inactive-color="#409eff" active-text="悬浮式" inactive-text="网页" active-value=1 inactive-value=0>
      </el-switch>
      <!-- <br><br>
          <el-button class="btn-c" type="primary">确定</el-button> -->
    </el-card>
    <el-card class="scard">
      <div slot="header">机器人设置</div>
      <el-switch @change="rbfun" v-model="form2.robot_flag" active-color="#13ce66" inactive-color="#ff4949" active-text="开启" inactive-text="关闭" active-value=1 inactive-value=0>
      </el-switch>
      <!-- <br><br>
          <el-button class="btn-c" type="primary">确定</el-button> -->
    </el-card>
    <el-card class="scard">
      <div slot="header">客服设置</div>
      <el-form ref="form3" :model="form3">
        <h5>分配方式</h5>
        <el-switch v-model="form3.distribution_type" active-color="#13ce66" inactive-color="#409eff" active-text="老用户" active-value=1 inactive-text="轮流" inactive-value=0>
        </el-switch>
        <h5>用户信息展示</h5>
        <el-switch v-model="form3.customer_info_flag" active-color="#13ce66" inactive-color="#409eff" active-text="开启" active-value=1 inactive-text="关闭" inactive-value=0>
        </el-switch>
        <h5>同时接待人数</h5>
        <el-input-number v-model="form3.mininum_operating_num" @change="handleChange" :min=0 :max=100 :step=5 label="同时接待人数"></el-input-number>
        <br><br>
        <el-button class="btn-c" type="primary" @click="submitForm('form3')">确定</el-button>
      </el-form>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      company_id: JSON.parse(localStorage.getItem('company_id')),
      form1_url: '/setAccessType',
      form1: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        access_type: ''
      },
      form2_url: '/setRobotStatus',
      form2: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        robot_flag: ''
      },
      form3_url: '/setCompanyInfo',
      form3: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        distribution_type: '',
        customer_info_flag: '',
        mininum_operating_num: ''
      }
    }
  },
  methods: {
    handleChange(value) {
      console.log(value)
    },
    // 客服设置
    submitForm(formName) {
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.form3))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.form3_url,
        data: params
      }).then(res => {
        if (res.data === 'UpdateSuccess') {
          alert('success')
        } else {
          console.log('return message: ' + res.data)
          this.$message({
            message: JSON.stringify(res.data),
            type: 'error'
          })
        }
      })
    },
    // 接入设置
    jrfun() {
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.form1))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.form1_url,
        data: params
      }).then(res => {
        if (res.data === 'UpdateSuccess') {
          alert('success')
        } else {
          console.log(JSON.parse(localStorage.getItem('company_id')))
          this.$message({
            message: JSON.stringify(res.data),
            type: 'error'
          })
        }
      })
    },
    // 机器人设置
    rbfun() {
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.form2))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.form2_url,
        data: params
      }).then(res => {
        if (res.data === 'success') {
          alert('success')
        } else {
          console.log(JSON.parse(localStorage.getItem('company_id')))
          this.$message({
            message: JSON.stringify(res.data),
            type: 'error'
          })
        }
      })
    }
  }
}
</script>

<style>
#sets {
  width: 30%;
}
.btn-c {
  width: 100%;
}
.scard {
  margin-top: 10px;
  background-color: #e0dfdf;
}
</style>
