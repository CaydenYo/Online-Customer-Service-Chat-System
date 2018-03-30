<template>
  <el-container>
    <el-header>
      <el-menu class='el-menu-demo' mode="horizontal">
        <el-submenu index="1" style="float:right;">
          <template slot="title">运维人员</template>
          <router-link to="/CsLogin">
            <el-menu-item index="4-1">
              <el-button type="text">登出</el-button>
            </el-menu-item>
          </router-link>
        </el-submenu>
      </el-menu>
    </el-header>
    <el-container>
      <el-aside>
        <el-menu default-active="index" :router="true">
          <el-menu-item index="index">
            <span slot="title">概览</span>
            <!-- 
              当前接待人数
              排队人数
              在线状态
              历史接待人数 
            -->
          </el-menu-item>
          <el-menu-item index="kfgl">
            <span slot="title">客服管理</span>
            <!-- 包含添加客服，已添加客服的评价 -->
          </el-menu-item>
          <el-menu-item index="yhgl">
            <span slot="title">用户管理</span>
            <!-- 消费者信息 -->
          </el-menu-item>
          <el-menu-item index="know">
            <span slot="title">知识库管理</span>
          </el-menu-item>
          <el-submenu index="5">
            <template slot="title">
              <span>会话管理</span>
            </template>
            <el-menu-item index="5-1">当前会话</el-menu-item>
            <el-menu-item index="5-2">历史会话</el-menu-item>
          </el-submenu>
          <el-menu-item index="settings">
            <span slot="title">设置</span>
            <!-- 嵌入、悬浮 -->
            <!-- 启动、名称 -->
            <!-- 最少接待人数、分配方式、是否提供用户信息 -->
          </el-menu-item>
        </el-menu>
      </el-aside>
      <el-container>
        <el-main>
          <div id="main-right">
            <!-- 视图 -->
            <router-view class="view"></router-view>
          </div>
        </el-main>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data() {
    return {
      activeIndex: 'index'
    }
  }
}
</script>

