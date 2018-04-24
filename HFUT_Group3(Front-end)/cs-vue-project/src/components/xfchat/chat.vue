<template>
  <div class="chat">
    <div id="capture" ref="capture" style="padding: 10px; background: #f5da55">
      <h4 style="color: #000; ">Hello world!</h4>
    </div>
    <!-- 评价 -->
    <transition name="el-zoom-in-top">
      <div class="rate" v-show="rate_visible">
        <div class="rateword1">
          请为客服打分
        </div>
        <div class="star">
          <el-rate v-model="rate_form.cs_score" :colors="['#99A9BF', '#F7BA2A', '#FF9900']">
          </el-rate>
        </div>
        <div class="rateinput">
          <el-input type="text" v-model="rate_form.content"></el-input>
        </div>
        <div>
          <el-button class="ratebtn" type="primary" @click="submitRate" icon="el-icon-check"></el-button>
        </div>
      </div>
    </transition>
    <!-- 对话框界面 -->
    <transition name="el-zoom-in-bottom">
      <div class="show" v-show="show">
        <div class="header">
          <span>{{cs_nickName}}正在为您服务</span>
          <img src="../../../static/images/close.png" alt="" @click="rate_visible=true">
        </div>
        <!-- 文本 -->
        <div class="message">
          <div class="ch
        atlog_body">
            <div class="chatlog_main" ref="chatlog">
              <ul>
                <template v-for="item in chat_past_list">
                  <li>
                    <div class="wordbody">
                      <div class="wordhead">{{ item.name }}&nbsp;&nbsp;{{ item.time }}</div>
                      <div class="wordmain">
                        <p>{{ item.content }}</p>
                      </div>
                    </div>
                  </li>
                </template>
              </ul>
            </div>
          </div>
        </div>
        <!-- 文本结束 -->
        <!-- 功能界面 -->
        <div class="tool">
          <el-button type="primary" circle @click="capture"> <img src="../../../static/images/emoji.png" alt="emoji"> </el-button>
          <input type="file" name="chatpicture" accept="image/jpeg,image/jpg,image/png" ref="chatpicture" style="display:none;" @change="onFileSelected">
          <el-button type="success" icon="el-icon-check" circle @click="$refs.chatpicture.click()"></el-button>
          <el-button type="warning" style="float:right" icon="el-icon-star-off" circle @click="submit()"></el-button>
        </div>
        <!-- 功能界面结束 -->
        <!-- 输入框 -->
        <div class="input">
          <el-input ref="textarea" type="textarea" :rows="5" placeholder="请输入内容" v-model="textarea" @keyup.enter.native="onKeyup">
          </el-input>
        </div>
        <!-- 输入框结束 -->
      </div>
    </transition>
    <!-- 对话框结束 -->
    <!-- 点击聊天按钮 -->
    <div class="div1">
      <div class="div2">
        <img src="../../../static/images/xfchat.png" alt="">
      </div>
      <div class="div3">
        <el-button type="primary" round @click="showdialog()">咨询</el-button>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      textarea: '',
      show: true,
      cs_nickName: 'zhangsan',
      rate_visible: false,
      selectedFile: '',
      rate_form: {
        cs_score: null,
        content: ''
      },
      chat_past_list: [
        {
          chatLog_id: 1,
          name: '用户',
          time: '2015.04.03',
          content: '这是第1句话'
        },
        {
          chatLog_id: 2,
          name: '客服',
          time: '2015.04.03',
          content: '这是第2句话aaaaaaaaaaaaaaaaaaaa,aaaaaaa,a'
        },
        {
          chatLog_id: 3,
          name: '用户',
          time: '2015.04.03',
          content:
            '这是第3句话这是第4句话这是第4句话这是第4句话这是第4句话这是第4句话这是第4句话'
        },
        {
          chatLog_id: 4,
          name: '客服',
          time: '2015.04.03',
          content:
            '这是第4句话这是第4句话这是第4句话这是第4句话这是第4句话这是第4句话'
        },
        {
          chatLog_id: 5,
          name: '用户',
          time: '2015.04.03',
          content: '这是第5句话'
        },
        {
          chatLog_id: 5,
          name: '客服',
          time: '2015.04.03',
          content: '这是第6句话'
        },
        {
          chatLog_id: 7,
          name: '用户',
          time: '2015.04.03',
          content: '这是第7句话'
        }
      ]
    }
  },
  mounted() {
    this.downmessage()
    this.show = false
  },
  methods: {
    downmessage() {
      console.log('已最下面')
      this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight
    },
    // 展示会话框
    showdialog() {
      this.show = !this.show
      this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight
      console.log('open dialog')
    },
    // 按回车发送信息
    onKeyup(e) {
      if (e.keyCode === 13) {
        this.submit()
      }
    },
    // 发送函数
    submit() {
      if (this.textarea === '') {
        this.$message({
          message: '文字不能为空',
          type: 'warning'
        })
      } else {
        console.log('长度:' + this.textarea.length)
        console.log(this.textarea)
        var d = new Date()
        var new_message = {
          chatLog_id: 1,
          name: '用户',
          time: d.getHours() + ' : ' + d.getMinutes(),
          content: this.textarea
        }
        this.chat_past_list.push(new_message)
        setTimeout(
          () =>
            (this.$refs.chatlog.scrollTop = this.$refs.chatlog.scrollHeight),
          50
        )
        this.textarea = ''
      }
    },
    // 提交评价
    submitRate() {
      if (this.rate_form.cs_score === null || this.rate_form.content === '') {
        this.$message({
          message: '请填写星级和评价',
          type: 'warning',
          center: true
        })
      } else {
        this.rate_visible = false
        this.show = false
        this.$message({
          message: '感谢您的使用',
          type: 'success',
          center: true
        })
        // 提交函数
      }
    },
    onFileSelected(event) {
      console.log(event)
      this.selectedFile = event.target.files[0]
      var file = event.target.files[0]
    }
  }
}
</script>

