<template>
  <div id="uesrtext">
    <quill-editor ref="QuillEditor"
    v-model="content"
    :config="editorOption"
    :options = "editorOption"
    @keyup="addMessage($event)"  
    @change="onEditorChange($event)"  
    @blur="onEditorBlur($event)"
    @focus="onEditorFocus($event)"
    @ready="onEditorReady($event)">
  </quill-editor>
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
      content:''
    }
  },
  computed: {
    editor() {
      return this.$refs.QuillEditor.quill
    }
  },
  methods: {
    addMessage (e) {
      if (e.ctrlKey && e.keyCode ===13 && this.content.length) {
        alert(e.keyCode)
        this.$store.commit('addMessage',this.content);
        this.content='';
      }
    },
    onEditorChange({ editor, html, text }) {//富文本编辑器  文本改变时 设置字段值  
      this.content = html
      if(e.ctrlKey && e.keyCode ===13 && this.content.length){
        this.$store.commit('addMessage',this.content);
        this.content='';
      }
    }
  }
}
</script>

<style lang="scss" scoped>
#uesrtext {
  bottom: 0;
  right: 0;
  width: 100%;
  height: 20%;
  border-top: solid 1px #DDD;
  > quill-editor {
    width: 100%;
    height: 100%;
    border: none;
    outline: none;
  }
}

</style>
