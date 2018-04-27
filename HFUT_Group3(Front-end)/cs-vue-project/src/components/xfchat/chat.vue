<template>
  <div class="chat">
    <!-- 评价 -->
    <transition name="el-zoom-in-top">
      <div class="rate" v-show="rateVisible">
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
          <el-button class="ratebtn" type="primary" @click="finishedRating" icon="el-icon-check"></el-button>
        </div>
      </div>
    </transition>
    <!-- 对话框界面 -->
    <transition name="el-zoom-in-bottom">
      <div class="show" v-show="show">
        <div class="header">
          <span>{{cs_nickName}}正在为您服务</span>
          <img src="../../../static/images/close.png" @click="show = false" alt="">
          <el-button type="primary" v-on:click="askForArtificialServices">人工服务</el-button>
        </div>
        <!-- 文本 -->
        <div class="message">
          <div v-loading="loading" class="chatlog_body">
            <div class="chatlog_main" ref="chatlog">
              <ul v-if="robotFlag==true">
                <template>
                  <li v-for="entry in robotChatting">
                    <!-- 机器人 -->
                    <template v-if="entry.self==false">
                      <div class="wordbody">
                        <div class="wordhead"><img class="imgavatar" src="../../../static/images/cs_def.png">&nbsp;&nbsp;{{ entry.name }}&nbsp;&nbsp;{{ entry.date }}</div>
                        <div class="wordmain">
                          <p v-if="entry.content.length < 100" v-html="entry.content"></p>
                          <template v-if="entry.content.length > 100">
                            <a :href="entry.content" download="screenshot.png" title="点我下载"><img class="imgcontent" :src="entry.content" /></a>
                            <p></p>
                          </template>
                        </div>
                      </div>
                    </template>
                    <!-- 用户 -->
                    <template v-if="entry.self">
                      <div class="wordbody">
                        <div class="wordhead wordheadu">&nbsp;&nbsp;{{ entry.date }}&nbsp;&nbsp;{{ entry.name }}&nbsp;&nbsp;<img class="imgavatar" src="../../../static/images/c_def.jpg"></div>
                        <div class="wordmain wordmainu">
                          <p class="userp"  v-if="entry.content.length < 100" v-html="entry.content"></p>
                          <template v-if="entry.content.length > 100">
                            <a :href="entry.content" download="screenshot.png" title="点我下载"><img class="imgcontent" :src="entry.content" /></a>
                          </template>
                        </div>
                        <div class="clear"></div>
                      </div>
                    </template>
                  </li>
                </template>
              </ul>
              <ul v-if="robotFlag==false">
                <template>
                  <li  v-for="entry in sessions">
                    <!-- 机器人 -->
                    <template v-if="entry.self==false">
                      <div class="wordbody">
                        <div class="wordhead"><img class="imgavatar" src="../../../static/images/cs_def.png">&nbsp;&nbsp;{{ entry.name }}&nbsp;&nbsp;{{ entry.date }}</div>
                        <div class="wordmain">
                          <p v-if="entry.content.length < 100" v-html="entry.content"></p>
                          <template v-if="entry.content.length > 100">
                            <a :href="entry.content" download="screenshot.png" title="点我下载"><img class="imgcontent" :src="entry.content" /></a>
                            <p></p>
                          </template>
                        </div>
                      </div>
                    </template>
                    <!-- 用户 -->
                    <template v-if="entry.self">
                      <div class="wordbody">
                        <div class="wordhead wordheadu">&nbsp;&nbsp;{{ entry.date }}&nbsp;&nbsp;{{ entry.name }}&nbsp;&nbsp;<img class="imgavatar" src="../../../static/images/c_def.jpg"></div>
                        <div class="wordmain wordmainu">
                          <p class="userp"  v-if="entry.content.length < 100" v-html="entry.content"></p>
                          <template v-if="entry.content.length > 100">
                            <a :href="entry.content" download="screenshot.png" title="点我下载"><img class="imgcontent" :src="entry.content" /></a>
                          </template>
                        </div>
                        <div class="clear"></div>
                      </div>
                    </template>
                  </li>
                </template>
              </ul>
            </div>
          </div>
        </div>
        <!-- 文本结束 -->
        <!-- 功能界面 -->
        <div class="tool">
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
          <el-button type="primary" circle>
            <i class="icon iconfont icon-face" v-popover:popover></i>
          </el-button>
          <input type="file" name="chatpicture" accept="image/jpeg,image/jpg,image/png" ref="chatpicture" class="imginput" @change="onFileSelected">
          <el-button type="success" icon="el-icon-picture-outline" circle @click="$refs.chatpicture.click()"></el-button>
          <!-- 使用空标签阻止相邻el-button触发特殊css -->
          <span></span>
          <el-button type="danger" icon="el-icon-edit-outline" circle @click="printscreen()"></el-button>
          <el-button type="warning" class="subbutton" icon="el-icon-star-off" circle @click="submit()"></el-button>
        </div>
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
import html2canvas from 'html2canvas'
export default {
  data() {
    return {
      file: null,
      filesubmit: '',
      loading: false,
      show: true,
      cs_nickName: '客服MM',
      rateVisible: false,
      selectedFile: '',
      rate_form: {
        cs_score: null,
        content: ''
      },
      hasWebsocket: false,
      ratingUrl: '/csEvaluate.action',
      assessment: '',
      nickname: this.$store.state.clientMessage.nickname,
      customer_id: this.$store.state.clientMessage.senderId,
      img: this.$store.state.clientMessage.img,
      robotFlag: true,
      userItemId: null,
      content:'',
      websocket: null,
      receiverId: null,
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
  mounted() {
    this.downmessage()
    this.show = false
  },
  computed: mapState(['sessions', 'currentSessionId', 'robotChatting', 'clientMessage']),
  directives: {
    'scroll-bottom' (el) {
      //console.log(el.scrollTop);
      setTimeout(function () {
        el.scrollTop+=9999;
    },1)
  }
},
  methods: {
    //完成评价
    finishedRating: function(event) {
      this.rateVisible = false
      let _this = this
      var params = new URLSearchParams();
      params.append('data', JSON.stringify({
        cs_id: this.receiverId,
        cs_score: this.rate_form.cs_score,
        content: this.rate_form.content,
      }));
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.ratingUrl,
        data: params
      })
    },
    addEmoji(temp) {
        this.content += temp
    },
    printscreen() {
      console.log('in printscreen function')
      this.loading = true
      this.$notify({
        type: 'info',
        title: '消息',
        message: '正在截图中...请耐心等待',
        showClose: false
      })
      html2canvas(document.querySelector('body')).then(canvas => {
        // 将截图添加至页面显示
        // document.getElementById("test ").appendChild(canvas)；

        // 尝试导入<a>标签进行操作
        // 把截取到的图片替换到a标签的路径下载
        // $('#download').attr('href', canvas.toDataURL())
        // 下载下来的图片名字
        // $('#download').attr('download', 'share.png')
        this.filesubmit = canvas.toDataURL()
        this.loading = false
        this.$notify({
          type: 'success',
          title: '成功',
          message: '您已成功上传图片，请点击发送按钮',
          showClose: false
        })
      })
    },
    onFileSelected(event) {
      console.log(event)
      this.file = event.target.files[0]
      console.log('file已赋值')
      var reader = new FileReader()
      var that = this
      reader.readAsDataURL(this.file)
      reader.onload = function(event) {
        console.log('this.result:' + this.result)
        that.filesubmit = this.result
        that.$notify({
          type: 'success',
          title: '成功',
          message: '您已成功上传图片，请点击发送按钮',
          showClose: false
        })
      }
    },
    downmessage() {
      console.log('已最下面')
      this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight+10
    },
    // 展示会话框
    showdialog() {
      this.nickname=this.$store.state.clientMessage.nickname,
      this.customer_id=this.$store.state.clientMessage.senderId,
      this.img=this.$store.state.clientMessage.img,
      alert(this.customer_id)
      if (this.customer_id !== "" && !this.hasWebsocket) {
        this.initWebSocket()
        this.hasWebsocket = true
      }
      this.show = !this.show
      this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight-10
      console.log('open dialog')
    },
    askForArtificialServices: function(event) {
      alert("开始转接人工服务。。。")
      alert(this.customer_id+" "+this.nickname+" "+this.img)
      this.robotFlag = false
      var obj = JSON.stringify({
            nickname: this.nickname,
            senderId: this.customer_id,
            receiverId: this.receiverId,
            companyName: "CISCO",
            companyId: "2",
            content: "firstTimeSession.action",
            userItemId: this.userItemId
        })
        this.websocket.send(obj)
        this.content = '';
    },
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
      alert("开始创建websocket")
        const wsurl = 'ws://localhost:8080/OCSSystem/serve'
        this.websocket = new WebSocket(wsurl);
        this.websocket.onmessage = this.websocketonmessage;
        this.websocket.onclose = this.websocketclose;
        //alert("准备执行加入等待队列")
        //this.$store.commit('addToRobotChatting',this.name)
        //alert("已执行")
    },
    websocketonmessage(e) {
        alert("客户接收到信息了！！！！")
        var receiverMsg = JSON.parse(e.data)
          if(this.robotFlag == true){
            alert("进入了机器人！")
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
              //alert("sessionStorage的senderId的类型是否为int: "+isInteger(this.senderId))
              //alert("收回来的的senderId的类型是否为int: "+isInteger(receiverMsg.senderId))
              if(receiverMsg.isSelf) {
                this.$store.commit('saveClientReveiverId', receiverMsg.receiverId)
                alert("接收到自己的消息后, receiverId为" + this.$store.state.clientMessage.receiverId)
                this.receiverId = this.$store.state.clientMessage.receiverId
              }else {
                this.$store.commit('saveClientReveiverId', receiverMsg.senderId)
                alert("接收到别人的消息后, receiverId为" + this.$store.state.clientMessage.receiverId)
                this.receiverId = this.$store.state.clientMessage.receiverId
              }
              this.userItemId = receiverMsg.userItemId
            this.$store.commit('addClientMessage', receiverMsg);
            alert("客户的receiverId"+this.receiverId)
          }
            setTimeout(
              () => (this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight),
              50)
    },
    websocketsend(e) {
        alert("客户的receiverId：" + this.receiverId)
        if(this.robotFlag == true) {
          this.content = "robotAnwser" + this.content
        }
        var obj = JSON.stringify({
            nickname: this.nickname,
            senderId: this.customer_id,
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
    submit() {
      // if (this.textarea === '') {
      //   this.$message({
      //     message: '文字不能为空',
      //     type: 'warning'
      //   })
      // } else {
      console.log('长度:' + this.textarea.length)
      console.log(this.textarea)
      var d = new Date()
      var new_message
      if (this.textarea !== '') {
        console.log('有文字')
        new_message = {
          chatLog_id: 1,
          name: '用户',
          time: d.getHours() + ' : ' + d.getMinutes(),
          content: this.textarea
        }
      } else {
        new_message = {
          chatLog_id: 1,
          name: '用户',
          time: d.getHours() + ' : ' + d.getMinutes(),
          content: this.filesubmit
        }
      }
      this.chat_past_list.push(new_message)
      setTimeout(
        () => (this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight),
        50
      )
      this.textarea = ''
    },
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
.imgcontent {
  max-height: 200px;
  max-width: 200px;
  width: auto;
  border-radius: 4px;
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
  bottom: 20%;
  overflow: hidden;
  width: 350px;
  height: 550px;
  z-index: 100;
  border-radius: 10px;
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
  width: 350px;
}
.chatlog_main {
  overflow: auto;
  height: 350px;
  width: 400px;
  overflow-x: hidden;
  background-color: #fcf9f9;
}
.wordbody {
  width: 350px;
  margin: 10px;
}
.wordhead {
  width: 320px;
  font-size: 12px;
}
.wordheadu{
  text-align:right;
}
.wordmain {
  min-width: 30px;
  width: fit-content;
  max-width: 240px;
  word-wrap: break-word;
  word-break: normal;
}
.wordmainu{
  float:right;padding-right:18px;
}
.imgavatar {
  padding-top: 5px;
  width: 20px;
  height: auto;
  border-radius: 11px;
}
.wordmain p {
  background-color: #fff;
  color: black;
  font-size: 16px;
  padding: 5px;
  border-radius: 7px;
  border: 1px solid #bfbfbf;
  -webkit-margin-before: 3px;
}
.userp{
  text-align:justify;
}
.wordmain img {
  background-color: #fff;
  margin: 5px;
  border-radius: 7px;
  border: 1px solid #bfbfbf;
}
.clear{
  clear:both;
}
.tool {
  height: 42px;
  background-color: #d6d6d6;
  padding: 5px 5px 0px 5px;
}
.tool img {
  width: 12px;
  height: auto;
}
.imginput{
  display:none;
}
.subbutton{
  float:right
}
.input {
  height: 100px;
  background-color: darkgray;
}
ul {
  padding-left: 0px;
}
ul > li {
  list-style: none;
}
.rate {
  position: absolute;
  bottom: 30%;
  right: 5%;
  width: 200px;
  height: 200px;
  background-color: #fff;
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


