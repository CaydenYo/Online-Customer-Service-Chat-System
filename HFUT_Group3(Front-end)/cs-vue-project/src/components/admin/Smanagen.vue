<template>
  <div id="dqhh">
    <!-- 用户列表显示表格 -->
    <el-card class="box-card">
      <h3>当前会话列表</h3>
      <el-table :data="chat_now" border style="width: 100%">
        <el-table-column prop="customer" label="用户名" width="180">
        </el-table-column>
        <el-table-column prop="cs" label="客服名" width="180">
        </el-table-column>
        <el-table-column prop="start_time" label="开始时间" width="180">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope='scope'>
            <el-button size="mini" type="primary" @click="handleEdit(scope.$index,scope.row)" icon="el-icon-document">聊天记录</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 查看聊天记录对话框 -->
    <el-dialog title="聊天记录" :visible.sync="chatn_form_visible" center width="30%">
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chatn_form_visible: false,
      smn_url: '/managerViewsCurrentConversation.action',
      smn_post: {
        company_id: JSON.parse(localStorage.getItem('company_id'))
      },
      chat_now: [
        {
          customer: 'tom',
          cs: 'jerry',
          start_time: '2017-05-04 12:12'
        },
        {
          customer: 'jerry',
          cs: 'tom',
          start_time: '2017-05-04 12:12'
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
      params.append('data', JSON.stringify(this.smn_post))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.smn_url,
        data: params
      }).then(res => {
        _this.chat_now = res.data
      })
    },
    handleEdit() {
      this.chatn_form_visible = true
    }
  }
}
</script>

