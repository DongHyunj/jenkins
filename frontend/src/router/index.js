import { createRouter, createWebHistory } from 'vue-router'
import BoardList from '../views/BoardList.vue'
import BoardDetail from '../views/BoardDetail.vue'
import BoardReg from '../views/BoardReg.vue'

const routes = [
  { path: '/', redirect: '/board/list' },
  { path: '/board/list', component: BoardList },
  { path: '/board/:idx', component: BoardDetail },
  { path: '/board/reg', component: BoardReg },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
