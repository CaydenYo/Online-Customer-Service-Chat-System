<template>
  <div id="lshh">
    <!-- 历史会话显示表格 -->
    <el-card class="box-card">
      <h3>历史会话列表</h3>
      <el-table :data="chat_past" border style="width: 100%">
        <el-table-column prop="customer_nickname" label="用户名" width="180">
        </el-table-column>
        <el-table-column prop="cs_nickName" label="客服名" width="180">
        </el-table-column>
        <el-table-column prop="start_time" label="开始时间">
        </el-table-column>
        <el-table-column prop="end_time" label="结束时间">
        </el-table-column>
        <el-table-column prop="score" label="评分">
          <template slot-scope='scope'>
            <el-rate disabled show-score text-color="#ff9900" score-template="{value}" v-model="scope.row.cs_score" :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
            </el-rate>
          </template>
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
      <div class="chatlog_body">
        <div class="chatlog_main">
          <ul>
            <!-- <template v-for="item in chat_past_list">
                <li>{{ item.name }}</br>{{ item.time }}</br>{{ item.content }}</br>
                  </br>
                </li>
              </template> -->
            <template v-for="item in chat_past_list">
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
      chatp_form_visible: false,
      smp_url: '/managerViewsHistoryConversation.action',
      query_chatp_list: '/managerViewsChatLog.action',
      query_chatp: {
        conversation_id: ''
      },
      smp_post: {
        company_id: JSON.parse(sessionStorage.getItem('company_id'))
      },
      chat_past: [
        // {
        //   customer_nickname: '张三',
        //   cs_nickName: '李四',
        //   start_time: '2017-04-06',
        //   end_time: '2017-04-07',
        //   score: '5'
        // }
      ],
      chat_past_list: [
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
      console.log(row.conversation_id)
      this.query_chatp.conversation_id = row.conversation_id
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.query_chatp))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.query_chatp_list,
        data: params
      }).then(res => {
        console.log('in this function')
        _this.chat_past_list = res.data
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
