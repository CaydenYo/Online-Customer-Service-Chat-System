<template>
  <div id="cs_statics">
    <div class="cs-info">
        <a href="">客服头像</a>
        <p>客服名称</p>
        <el-rate
        v-model="cs_score"
        disabled
        show-score
        text-color="#ff9900"
        score-template="{value}">
        </el-rate>
  </div>
  <div class="statics-frame">
      <div class="allActive">
        <el-row class="head">
          <el-col :span="24" class="statics">
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
<div class="graphic">
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
      inWait: 28,
      cs_score: 3.5
  }
}
}
</script>


<style scoped>
#cs_statics {
  height: 100%;
  width: 100%;
}
.statics {
   padding:20px 0;
   background: #33CCCC;
}
.cs-info {
  height:20%;
  width:100%;
}
.graphic {
  height:50%;
}
.static-frame {
   height:10%;
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