<template>
  <div class="chat">
    <div id="capture" ref="capture" style="padding: 10px; background: #f5da55">
      <h4 style="color: #000; ">Hello world!</h4>
    </div>
    <!-- 评价 -->
    <transition name="el-zoom-in-top">
      <div class="rate" v-show="rate_visible">
        <div class="rateword1">
          请为客服打分
        </div>
        <div class="star">
          <el-rate v-model="rate_form.cs_score" :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
          </el-rate>
        </div>
        <div class="rateinput">
          <el-input type="text" v-model="rate_form.content"></el-input>
        </div>
        <div>
          <el-button class="ratebtn" type="primary" @click="submitRate" icon="el-icon-check"></el-button>
        </div>
      </div>
    </transition>
    <!-- 对话框界面 -->
    <transition name="el-zoom-in-bottom">
      <div class="show" v-show="show">
        <div class="header">
          <el-button type="primary" v-on:click="askForArtificialServices">人工服务</el-button>
          <span>{{cs_nickName}}正在为您服务</span>
          <img src="../../../static/images/close.png" alt="" @click="rate_visible=true">
        </div>
        <!-- 文本 -->
        <div class="message">
          <div class="ch
        atlog_body">
            <div class="chatlog_main" ref="chatlog">
              <!--机器人-->
              <ul v-if="robotFlag==true">
                 <li v-for="entry in robotChatting">
                   <div class="wordbody">
                     <div class="wordhead">{{ entry.nickename }}&nbsp;&nbsp;{{entry.date }}</div>
                     <div class="wordmain">
                       <p>{{ entry.content }}</p>
                     </div>
                   </div>
                 </li>
              </ul>
              <!--客服-->
              <ul v-if="robotFlag==false">
                <li v-for="entry in sessions">
                  <div class="wordbody">
                    <div class="wordhead">{{ entry.name }}&nbsp;&nbsp;{{ entry.date }}</div>
                    <div class="wordmain">
                      <p>{{ entry.content }}</p>
                    </div>
                  </div>
                </li>
              </ul>
            </div>
          </div>
        </div>
        <!-- 文本结束 -->
        <!-- 功能界面 -->
        <div class="tool">
          <el-button type="primary" circle @click="capture"> <img src="../../../static/images/emoji.png" alt="emoji"> </el-button>
          <input type="file" name="chatpicture" accept="image/jpeg,image/jpg,image/png" ref="chatpicture" style="display:none;" @change="onFileSelected">
          <el-button type="success" icon="el-icon-check" circle @click="$refs.chatpicture.click()"></el-button>
          <el-button type="warning" style="float:right" icon="el-icon-star-off" circle @click="submit()"></el-button>
        </div>
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
        <!-- 功能界面结束 -->
        <!-- 输入框 -->
        <div class="input">
          <el-input ref="textarea" type="textarea" :rows="5" placeholder="请输入内容" v-model="content" @keyup.enter.native="addMessage">
          </el-input>
        </div>
        <!-- 输入框结束 -->
      </div>
    </transition>
    <!-- 对话框结束 -->
    <!-- 点击聊天按钮 -->
    <div class="div1">
      <div class="div2">
        <img src="../../../static/images/xfchat.png" alt="">
      </div>
      <div class="div3">
        <el-button type="primary" round @click="showdialog()">咨询</el-button>
      </div>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
