import Vue from 'vue'
import Vuex from 'vuex'

import {auth} from '@/store/auth'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userId: 1
  },
  mutations: {
  },
  actions: {
  },
  modules: {
    auth
  }
})