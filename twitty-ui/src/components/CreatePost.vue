<template>
 <div id="form">
   <b-form-textarea id="text-area" placeholder="Create a new post..." v-model="text"></b-form-textarea>
   <b-button id="button" v-on:click="savePost">Post</b-button>
   <b-alert :show="showAlert" dismissible v-on:dismissed="showAlert = false" variant="danger">The post must not be empty!</b-alert>
 </div>
</template>

<script>
export default {
  name: "CreatePost",
  data () {
    return {
      text:'',
      showAlert: false
    }
  },
  methods: {
    savePost () {
      if (this.text.length === 0) {
        this.showAlert = true
      }
      else {
        alert('post saved')
        this.showAlert = false
      }
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
  }

  #button {
    margin: 10px auto;
  }

</style>