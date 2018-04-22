<template>
  <div id="uesrtext">
    <textarea placeholder="按 Ctrl + Enter 发送" v-model="content" v-on:keyup="addMessage"></textarea>
</div>
</template>

<script>
import {mapState} from 'vuex'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'

export default {
  name: 'uesrtext',
  data () {
    return {
      robotFlag: true,
      userItemId: null,
      content:'',
      websocket: null,
      name: 'yukang'
  }
},
created() {
    this.initWebSocket()
},
computed: mapState(['sessions', 'currentSessionId']),
methods: {
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
        const wsurl = 'ws://localhost:8080/HFUT_Group3/serve'
        this.websocket = new WebSocket(wsurl);
        this.websocket.onmessage = this.websocketonmessage;
        this.websocket.onclose = this.websocketclose;
        // alert("准备执行加入等待队列")
        // this.$store.commit('addToRobotChatting',this.name)
        // alert("已执行")
    },
    websocketonmessage(e) {
        var receiverMsg = JSON.parse(e.data)
        alert(JSON.stringify(receiverMsg))
        // if(receiverMsg.content instanceof Array) {
        //   alert("进来了")
        //   var html = "";
        //   html += "<p>" + "您好我是机器人小机，请问您想问的是以下问题吗？" + "</p>";
        //   var result = receiverMsg.content;
        //   html += "<ol>";
        //   for(var i = 0;i < result.length;i++) {
        //     var obj = result[i];
        //     var question = obj.question;
        //     html += "<li>" + question + "</li>";
        //   }
        //   html += "</ol>";
        //   receiverMsg.content = html;
        //   alert(receiverMsg.content)
        //   this.$store.commit('addRobotMessage', {
        //     msg: receiverMsg
        //   })
        // }else {
          if(this.userItemId === null){
          this.userItemId = receiverMsg.userItemId
          alert(this.userItemId)
        }
          this.$store.commit('addMessage', {
          msg: receiverMsg,
          itemId: this.userItemId
        });
        
    },
    websocketsend(e) {
        if(this.robotFlag === true){
          this.content = "robotAnwser" + this.content
        }
        var obj = JSON.stringify({
            nickname: "yukang",
            senderId: "2000",
            receiverId: "1000",
            companyName: "pentaKill",
            companyId: "1",
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

</style>
