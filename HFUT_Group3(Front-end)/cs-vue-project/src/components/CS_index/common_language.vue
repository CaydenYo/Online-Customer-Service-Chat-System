<template>
  <div id="common_language">
    <el-tabs class="cl-index" v-model="activeName" @tab-click="handleClick">
      <!-- 知识库展示标签页 -->
      <!-- <el-tab-pane class="knowledge-index" label="知识库管理" name="first">
        <el-container>
          <el-header class="kmanage-head">
            <el-button type="primary" class="add-knowledge">添加知识</el-button>
            <el-input class="search-knowledge">
              <i class="el-icon-edit el-input__icon" slot="suffix" />
            </el-input> 
          </el-header>
          <el-main>
            <el-row :gutter="12">
              <el-col :span="8">
                <el-card shadow="always">
                  总是显示
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card shadow="hover">
                  鼠标悬浮时显示
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card shadow="never">
                  从不显示
                </el-card>
              </el-col>
            </el-row>
            <el-row :gutter="12">
              <el-col :span="8">
                <el-card shadow="always">
                  总是显示
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card shadow="hover">
                  鼠标悬浮时显示
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card shadow="never">
                  从不显示
                </el-card>
              </el-col>
            </el-row>
            <el-row :gutter="12">
              <el-col :span="8">
                <el-card shadow="always">
                  总是显示
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card shadow="hover">
                  鼠标悬浮时显示
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card shadow="never">
                  从不显示
                </el-card>
              </el-col>
            </el-row>
            <el-row :gutter="12">
              <el-col :span="8">
                <el-card shadow="always">
                  总是显示
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card shadow="hover">
                  鼠标悬浮时显示
                </el-card>
              </el-col>
              <el-col :span="8">
                <el-card shadow="never">
                  从不显示
                </el-card>
              </el-col>
            </el-row>
          </el-main>
        </el-container>
      </el-tab-pane> -->
      <!-- 常用语管理标签页 -->
      <el-tab-pane label="常用语管理" name="second">
        <el-container>
          <el-header class="kmanage-head">
            <el-button type="primary" @click="insertCLFV = true" class="add-commonlanguage">添加常用语</el-button>
            <el-button type="danger" @click="deleteCL()" class="delete-commonlanguage">删除常用语</el-button>
            <!-- 添加常用语对话框 -->
            <el-dialog title="添加常用语" :visible.sync="insertCLFV" center width="30%">
              <el-form :model="insertCLF">
                <el-form-item label="常用语">
                  <el-input v-model="insertCLF.question" auto-complete="off"></el-input>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="insertCLFV = false">取 消</el-button>
                <el-button type="primary" @click="insertCLFfun()">确 定</el-button>
              </div>
            </el-dialog>
          </el-header>
          <el-main>
            <!-- 常用语显示表格 -->
            <el-table ref="multipleTable" :data="tableData3" tooltip-effect="dark" style="width: 100%" @selection-change="handleSelectionChange">
              <el-table-column type="selection" width="50">
              </el-table-column>
              <el-table-column label="常用语" prop="common_language" width="1100">
              </el-table-column>
              <el-table-column label="频率" prop="frequency">
              </el-table-column>
            </el-table>
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
          common_language: 'cl1',
          frequency: '1'
        },
        {
          common_language: 'cl2',
          frequency: '1'
        },
        {
          common_language: 'cl3',
          frequency: '1'
        },
        {
          common_language: 'cl4',
          frequency: '1'
        },
        {
          common_language: 'test',
          frequency: '1'
        },
        {
          common_language: 'know',
          frequency: '1'
        },
        {
          common_language: 'john',
          frequency: '1'
        }
      ],
      // 标签页2相关属性
      init_tab2_url: '',
      delete_CL_url: '',
      insert_CL_url: '',
      insertCLFV: false,
      initCLF: {
        cs_id: JSON.parse(localStorage.getItem('cs_id'))
      },
      insertCLF: {
        question: '',
        cs_id: JSON.parse(localStorage.getItem('cs_id'))
      },
      // 记录被选中行
      multipleSelection: [],
      // 记录被选中行id
      delete_CL_form: {
        ids: []
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
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.initCLF))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.init_tab2_url,
        data: params
      }).then(res => {
        _this.tableData3 = res.data
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
        this.delete_CL_form.ids.push(item.common_language)
      })
      var x
      for (x in this.delete_CL_form.ids) {
        console.log(this.delete_CL_form.ids[x])
      }
    },
    // 添加一个常用语
    insertCLFfun() {
      this.insertCLFV = false
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.insertCLF))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.insert_CL_url,
        data: params
      })
        .then(res => {
          if (res.data === 'success') {
            alert('success')
          } else {
            this.$message({
              message: JSON.stringify(res.data),
              type: 'error'
            })
          }
        })
        .then(_this.init())
    },
    // 删除常用语
    deleteCL() {
      this.tableData3
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.delete_CL_form))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.delete_CL_url,
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