<template>
  <div id="know">
    <!-- 知识库添加按钮及悬浮框 -->
    <el-card class="body">
      <el-button type="primary" @click="dialogFormVisible = true" icon="el-icon-setting">添加知识库</el-button>
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
    </el-card>
    <!-- 知识库显示表格 -->
    <el-card>
      <el-table :data="know" height="250" border style="width: 100%">
        <el-table-column prop="question" label="问题">
        </el-table-column>
        <el-table-column prop="answer" label="答案">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope='scope'>
            <el-button size="mini" type="primary" @click="handleEdit(scope.$index,scope.row)" icon="el-icon-edit-outline">修改</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, know)" icon="el-icon-delete">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 修改知识库对话框 -->
    <el-dialog title="修改客服" :visible.sync="updateFormVisible" center width="30%">
      <el-form :model="updatekn">
        <el-form-item label="问题">
          <el-input v-model="updatekn.question" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="答案">
          <el-input v-model="updatekn.answer" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateknfun()">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      update_line: '',
      dialogFormVisible: false,
      updateFormVisible: false,
      zsk_url: '/listAllKnowledge',
      insert_know_url: '/insertKnowledge',
      update_know_url: '',
      delete_know_url: '',
      zsk: {
        company_id: JSON.parse(localStorage.getItem('company_id'))
      },
      insertkn: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        question: '',
        answer: ''
      },
      updatekn: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        knowledge_id: '',
        question: '',
        answer: ''
      },
      deletekn: {
        company_id: JSON.parse(localStorage.getItem('company_id')),
        knowledge_id: ''
      },
      know: [
        {
          knowledge_id: '1314521',
          question: '123',
          answer: '234'
        },
        {
          knowledge_id: '2',
          question: '4563',
          answer: '234'
        }
      ]
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
    },
    // 点击表格修改按键
    handleEdit(index, row) {
      //index:行数
      //row:具体信息
      console.log(index, row)
      console.log(row.id)
      this.updateFormVisible = true
      this.update_line = index
      this.updatekn.question = row.question
      this.updatekn.answer = row.answer
    },
    // 点击表格删除按键
    handleDelete(index, row) {
      console.log(index, row)
      this.deletekn.knowledge_id = row[index].knowledge_id
      console.log('12345:      ' + row[index].knowledge_id)
      console.log('lalal:      ' + this.deletekn.knowledge_id)
      row.splice(index, 1)
    },
    // 点击对话框确定按键，进行修改
    updateknfun() {
      this.updateFormVisible = false
      this.know[this.update_line].question = this.updatekn.question
      this.know[this.update_line].answer = this.updatekn.answer
    }
  }
}
</script>

<style>
.body {
  margin-bottom: 2%;
}
</style>

