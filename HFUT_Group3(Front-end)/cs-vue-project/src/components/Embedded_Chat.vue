<template>
  <div class="embedded_frame">
  <div id="embedded_chat">
    <div class="sidebar">
      <card></card>
      <list></list>
    </div>
    <div class="main">
      <message></message>
      <usertext></usertext>
    </div>
  </div>
</div>
</template>

<script>
import card from './embedded_chat/card.vue'
import list from './embedded_chat/list.vue'
import message from './embedded_chat/message.vue'
import usertext from './embedded_chat/usertext.vue'

export default {
  name: 'embedded_chat',
  data () {
    return {
      
    }
  },
  mounted:function() {
    this.$store.dispatch('initData');
  },
  components:{
    card,
    list,
    message,
    usertext
  }
}
</script>

<style lang="scss" scoped>
.embedded_frame {
  width: 100%;
  height: 100%;
  background-image: url("../img/user_logo.jpg");
}
#embedded_chat {
  margin: 0px auto;
  width: 60%;
  height: 90%;
  overflow: hidden;
  border-radius: 10px;
  .sidebar, .main {
    height: 100%;
  }
  .sidebar {
    float: left;
    color: #f4f4f4;
    background-color: #2e3238;
    width: 200px;
  }
  .main {
    position: relative;
    overflow: hidden;
    background-color: #eee;
  }
}
</style>
