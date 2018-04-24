<template>
  <div id="cs_statics">
    <el-card class="box_card">
      <div class="show_head">
        <img src="../../../static/images/cs_def.png" alt="" class="show_head_img">
        <div class="show_head_text">{{ cs_nickName }}</div>
        <el-rate v-model="avg_eva" disabled show-score text-color="#ff9900" score-template="{value}">
        </el-rate>
      </div>
    </el-card>
    <div class="general">
      <el-row type="flex" justify="space-around">
        <el-col :span="5" class="col col1">
          <div class="overview">
            <i class="iicon el-icon-share"></i>
            <span class="word">会话总数</span>
            <span class="num">{{ total_dialog }}</span>
          </div>
        </el-col>
        <el-col :span="5" class="col col2">
          <div class="overview">
            <i class="iicon el-icon-service"></i>
            <span class="word">总时长(min)</span>
            <span class="num">{{ total_server_time }}</span>
          </div>
        </el-col>
        <el-col :span="5" class="col col3">
          <div class="overview">
            <i class="iicon el-icon-phone"></i>
            <span class="word">平均评价</span>
            <span class="num">{{ avg_eva }}</span>
          </div>
        </el-col>
        <el-col :span="5" class="col col4">
          <div class="overview">
            <i class="iicon el-icon-loading"></i>
            <span class="word">今日会话</span>
            <span class="num">{{ today_chat }}</span>
          </div>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  name: 'allActive',
  data: function() {
    return {
      cs_nickName: sessionStorage.getItem('cs_nickName'),
      total_dialog: 46,
      total_server_time: 50,
      avg_eva: 4.8,
      today_chat: 5,
      cs_score: 3.5,
      init_css_url: '/customerService/showTimeAndScore',
      init_css: {
        cs_id: sessionStorage.getItem('cs_id')
      }
    }
  },
  mounted() {
    this.init()
  },
  methods: {
    init() {
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.init_css))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.init_css_url,
        data: params
      }).then(res => {
        var result = res.data
        _this.total_dialog = result[0]
        _this.total_server_time = result[1]
        _this.avg_eva = result[2] / 10
        _this.today_chat = result[3]
      })
    }
  }
}
</script>


<style scoped>
.box_card {
  margin-bottom: 5%;
}
.show_head_img {
  border-radius: 100%;
  width: 100px;
  margin: 10px 50px 10px 10px;
  float: left;
}
.show_head_text {
  margin-bottom: 20px;
}
.col {
  border-radius: 4px;
  min-height: 150px;
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
  padding: 40px 12px 12px 12px;
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
.general {
  margin-bottom: 50px;
}
</style>