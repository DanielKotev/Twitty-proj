import Vue from 'vue'
import Vuex from 'vuex'

import {auth} from '@/store/auth'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId: ''
  },
  mutations: {
    login(state,id) {
      state.userId = id
      console.log('id set to ' + id)
    },
    logout(state) {
      state.userId = null
      console.log('logged out')
    }
  },
  actions: {

  },
  modules: {
    auth
  }
})