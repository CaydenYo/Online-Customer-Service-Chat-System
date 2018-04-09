<template>
  <div id="kfgl">
    <el-card>
      <el-button type="primary" @click="dialogFormVisible = true">添加客服</el-button>
      <el-dialog title="添加客服" :visible.sync="dialogFormVisible" center width="30%">
        <el-form :model="form">
          <el-form-item label="客服邮箱">
            <el-input v-model="form.email" auto-complete="off"></el-input>
        </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
  </div>
</el-dialog>
</el-card><br>
<el-card class="box-card">
  <h3>客服列表</h3>
  <el-table :data="tableData3" height="250" border style="width: 100%">
    <el-table-column prop="cs_workId" label="工号" width="180">
      <template slot-scope="scope">
        <span>{{scope.row.cs_workId}}</span>
    </template>
</el-table-column>
<el-table-column prop="cs_name" label="姓名" width="180">
  <template slot-scope="scope">
    <span>{{scope.row.cs_name}}</span>
</template>
</el-table-column>
<el-table-column prop="cs_nickName" label="昵称" width="180">
  <template slot-scope="scope">
    <span>{{scope.row.cs_nickName}}</span>
</template>
</el-table-column>
<el-table-column prop="cs_email" label="电邮" width="180">
  <template slot-scope="scope">
    <span>{{scope.row.cs_email}}</span>
</template>
</el-table-column>
<el-table-column prop="cs_score" label="评分" width="180">
  <template slot-scope="scope">
    <span>{{scope.row.cs_score}}</span>
</template>
</el-table-column>
<el-table-column prop="cs_register_status" label="注册状态" width="180">
  <template slot-scope="scope">
    <span>{{scope.row.cs_register_status}}</span>
</template>
</el-table-column>
<el-table-column prop="cs_status" label="状态" width="180">
  <template slot-scope="scope">
    <span>{{scope.row.cs_status}}</span>
</template>
</el-table-column>
</el-table>
</el-card>
</div>
</template>

<script>
export default {
  data() {
    return {
      company_id: JSON.parse(localStorage.getItem('company_id')),
      kfgl_url: '/ListCSInfoList',
      dialogFormVisible: false,
      form: {
        email: ''
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
      console.log(JSON.stringify(this.company_id))
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.company_id))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.kfgl_url,
        data: params
    }).then(res => {
        _this.tableData3 = JSON.parse(res.data)
    })
}
}
}
</script>


