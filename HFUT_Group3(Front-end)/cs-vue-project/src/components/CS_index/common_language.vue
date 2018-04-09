    <template>
      <div id="common_language">
          <el-tabs v-model="activeName" @tab-click="handleClick" style="height:100%;">
            <el-tab-pane label="知识库管理" name="first">
              知识库管理
          </el-tab-pane>
          <el-tab-pane label="常用语管理" name="second">常用语管理</el-tab-pane>
      </el-tabs>
  </div>
</template>

<script>
export default {
    data() {
      return {
        activeName: 'first'
    };
},
methods: {
  handleClick(tab, event) {
    console.log(tab, event);
}
}
};
</script>

<style type="text/css" scoped>
#common_language {
    width: 100%;
    height: 100%;
}
</style>