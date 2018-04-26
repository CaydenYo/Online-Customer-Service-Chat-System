<template>
  <div id="cs_dialogue">
    <el-row>
      <el-col :span="5">
        <div class="grid-content">
          <el-tabs type="border-card" @tab-click="addToWaitings">
            <el-tab-pane label="当前会话">
              <el-container class="dialogue">
                <el-header>
                  <el-button v-on:click="addItem" icon="el-icon-circle-plus">接入新会话</el-button>
                </el-header>
                <el-main>
                  <div id="list">
                    <ul>
                      <li v-for="item in sessions" :class="{ active: item.id === currentSessionId }" v-if="item.serving" v-on:click="changeCurrentSessionId(item.id)">
                        <!--   :class="[item.id === currentSessionId ? 'active':'']" -->
                        <img class="avatar" :src="item.user.img">
                        <p class="name">{{item.user.name}}</p>
                        <el-button icon="el-icon-phone-outline" @click="switchVisible = true" circle></el-button>
                      </li>
                    </ul>
                  </div>
                </el-main>
              </el-container>
            </el-tab-pane>
            <el-tab-pane label="排队列表">
              <el-container class="dialogue">
                <el-main>
                  <div id="list">
                    <ul>
                      <li v-for="(item, index) in sessions" v-if="item.waiting" v-on:click="changeToServing(item.id)">
                        <!--   :class="[item.id === currentSessionId ? 'active':'']" -->
                        <img class="avatar" :src="item.user.img">
                        <p class="name">{{item.user.name}}</p>
                        <i class="el-icon-circle-plus-outline el-icon--right"></i>
                      </li>
                    </ul>
                  </div>
                </el-main>
              </el-container>
            </el-tab-pane>
          </el-tabs>     
        </div>
        <el-dialog
        title="提示"
        :visible.sync="switchVisible"
        RepeatColumns="1"
        width="30%">
          <el-table
          :data="onlineCSList"
          style="width: 100%"
          max-height="250">
            <el-table-column
            fixed
            prop="cs_name"
            label="名字"
            width="150">
            </el-table-column>
            <el-table-column
            prop="cs_id"
            label="客服编号"
            width="120">
            </el-table-column>
            <el-table-column
            fixed="right"
            label="操作"
            width="120">
              <template slot-scope="scope">
                <el-button
                @click.native.prevent="changeCS(scope.$index, onlineCSList)"
                type="text"
                size="small">
                转接
                </el-button>
              </template>
            </el-table-column>
          </el-table>
        </el-dialog>
    </el-col>
<el-col :span="9">
  <div class="grid-content bg-purple-light">
    <div>
      <el-button-group>
        <el-button type="primary" icon="el-icon-search" v-on:click="lookForHistory">历史消息</el-button>
        <el-button type="primary" icon="el-icon-circle-close" v-on:click="closeConversation">结束会话</el-button>
      </el-button-group>
    </div>
    <message></message>
    <div>
      <el-popover
      ref="popover"
      placement="top-start"
      trigger="click">
        <div class="emoji-box">
          <div v-for="temp in emojiTemp" class="emoji">
            <a class="emoji-link" href="javascript:void(0)" v-on:click="addEmoji(temp)">{{temp}}</a>
          </div>
        <span class="pop-arrow arrow"></span>
        </div>
      </el-popover>
      <i class="icon iconfont icon-face" v-popover:popover></i>
    </div>
    <div id="uesrtext">
      <textarea placeholder="按 Ctrl + Enter 发送" v-model="content" v-on:keyup="addMessage"></textarea>
    </div>
  </div>
</el-col>
<el-col :span="5">
  <div class="grid-content">
    <el-tabs type="border-card" v-model="activeName">
      <el-tab-pane label="用户资料"  name="first">
        <img src="../../../static/images/c_def.jpg" alt="" class="show_head_img">
        <div>
          <div>
            <span>名称：{{ customer_nickname }}</span>
          </div>
          <div>
            <span>年龄：{{ customer_age }}</span>
          </div>
          <div>
            <span>电子邮件：{{ customer_email }}</span>
          </div>
          <div>
            <span>住址：{{ customer_address }}</span>
          </div>
        </div>
      </el-tab-pane>
      <!-- <el-tab-pane label="知识库">配置管理</el-tab-pane> -->
    </el-tabs>
  </div>
</el-col>
<el-col :span="5">
  <div class="grid-content">
    <el-tabs type="border-card">
      <el-tab-pane label="常用语">
        <div v-for="o in 4" :key="o" class="text item">
          {{'常用语 ' + o }}
        </div>
      </el-tab-pane>
      <el-tab-pane label="聊天记录">配置管理</el-tab-pane>
    </el-tabs>
  </div>
</el-col>
</el-row>
</div>
</template>


<script>
import { mapState } from 'vuex'
import message from '../CS_embeddedChat/message'
import usertext from '../CS_embeddedChat/usertext'

