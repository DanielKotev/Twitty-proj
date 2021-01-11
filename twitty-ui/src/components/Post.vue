<template>
  <div>
    <div class="post">
      <div id="post-content">
        <div v-if="isOwnPost()">
          <b-dropdown class="dropdown no-background" size="sm" text="" variant="none" no-caret>
            <template #button-content>
              <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
                <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
              </svg>
            </template>
            <b-dropdown-item-button v-on:click="deletePost">Delete</b-dropdown-item-button>
          </b-dropdown>
        </div>
        <router-link :to="{name: 'UserPage', params: {id: post.user.id}}" class="name" tag="h5">{{post.user.username}}</router-link>
        <pre class="text-format">{{post.content}}</pre>
      </div>
        <b-form id="add-comment" v-on:submit="saveComment">
          <b-form-input placeholder="Write a new comment..." v-model="commentText" class="no-background" required></b-form-input>
          <b-button size="sm" type="submit">Comment</b-button>
        </b-form>
        <div v-for="comment in this.comments.slice(0, displayComments)" v-bind:key="comment.id">
          <div id="comment">
            <div v-if="isOwnComment(comment.user.id)">
              <b-dropdown class="dropdown no-background" size="sm" text="" variant="none" no-caret>
                <template #button-content>
                  <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
                    <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
                  </svg>
                </template>
                <b-dropdown-item-button v-on:click="deleteComment(comment.id)">Delete</b-dropdown-item-button>
              </b-dropdown>
            </div>
            <router-link :to="{name: 'UserPage', params: {id: comment.user.id}}" class="name" tag="h6">{{comment.user.username}}</router-link>
            <p class="text-format">{{comment.content}}</p>
          </div>
        </div>
      <div v-if="displayComments < this.numberOfComments">
        <button class="no-background" v-on:click="displayComments += 2">Show more comments</button>
        <button class="no-background" v-on:click="displayComments = numberOfComments">Show all comments ({{this.numberOfComments}})</button>
      </div>
    </div>
  </div>
</template>

<script>
import PostServices from "../services/post-services";
import CommentServices from "../services/comment-services";

export default {
  name: "Post",
  data () {
    return {
      displayComments: 1,
      commentText:'',
      comments: '',
      numberOfComments: ''
    }
  },
  props: {
    post: {
      type: Object,
      required: true
    },

  },
  mounted() {
    this.getComments()
  },
  methods: {
  getComments () {
    PostServices.getComments(this.post.id).then(response =>{
      this.comments = response.data.comments
      this.numberOfComments = response.data.numberOfComments
    })
  },
    saveComment () {
      CommentServices.saveComment(this.commentText, this.$store.state.userId , this.post.id).then(
          () => {
            this.getComments()
            this.$forceUpdate()
          }
      )
      this.commentText = ''
    },
    deleteComment (commentId) {
      CommentServices.deleteComment(commentId).then(
          () => {
            this.getComments()
            this.$forceUpdate()
          }
      )
    },
    deletePost () {
      PostServices.deletePost(this.post.id).then(
          this.$emit('deleted')
      )
    },
    isOwnPost () {
      return this.$store.state.userId === this.post.user.id
    },
    isOwnComment (id) {
      return this.$store.state.userId === id
    }
  }
}
</script>

<style scoped>
  .post {
    margin: 10px auto;
    padding: 5px 20px;
    border: 1px solid #eee;
    border-radius: 5px;
    background-color: #eee;
  }

  #comment {
    margin: 5px;
    padding: 5px 10px;
    border: 1px solid #dedede;
    background-color: #dedede;
  }

  .no-background {
    border: none;
    background: none;
  }

  .name {
    font-weight: bold;
  }

  #post-content {
    border: 1px solid #dedede;
    border-radius: 5px;
    padding: 10px;
    margin: 10px 0px;
    height: auto;
  }

  #add-comment {
    display: flex;
    margin: 5px;
  }

  .dropdown {
    float: right;
  }

  #link {
    color: black;
  }

</style>