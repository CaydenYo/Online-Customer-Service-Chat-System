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
import Customer_UI from '@/components/Customer_UI'

import UserHCM from '@/components/UserHoverChat/UserHCM'
import UserHCM2 from '@/components/UserHoverChat2/UserHCM2'
import Embedded_Chat from '@/components/Embedded_Chat'
import CS_UI from '@/components/CS_UI'
import cs_dialogue from '@/components/CS_index/cs_dialogue'
import cs_statics from '@/components/CS_index/cs_statics'
import cs_settings from '@/components/CS_index/cs_settings'
import common_language from '@/components/CS_index/common_language'
import customer_information from '@/components/CS_index/customer_information'


Vue.use(Router)

export default new Router({
  routes: [{

      path: '/Customer_UI',
      name: 'Customer_UI',
      component: Customer_UI

      path: '/',
      name: 'CS_UI',
      component: CS_UI
    },
    {
      path: '/show_embedded_cs',
      name: 'Embedded_Chat',
      component: Embedded_Chat
    },
    {
      path: '/',
      name: 'CS_UI',
      redirect: '/cs_dialogue',
      component: CS_UI,
      children: [{
        path: '/cs_dialogue',
        component: cs_dialogue,
        name: 'cs_dialogue'
      }]
    },
    {
      path: '/',
      name: 'CS_UI',
      component: CS_UI,
      children: [{
        path: '/cs_statics',
        component: cs_statics,
        name: 'cs_statics'
      }]
    },
    {
      path: '/',
      name: 'CS_UI',
      component: CS_UI,
      children: [{
        path: '/cs_settings',
        component: cs_settings,
        name: 'cs_settings'
      }]
    },
    {
      path: '/',
      name: 'CS_UI',
      component: CS_UI,
      children: [{
        path: '/common_language',
        component: common_language,
        name: 'common_language'
      }]
    },
    {
      path: '/',
      name: 'CS_UI',
      component: CS_UI,
      children: [{
        path: '/customer_information',
        component: customer_information,
        name: 'customer_information'
      }]

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
