<template>
  <div id="cs_statics">
    <div style="height:20%;width:100%;">
      客服资料
    </div>
    <div style="height:10%;">
      <div class="allActive">
        <el-row class="head">
          <el-col :span="24" style="padding:20px 0;background: #33CCCC;">
            <el-col :span="6">
              {{ totalKfNum }}<br>
              <span>客服总数</span>
            </el-col>
            <el-col :span="6">
              {{ totalActiveNum }}<br>
              <span>在线客服</span>
            </el-col>
            <el-col :span="6">
              {{ inConv }}<br>
              <span>接入会话</span>
            </el-col>
            <el-col :span="6">
              {{ inWait }}<br>
              <span>正在等待</span>
            </el-col>
          </el-col>
        </el-row>
      </div>
    </div> 
    <div style="height:50%;">
      图表
    </div>
  </div>
</template>

<script>
export default {
  name: 'allActive',
  data: function() {
    return {
      totalKfNum: 10,
      totalActiveNum: 5,
      inConv: 25,
      inWait: 28
    }
  }
}
</script>


<style scoped>
#cs_statics {
  height: 100%;
  width: 100%;
}

.allActive > .head > .el-col > .el-col {
  padding: 20px 0;
  border-right: solid 1px #fff;
}

.allActive .head {
  text-align: center;
  color: #fff;
  font-size: 30px;
  margin-bottom: 50px;
}

.allActive .head span {
  font-size: 16px;
}
</style>