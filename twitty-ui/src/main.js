import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as axios from 'axios'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import VeeValidate from 'vee-validate'
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import { BButton, BFormTextarea, BFormInput, BDropdown, BDropdownItemButton, BForm, BFormSelect, PaginationPlugin, BTable, BootstrapVue, IconsPlugin } from 'bootstrap-vue'

Vue.component('b-button', BButton)
Vue.component('b-form', BForm)
Vue.component('b-form-textarea', BFormTextarea)
Vue.component('b-form-input', BFormInput)
Vue.component('b-dropdown', BDropdown)
Vue.component('b-dropdown-item-button', BDropdownItemButton)
Vue.component('font-awesome-icon', FontAwesomeIcon)
Vue.component('b-form-select', BFormSelect)
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.component('b-table', BTable)
Vue.component('b-form-select', BFormSelect)
Vue.use(PaginationPlugin)

import {
  faHome,
  faUser,
  faUserPlus,
  faSignInAlt,
  faSignOutAlt
} from '@fortawesome/free-solid-svg-icons'
library.add(faHome, faUser, faUserPlus, faSignInAlt, faSignOutAlt);
Vue.config.productionTip = false;

Vue.use(VeeValidate);
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
