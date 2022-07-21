import TabsView from '@/layouts/tabs/TabsView'
import BlankView from '@/layouts/BlankView'
import PageView from "@/layouts/PageView";

/**
 * name  菜单名称
 * path  点击菜单时的跳转链接
 * meta.icon  菜单图标，图标使用 ant-design-vue 图标库，对应 Icon 组件 的 type 属性
 * meta.invisible  是否不将此路由项渲染为菜单项，默认false；如设置为 true，则生成菜单时将忽略此路由
 * meta.authority: {
      role: 'admin'
    }
 */


// 路由配置
const options = {
  routes: [
    {
      path: '/login',
      name: 'Login Page',
      component: () => import('@/pages/common/login/Login')
    },
    {
      path: '/register',
      name: 'Registration Page',
      component: () => import('@/pages/common/login/register')
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
    },
    {
      path: '/',
      name: 'Main Page',
      component: TabsView,
      redirect: '/login',
      children: [
        {
          path: 'dashboard',
          name: 'Dashboard',
          meta: {
            icon: 'dashboard'
          },
          component: BlankView,
          children: [
            {
              path: 'index',
              name: 'Workbench',
              meta: {
                page: {
                  closable: false
                }
              },
              component: () => import('@/pages/dashboard/index'),
            }
          ]
        },
        {
          path: 'notice',
          name: 'Dataset Mangement',
          meta: {
            icon: 'align-left',
          },
          component: PageView,
          children: [
            {
              meta: {
                icon: 'align-left',
              },
              path: 'index',
              name: 'Upload Dataset',
              component: () => import('@/pages/admin/notice/index'),
            },
            {
              meta: {
                icon: 'align-left',
              },
              path: 'dataSetList',
              name: 'Dataset List',
              component: () => import('@/pages/admin/notice/dataSetList'),
            },
            {
              meta: {
                icon: 'align-left',
                authority: {
                  role: 'admin'
                },
                query: {
                  id: 0
                }
              },
              path: 'detail',

              name: '添加公告',
              component: () => import('@/pages/admin/notice/detail'),
            }
          ]
        },
        {
          path: 'goods',
          name: '楼宇管理',
          meta: {
            icon: 'align-left',
            authority: {
              role: 'admin'
            },
          },
          component: PageView,
          children: [
            {
              meta: {
                icon: 'align-left',
              },
              path: 'index',
              name: '楼宇列表',
              component: () => import('@/pages/admin/goods/index'),
            },
            {
              meta: {
                invisible: true
              },
              path: 'detail',
              name: '楼宇信息',
              component: () => import('@/pages/admin/goods/detail'),
            }
          ]
        },
        {
          path: 'bill',
          name: 'Display Result',
          meta: {
            icon: 'align-left',

          },
          component: PageView,
          children: [
            {
              meta: {
                icon: 'align-left',
              },
              path: 'index',
              name: 'Report Result',
              component: () => import('@/pages/admin/bill/index'),
            },
          ]
        },
        {
          path: 'score',
          name: 'Export Report',
          meta: {
            icon: 'align-left',
          },
          component: PageView,
          children: [
            {
              meta: {
                icon: 'align-left',

              },
              path: 'index',
              name: 'Report Save/Export',
              component: () => import('@/pages/admin/score/index'),
            },
            {
              meta: {
                invisible: true
              },
              path: 'detail',
              name: '水电气表信息',
              component: () => import('@/pages/admin/score/detail'),
            },

          ]
        }, {
          path: 'user',
          name: '用户管理',
          meta: {
            icon: 'align-left',
            authority: {
              role: 'admin'
            },
          },
          component: PageView,
          children: [
            {
              meta: {
                icon: 'align-left',
              },
              path: 'index',
              name: '用户列表',
              component: () => import('@/pages/admin/user/index'),
            },
            {
              meta: {
                invisible: true
              },
              path: 'detail',
              name: '用户信息',
              component: () => import('@/pages/admin/user/detail'),
            }
          ]
        }, {
          path: 'orders',
          name: '报修投诉',
          meta: {
            icon: 'align-left',
          },
          component: PageView,
          children: [
            {
              meta: {
                icon: 'align-left',
              },
              path: 'index',
              name: '报修投诉列表',
              component: () => import('@/pages/admin/orders/index'),
            },
            {
              meta: {
                invisible: true
              },
              path: 'detail',
              name: '报修投诉信息',
              component: () => import('@/pages/admin/orders/detail'),
            },{
              meta: {
                invisible: true
              },
              path: 'verify',
              name: '报修投诉信息',
              component: () => import('@/pages/admin/orders/verify'),
            }
          ]
        },
      ]
    },
    // 设置
    {
      path: 'setting',
      name: '设置页面',
      component: TabsView,
      meta: {
        invisible: true,
      },
      children: [
        {
          path: 'modifyPwd',
          name: '修改密码',
          component: () => import('@/pages/setting/modifyPwd'),
        },
        {
          path: 'userInfo',
          name: '个人信息',
          component: () => import('@/pages/setting/userInfo'),
        }
      ]
    }
  ]
}

export default options
