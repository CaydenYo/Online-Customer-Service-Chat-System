<template>
    <el-container>
        <el-header>
            <div class="head-title-left">
                pentaKill客服中心
            </div>
            <div class="head-logout-right">
                <el-button icon="el-icon-remove" class="logout-button">登出</el-button>
            </div>
            <div class="head-status-right">
                <el-switch
                v-model="cs_status"
                active-text="在线"
                inactive-text="离线">
                </el-switch>
            </div>
            <div class="head-title-right">
            </div>
        </el-header>
        <el-container class="cs-index">
            <el-aside width="11%">
                <el-menu default-active="cs_dialogue" :router="true">
                    <el-menu-item index="cs_dialogue">
                        <template slot="title"><i class="el-icon-service"></i>客服会话中心</template>
                    </el-menu-item>
                    <el-menu-item index="cs_statics">
                        <template slot="title"><i class="el-icon-edit-outline"></i>统计</template>
                    </el-menu-item>
                    <el-menu-item index="common_language">
                        <template slot="title"><i class="el-icon-tickets"></i>常用语</template>
                    </el-menu-item>
                    <el-menu-item index="customer_information">
                        <template slot="title"><i class="el-icon-document"></i>客户资料库</template>
                    </el-menu-item>
                    <el-menu-item index="cs_settings">
                        <template slot="title"><i class="el-icon-setting"></i>设置</template>
                    </el-menu-item>
                </el-menu>
            </el-aside>
            <el-container>
                <el-main>
                    <div id="main">
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
    color: #333;
    line-height: 60px;
}

.head-title-left {
    float: left;
}

.head-logout-right {
    float: right;
}

.head-status-right {
    float: right;
    margin-right: 10px;
}

.logout-button {
    border: none;
    hover:none;
}

.el-aside {
  background-color: white;
  color: #333;
  width: 5%;
  border-right: 1px solid #E6E6E6;
}

.el-menu {
    border: none;
}

.el-container {
    height:100%;
    width:100%; 
}

.cs-index {
    border: 1px solid #eee;
}

#main {
     height: 100%;
     width: 100%;
}
</style>

<script>
export default {
  data() {
    return {
        cs_status: true
    }
  }
}
</script>