export default {
  name: 'list',
  data() {
    return {
      showWaitingQueueUrl: '/customerService/showWaitingQueue',
      switchVisible: false,
      activeName: 'first',
      customer_nickname: 'test',
      customer_age: 15,
      customer_sex: 1,
      customer_email: '123@qq.com',
      customer_address: '上海市普陀区金沙江路 1518 弄',
      cs_id: this.$store.state.customerServiceMessage.senderId,
      companyId: this.$store.state.customerServiceMessage.company_Id,
      nickname: this.$store.state.customerServiceMessage.nickname,
      CS_id: {
        cs_id: this.$store.state.customerServiceMessage.senderId
      },
      content: '',
      websocket: null,
      onlineCSList: [{
        cs_name: 'kefu2',
        cs_id: '1001'
      }],
      emojiTemp: [
        "😀",
        "😁",
        "😂",
        "😃",
        "😄",
        "😅",
        "😆",
        "😇",
        "😈",
        "😉",
        "😊",
        "😋",
        "😌",
        "😀",
        "😁",
        "😂",
        "😃",
        "😄",
        "😅",
        "😆",
        "😇",
        "😈",
        "😉",
        "😊",
        "😋",
        "😌",
        "😍",
        "😎",
        "😏",
        "😐",
        "😑",
        "😒",
        "😓",
        "😔",
        "😕",
        "😖",
        "😗",
        "😘",
        "😙",
        "😚",
        "😛",
        "😜",
        "😝",
        "😞",
        "😟",
        "😠",
        "😡",
        "😢",
        "😣",
        "😤",
        "😥",
        "😦",
        "😧",
        "😨",
        "😩",
        "😪",
        "😫",
        "😬",
        "😭",
        "😮",
        "😯",
        "😰",
        "😱",
        "😲",
        "😳",
        "😴",
        "😵",
        "😶",
        "😷",
        "😸",
        "😹",
        "😺",
        "😻",
        "😼",
        "😽",
        "😾",
        "😿",
        "🙀",
        "🙅",
        "🙆",
        "🙇",
        "🙈",
        "🙉",
        "🙊",
        "🙋",
        "🙌",
        "🙍",
        "🙎",
        "🙏",
        "🚀",
        "🚁",
        "🚂",
        "🚃",
        "🚄",
        "🚅",
        "🚆",
        "🚇",
        "🚈",
        "🚉",
        "🚊",
        "🚋",
        "🚌",
        "🚍",
        "🚎",
        "🚏",
        "🚐",
        "🚑",
        "🚒",
        "🚓",
        "🚔",
        "🚕",
        "🚖",
        "🚗",
        "🚘",
        "🚙",
        "🚚",
        "🚛",
        "🚜",
        "🚝",
        "🚞",
        "🚟",
        "🚠",
        "🚡",
        "🚢",
        "🚣",
        "🚤",
        "🚥",
        "🚦",
        "🚧",
        "🚨",
        "🚩",
        "🚪",
        "🚫",
        "🚬",
        "🚭",
        "🚮",
        "🚯",
        "🚰",
        "🚱",
        "🚲",
        "🚳",
        "🚴",
        "🚵",
        "🚶",
        "🚷",
        "🚸",
        "🚹",
        "🚺",
        "🚻",
        "🚼",
        "🚽",
        "🚾",
        "🚿",
        "🛀",
        "🛁",
        "🛂",
        "🛃",
        "🛄",
        "🛅",
    ]
    }
  },
  components: {
    message,
    usertext
  },
  created() {
    this.initWebSocket()
  },
  computed: mapState(['sessions', 'currentSessionId', 'customerServiceMessage']),
  methods: {
    addEmoji(temp) {
        this.content += temp
    },
    //客服聊天功能
    addMessage(e) {
      if(e.ctrlKey && e.keyCode === 13 && this.content.length) {
        if(this.websocket.readyState === this.websocket.OPEN) {
          this.websocketsend(this.content)
        }else if(this.websocket.readyState === this.websocket.CONNECTING) {
          let that = this;
          setTimeout(function() {
            that.websocketsend(this.content)
          }, 300)
        }else {
          this.initWebSocket();
          let that = this;
          setTimeout(function() {
            that.websocketsend(this.content)
          },500)
        }
      }
    },
    initWebSocket() {
      const wsurl = 'ws://localhost:8080/OCSSystem/serve'
      this.websocket = new WebSocket(wsurl);
      this.websocket.onmessage = this.websocketonmessage;
      this.websocket.onclose = this.websocketclose;
    },
    websocketonmessage(e) {
      var receiverMsg = JSON.parse(e.data)
      alert('客服收到的消息'+JSON.stringify(receiverMsg))
      if(receiverMsg.receiverId !== null){
        alert('客户在服务列表中的id'+receiverMsg.userItemId)
        this.$store.commit('addMessage', {
          msg: receiverMsg,
          itemId: receiverMsg.userItemId
        });
      }
    },
    websocketsend(e) {
      alert("客服要发送的客户id"+this.sessions[this.currentSessionId - 1].user.customer_id)
      var obj = JSON.stringify({
        nickname: this.nickname,
        senderId: this.cs_id,
        receiverId: this.sessions[this.currentSessionId - 1].user.customer_id,
        companyName: "CISCO",
        companyId: "2",
        content: this.content,
        userItemId: this.currentSessionId
      })
      this.websocket.send(obj)
      this.content = '';
    },
    websocketclose(e) {

    },
    onEditorChange({ editor, html, text}) {
      this.content = html;
    },
    //排队转接功能
    addToWaitings: function(event) {
      alert(this.cs_id + " " + this.companyId + " " + this.nickname + " " + this.CS_id.cs_id)
      alert(this.CS_id.cs_id)
      this.$store.commit('clearWaitings')
      let _this = this
      var params = new URLSearchParams();
      params.append('data', JSON.stringify(this.CS_id));
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.showWaitingQueueUrl,
        data: params
      }).then((res)=>{
        var waitingList = res.data;
        for(var i=0;i < waitingList.length;i++){
          this.$store.commit('addToWaitings', waitingList[i])
        }
      })
    },
    changeCurrentSessionId: function(id) {
      alert("目前的sessionId为" + id)
      this.$store.commit('changeCurrentSessionId', id)
    },
    changeToServing: function(id) {
      alert(id)
      this.$store.commit('changeToServing', id)
    },
    addItem: function(){
      this.$store.commit('addItem')
    },
    //客服转接功能
    changeCS(index, rows) {
      alert(rows[index].cs_name + " " + rows[index].cs_id + " " + this.sessions[this.currentSessionId - 1].user.customer_id)
      var obj = JSON.stringify({
        nickname: this.nickname,
        senderId: this.cs_id,
        receiverId: this.sessions[this.currentSessionId - 1].user.customer_id,
        companyName: "CISCO",
        companyId: "2",
        content: "sessionTransfer",
        newCsId: rows[index].cs_id,
        userItemId: this.currentSessionId,
        servedClient: {
          name: this.sessions[this.currentSessionId - 1].user.name,
          img: this.sessions[this.currentSessionId - 1].user.img
        }
      })
      this.websocket.send(obj)
      this.$store.commit('finishedChangeCS',this.currentSessionId)
    },
    //查看历史消息
    lookForHistory: function(event) {
      this.$store.commit('clearMessages', this.currentSessionId)
      alert("查看历史消息中……")
        var obj = JSON.stringify({
        nickname: this.nickname,
        senderId: this.cs_id,
        receiverId: this.sessions[this.currentSessionId - 1].user.customer_id,
        companyName: "CISCO",
        companyId: "2",
        content: 'csViewsHistoryMessage.action',
        userItemId: this.currentSessionId
      })
      this.websocket.send(obj)
      this.content = '';
    },
    closeConversation: function(event) {
      alert("关闭会话")
      var obj = JSON.stringify({
        nickname: this.nickname,
        senderId: this.cs_id,
        receiverId: this.sessions[this.currentSessionId - 1].user.customer_id,
        companyName: "CISCO",
        companyId: "2",
        content: 'closeSession.action',
        userItemId: this.currentSessionId
      })
      this.websocket.send(obj)
      this.content = '';
      this.$store.commit('closeConversation',this.currentSessionId)
    }
  }
}
</script>

