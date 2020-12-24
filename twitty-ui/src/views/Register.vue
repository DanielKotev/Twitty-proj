<template>
  <div class="container">

    <h1>Register To Twitty</h1>
    <br>
    <br>
    <div class="row" >
      <div class="col-md-3"></div>
      <div class="col-md-6"><form>
        <div class="form-group">
          <input type="component"
                 class="form-control"
                 required
                 id="UserName" v-model="username"
                 placeholder="Enter Nickname">
        </div>
        <br>
        <div class="form-group">
          <input type="password"
                 class="form-control"
                 id="password" v-model="password"
                 required
                 placeholder="Password" />
<!--          <div v-if="user.password.length >7 &&user.password.length<13" class="text-danger">Password too short</div>-->
<!--          <div id="passwordverification" class="form-text">Enter Password Between 8-12 Digits</div>-->
        </div>
<!--        <div class="form-group">-->
<!--          <input type="password"-->
<!--                 class="form-control"-->
<!--                 id="reenterpassword" v-model="reenterpassword"-->
<!--                 placeholder="Re-enter Password">-->
<!--          <div v-if="user.password != user.reenterpassword" class="text-danger">Password don't match</div>-->
<!--        </div>-->
        <br>
        <b-button v-on:click="SaveUser" variant="success" style="margin: 5px">Register</b-button>
      </form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>
</template>
<script>

import UserServices from '../services/user-services'
export default {
  name: 'Register',
  data() {
    return {

        username: '',
        password: '',

    }
  },
  methods: {
    SaveUser() {
      UserServices.saveUser(this.username,this.password).then(
          response =>{
            console.log(response.data)
          }
      )
    }
  }
}

</script>

<style scoped>

</style>