<template>
  <div class="allActive">
    <div class="general">
      <el-row type="flex" justify="space-around">
        <el-col :span="5" class="col col1">
          <div class="overview">
            <i class="iicon el-icon-share"></i>
            <span class="word">客服总数</span>
            <span class="num">{{ totalKfNum }}</span>
        </div>
    </el-col>
    <el-col :span="5" class="col col2">
      <div class="overview">
        <i class="iicon el-icon-service"></i>
        <span class="word">在线客服</span>
        <span class="num">{{ totalActiveNum }}</span>
    </div>
</el-col>
<el-col :span="5" class="col col3">
  <div class="overview">
    <i class="iicon el-icon-phone"></i>
    <span class="word">接入会话</span>
    <span class="num">{{ inConv }}</span>
</div>
</el-col>
<el-col :span="5" class="col col4">
  <div class="overview">
    <i class="iicon el-icon-loading"></i>
    <span class="word">正在等待</span>
    <span class="num">{{ inWait }}</span>
</div>
</el-col>
</el-row>
</div>
<div class="pan">
  <el-row type="flex" justify="space-around">
    <el-col :span="7" class="pcol pcol1">
      <el-card>
        <div slot="header">
          <span>15日每日会话数</span>
      </div>
  </el-card>
</el-col>
<el-col :span="7" class="pcol pcol2">
  <el-card>
    <div slot="header">
      <span>5分钟内实时人数</span>
  </div>
</el-card>
</el-col>
<el-col :span="7" class="pcol pcol3">
  <el-card>
    <div slot="header">
      <span>7日会话时段比</span>
  </div>
</el-card>
</el-col>
</el-row>
</div>
</div>
</template>

<script>
export default {
  company_id: JSON.parse(localStorage.getItem('company_id')),
  name: 'allActive',
  data: function() {
    return {
      totalKfNum: 88,
      totalActiveNum: 22,
      inConv: 25,
      inWait: 28,
      four_data_url: ''
  }
},
mounted() {
    this.init()
},
methods: {
    init() {
      console.log(JSON.stringify(this.company_id))
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.company_id))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.four_data_url,
        data: params
    }).then(res => {
        var result = JSON.parse(res.data)
        _this.totalKfNum = result.totalKfNum
        _this.totalActiveNum = result.totalActiveNum
        _this.inConv = result.inConv
        _this.inWait = result.inWait
    })
}
}
}
</script>
<style>
.col {
  border-radius: 4px;
  min-height: 80px;
  text-align: center;
}
.col1 {
  background-color: #67c23a;
  box-shadow: 0px 10px 10px rgba(103, 194, 58, 0.5);
}
.col2 {
  background-color: #e6a23c;
  box-shadow: 0px 10px 10px rgba(230, 162, 60, 0.5);
}
.col3 {
  background-color: #f56c6c;
  box-shadow: 0px 10px 10px rgba(245, 108, 108, 0.5);
}
.col4 {
  background-color: #909399;
  box-shadow: 0px 10px 10px rgba(141, 147, 153, 0.5);
}
.overview {
  color: #fff;
  padding: 12px;
}
.iicon {
  margin-right: 0.3em;
  float: left;
}
.word {
  font-size: 1.2em;
  font-weight: 200;
}
.num {
  font-size: 3em;
}
.pan {
  margin: 20px;
}
.pcol {
  min-height: 450px;
}
</style>