<style lang="scss" scoped>
.emoji-box {
  max-height: 200px;
  max-width:200px;
}
.message-frame {
  height: 100%;
  width: 100%;
}
.emoji-link {
  text-decoration:none;
}
.emoji {
  float: left;
}
#uesrtext {
  bottom: 0;
  right: 0;
  width: 100%;
  height: 100%;
  border-top: solid 1px #DDD;
  > textarea {
    width: 100%;
    height: 100%;
    border: none;
    outline: none;
  }
}
#list {
  ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
  }
  li {
    padding: 12px 15px;
    border-bottom: 1px solid #e6e6e6;
    cursor: pointer;
    &:hover {
      background-color: rgba(255, 255, 255, 0.03);
    }
  }
  li.active {
    /*注意这个是.不是冒号:*/
    background-color: rgba(255, 255, 255, 0.1);
  }
  .avatar {
    border-radius: 2px;
    width: 30px;
    height: 30px;
    float:left;
  }
  .name {
    display: inline-block;
    margin-left: 15px;
  }
}
.dialogue {
  height: 100%;
}
.el-tabs {
  height: 100%;
}
.el-row {
  height: 100%;
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
  height: 100%;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  height: 100%;
  overflow: hidden;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
#cs_dialogue {
  height: 100%;
}
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}
.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
}
body > .el-container {
  margin-bottom: 40px;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}
.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.text {
  font-size: 14px;
}
.item {
  padding: 18px 0;
}
.show_head_img {
  border-radius: 100%;
  width: 100px;
  margin: 50px 10px 50px 70px;
}
</style>