<template>
  <div id="sets">
    <el-row :gutter="20">
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card>
            <div slot="header">接入方式</div>
            <el-switch style="display: block" v-model="value1" active-color="#13ce66" inactive-color="#ff4949" active-text="悬浮式" inactive-text="网页">
            </el-switch>
            <br><br>
            <el-button class="btn-c" type="primary">确定</el-button>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card>
            <div slot="header">机器人设置</div>
            <el-switch style="display: block" v-model="value2" active-color="#13ce66" inactive-color="#ff4949" active-text="开启" inactive-text="关闭">
            </el-switch><br><br>
            <el-button class="btn-c" type="primary">确定</el-button>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card>
            <div slot="header">客服设置</div>
            <el-form ref="form3" :model="form3">
              <h5>分配方式</h5>
              <el-switch style="display: block" v-model="form3.dstribution_type" active-color="#13ce66" inactive-color="#ff4949" active-text="老用户" active-value=1 inactive-text="轮流" inactive-value=0>
              </el-switch>
              <h5>用户信息展示</h5>
              <el-switch style="display: block" v-model="form3.customer_info_flag" active-color="#13ce66" inactive-color="#ff4949" active-text="开启" active-value=1 inactive-text="关闭" inactive-value=0>
              </el-switch>
              <h5>同时接待人数</h5>
              <el-input-number v-model="form3.mininum_operating_num" @change="handleChange" :min="1" :max="100" label="同时接待人数"></el-input-number>
              <br><br>
              <el-button class="btn-c" type="primary" @click="submitForm('form3')">确定</el-button>
            </el-form>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      company_id: 1,
      value1: true,
      value2: true,
      form3_url: '/setCompanyInfo',
      form3: {
        company_id: 1,
        dstribution_type: 1,
        customer_info_flag: 1,
        mininum_operating_num: 1
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
        if (res.data === 'success') {
          alert('success')
        } else {
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

<style scoped>
.btn-c {
  width: 100%;
}
</style>
