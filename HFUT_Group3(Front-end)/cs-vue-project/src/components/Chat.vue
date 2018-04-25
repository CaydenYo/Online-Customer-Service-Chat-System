<template>
  <div>
    <kindeditor-emoji ref="kindEmoji" init-text="" @contentChange="contentChange"></kindeditor-emoji>
    <div style="padding-top: 20px; text-align: left">
      <button type="button" v-on:click="sendMes">发送</button>
      <button type="button" v-on:click="clearMes">清空</button>
    </div>
    <div v-for="item in contentArr" v-bind:key="item">
      <p v-html="item" style="text-align:left"></p>
    </div>
  </div>
</template>
<script>
import KindeditorEmoji from './KindeditorEmoji.vue'
export default {
  name: 'Chat',
  data () {
    return {
      textContent: '123',
      contentArr: []
    }
  },
  methods: {
    // 点击发送
    sendMes: function (event) {
      this.contentArr.push(this.textContent)
      this.textContent = ''
      // 调用子组件的方法清空富文本编辑器的内容
      this.$refs.kindEmoji.clearContent()
    },
    // 点击清空
    clearMes: function (event) {
      this.textContent = ''
      // 调用子组件的方法清空富文本编辑器的内容
      this.$refs.kindEmoji.clearContent()
    },
    // 内容变化回调
    contentChange: function (msg) {
      this.textContent = msg
    }
  },
  components: {
    KindeditorEmoji
  }
}
</script>
