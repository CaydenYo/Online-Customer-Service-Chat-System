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
    <div id="uesrtext">
      <textarea placeholder="按 Ctrl + Enter 发送" v-model="content" v-on:keyup="addMessage"></textarea>
    </div>
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
      img: '../src/assets/images/1.jpg',
      robotFlag: false,
      userItemId: null,
      content:'',
      websocket: null,
      name: 'yefei',
      receiverId: null
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
computed: mapState(['sessions', 'currentSessionId', 'robotChatting']),
methods: {
    askForArtificialServices: function(event) {
      alert("开始转接人工服务。。。")
      var obj = JSON.stringify({
            nickname: "yefei",
            senderId: "2001",
            receiverId: this.receiverId,
            companyName: "CISCO",
            companyId: "2",
            content: this.content,
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
          } else {
              this.receiverId = receiverMsg.senderId
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
            nickname: "yefei",
            senderId: "2001",
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
.message-frame {
  height: 100%;
  width: 100%;
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
    list-style-type: none;
    li {
      margin-bottom: 15px;
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
