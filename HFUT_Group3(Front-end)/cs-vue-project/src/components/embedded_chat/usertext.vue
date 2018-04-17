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
      content:'',
      websocket: null
  }
},
created() {
    this.initWebSocket()
},
computed: {
    editor() {
      return this.$refs.QuillEditor.quill
  }
},
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
    },
    websocketonmessage(e) {
        var receiverMsg = JSON.parse(e.data)
        this.$store.commit('addMessage', receiverMsg);
    },
    websocketsend(e) {
        var obj = JSON.stringify({
            nickname: "yukang",
            senderId: "2000",
            receiverId: "1000",
            companyName: "pentaKill",
            companyId: "1",
            content: this.content
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
  height: 10%;
  border-top: solid 1px #DDD;
  > textarea {
    width: 100%;
    height: 100%;
    border: none;
    outline: none;
}
}

</style>
