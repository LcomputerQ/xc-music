import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/Home',
    component: () => import('@/views/Home/Home.vue'),
    meta: { title: '自述文件' },
    children: [
      {
        path: '/Info',
        component: () => import('@/views/Home/InfoPage.vue'),
        meta: { title: 'Info' }
      },
      {
        path: '/Consumer',
        component: () => import('@/views/User/ConsumerPage.vue'),
        meta: { title: 'Consumer' }
      },
      {
        path: '/Collect',
        component: () => import('@/views/User/CollectPage.vue'),
        meta: { title: 'Collect' }
      },
      {
        path: '/Latest',
        component: () => import('@/views/User/LatestVisitors.vue'),
        meta: { title: 'Latest' }
      },
      {
        path: '/OnlineNumber',
        component: () => import('@/views/User/OnlineNumberPage.vue'),
        meta: { title: 'OnlineNumber' }
      }
    ]
  },
  {
    path: '/',
    component: () => import('@/views/Login.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
