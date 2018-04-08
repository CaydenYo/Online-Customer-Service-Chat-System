<template>
  <el-container style="height: 100%">
    <el-header class="top">
      <el-menu class='el-menu-demo' mode="horizontal" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
        <div class="logo">
          <img src="../../static/images/admin_home_logo.png" width="100%" height="100%" alt="ADMIN">
        </div>
        <el-submenu index="1" style="float:right;">
          <template slot="title">运维人员</template>
          <router-link to="/CsLogin">
            <el-menu-item index="4-1">
              <el-button type="text">
                <span class="logout">登出</span>
              </el-button>
            </el-menu-item>
          </router-link>
        </el-submenu>
      </el-menu>
    </el-header>
    <el-container style="height: 100%">
      <el-aside class="side">
        <el-menu default-active="index" :router="true" background-color="#304156" text-color="#fff" active-text-color="#ffd04b">
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
      company_id: JSON.parse(localStorage.getItem('company_id')),
      activeIndex: 'index'
    }
  }
}
</script>

<style>
.top {
  background-color: #545c64;
}
.side {
  background-color: #304156;
}
.logo {
  position: absolute;
  top: 0;
  left: 0;
  padding: 5px;
  height: 54px;
  width: 54px;
}
.logout {
  color: #fff;
}
</style>


