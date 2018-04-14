<template>
	<div id="customer_information">
		<el-container>
            <el-header class="customer-head">
                <el-button type="primary" class="add-commonlanguage">添加客户</el-button>
                <el-button type="danger" class="delete-commonlanguage">删除客户</el-button>
                <el-input class="search-knowledge">
                <i class="el-icon-search el-input__icon"
                slot="suffix"/>
                </el-input>
            </el-header>
            <el-main>
                <el-table :data="tableData">
                    <el-table-column prop="date" label="日期" width="140">
                    </el-table-column>
                    <el-table-column prop="name" label="姓名" width="120">
                    </el-table-column>
                    <el-table-column prop="address" label="地址">
                    </el-table-column>
                </el-table>
            </el-main>
        </el-container>
    </div>
</template>

<script>
export default {
  data() {
    const item = {
      date: '2016-05-02',
      name: '游凯佳',
      address: '上海市普陀区金沙江路 1518 弄'
  };
  return {
      tableData: Array(20).fill(item)
  }
}
};
</script>

<style scoped>
.customer-head {
    height: 50px;
    margin: 20px;
    border-bottom: 1px solid #E6E6E6;
}
.add-customer {
    float: left;
    margin-left: 10px;
}
.delete-customer {
    float: left;
    margin-left: 10px;
}
.search-knowledge {
    float: right;
    width: 10%;
}
</style>