<template>
  <div id="message-frame">
    <el-button type="primary" v-on:click="askForArtificialServices">人工服务</el-button>
    <div id="message" v-scroll-bottom="session">
        <ul v-if="robotFlag==true">
            <li v-for="entry in robotChatting">
                <p class="time">
                    <span>{{entry.date}}</span>
                </p>
                <div class="main" :class="{self:entry.self}">
                    <img class="avatar" :src="entry.self ? img : entry.img" alt="">
                    <p class="text" v-html="entry.content"></p>
                </div>
            </li>
        </ul>
        <ul v-if="robotFlag==false">
            <li v-for="entry in sessions">
                <p class="time">
                    <span>{{entry.date}}</span>
                </p>
                <div class="main" :class="{self:entry.self}">
                    <img class="avatar" :src="entry.self ? img : entry.img" alt="">
                    <p class="text" v-html="entry.content"></p>
                </div>
            </li>
        </ul>
    </div>
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
      <textarea id="user-text-area" placeholder="按 Ctrl + Enter 发送" v-model="content" v-on:keyup="addMessage"></textarea>
    </div>
    <el-dialog
    title="请给客服评价"
    :visible.sync="rateVisible"
    width="30%">
      <el-rate
      v-model="ratingStar"
      :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
      </el-rate>
      <el-input
        type="textarea"
        :rows="4"
        placeholder="请输入给客服的评价"
        v-model="assessment">
      </el-input>
      <span slot="footer" class="dialog-footer">
      <el-button @click="rateVisible = false">取 消</el-button>
      <el-button type="primary" @click="finishedRating">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'

export default {
  name: 'message',
  data () {
    return {
      ratingUrl: '/csEvaluate.action',
      rateVisible: false,
      ratingStar: null,
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
filters:{
    time (date) {
      if (date) {
        date = new Date(date);
    }
    return `${date.getHours()}:${date.getMinutes()}`;
}
},
directives: {
    'scroll-bottom' (el) {
      //console.log(el.scrollTop);
      setTimeout(function () {
        el.scrollTop+=9999;
    },1)
  }
},
created() {
    this.initWebSocket()
},
computed: mapState(['sessions', 'currentSessionId', 'robotChatting', 'clientMessage']),
methods: {
    //完成评价
    finishedRating: function(event) {
      this.rateVisible = false
      let _this = this
      var params = new URLSearchParams();
      params.append('data', JSON.stringify({
        cs_id: this.receiverId,
        cs_score: this.ratingStar,
        content: this.assessment
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
            alert()
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
    onEditorChange({ editor, html, text}) {
        this.content = html;
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
  height: 500px;
  border-top: solid 1px #DDD;
  > textarea {
    width: 100%;
    height: 100%;
    border: none;
    outline: none;
    resize: none;
}
}
#message {
  padding: 15px;
  height: 400px;
  overflow-y: scroll;
  ul {
    li {
      margin-bottom: 15px;
      list-style-type: none;
  }
}
.time {
    text-align: center;
    margin: 7px 0;
    > span {
      display: inline-block;
      padding: 0 18px;
      font-size: 12px;
      color: #FFF;
      background-color: #dcdcdc;
      border-radius: 2px;
  }
}
.main {
    .avatar {
      float: left;
      margin: 0 10px 0 0;
      border-radius: 3px;
      width: 30px;
      height: 30px;

  }
  .text {
      display: inline-block;
      padding: 0 10px;
      max-width: 80%;
      background-color: #fafafa;
      border-radius: 4px;
      line-height: 30px;
  }
}
.self {
    text-align: right;
    .avatar {
      float: right;
      margin: 0 0 0 10px;
      border-radius: 3px;
      width: 30px;
      height: 30px;
  }
  .text {
      display: inline-block;
      padding: 0 10px;
      max-width: 80%;
      background-color: #b2e281;
      border-radius: 4px;
      line-height: 30px;
  }
}
}
</style>
