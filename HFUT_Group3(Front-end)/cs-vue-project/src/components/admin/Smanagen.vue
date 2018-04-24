<template>
  <div id="dqhh">
    <!-- 用户列表显示表格 -->
    <el-card class="box-card">
      <h3>当前会话列表</h3>
      <el-table :data="chat_now" border style="width: 100%">
        <el-table-column prop="customer_nickname" label="用户名" width="180">
        </el-table-column>
        <el-table-column prop="cs_nickName" label="客服名" width="180">
        </el-table-column>
        <el-table-column prop="start_time" label="开始时间" width="400">
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
      <div class="chatlog_body">
        <div class="chatlog_main">
          <ul>
            <template v-for="item in chat_now_list">
              <li>
                <div class="wordbody">
                  <div class="wordhead">{{ item.sender_nickname }}&nbsp;&nbsp;{{ item.time }}</div>
                  <div class="wordmain">
                    <p>{{ item.content }}</p>
                  </div>
                </div>
              </li>
            </template>
          </ul>
        </div>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      chatn_form_visible: false,
      smn_url: '/managerViewsCurrentConversation.action',
      query_chatn_list: '/managerViewsChatLog.action',
      query_chatn: {
        conversation_id: ''
      },
      smn_post: {
        company_id: JSON.parse(sessionStorage.getItem('company_id'))
      },
      chat_now: [
        // {
        //   customer: 'tom',
        //   cs: 'jerry',
        //   start_time: '2017-05-04 12:12'
        // },
        // {
        //   customer: 'jerry',
        //   cs: 'tom',
        //   start_time: '2017-05-04 12:12'
        // }
      ],
      chat_now_list: [
        // {
        //   chatLog_id: 1,
        //   name: '用户',
        //   time: '2015.04.03',
        //   content: '这是第1句话'
        // },
        // {
        //   chatLog_id: 2,
        //   name: '客服',
        //   time: '2015.04.03',
        //   content: '这是第2句话aaaaaaaaaaaaaaaaaaaa,aaaaaaa,a'
        // },
        // {
        //   chatLog_id: 3,
        //   name: '用户',
        //   time: '2015.04.03',
        //   content:
        //     '这是第3句话这是第4句话这是第4句话这是第4句话这是第4句话这是第4句话这是第4句话'
        // },
        // {
        //   chatLog_id: 4,
        //   name: '客服',
        //   time: '2015.04.03',
        //   content:
        //     '这是第4句话这是第4句话这是第4句话这是第4句话这是第4句话这是第4句话'
        // },
        // {
        //   chatLog_id: 5,
        //   name: '用户',
        //   time: '2015.04.03',
        //   content: '这是第5句话'
        // },
        // {
        //   chatLog_id: 5,
        //   name: '客服',
        //   time: '2015.04.03',
        //   content: '这是第6句话'
        // },
        // {
        //   chatLog_id: 7,
        //   name: '用户',
        //   time: '2015.04.03',
        //   content: '这是第7句话'
        // }
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
    handleEdit(index, row) {
      
      console.log("conversation_id:"+row.conversation_id)
      this.query_chatn.conversation_id = row.conversation_id
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.query_chatn))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.query_chatn_list,
        data: params
      }).then(res => {
        if(res.data.length !== 0){
          console.log('in this function')
          _this.chat_now_list = res.data
          console.log("length:"+_this.chat_now_list.length)
          _this.chatn_form_visible = true
        } else {
          this.$message({
            message: "暂无历史记录，请稍后查看",
            type: 'error'
          })
        }
      })
    }
  }
}
</script>

<style>
.chatlog_body {
  overflow: hidden;
  width: 450px;
}
.chatlog_main {
  overflow: auto;
  height: 500px;
  width: 500px;
  overflow-x: hidden;
}
.wordbody {
  width: 400px;
  margin: 10px;
}
.wordhead {
  width: 350px;
  font-size: 12px;
}
.wordmain {
  background-color: #f0f3f9;
  width: 240px;
  word-wrap: break-word;
  word-break: normal;
}
.wordmain p {
  color: black;
  font-size: 16px;
  padding: 5px;
}
</style>