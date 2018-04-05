<template>
  <div id="sets">
    <el-row :gutter="20">
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card>
            <div slot="header">接入方式</div>
            <el-switch style="display: block" v-model="value1" active-color="#13ce66" inactive-color="#ff4949" active-text="悬浮式" inactive-text="网页">
            </el-switch>
            <br><br>
            <el-button class="btn-c" type="primary">确定</el-button>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card>
            <div slot="header">机器人设置</div>
            <el-switch style="display: block" v-model="value2" active-color="#13ce66" inactive-color="#ff4949" active-text="开启" inactive-text="关闭">
            </el-switch><br><br>
            <el-button class="btn-c" type="primary">确定</el-button>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card>
            <div slot="header">客服设置</div>
            <el-form ref="form3" :model="form3">
              <h5>分配方式</h5>
              <el-switch style="display: block" v-model="form3.dstribution_type" active-color="#13ce66" inactive-color="#ff4949" active-text="老用户" active-value=1 inactive-text="轮流" inactive-value=0>
              </el-switch>
              <h5>用户信息展示</h5>
              <el-switch style="display: block" v-model="form3.customer_info_flag" active-color="#13ce66" inactive-color="#ff4949" active-text="开启" active-value=1 inactive-text="关闭" inactive-value=0>
              </el-switch>
              <h5>同时接待人数</h5>
              <el-input-number v-model="form3.mininum_operating_num" @change="handleChange" :min="1" :max="100" label="同时接待人数"></el-input-number>
              <br><br>
              <el-button class="btn-c" type="primary" @click="submitForm('form3')">确定</el-button>
            </el-form>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>



<style>
.btn-c {
  width: 100%;
}
</style>
