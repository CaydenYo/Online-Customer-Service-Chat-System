<template>
  <div id="customer_information">
    <!-- 客户信息 -->
    <el-container>
      <el-header class="customer-head">
        <el-button type="primary" class="add-commonlanguage">添加客户</el-button>
        <el-input class="search_user" placeholder="请输入用户名" v-model="search_user_input" v-on:input="doFilter">
          <i class="el-icon-search el-input__icon" slot="suffix" />
        </el-input>
      </el-header>
      <el-main>
        <el-table :data="table_data_show">
          <el-table-column prop="start_time" label="日期" width="200">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.current_time }}</span>
            </template>
          </el-table-column>
          <el-table-column prop="customer_name" label="姓名" width="120">
            <template slot-scope="scope">
              <el-popover trigger="hover" placement="top" open-delay=500>
                <p>姓名: {{ scope.row.customer_name }}</p>
                <p>昵称: {{ scope.row.customer_nickname }}</p>
                <p>年龄: {{ scope.row.customer_age }}</p>
                <p>性别: {{ scope.row.customer_sex }}</p>
                <p>电邮: {{ scope.row.customer_email }}</p>
                <div slot="reference" class="name-wrapper">
                  <el-tag size="medium">{{ scope.row.customer_name }}</el-tag>
                </div>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column prop="customer_address" label="地址">
            <template slot-scope="scope">
              <i class="el-icon-location-outline"></i>
              <span style="margin-left: 10px">{{ scope.row.customer_address }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope='scope'>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, table_data_show)" icon="el-icon-delete">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
export default {
  data() {
    return {
      search_user_input: '',
      search_user_url: '/csViewsAllCustomerInfo.action',
      search_user_from: {
        cs_id: sessionStorage.getItem('cs_id')
      },
      table_data_begin: [],
      table_data_show: [],
      flag: false
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    // 搜索用户
    doFilter() {
      console.log('in filter function')
      this.table_data_show = []
      if (this.search_user_input === '') {
        console.log('query is empty')
        this.table_data_show = this.table_data_begin
        return
      }
      console.log('query is not empty')
      this.table_data_show = []
      this.table_data_begin.forEach((value, index) => {
        if (value.customer_name) {
          if (value.customer_name.indexOf(this.search_user_input) >= 0) {
            this.table_data_show.push(value)
          }
        }
      })
    },
    // 页面加载完成查看该客服服务的用户
    init() {
      console.log('页面加载完成')
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.search_user_from))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.search_user_url,
        data: params
      }).then(res => {
        if (res.data === 'fail') {
          this.$message({
            message: '管理员已关闭查看功能',
            type: 'warning'
          })
        } else {
          _this.table_data_begin = res.data
          _this.table_data_show = _this.table_data_begin
        }
      })
    },
    // 表格删除键按下
    handleDelete(index, row) {
      row.splice(index, 1)
    }
  }
}
</script>

<style scoped>
.customer-head {
  height: 50px;
  margin: 20px;
  border-bottom: 1px solid #e6e6e6;
}
.add-customer {
  float: left;
  margin-left: 10px;
}
.delete-customer {
  float: left;
  margin-left: 10px;
}
.search_user {
  float: right;
  width: 10%;
}
</style>