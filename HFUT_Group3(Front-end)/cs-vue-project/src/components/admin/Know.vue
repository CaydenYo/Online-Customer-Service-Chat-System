<template>
  <div id="know">
    <el-card>
      <el-button type="primary" @click="dialogFormVisible = true">添加知识库</el-button>
      <el-dialog title="添加客服" :visible.sync="dialogFormVisible" center width="30%">
        <el-form :model="insertkn">
          <el-form-item label="问题">
            <el-input v-model="insertkn.question" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="答案">
            <el-input v-model="insertkn.answer" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="insertknfun()">确 定</el-button>
        </div>
      </el-dialog>
    </el-card><br>
    <el-card>
      <el-table :data="know" height="250" border style="width: 100%">
        <el-table-column prop="question" label="问题">
        </el-table-column>
        <el-table-column prop="answer" label="答案">
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
      zsk_url: '/listAllKnowledge',
      insert_know_url: '/insertKnowledge',
      zsk: {
        company_id: JSON.parse(localStorage.getItem('company_id'))
      },
      insertkn: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        question: '',
        answer: ''
      },
      know: []
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      console.log('页面加载完成')
      console.log('this.zsk')
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.zsk))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.zsk_url,
        data: params
      }).then(res => {
        _this.know = res.data
      })
    },
    insertknfun() {
      console.log('执行添加函数')
      this.dialogFormVisible = false
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.insertkn))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.insert_know_url,
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
