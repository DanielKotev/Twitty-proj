import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import { BButton, BFormTextarea, BFormInput, BDropdown, BDropdownItemButton, BForm, ModalPlugin } from 'bootstrap-vue'

Vue.component('b-button', BButton)
Vue.component('b-form', BForm)
Vue.component('b-form-textarea', BFormTextarea)
Vue.component('b-form-input', BFormInput)
Vue.component('b-dropdown', BDropdown)
Vue.component('b-dropdown-item-button', BDropdownItemButton)
Vue.use(ModalPlugin)

Vue.config.productionTip = false

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
