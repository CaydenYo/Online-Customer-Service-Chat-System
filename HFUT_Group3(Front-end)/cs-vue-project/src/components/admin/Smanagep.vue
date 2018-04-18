<template>
  <div id="lshh">
    <!-- 历史会话显示表格 -->
    <el-card class="box-card">
      <h3>历史会话列表</h3>
      <el-table :data="chat_past" border style="width: 100%">
        <el-table-column prop="customer_id" label="用户名" width="180">
        </el-table-column>
        <el-table-column prop="cs_id" label="客服名" width="180">
        </el-table-column>
        <el-table-column prop="start_time" label="开始时间">
        </el-table-column>
        <el-table-column prop="end_time" label="结束时间">
        </el-table-column>
        <el-table-column prop="score" label="评分">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope='scope'>
            <el-button size="mini" type="primary" @click="handleEdit(scope.$index,scope.row)" icon="el-icon-document">历史记录</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 查看聊天历史记录对话框 -->
    <el-dialog title="历史记录" :visible.sync="chatp_form_visible" center width="30%">
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chatp_form_visible: false,
      smp_url: '/managerViewsHistoryConversation.action',
      smp_post: {
        company_id: JSON.parse(localStorage.getItem('company_id'))
      },
      chat_past: [
        {
          customer_id: '1',
          cs_id: '2',
          start_time: '2017-04-06',
          end_time: '2017-04-07',
          score: '5'
        }
      ]
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
    },
    handleEdit(index, row) {
      this.chatp_form_visible = true
    }
  }
}
</script>

