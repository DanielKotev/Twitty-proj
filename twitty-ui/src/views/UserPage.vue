<template>
  <div id="page-content">
    <div v-if="user">
      <div id="user-card">
        <h3 class="center">{{user.username}}</h3>
        <div class="center" v-if="!ownPage()">
          <div v-if="followed">
            <b-button variant="danger" v-on:click="unfollow">Unfollow</b-button>
          </div>
          <div v-else>
            <b-button variant="success" v-on:click="follow">Follow</b-button>
          </div>
        </div>
      </div>
      <div v-if="ownPage()">
        <create-post/>
      </div>
      <div v-if="this.posts.length != 0">
        <div class="posts" v-for="post in posts" v-bind:key="post.id">
          <post v-bind:post="post"/>
        </div>
      </div>
      <div v-else>
        <h5>No posts found</h5>
      </div>
    </div>
    <div v-else>
      <h2>No user was passed</h2>
    </div>
  </div>
</template>

<script>
import Post from "../components/Post";
import UserService from '../services/user-service'
import CreatePost from "../components/CreatePost";

export default {
  name: "UserPage",
  components: {
    "post": Post,
    "create-post": CreatePost
  },
  data () {
    return {
      followed: true,
      user:'',
      posts: [],
      currentPage: 1,
      perPage: 5
    }
  },
  mounted() {
    this.scroll()
  },
  beforeRouteEnter (to, from, next) {
    if (to.params) {
      UserService.getUserById(to.params.id).then(
          response => next(vm => {
                vm.setUser(response.data)
                if (vm.user) {
                  vm.isFollowing()
                  vm.getNextPageOfPosts()
                }
              }
          )
      )
    }
  },
  methods: {
    isFollowing () {
      UserService.isFollowing(this.$store.state.userId, this.user.id).then(
          response => {
            this.followed = response.data.isFollowing
          }
      )
    },
    follow () {
      UserService.follow(this.$store.state.userId, this.user.id).then(
          this.followed = true
      )
    },
    unfollow () {
      UserService.unfollow(this.$store.state.userId, this.user.id).then(
          this.followed = false
      )
    },
    ownPage () {
      return this.user.id === this.$store.state.userId
    },
    setUser (user) {
      this.user = user;
    },
    getUser (id) {
      UserService.getUserById(id).then(
          response => {
            this.user = response.data
          }
      )
    },
    getNextPageOfPosts () {
      UserService.getPostsOfUser(this.user.id, this.currentPage++, this.perPage).then(response => {
        this.posts.push(...response.data.posts)
        this.totalPages = response.data.totalPages
      })
    },
    scroll () {
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

<style scoped>

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