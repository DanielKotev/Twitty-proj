<template>
 <div id="form">
   <b-form v-on:submit.prevent="savePost">
     <b-form-textarea id="text-area" placeholder="Create a new post..." rows="6" v-model="text" no-resize required></b-form-textarea>
     <b-button id="button" type="submit">Post</b-button>
   </b-form>
 </div>
</template>

<script>
import PostServices from "../services/post-services";

export default {
  name: "CreatePost",
  data () {
    return {
      text:''
    }
  },
  methods: {
    savePost () {
      PostServices.savePost(this.text, this.$store.state.userId).then(
          () => this.$emit('posted')
      )
      this.text = ''
    }
  }
}
</script>

<style scoped>

  #form {
    margin: 10px auto;
    padding: 10px;
    border: 1px solid #eee;
    border-radius: 5px;
    background-color: #eee;
  }

  #text-area {
    background: none;
    border: none;
  }

  #button {
    margin: 10px 0px;
    bottom: 10px;
    right: 10px;
  }

</style>