<template>
  <div id="page-content">
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
        <post v-bind:post="post" v-on:deleted="removePost"/>
      </div>
    </div>
    <div v-else>
      <h5>No posts found</h5>
    </div>
  </div>
</template>

<script>
import Post from "../components/Post";
import UserServices from '../services/user-services'
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
    if (!this.$store.state.userId)
    {
      this.$router.push('Login')
    }
    this.init(this.$route.params.id)
    this.scroll()
  },
  // beforeRouteEnter (to, from, next) {
  //   if (to.params) {
  //     UserServices.getUserById(to.params.id).then(
  //         response => next(vm => {
  //               vm.setUser(response.data)
  //               if (vm.user) {
  //                 vm.isFollowing()
  //                 vm.getNextPageOfPosts()
  //               }
  //             }
  //         )
  //     )
  //   }
  // },
  methods: {
    isFollowing () {
      UserServices.isFollowing(this.$store.state.userId, this.user.id).then(
          response => {
            this.followed = response.data.isFollowing
          }
      )
    },
    follow () {
      UserServices.follow(this.$store.state.userId, this.user.id).then(
          this.followed = true
      )
    },
    unfollow () {
      UserServices.unfollow(this.$store.state.userId, this.user.id).then(
          this.followed = false
      )
    },
    ownPage () {
      return this.user.id === this.$store.state.userId
    },
    init (id) {
      UserServices.getUserById(id).then(response => {
            this.user = response.data;
            this.isFollowing()
            this.getNextPageOfPosts()
          }
      )
    },
    // setUser (user) {
    //   this.user = user
    // },
    getNextPageOfPosts () {
      UserServices.getPostsOfUser(this.user.id, this.currentPage++, this.perPage).then(response => {
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
    },
    removePost(id) {
      this.posts = this.posts.filter(p => p.id != id)
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