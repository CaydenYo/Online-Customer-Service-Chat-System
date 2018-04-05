<template>
    <div id="hehe">
        <div id="xf" v-if="show">
            <div class="content">
                <div class="chatbox">
                    <message></message>
                    <v-text></v-text>
                </div>
            </div>
        </div>
        <button @click="show=!show">咨詢</button>
    </div>
</template>

<script>
import message from '../../components/message/message'
import vText from '../../components/text/text'
export default {
  data() {
    return {
      show: true
    }
  },
  components: {
    message,
    vText
  }
}
</script>

<style lang="stylus" scoped>
.content {
    display: flex;
    width: 500px;
}

.chatbox {
    flex: 1;
}

.xf {
    position: absolute;
    z-index: 99999;
    margin-top: 5%;
}
</style>