export default {
  data() {
    return {
      ratingUrl: '/csEvaluate.action',
      content: '',
      robotFlag: true,
      userItemId: null,
      websocket: null,
      receiverId: null,
      senderId: '2000',
      show: true,
      cs_nickName: 'zhangsan',
      rate_visible: false,
      selectedFile: '',
      rate_form: {
        cs_score: null,
        content: ''
      },
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
  created() {
    this.initWebSocket()
  },
  mounted() {
    this.downmessage()
    this.show = false
  },
  computed: mapState(['sessions', 'currentSessionId', 'robotChatting']),
  methods: {
    addEmoji(temp) {
        this.content += temp
    },
    askForArtificialServices: function(event) {
      this.robotFlag == false
      alert("开始转接人工服务。。。")
      this.robotFlag = false
      var obj = JSON.stringify({
            nickname: "yukang",
            senderId: this.senderId,
            receiverId: this.receiverId,
            companyName: "CISCO",
            companyId: "2",
            content: firstTimeSession.action,
            userItemId: this.userItemId
        })
        this.websocket.send(obj)
        this.content = '';
        alert("已完成转接人工服务函数")
    },
    downmessage() {
      console.log('已最下面')
      this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight
    },
    // 展示会话框
    showdialog() {
      this.show = !this.show
      this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight
      console.log('open dialog')
    },
    // 按回车发送信息
    addMessage(e) {
        if(e.keyCode === 13 && this.content.length) {
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
        alert("开始创建websocket")
        const wsurl = 'ws://localhost:8080/OCSSystem/serve'
        this.websocket = new WebSocket(wsurl);
        this.websocket.onmessage = this.websocketonmessage;
        this.websocket.onclose = this.websocketclose;
        // alert("准备执行加入等待队列")
        // this.$store.commit('addToRobotChatting',this.name)
        // alert("已执行")
    },
    websocketonmessage(e) {
        alert("客户接收到信息了！！！！")
        var receiverMsg = JSON.parse(e.data)
          if(this.robotFlag == true){
            if(receiverMsg.content instanceof Array) {
              var html = "";
              html += "<p>" + "您好我是机器人小机，请问您想问的是以下问题吗？" + "</p>";
              var result = receiverMsg.content;
              html += "<ol>";
              for(var i = 0;i < result.length;i++) {
                var obj = result[i];
                var question = obj.question;
                html += "<li>" + question + "</li>";
              }
              html += "</ol>";
              receiverMsg.content = html;
              this.$store.commit('addRobotMessage', receiverMsg)
            }
            else{
              this.$store.commit('addRobotMessage', receiverMsg)
            }
          } else if(receiverMsg.content == "csEvaluate") {
            this.rateVisible = true
          }else {
              if(this.senderId == receiverMsg.senderId) {
                this.receiverId = receiverMsg.receiverId
              }else {
                this.receiverId = receiverMsg.senderId
              }
              alert("客户的receiverId"+this.receiverId)
              this.userItemId = receiverMsg.userItemId
            this.$store.commit('addClientMessage', receiverMsg);  
          }
    },
    websocketsend(e) {
        if(this.robotFlag == true) {
          this.content = "robotAnwser" + this.content
        }
        var obj = JSON.stringify({
            nickname: "yukang",
            senderId: this.senderId,
            receiverId: this.receiverId,
            companyName: "CISCO",
            companyId: "2",
            content: this.content,
            userItemId: this.userItemId
        })
        this.websocket.send(obj)
        this.content = '';
    },
    websocketclose(e) {

    },
    // 发送函数
    // submit() {
    //   if (this.textarea === '') {
    //     this.$message({
    //       message: '文字不能为空',
    //       type: 'warning'
    //     })
    //   } else {
    //     console.log('长度:' + this.textarea.length)
    //     console.log(this.textarea)
    //     var d = new Date()
    //     var new_message = {
    //       chatLog_id: 1,
    //       name: '用户',
    //       time: d.getHours() + ' : ' + d.getMinutes(),
    //       content: this.textarea
    //     }
    //     this.chat_past_list.push(new_message)
    //     setTimeout(
    //       () =>
    //         (this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight),
    //       50
    //     )
    //     this.textarea = ''
    //   }
    // },
    // 提交评价
    submitRate() {
      if (this.rate_form.cs_score === null || this.rate_form.content === '') {
        this.$message({
          message: '请填写星级和评价',
          type: 'warning',
          center: true
        })
      } else {
        this.rate_visible = false
        this.show = false
        this.$message({
          message: '感谢您的使用',
          type: 'success',
          center: true
        })
        // 提交函数
        this.rateVisible = false
        let _this = this
        var params = new URLSearchParams();
        params.append('data', JSON.stringify({
        cs_id: this.receiverId,
        cs_score: this.rate_form.cs_score,
        content: this.rate_form.content
        }));
        this.$axios({
          method: 'post',
          url: this.rootUrl + _this.ratingUrl,
          data: params
        })
      }
    },
    onFileSelected(event) {
      console.log(event)
      this.selectedFile = event.target.files[0]
      var file = event.target.files[0]
    }
  }
}
</script>

<style>
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
.div1 {
  position: absolute;
  right: 2%;
  bottom: 10%;
  color: #ffffff;
}
.div2 {
  width: 50px;
  height: 50px;
  margin-bottom: 5px;
  padding-left: 10px;
}
.div2 img {
  width: 50px;
  height: auto;
}
.show {
  position: absolute;
  right: 1%;
  bottom: 15%;
  overflow: hidden;
  width: 400px;
  height: 550px;
  z-index: 100;
}
.header {
  background-color: #409eff;
  height: 50px;
  text-align: center;
}
.header span {
  position: absolute;
  top: 10px;
  left: 20px;
}
.header img {
  position: absolute;
  top: 5px;
  right: 8px;
  width: 23px;
  height: auto;
}
.message {
  height: 350px;
}
.chatlog_body {
  overflow: hidden;
  width: 400px;
}
.chatlog_main {
  overflow: auto;
  height: 350px;
  width: 450px;
  overflow-x: hidden;
  background-color: #e0e0e0;
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
  min-width: 30px;
  width: fit-content;
  max-width: 240px;
  word-wrap: break-word;
  word-break: normal;
}
.wordmain p {
  background-color: #fff;
  color: black;
  font-size: 16px;
  padding: 5px;
  border-radius: 7px;
  border: 1px solid #bfbfbf;
}
.tool {
  height: 40px;
  background-color: darkgray;
  padding: 0px 5px 0px 5px;
}
.tool img {
  width: 12px;
  height: auto;
}
.input {
  height: 100px;
  background-color: darkgray;
}
li {
  list-style: none;
}
ul {
  padding-left: 0px;
}
.rate {
  position: absolute;
  bottom: 40%;
  right: 7%;
  width: 200px;
  height: 200px;
  background-color: rgb(224, 224, 245);
  z-index: 999999;
  padding: 10px;
  -moz-box-shadow: 1px 1px 5px #333333;
  -webkit-box-shadow: 1px 1px 5px #333333;
  box-shadow: 1px 1px 5px #333333;
}
.rateword1 {
  margin: 10px 0px 15px 0px;
  font-size: 16px;
  text-align: center;
}
.star {
  margin-left: 40px;
  margin-bottom: 15px;
}
.rateinput {
  margin: 5px, 5px, 10px, 5px;
}
.ratebtn {
  margin: 10px;
  width: 90%;
}
</style>


