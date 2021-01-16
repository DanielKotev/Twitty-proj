<template>
  <div id="page-content">
    <h1>Posts</h1>
    <div class="container">
      <div class="searchBox">
        <input class="search-txt" type="text" v-model="content" placeholder="search posts"/>
          <a class="search-btn">
            <svg viewBox="0 0 24 24" height="70px" v-on:click="searchPosts">
              <g class="search-btn">
              <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91
               3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6
                0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z" class="search-btn"/>
              </g>
            </svg>
            <i class="fas fa-search"></i>
          </a>
      </div>
    </div>
    <create-post v-on:posted="updatePage"/>
    <div v-if="this.posts.length != 0">
      <div class="posts" v-for="post in posts" v-bind:key="post.id">
        <post v-bind:post="post" v-on:deleted="updatePage"/>
      </div>
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
      totalPages: '',
      content: ''
    }
  },
  mounted () {
    if (!this.$store.state.userId)
    {
        this.$router.push({name: 'Login'})
    }
    this.getNextPageOfPosts()
    this.scroll()
  },
  methods: {
    getNextPageOfPosts () {
      UserServices.getPostsOfFollowed(this.$store.state.userId, this.currentPage++, this.perPage, this.content).then(response => {
        if (response.data.posts) {
            this.posts.push(...response.data.posts)
        }
        this.totalPages = response.data.totalPages
      })
    },
    searchPosts () {
      this.currentPage = 1
      this.posts = []
      this.getNextPageOfPosts()
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
      console.log('update page called')
      this.posts = []
      this.currentPage = 1
      this.getNextPageOfPosts()
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
.searchBox{
  background: white;
  height: 40px;
  border-radius: 40px;
  transition:0.4s;
  width: 46px;
}
.search-txt{
   border:none;
   background: none;
   outline:none;
   color: white;
   font-size: 16px;
   transition:0.4s;
   line-height: 40px;
   width: 0px;
 }
.search-btn{
  float: right;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: white;
  display: flex;
  justify-content: center;
  align-items: center;
  transition:0.4s;
}
.searchBox:hover>.search-txt{
  width: 240px;
  padding: 0 6px;
}
.searchBox:hover {
  background: #2f3640;
  width: 300px;
  padding: 0 6px;
}
.searchBox:hover.search-btn{
  padding: 0 6px;
  width: 39px;
  height: 39px;
  justify-content: center;
  align-items: center;
}
</style>