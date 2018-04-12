<template>
  <div id="kfgl">
    <el-card>
      <el-button type="primary" @click="dialogFormVisible = true">添加客服</el-button>
      <el-dialog title="添加客服" :visible.sync="dialogFormVisible" center width="30%">
        <el-form :model="insertcs">
          <el-form-item label="客服邮箱">
            <el-input v-model="insertcs.cs_email" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="工号">
            <el-input v-model="insertcs.cs_workId" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="insertcsfun()">确 定</el-button>
        </div>
      </el-dialog>
    </el-card><br>
    <el-card class="box-card">
      <h3>客服列表</h3>
      <el-table :data="tableData3" height="250" border style="width: 100%">
        <el-table-column prop="cs_workId" label="工号" width="100">
        </el-table-column>
        <el-table-column prop="cs_name" label="姓名" width="100">
        </el-table-column>
        <el-table-column prop="cs_nickName" label="昵称" width="180">
        </el-table-column>
        <el-table-column prop="cs_email" label="电邮" width="180">
        </el-table-column>
        <el-table-column prop="cs_score" label="评分" width="180">
        </el-table-column>
        <el-table-column prop="cs_status" label="状态" width="180">
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogFormVisible: false,
      kfgl_url: '/ListCSInfoList',
      tjkf_url: '/reg',
      csmanage: {
        company_id: JSON.parse(localStorage.getItem('company_id'))
      },
      insertcs: {
        cs_email: '',
        cs_workId: '',
        company_id: JSON.parse(localStorage.getItem('company_id'))
      },
      tableData3: []
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      console.log('页面加载完成')
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.csmanage))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.kfgl_url,
        data: params
      }).then(res => {
        _this.tableData3 = res.data
      })
    },
    insertcsfun() {
      console.log('执行添加函数')
      this.dialogFormVisible = false
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.insertcs))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.tjkf_url,
        data: params
      }).then(res => {
        if (res.data === 'success') {
          alert('success')
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


