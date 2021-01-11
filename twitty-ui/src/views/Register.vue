<template>

  <div class="row" >


    <div class="col-md-4"></div>
    <div class="col-md-3">
      <br>
      <h1> Sign up to Twitty</h1>
      <br>

    <b-form @submit="onSubmit">
      <b-form-group
          id="input-group-1"
          label-for="input-1">

        <b-form-input
            id="input"
            v-model="user.username"
            type="text"
            placeholder="Enter email"
            required
        ></b-form-input>
      </b-form-group>

      <b-form-group id="input-group-2"  label-for="input-2">
        <b-form-input
            id="input-2"
            type="password"
            v-model="user.password"
            placeholder="Password"
            required
        ></b-form-input>
      </b-form-group>


      <b-button type="submit" v-on:click="newUser" variant="success">Submit</b-button>
    </b-form>
      <div class="col-md-4"></div>
    </div>
  </div>

</template>


<script>



import UserServices from '../services/user-services'
export default {
  name: 'Register',
  data() {
    return {
      user: {
        username: '',
        password: '',
        show:true
      }

    }
  },
  methods: {
    newUser() {
      UserServices.saveUser(this.user).then(
          response => {
            console.log(response.data)
          }
      )
    },
    onSubmit(event) {
      event.preventDefault()
      alert(JSON.stringify(this.form))
    }
  }
}

</script>

<style scoped>

</style>