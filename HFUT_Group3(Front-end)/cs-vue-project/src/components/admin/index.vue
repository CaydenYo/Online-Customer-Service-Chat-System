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
    <div class="card2">
      <el-card class="box-card">
        <h3>客服列表</h3>
        <el-table :data="tableData3" height="250" border style="width: 100%">
          <el-table-column prop="cs_workId" label="工号" width="100">
          </el-table-column>
          <el-table-column prop="cs_name" label="姓名" width="100">
          </el-table-column>
          <el-table-column prop="cs_waited_number" label="等待人数" width="180">
          </el-table-column>
          <el-table-column prop="cs_operated_number" label="正在服务人数" width="180">
          </el-table-column>
          <el-table-column prop="cs_score" label="评分" width="180">
          </el-table-column>
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  data: function() {
    return {
      name: 'allActive',
      totalKfNum: 88,
      totalActiveNum: 22,
      inConv: 25,
      inWait: 28,
      index_url: '/listNumbers',
      query_ocs_url: '',
      index_post: {
        company_id: JSON.parse(localStorage.getItem('company_id'))
      },
      tableData3: []
    }
  },
  mounted() {
    this.init()
    this.query_ocs()
  },
  methods: {
    init() {
      var params = new URLSearchParams()
      let _this = this
      params.append('data', JSON.stringify(this.index_post))
      this.$axios({
        method: 'post',
        url: this.rootUrl + _this.index_url,
        data: params
      }).then(res => {
        var result = res.data
        _this.totalKfNum = result[0]
        _this.totalActiveNum = result[1]
        _this.inConv = result[2]
        _this.inWait = result[3]
      })
    }
  },
  query_ocs() {
    var params = new URLSearchParams()
    let _this = this
    params.append('data', JSON.stringify(this.index_post))
    this.$axios({
      method: 'post',
      url: this.rootUrl + _this.query_ocs_url,
      data: params
    }).then(res => {
      _this.tableData3 = res.data
    })
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
.general {
  margin-bottom: 50px;
}
</style>
