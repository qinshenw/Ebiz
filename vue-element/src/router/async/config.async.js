/**
 * 基本路由配置
 * @type
 */
import TabsView from '@/layouts/tabs/TabsView'
import BlankView from '@/layouts/BlankView'
import PageView from '@/layouts/PageView'
// 异步路由配置  基础的路由
const routesConfig = [
  {
    path: '/login',
    name: '登录页',
    component: () => import('@/pages/common/login/Login')
  },
  {
    path: '*',
    name: '404',
    component: () => import('@/pages/common/exception/404'),
  },
  {
    path: '/403',
    name: '403',
    component: () => import('@/pages/common/exception/403'),
  }
]
/**
 * TabsView / 多页签视图，包含了 AdminLayout 布局、多页签头和路由视图内容区
 *
 * PageView / 页面视图，包含了 PageLayout 布局和路由视图内容区
 *
 * BlankView / 空白视图，仅包含一个路由视图内容区
 * @type {{redirect: string, path: string, component: string, children: [{path: string, component: string, name: string, icon: string}, {path: string, component: string, children: [{path: string, component: string, name: string}], name: string, icon: string}, {path: string, component: string, children: [{path: string, component: string, name: string}, {path: string, component: string, name: string}], name: string, icon: string}], name: string}[]}
 */

const extra = [
  // 设置
  {
    path: 'setting',
    name: '设置页面',
    component: PageView,
    meta: {
      invisible: true,
    },
    children: [
      {
        path: 'modifyPwd',
        name: '修改密码',
        component: () => import('@/pages/setting/modifyPwd'),
      }
    ]
  }
]

const options = {
  routes: routesConfig,
  extra: extra
}

export default options
