<template>
  <div id="common_language">
    <el-tabs class="cl-index" v-model="activeName" @tab-click="handleClick">
      <!-- 常用语管理标签页 -->
      <el-tab-pane label="常用语管理" name="second">
        <el-container>
          <el-header class="kmanage-head">
            <el-button type="primary" @click="insertCLFV = true" class="add-commonlanguage">添加常用语</el-button>
            <el-button type="danger" @click="deleteCL()" class="delete-commonlanguage">删除常用语</el-button>
            <!-- 添加常用语对话框 -->
            <el-dialog title="添加常用语" :visible.sync="insertCLFV" center width="30%">
              <el-form :model="insertCLF" ref="insertCLF" :rules="rules1">
                <el-form-item label="常用语" prop="content">
                  <el-input v-model="insertCLF.content" auto-complete="off"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="insertCLFV = false">取 消</el-button>
                <el-button type="primary" @click="insertCLFfun('insertCLF')">确 定</el-button>
              </div>
            </el-dialog>
          </el-header>
          <el-main>
            <!-- 常用语显示表格 -->
            <el-table ref="multipleTable" :data="tableData3" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
              <el-table-column type="selection" width="50">
              </el-table-column>
              <el-table-column label="常用语" prop="content">
              </el-table-column>
              <el-table-column label="频率" prop="frequency">
                <template slot-scope='scope'>
                  <p>{{ Math.floor(Math.random()*10+9) }}</p>
                </template>
              </el-table-column>
              <el-table-column label="操作">
                <template slot-scope='scope'>
                  <el-button size="mini" class="tabbut" type="primary" @click="handleEdit(scope.$index,scope.row)" icon="el-icon-edit-outline">修改</el-button>
                </template>
              </el-table-column>
            </el-table>
            <!-- 修改常用语对话框 -->
            <el-dialog title="修改常用语" :visible.sync="updateCLFV" center width="30%">
              <el-form :model="updateCLF" ref="updateCLF" :rules="rules2">
                <el-form-item label="常用语" prop="content">
                  <el-input v-model="updateCLF.content" auto-complete="off"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="updateCLFV = false">取 消</el-button>
                <el-button type="primary" @click="updateclfun('updateCLF')">确 定</el-button>
              </div>
            </el-dialog>
          </el-main>
        </el-container>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
export default {
  data() {
    return {
      activeName: 'second',
      tableData3: [
        {
          shortcut_language_id: 1,
          content: 'hehehe',
          frequency: 35
        }
      ],
      // 标签页2相关属性
      init_tab2_url: '/customerService/showFastReply',
      delete_CL_url: '/customerService/deleteFastReply',
      insert_CL_url: '/customerService/addFastReply',
      update_CL_url: '/customerService/modifyFastReply',
      insertCLFV: false,
      updateCLFV: false,
      initCLF: {
        cs_id: JSON.parse(localStorage.getItem('cs_id'))
      },
      insertCLF: {
        content: '',
        cs_id: JSON.parse(localStorage.getItem('cs_id'))
      },
      updateCLF: {
        content: '',
        shortcut_language_id: ''
      },
      // 记录被选中行
      multipleSelection: [],
      // 记录被选中行id
      ids: [],
      rules1: {
        content: [
          { required: true, message: '常用语不能为空', trigger: 'blur' }
        ]
      },
      rules2: {
        content: [
          { required: true, message: '常用语不能为空', trigger: 'blur' }
        ]
      }
    }
  },
  mounted() {
    this.inittab2()
  },
  methods: {
    // 标签页 1 函数
    handleClick(tab, event) {
      console.log(tab, event)
    },
    // 标签页 2 函数
    // 初始化第二个标签页函数
    inittab2() {
      localStorage.setItem('cs_id', '1000')
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.initCLF))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.init_tab2_url,
        data: params
      }).then(res => {
        _this.tableData3 = res.data
        localStorage.setItem('cl', JSON.stringify(_this.tableData3))
      })
    },
    // 点击表格修改按键
    handleEdit(index, row) {
      //index:行数
      //row:具体信息
      console.log(index, row)
      console.log(row.content)
      this.updateCLFV = true
      this.update_line = index
      this.updateCLF.content = row.content
    },
    // 点击对话框修改按键
    updateclfun(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.updateCLFV = false
          this.tableData3[this.update_line].content = this.updateCLF.content
          this.updateCLF.shortcut_language_id = this.tableData3[
            this.update_line
          ].shortcut_language_id
          var params = new URLSearchParams()
          let _this = this
          params.append('data', JSON.stringify(this.updateCLF))
          this.$axios({
            method: 'post',
            url: this.rootUrl + _this.update_CL_url,
            data: params
          }).then(res => {
            if (res.data === 'ModifySuccess') {
              this.$message({
                message: '修改成功',
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
    },
    // 多选按钮相关
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
      this.multipleSelection.map(item => {
        this.multipleSelection.push(item.shortcut_language_id)
      })
      var x
      this.ids = []
      for (x in this.multipleSelection) {
        console.log('test:' + this.multipleSelection[x])
      }
    },
    // 添加一个常用语
    insertCLFfun(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.insertCLFV = false
          var params = new URLSearchParams()
          let _this = this
          params.append('data', JSON.stringify(this.insertCLF))
          this.$axios({
            method: 'post',
            url: this.rootUrl + _this.insert_CL_url,
            data: params
          }).then(res => {
            if (res.data === 'AddSuccess') {
              this.$message({
                message: '添加成功',
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
            this.inittab2()
          }, 500)
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 删除常用语
    deleteCL() {
      this.ids = this.multipleSelection
      var x
      for (x in this.ids) {
        console.log('delete: ' + this.ids[x])
      }
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.ids))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.delete_CL_url,
        data: params
      }).then(res => {
        if (res.data === 'DeleteSuccess') {
          this.$message({
            message: '删除成功',
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
        this.inittab2()
      }, 500)
    }
  }
}
</script>

<style type="text/css" scoped>
.el-row {
  margin-bottom: 10px;
}
.cl-index {
  height: 100%;
  width: 100%;
}
.knowledge-index {
  height: 100%;
  width: 100%;
}
.kmanage-head {
  height: 50px;
  margin: 20px;
  border-bottom: 1px solid #e6e6e6;
}
.add-knowledge {
  float: left;
  margin-left: 10px;
}
.add-commonlanguage {
  float: left;
  margin-left: 10px;
}
.delete-commonlanguage {
  float: left;
  margin-left: 10px;
}
.search-knowledge {
  width: 10%;
  float: left;
  margin-left: 10px;
}
#common_language {
  width: 100%;
  height: 100%;
}
.el-tabs {
  height: 100%;
}
</style>