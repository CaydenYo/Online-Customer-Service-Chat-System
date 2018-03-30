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
            </el-switch>
            <br>
            <el-input v-model="input" placeholder="机器人名称"></el-input><br><br>
            <el-button class="btn-c" type="primary">确定</el-button>
          </el-card>
        </div>
      </el-col>
      <el-col :span="8">
        <div class="grid-content bg-purple">
          <el-card>
            <div slot="header">客服设置</div>
            <h5>分配方式</h5>
            <el-switch style="display: block" v-model="value3" active-color="#13ce66" inactive-color="#ff4949" active-text="老用户" inactive-text="轮流">
            </el-switch>
            <h5>用户信息展示</h5>
            <el-switch style="display: block" v-model="value4" active-color="#13ce66" inactive-color="#ff4949" active-text="开启" inactive-text="关闭">
            </el-switch>
            <h5>同时接待人数</h5>
            <el-input-number v-model="num1" @change="handleChange" :min="1" :max="100" label="描述文字"></el-input-number>
            <br><br>
            <el-button class="btn-c" type="primary">确定</el-button>
          </el-card>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value1: true,
      value2: true,
      value3: true,
      value4: true,
      input: '',
      num1: 1
    }
  },
  methods: {
    handleChange(value) {
      console.log(value)
    }
  }
}
</script>

<style>
.btn-c {
  width: 100%;
}
</style>
