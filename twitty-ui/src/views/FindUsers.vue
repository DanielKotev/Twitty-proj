<template>
  <div id="page-content">
    <div v-for="user in users" v-bind:key="user.id">
      <div id="user-card">
        <router-link :to="{name: 'UserPage', params: {id: user.id}}" tag="h5" class="center">{{user.username}}</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import UserServices from "@/services/user-services";

export default {
  name: "FindUsers.vue",
  data() {
    return {
      search: '',
      users: [],
      perPage: 10,
      currentPage: 1
    }
  },
  mounted() {
    this.search = this.$route.params.search
    this.searchPeople()
    this.scroll()
  },
  // beforeRouteEnter(to, from, next) {
  //   console.log('entered router with params ' + to.params.search)
  //   if (to.params) {
  //     next(vm => {
  //       vm.search = to.params.search
  //       vm.searchPeople()
  //     })
  //   }
  // },
  methods: {
    searchPeople() {
      UserServices.getFriendByName(this.search, this.currentPage++, this.perPage).then(
          response => {
            this.users.push(...response.data.friends)
          })
    },
    scroll() {
      window.onscroll = () => {
        let bottomOfWindow = document.documentElement.scrollTop + window.innerHeight === document.documentElement.offsetHeight;

        if (bottomOfWindow && this.currentPage <= this.totalPages) {
          this.getNextPageOfPosts()
        }
      }
    }
  }
}
</script>

<style>

#page-content {
  max-width: 600px;
  margin: 0px auto;
}

#user-card {
  display: grid;
  margin: 10px auto;
  padding: 5px 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #eee;
}

.center {
  margin: 10px auto;
}

</style>