<template>
  <div id="lshh">
    <el-card class="box-card">
      <h3>历史会话列表</h3>
      <el-table :data="chat_past" border style="width: 100%">
        <el-table-column prop="customer" label="用户名" width="180">
        </el-table-column>
        <el-table-column prop="cs" label="客服名" width="180">
        </el-table-column>
        <el-table-column prop="start_time" label="开始时间">
        </el-table-column>
        <el-table-column prop="end_time" label="结束时间">
        </el-table-column>
        <el-table-column prop="score" label="评分">
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      smp_url: '/managerViewsHistoryConversation.action',
      smp_post: {
        company_id: JSON.parse(localStorage.getItem('company_id'))
      },
      chat_past: []
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      console.log('页面加载完成')
      console.log(JSON.stringify(this.company_id))
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.smp_post))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.smp_url,
        data: params
      }).then(res => {
        _this.chat_past = res.data
      })
    }
  }
}
</script>

