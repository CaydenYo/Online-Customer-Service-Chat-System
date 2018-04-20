<template>
  <div id="cs_dialogue">
    <el-row>
      <el-col :span="5">
        <div class="grid-content">
          <el-tabs type="border-card">
            <el-tab-pane label="当前会话">
              <el-container class="dialogue">
                <el-header>
                  <el-button v-on:click="addItem" icon="el-icon-circle-plus">接入新会话</el-button>
                </el-header>
                <el-main>
                  <div id="list">
                    <ul>
                      <li v-for="item in sessions" :class="{ active: item.id === currentSessionId }" v-if="item.serving" v-on:click="changeCurrentSessionId(item.id)">
                        <!--   :class="[item.id === currentSessionId ? 'active':'']" -->
                        <img class="avatar" :src="item.user.img">
                        <p class="name">{{item.user.name}}</p>
                      </li>
                    </ul>
                  </div>
                </el-main>
              </el-container>
            </el-tab-pane>
            <el-tab-pane label="排队列表">
              <el-container class="dialogue">
                <el-main>
                  <div id="list">
                    <ul>
                      <li v-for="(item, index) in sessions" v-if="item.waiting" v-on:click="changeToServing(item.id)">
                        <!--   :class="[item.id === currentSessionId ? 'active':'']" -->
                        <img class="avatar" :src="item.user.img">
                        <p class="name">{{item.user.name}}</p>
                        <i class="el-icon-circle-plus-outline el-icon--right"></i>
                      </li>
                    </ul>
                  </div>
                </el-main>
              </el-container>
            </el-tab-pane>
          </el-tabs>     
        </div>
      </el-col>
      <el-col :span="9">
        <div class="grid-content bg-purple-light">
          <message></message>
          <usertext></usertext>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content">
          <el-tabs type="border-card" v-model="activeName">
            <el-tab-pane label="用户资料"  name="first">
              <img src="../../../static/images/c_def.jpg" alt="" class="show_head_img">
              <div>
                <div>
                  <span>名称：{{ customer_nickname }}</span>
                </div>
                <div>
                  <span>年龄：{{ customer_age }}</span>
                </div>
                <div>
                  <span>电子邮件：{{ customer_email }}</span>
                </div>
                <div>
                  <span>住址：{{ customer_address }}</span>
                </div>
              </div>
            </el-tab-pane>
            <!-- <el-tab-pane label="知识库">配置管理</el-tab-pane> -->
          </el-tabs>
        </div>
      </el-col>
      <el-col :span="5">
        <div class="grid-content">
          <el-tabs type="border-card">
            <el-tab-pane label="常用语">
              <div v-for="o in 4" :key="o" class="text item">
                {{'常用语 ' + o }}
              </div>
            </el-tab-pane>
            <el-tab-pane label="聊天记录">配置管理</el-tab-pane>
          </el-tabs>
        </div>
      </el-col>
    </el-row>
  </div>
</template>


<script>
import { mapState } from 'vuex'
import message from '../CS_embeddedChat/message'
import usertext from '../CS_embeddedChat/usertext'

export default {
  name: 'list',
  data() {
    return {
      activeName: 'first',
      customer_nickname: 'test',
      customer_age: 15,
      customer_sex: 1,
      customer_email: '123@qq.com',
      customer_address: '上海市普陀区金沙江路 1518 弄'
    }
  },
  components: {
    message,
    usertext
  },
  computed: mapState(['sessions', 'currentSessionId']),
  methods: {
    changeCurrentSessionId: function(id) {
      this.$store.commit('changeCurrentSessionId', id)
    },
    changeToServing: function(id) {
      alert(id)
      this.$store.commit('changeToServing', id)
    },
    addItem: function(){
      this.$store.commit('addItem')
    }
  }
}
</script>

<style lang="scss" scoped>
#list {
  ul {
    list-style-type: none;
    padding: 0;
    margin: 0;
  }
  li {
    padding: 12px 15px;
    border-bottom: 1px solid #e6e6e6;
    cursor: pointer;
    &:hover {
      background-color: rgba(255, 255, 255, 0.03);
    }
  }
  li.active {
    /*注意这个是.不是冒号:*/
    background-color: rgba(255, 255, 255, 0.1);
  }
  .avatar {
    border-radius: 2px;
    width: 30px;
    height: 30px;
    float:left;
  }
  .name {
    display: inline-block;
    margin-left: 15px;
  }
}
.dialogue {
  height: 100%;
}
.el-tabs {
  height: 100%;
}
.el-row {
  height: 100%;
  margin-bottom: 20px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
  height: 100%;
}
.bg-purple-light {
  background: #e5e9f2;
}
.grid-content {
  border-radius: 4px;
  height: 100%;
  overflow: hidden;
}
.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
#cs_dialogue {
  height: 100%;
}
.el-header,
.el-footer {
  background-color: #b3c0d1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-aside {
  background-color: #d3dce6;
  color: #333;
  text-align: center;
  line-height: 200px;
}
.el-main {
  background-color: #e9eef3;
  color: #333;
  text-align: center;
}
body > .el-container {
  margin-bottom: 40px;
}
.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}
.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.text {
  font-size: 14px;
}
.item {
  padding: 18px 0;
}
.show_head_img {
  border-radius: 100%;
  width: 100px;
  margin: 50px 10px 50px 70px;
}
</style>