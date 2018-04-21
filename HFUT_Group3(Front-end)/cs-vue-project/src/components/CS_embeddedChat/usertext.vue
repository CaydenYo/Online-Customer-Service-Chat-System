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
      content: '',
      websocket: null
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
        const wsurl = 'ws://localhost:8080/OCSSystem/serve'
        this.websocket = new WebSocket(wsurl);
        this.websocket.onmessage = this.websocketonmessage;
        this.websocket.onclose = this.websocketclose;
    },
    websocketonmessage(e) {
        var receiverMsg = JSON.parse(e.data)
        alert('客户在服务列表中的id'+receiverMsg.userItemId)
        if(receiverMsg.receiverId !== null){
          this.$store.commit('addMessage', {
          msg: receiverMsg,
          itemId: receiverMsg.userItemId
        });
      }
    },
    websocketsend(e) {
        alert("客服要发送的客户id"+this.sessions[this.currentSessionId - 1].user.customer_id)
        var obj = JSON.stringify({
            nickname: "kefu1",
            senderId: "1000",
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
