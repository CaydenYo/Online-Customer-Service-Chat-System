<template>
  <div id="know">
    <!-- 知识库添加按钮及悬浮框 -->
    <el-card class="body">
      <el-button type="primary" @click="dialogFormVisible = true" icon="el-icon-setting">添加知识库</el-button>
      <el-dialog title="添加知识库" :visible.sync="dialogFormVisible" center width="30%">
        <el-form :model="insertkn" ref="insertkn" :rules="rules1">
          <el-form-item label="问题" prop="question">
            <el-input v-model="insertkn.question" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="答案" prop="answer">
            <el-input v-model="insertkn.answer" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="insertknfun('insertkn')">确 定</el-button>
        </div>
      </el-dialog>
    </el-card>
    <!-- 知识库显示表格 -->
    <el-card>
      <el-table :data="know" height="500" border class="tab">
        <el-table-column prop="question" label="问题">
        </el-table-column>
        <el-table-column prop="answer" label="答案">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope='scope'>
            <el-button size="mini" class="tabbut" type="primary" @click="handleEdit(scope.$index,scope.row)" icon="el-icon-edit-outline">修改</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, know)" icon="el-icon-delete">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
    <!-- 修改知识库对话框 -->
    <el-dialog title="修改知识库" :visible.sync="updateFormVisible" center width="30%">
      <el-form :model="updatekn" ref="updatekn" :rules="rules2">
        <el-form-item label="问题" prop="question">
          <el-input v-model="updatekn.question" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="答案" prop="answer">
          <el-input v-model="updatekn.answer" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="updateFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="updateknfun('updatekn')">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<script>
export default {
  data() {
    return {
      dialogFormVisible: false,
      updateFormVisible: false,
      zsk_url: '/listAllKnowledge',
      insert_know_url: '/insertKnowledge',
      update_know_url: '/updateKnowledge',
      delete_know_url: '/deleteKnowledge',
      zsk: {
        company_id: JSON.parse(sessionStorage.getItem('company_id'))
      },
      insertkn: {
        company_id: JSON.parse(sessionStorage.getItem('company_id')),
        question: '',
        answer: ''
      },
      updatekn: {
        company_id: JSON.parse(sessionStorage.getItem('company_id')),
        knowledge_id: '',
        question: '',
        answer: ''
      },
      deletekn: {
        company_id: JSON.parse(sessionStorage.getItem('company_id')),
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
      ],
      rules1: {
        question: [
          { required: true, message: '问题不能为空', trigger: 'blur' }
        ],
        answer: [{ required: true, message: '答案不能为空', trigger: 'blur' }]
      },
      rules2: {
        question: [
          { required: true, message: '问题不能为空', trigger: 'blur' }
        ],
        answer: [{ required: true, message: '答案不能为空', trigger: 'blur' }]
      }
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
    // 添加知识库
    insertknfun(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
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
          setTimeout(() => {
            this.init()
          }, 500)
        } else {
          console.log('error submit!!')
          return false
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
      this.deletekn.knowledge_id = row[index].knowledge_id
      // console.log('12345:      ' + row[index].knowledge_id)
      // console.log('lalal:      ' + this.deletekn.knowledge_id)
      row.splice(index, 1)
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.deletekn))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.delete_know_url,
        data: params
      }).then(res => {
        if (res.data === 'success') {
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
    // 点击对话框确定按键，进行修改
    updateknfun(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.updateFormVisible = false
          this.know[this.update_line].question = this.updatekn.question
          this.know[this.update_line].answer = this.updatekn.answer
          this.updatekn.knowledge_id = this.know[this.update_line].knowledge_id
          this.updatekn.question = this.know[this.update_line].question
          this.updatekn.answer = this.know[this.update_line].answer
          var params = new URLSearchParams()
          let _this = this
          params.append('data', JSON.stringify(this.updatekn))
          this.$axios({
            method: 'post',
            url: this.rootUrl + _this.update_know_url,
            data: params
          }).then(res => {
            if (res.data === 'success') {
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
        } else {
          console.log('error submit!!')
          return false
        }
      })
    }
  }
}
</script>

<style>
.body {
  margin-bottom: 2%;
}
.tab {
  width: 100%;
}
</style>

