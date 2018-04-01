<template>
<div id="hehe">
    <!-- <el-popover ref="popchat" width="800px" visible-arrow="false" placement="top-start"> -->
        <div id="app">

            <ChatList style="width:800px;">
            </ChatList>
        </div>
    <!-- </el-popover> -->
    <!-- <el-button type="primary" v-popover:popchat icon="el-icon-service" class="bluetn" style="position: fixed; right: 5px; bottom: 60px; z-index: 10; padding: 3px 3px 0 3px;">咨询</el-button> -->
  </div>

</template>

<script>
import Heads from './Heads'
import ChatList from './ChatList'

export default {
  name: 'app',
  components: {
    
    ChatList
  }
}
</script>

<style>
  body {
    /*background-color: #324057;*/
    margin: 0px;
    padding: 0px;
    font-family: Helvetica Neue, Helvetica, PingFang SC, Hiragino Sans GB, Microsoft YaHei, SimSun, sans-serif;
    /*font-weight: 400;
    -webkit-font-smoothing: antialiased;*/
    overflow: hidden;
  }
  
  blockquote,
  body,
  button,
  dd,
  div,
  dl,
  dt,
  form,
  h1,
  h2,
  h3,
  h4,
  h5,
  h6,
  input,
  li,
  ol,
  p,
  pre,
  td,
  textarea,
  th,
  ul {
    margin: 0;
    margin-top: 0px;
    margin-right: 0px;
    margin-bottom: 0px;
    margin-left: 0px;
    padding: 0;
    -webkit-tap-highlight-color: rgba(0, 0, 0, 0);
  }
  
  ul,
  li {
    list-style: none;
  }
  
  a {
    color: #666;
  }
  /*定义滚动条高宽及背景 高宽分别对应横竖滚动条的尺寸*/
  
  ::-webkit-scrollbar {
    width: 10px;
    height: 10px;
    background-color: #F8F8F8;
  }
  /*定义滚动条轨道 内阴影+圆角*/
  
  ::-webkit-scrollbar-track {
    background-color: #F8F8F8;
  }
  /*定义滑块 内阴影+圆角*/
  
  ::-webkit-scrollbar-thumb {
    background-color: #e2e2e2;
  }
  
  #app {
    overflow: hidden;
  }
</style>