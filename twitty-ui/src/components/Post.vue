<template>
  <div>
    <div class="post">
      <div id="post-content">
        <b-dropdown class="dropdown" size="sm" text="" variant="none" no-caret>
          <template #button-content>
            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
              <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
            </svg>
          </template>
          <b-dropdown-item-button>Delete</b-dropdown-item-button>
          <b-dropdown-item-button>Edit</b-dropdown-item-button>
        </b-dropdown>
        <h5 class="name">{{post.user.username}}</h5>
        <pre class="text-format">{{post.content}}</pre>
      </div>
      <div id="add-comment">
        <b-form-input placeholder="Write a new comment..." v-model="commentText"></b-form-input>
        <b-button size="sm" v-on:click="saveComment">Comment</b-button>
      </div>
      <b-alert :show="showAlert" dismissible v-on:dismissed="showAlert = false" variant="danger">The comment must not be empty!</b-alert>
        <div v-for="comment in this.comments.slice(0, displayComments)" v-bind:key="comment.id">
          <div id="comment">
            <b-dropdown class="dropdown" size="sm" text="" variant="none" no-caret>
              <template #button-content>
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-three-dots" viewBox="0 0 16 16">
                  <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/>
                </svg>
            </template>
              <b-dropdown-item-button>Delete</b-dropdown-item-button>
              <b-dropdown-item-button>Edit</b-dropdown-item-button>
            </b-dropdown>
            <h6 class="name">{{comment.user.username}}</h6>
            <p class="text-format">{{comment.content}}</p>
          </div>
        </div>
        <div v-if="displayComments < this.numberOfComments">
          <button class="text-button" v-on:click="displayComments += 2">Show more comments</button>
          <button class="text-button" v-on:click="displayComments = numberOfComments">Show all comments ({{this.numberOfComments}})</button>
        </div>
    </div>
  </div>
</template>

<script>
import PostService from "../services/post-service";

export default {
  name: "Post",
  data () {
    return {
      displayComments: 1,
      commentText:'',
      showAlert: false,
      comments: [],
      numberOfComments: ''
    }
  },
  props: {
    post: {
      id: '',
      content: '',
      user: ''
    }
  },
  mounted() {
    this.getComments()
  },
  methods: {
    saveComment () {
      if (this.commentText.length === 0) {
        this.showAlert = true
      }
      else {
        alert('comment saved')
        this.showAlert = false
      }
    },
    getComments () {
      PostService.getComments(this.post.id).then(response =>{
        this.comments = response.data.comments
        this.numberOfComments = response.data.numberOfComments
      })
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

  .text-button {
    border: none;
    background: none;
    color: #888;
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
    background: none;
    border: none;
  }

  .text-format {
  }

</style>

