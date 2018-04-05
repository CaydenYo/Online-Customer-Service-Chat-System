<template>
<el-container style="height:100%;width:100%;">
  <el-header>hi</el-header>
<el-container style="height:100%;width:100%; border: 1px solid #eee">
  <el-aside width="13%" style="background-color: rgb(238, 241, 246)">
    <el-menu default-active="cs_dialogue" :router="true">
      <el-menu-item index="cs_dialogue">
        <template slot="title"><i class="el-icon-service"></i>客服会话中心</template>
      </el-menu-item>
      <el-menu-item index="cs_statics">
        <template slot="title"><i class="el-icon-edit-outline"></i>统计</template>
      </el-menu-item>
      <el-menu-item index="common_language">
        <template slot="title"><i class="el-icon-tickets"></i>知识库与常用语</template>
      </el-menu-item>
      <el-menu-item index="customer_information">
        <template slot="title"><i class="el-icon-document"></i>客户资料库</template>
      </el-menu-item>
      <el-menu-item index="cs_settings">
        <template slot="title"><i class="el-icon-setting"></i>设置</template>
      </el-menu-item>
    </el-menu>
  </el-aside>
  
  <el-container style="height:100%;width:100%;">
    <!--
    <el-header style="text-align: right; font-size: 12px">
      <el-dropdown>
        <i class="el-icon-setting" style="margin-right: 15px"></i>
        <el-dropdown-menu slot="dropdown">
          <el-dropdown-item>查看</el-dropdown-item>
          <el-dropdown-item>新增</el-dropdown-item>
          <el-dropdown-item>删除</el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
      <span>王小虎</span>
    </el-header>-->
    
    <el-main>
      <div id="main" style="height:100%;width:100%;">
            <!-- 视图 -->
            <router-view class="view"></router-view>
          </div>
    </el-main>
  </el-container>
</el-container>
</el-container>
</template>

<style scoped>
  .el-header {
    background-color: #B3C0D1;
    color: #333;
    line-height: 60px;
  }
  
  .el-aside {
    color: #333;
    width: 5%;
  }
</style>

<script>
  export default {
    data() {
      const item = {
        date: '2016-05-02',
        name: '王小虎',
        address: '上海市普陀区金沙江路 1518 弄'
      };
      return {
        tableData: Array(20).fill(item),
        activeIndex: 'cs_dialogue'
      }
    }
  };
</script>