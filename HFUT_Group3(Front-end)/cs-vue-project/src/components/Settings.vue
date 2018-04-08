<template>
  <div id="sets">
    <el-card class="scard">
      <div slot="header">接入方式</div>
      <el-switch @change="jrfun" v-model="value1" active-color="#13ce66" inactive-color="#409eff" active-text="悬浮式" inactive-text="网页">
      </el-switch>
      <!-- <br><br>
      <el-button class="btn-c" type="primary">确定</el-button> -->
    </el-card>
    <el-card class="scard">
      <div slot="header">机器人设置</div>
      <el-switch @change="rbfun" v-model="form2.robot_flag" active-color="#13ce66" inactive-color="#ff4949" active-text="开启" inactive-text="关闭">
      </el-switch>
      <!-- <br><br>
      <el-button class="btn-c" type="primary">确定</el-button> -->
    </el-card>
    <el-card class="scard">
      <div slot="header">客服设置</div>
      <el-form ref="form3" :model="form3">
        <h5>分配方式</h5>
        <el-switch v-model="form3.dstribution_type" active-color="#13ce66" inactive-color="#409eff" active-text="老用户" active-value=1 inactive-text="轮流" inactive-value=0>
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
      value1: '',
      form2_url: '/setRobotStatus',
      form2: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        robot_flag: ''
      },
      form3_url: '/setCompanyInfo',
      form3: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        dstribution_type: 1,
        customer_info_flag: 1,
        mininum_operating_num: 0
      }
    }
  },
  methods: {
    handleChange(value) {
      console.log(value)
    },
    submitForm(formName) {
      alert(JSON.stringify(this.form3))
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
    jrfun() {
      console.log(val)
    },
    rbfun() {
      console.log(this.form2.robot_flag)
      var params = new URLSearchParams()
      let _this = this
      alert(JSON.stringify(this.form2))
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
