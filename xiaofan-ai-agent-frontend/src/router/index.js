import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'Home',
    component: () => import('../views/Home.vue'),
    meta: {
      title: '首页 - 小凡AI编程助手智能体应用平台',
      description: '小凡AI编程助手智能体应用平台提供AI编程助手和AI超级智能体服务，满足您的各种AI对话需求'
    }
  },
  {
    path: '/program-assis',
    name: 'programassis',
    component: () => import('../views/ProgramAssis.vue'),
    meta: {
      title: '小凡AI编程助手',
      description: '小凡AI编程助手是小凡AI编程助手应用平台的专业编程顾问，帮你解答各种编程问题，提供建议'
    }
  },
  {
    path: '/super-agent',
    name: 'SuperAgent',
    component: () => import('../views/SuperAgent.vue'),
    meta: {
      title: '小凡AI编程助手',
      description: '小凡AI编程助手是小凡AI编程助手应用平台的专业编程顾问，帮你解答各种编程问题，提供建议'
    }
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

// 全局导航守卫，设置文档标题
router.beforeEach((to, from, next) => {
  // 设置页面标题
  if (to.meta.title) {
    document.title = to.meta.title
  }
  next()
})

export default router 