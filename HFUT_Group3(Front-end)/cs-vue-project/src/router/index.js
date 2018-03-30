import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import CSM_UI from '@/components/CSM_UI'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'CSM_UI',
      component: CSM_UI
    }
  ]
})
