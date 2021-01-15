import Vue from 'vue'
import VueRouter from 'vue-router'
import Register from '@/views/Register'
import Login from '@/views/Login'
import HomePage from '../views/HomePage.vue'
import UserPage from '../views/UserPage.vue'
import FindUsers from "@/views/FindUsers"

Vue.use(VueRouter)

const routes = [
  {
    path: '/Login',
    name: 'Login',
    component: Login
  },
  {
    path: '/Register',
    name: 'Register',
    component: Register
  },
  {
    path: '/',
    name: 'HomePage',
    component: HomePage
  },
  {
    path: '/UserPage/:id',
    name: 'UserPage',
    component: UserPage
  },
  {
    path: '/FindUsers/:search',
    name: 'FindUsers',
    component: FindUsers
  }

]

const router = new VueRouter({
  mode: "history",
  routes
})

export default router
