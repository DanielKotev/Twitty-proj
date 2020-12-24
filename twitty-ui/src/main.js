import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as axios from 'axios'
import { BButton, BFormSelect, BForm, PaginationPlugin, BTable } from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.component('b-form-select', BFormSelect)
Vue.component('b-form', BForm)
Vue.component('b-table', BTable)
Vue.component('b-button', BButton)
Vue.component('b-form-select', BFormSelect)
Vue.component('b-form', BForm)
Vue.use(PaginationPlugin)
Vue.prototype.$axios = axios
Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
