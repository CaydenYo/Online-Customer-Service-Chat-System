<template>
  <div id="yhgl">
    <el-card class="box-card">
      <h3>用户列表</h3>
      <el-table :data="tableData3" border style="width: 100%">
        <el-table-column prop="date" label="日期" width="180">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="180">
        </el-table-column>
        <el-table-column prop="address" label="地址">
        </el-table-column>
    </el-table>
</el-card>
</div>
</template>
<script>
export default {
  data() {
    return {
      tableData3: [
      {
          date: '2016-05-03',
          name: '张晓明',
          address: '合肥翡翠湖6#'
      },
      {
          date: '2016-05-02',
          name: '张晓明',
          address: '合肥翡翠湖6#'
      },
      {
          date: '2016-05-04',
          name: '张晓明',
          address: '合肥翡翠湖6#'
      },
      {
          date: '2016-05-01',
          name: '张晓明',
          address: '合肥翡翠湖6#'
      },
      {
          date: '2016-05-08',
          name: '张晓明',
          address: '合肥翡翠湖6#'
      },
      {
          date: '2016-05-06',
          name: '张晓明',
          address: '合肥翡翠湖6#'
      },
      {
          date: '2016-05-07',
          name: '张晓明',
          address: '合肥翡翠湖6#'
      }
      ]
  }
}
}
</script>
