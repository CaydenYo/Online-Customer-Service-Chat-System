import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import chat from '@/page/chat/chat'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'chat',
      component: chat
    }
  ]
})