<style>
.div1 {
  position: absolute;
  right: 2%;
  bottom: 10%;
  color: #ffffff;
}
.div2 {
  width: 50px;
  height: 50px;
  margin-bottom: 5px;
  padding-left: 10px;
}
.div2 img {
  width: 50px;
  height: auto;
}
.show {
  position: absolute;
  right: 1%;
  bottom: 15%;
  overflow: hidden;
  width: 400px;
  height: 550px;
  z-index: 100;
}
.header {
  background-color: #409eff;
  height: 50px;
  text-align: center;
}
.header span {
  position: absolute;
  top: 10px;
  left: 20px;
}
.header img {
  position: absolute;
  top: 5px;
  right: 8px;
  width: 23px;
  height: auto;
}
.message {
  height: 350px;
}
.chatlog_body {
  overflow: hidden;
  width: 400px;
}
.chatlog_main {
  overflow: auto;
  height: 350px;
  width: 450px;
  overflow-x: hidden;
  background-color: #e0e0e0;
}
.wordbody {
  width: 400px;
  margin: 10px;
}
.wordhead {
  width: 350px;
  font-size: 12px;
}
.wordmain {
  min-width: 30px;
  width: fit-content;
  max-width: 240px;
  word-wrap: break-word;
  word-break: normal;
}
.wordmain p {
  background-color: #fff;
  color: black;
  font-size: 16px;
  padding: 5px;
  border-radius: 7px;
  border: 1px solid #bfbfbf;
}
.tool {
  height: 40px;
  background-color: darkgray;
  padding: 0px 5px 0px 5px;
}
.tool img {
  width: 12px;
  height: auto;
}
.input {
  height: 100px;
  background-color: darkgray;
}
li {
  list-style: none;
}
ul {
  padding-left: 0px;
}
.rate {
  position: absolute;
  bottom: 40%;
  right: 7%;
  width: 200px;
  height: 200px;
  background-color: rgb(224, 224, 245);
  z-index: 999999;
  padding: 10px;
  -moz-box-shadow: 1px 1px 5px #333333;
  -webkit-box-shadow: 1px 1px 5px #333333;
  box-shadow: 1px 1px 5px #333333;
}
.rateword1 {
  margin: 10px 0px 15px 0px;
  font-size: 16px;
  text-align: center;
}
.star {
  margin-left: 40px;
  margin-bottom: 15px;
}
.rateinput {
  margin: 5px, 5px, 10px, 5px;
}
.ratebtn {
  margin: 10px;
  width: 90%;
}
</style>


