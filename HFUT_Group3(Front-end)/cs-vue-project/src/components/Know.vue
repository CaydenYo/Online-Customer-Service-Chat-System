<template>
  <div id="know">
    <el-card>
      <el-button type="primary" @click="dialogFormVisible = true">添加知识库</el-button>
      <el-dialog title="添加客服" :visible.sync="dialogFormVisible" center width="30%">
        <el-form :model="form">
          <el-form-item label="问题">
            <el-input v-model="form.question" auto-complete="off"></el-input>
          </el-form-item>
          <el-form-item label="答案">
            <el-input v-model="form.anwser" auto-complete="off"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogFormVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </el-card><br>
    <el-card>
      <el-table :data="tableData3" height="250" border style="width: 100%">
        <el-table-column prop="question" label="问题">
        </el-table-column>
        <el-table-column prop="anwser" label="答案">
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
      form: {
        question: '',
        anwser: ''
      },
      tableData3: [
        {
          question: '人工客服',
          anwser: '您好，正在为您转接人工客服，请稍后！'
        },
        {
          question: '人工客服',
          anwser: '您好，正在为您转接人工客服，请稍后！'
        },
        {
          question: '人工客服',
          anwser: '您好，正在为您转接人工客服，请稍后！'
        }
      ]
    }
  }
}
</script>
