<template>
  <div id="uesrtext">
    <div>
      <UE :config=config ref="ue"></UE>
    </div>
  </div>
</template>

<script>
import {mapState} from 'vuex'
import UE from '../common/UE'

export default {
  name: 'uesrtext',
  data () {
    return {
      content:'',
      config: {
          initialFrameWidth: 250,
          initialFrameHeight: 350
      }
    }
  },
  components: {
    UE
  },
  methods: {
    addMessage (e) {
      if (e.ctrlKey && e.keyCode ===13 && this.content.length) {
        this.$store.commit('addMessage',this.content);
        this.content='';
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#uesrtext {
  position: absolute;
  bottom: 0;
  right: 0;
  width: 100%;
  height: 30%;
  border-top: solid 1px #DDD;
  > textarea {
    padding: 10px;
    width: 100%;
    height: 100%;
    border: none;
    outline: none;
  }
}
</style>
