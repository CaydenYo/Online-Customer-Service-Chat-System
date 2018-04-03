<template>
<div id="hehe" >
        <div id="xf" style="background-color:red;" v-if="show">
            <div class="content" style="background-color:blue;">
                <div class="chatbox" style="background-color:green;">
                    <message></message>
                    <v-text></v-text>
                </div>
            </div>
        </div>
        <button @click="show=!show" style="position: fixed; right: 5px; bottom: 60px; z-index: 10; padding: 3px 3px 0 3px;">咨詢</button></div>
</template>

<script>
import message from '../../components/message/message'
import vText from '../../components/text/text'
export default {
    data(){
        return{
            show:true
        }
    },
  components: {
    message,
    vText
  }
}
</script>

<style lang="stylus" scoped>
.content {
    display: flex;
    width: 500px;
}

.chatbox {
    flex: 1;
}

.xf {
    position: absolute;
    z-index: 99999;
    margin-top: 5%;
}
</style>