<template>
  <div id="page-content">
    <create-post/>
    <div v-if="this.posts.length != 0">
      <div class="posts" v-for="post in posts" v-bind:key="post.id">
        <post v-bind:post="post" v-on:deleted="updatePage"/>
      </div>
    </div>
    <div v-else>
      <h5>No posts found</h5>
    </div>
  </div>
</template>

<script>
import Post from "../components/Post";
import CreatePost from "../components/CreatePost";
import UserServices from "../services/user-services";

export default {
  name: "HomePage",
  components: {
    "post": Post,
    "create-post": CreatePost
  },
  data () {
    return {
      posts: [],
      currentPage: 1,
      perPage: 5,
      totalPages: ''
    }
  },
  mounted () {
    this.getNextPageOfPosts()
    this.scroll()
  },
  methods: {
    getNextPageOfPosts () {
      UserServices.getPostsOfFollowed(this.$store.state.userId, this.currentPage++, this.perPage).then(response => {
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
    updatePage() {
      () => this.$forceUpdate()
    }
  }
}
</script>

<style>

#page-content {
  max-width: 600px;
  margin: 0px auto;
}

.posts{
  display: block;
}


</style>