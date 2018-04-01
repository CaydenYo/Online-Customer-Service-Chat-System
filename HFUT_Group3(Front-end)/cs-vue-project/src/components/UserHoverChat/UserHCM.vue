<template>
    <div id="chat">
        <el-popover ref="popchat" width="350px" visible-arrow="false" placement="top-start">
            <div id="app">
                <div class="main">
                    <message></message>
                    <usertext></usertext>
                </div>
            </div>
        </el-popover>
        <el-button type="primary" v-popover:popchat icon="el-icon-service" class="bluetn" style="position: fixed; right: 5px; bottom: 60px; z-index: 10; padding: 3px 3px 0 3px;">咨询</el-button>
    </div>
</template>

<script>
import message from './message.vue'
import usertext from './usertext.vue'

export default {
  name: 'app',
  data () {
    return {
      
    }
  },
  mounted:function() {
    this.$store.dispatch('initData');
  },
  components:{
    // card,
    // list,
    message,
    usertext
  }
}
</script>


<style lang="scss" scoped>
#app {
  margin: 20px auto;
  width: 300px;
  height: 400px;
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
