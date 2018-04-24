<template>
  <el-container>
    <el-header>
      <div class="head-title-left">
        pentaKill客服中心
      </div>
      <div class="head-logout-right">
        <el-popover ref="logoutpopover" placement="bottom" width="160" v-model="logoutv">
          <p>您确定要登出吗？</p>
          <div style="text-align: right; margin: 0">
            <el-button size="mini" type="text" @click="logoutv = false">取消</el-button>
            <el-button type="primary" size="mini" @click="logoutfun()">确定</el-button>
          </div>
        </el-popover>
        <el-button icon="el-icon-remove" class="logout-button" v-popover:logoutpopover>登出</el-button>
      </div>
      <div class="head-status-right">
        <el-switch @change="changestatuesfun" v-model="online_form.cs_status" active-color="#13ce66" inactive-color="#409eff" active-text="在线" inactive-text="休息" active-value=1 inactive-value=2></el-switch>
      </div>
      <div class="head-title-right">
      </div>
    </el-header>
    <el-container class="cs-index">
      <el-aside width="11%">
        <el-menu default-active="cs_dialogue" :router="true">
          <el-menu-item index="cs_dialogue">
            <template slot="title">
              <i class="el-icon-service"></i>客服会话中心</template>
          </el-menu-item>
          <el-menu-item index="cs_statics">
            <template slot="title">
              <i class="el-icon-edit-outline"></i>统计</template>
          </el-menu-item>
          <el-menu-item index="common_language">
            <template slot="title">
              <i class="el-icon-tickets"></i>常用语</template>
          </el-menu-item>
          <el-menu-item index="customer_information">
            <template slot="title">
              <i class="el-icon-document"></i>客户资料库</template>
          </el-menu-item>
          <el-menu-item index="cs_settings">
            <template slot="title">
              <i class="el-icon-setting"></i>设置</template>
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

<script>
export default {
  data() {
    return {
      logoutv: false,
      online_url: '/customerService/setStatus',
      cs_logout_url: '/customerService/setStatus',
      online_form: {
        cs_email: sessionStorage.getItem('cs_email'),
        cs_status: sessionStorage.getItem('cs_status')
      },
      cs_logout_form: {
        cs_status: '0',
        cs_email: sessionStorage.getItem('cs_email')
      }
    }
  },
  methods: {
    changestatuesfun() {
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.online_form))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.online_url,
        data: params
      }).then(res => {
        if (res.data === 'success') {
          sessionStorage.setItem('cs_status', this.online_form.cs_status)
          this.$message({
            message: '成功',
            type: 'success'
          })
        } else {
          this.$message({
            message: JSON.stringify(res.data),
            type: 'error'
          })
        }
      })
    },
    logoutfun() {
      this.logoutv = false
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.cs_logout_form))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.cs_logout_url,
        data: params
      }).then(res => {
        if (res.data === 'success') {
          sessionStorage.setItem('cs_status', this.cs_logout_form.cs_status)
          this.$message({
            message: '成功退出',
            type: 'success'
          })
        } else {
          this.$message({
            message: JSON.stringify(res.data),
            type: 'error'
          })
        }
      })
    }
  }
}
</script>


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
  hover: none;
}

.el-aside {
  background-color: white;
  color: #333;
  width: 5%;
  border-right: 1px solid #e6e6e6;
}

.el-menu {
  border: none;
}

.el-container {
  height: 100%;
  width: 100%;
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
      logoutv: false,
      online_url: '/customerService/setStatus',
      cs_logout_url: '/customerService/setStatus',
      online_form: {
        cs_email: sessionStorage.getItem('cs_email'),
        cs_status: sessionStorage.getItem('cs_status')
      },
      cs_logout_form: {
        cs_status: '0',
        cs_email: sessionStorage.getItem('cs_email')
      }
    }
  },
  methods: {
    changestatuesfun() {
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.online_form))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.online_url,
        data: params
      }).then(res => {
        if (res.data === 'success') {
          sessionStorage.setItem('cs_status', this.online_form.cs_status)
          this.$message({
            message: '成功',
            type: 'success'
          })
        } else {
          this.$message({
            message: JSON.stringify(res.data),
            type: 'error'
          })
        }
      })
    },
    logoutfun() {
      this.logoutv = false
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.cs_logout_form))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.cs_logout_url,
        data: params
      }).then(res => {
        if (res.data === 'success') {
          sessionStorage.setItem('cs_status', this.cs_logout_form.cs_status)
          this.$message({
            message: '成功退出',
            type: 'success'
          })
          setTimeout(() => {
          this.$router.push('/CsLogin')
          }, 500)
        } else {
          this.$message({
            message: JSON.stringify(res.data),
            type: 'error'
          })
        }
      })
    }
  }
}
</script>