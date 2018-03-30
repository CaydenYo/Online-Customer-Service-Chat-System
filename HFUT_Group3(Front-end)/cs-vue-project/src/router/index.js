import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import CSM_UI from '@/components/CSM_UI'
import CsLogin from '@/components/CsLogin'
import CsRegister from '@/components/CsRegister'
import AdminHome from '@/components/AdminHome'
import index from '@/components/index'
import kfgl from '@/components/KfManage'
import yhgl from '@/components/CsManage'
import settings from '@/components/Settings'
import know from '@/components/Know'
import UserChat from '@/components/UserChat'

Vue.use(Router)

export default new Router({
  routes: [{
      path: '/',
      name: 'CSM_UI',
      component: CSM_UI
    },
    {
      path: '/UserChat',
      name: '聊天',
      component: UserChat
    },
    {
      path: '/CsLogin',
      name: '客服登录',
      component: CsLogin
    },
    {
      path: '/CsRegister',
      name: '客服注册',
      component: CsRegister
    },
    {
      path: '/',
      name: 'AdminHome',
      redirect: '/index',
      component: AdminHome,
      children: [{
        path: '/index',
        component: index,
        name: '首页'
      }]
    },
    {
      path: '/',
      name: '客服管理',
      component: AdminHome,
      children: [{
        path: '/kfgl',
        component: kfgl,
        name: '客服管理'
      }]
    },
    {
      path: '/',
      name: '用户管理',
      component: AdminHome,
      children: [{
        path: '/yhgl',
        component: yhgl,
        name: '用户管理'
      }]
    },
    {
      path: '/',
      name: '设置',
      component: AdminHome,
      children: [{
        path: '/settings',
        component: settings,
        name: '设置'
      }]
    },
    {
      path: '/',
      name: '知识库',
      component: AdminHome,
      children: [{
        path: '/know',
        component: know,
        name: '知识库'
      }]
    }
  ]
})
