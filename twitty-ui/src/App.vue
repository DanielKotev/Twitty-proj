<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-light bg-light" >
      <div class="container-fluid">
        <router-link class="navbar-brand" :to="{name: 'HomePage'}">Twitty</router-link>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbarSupportedContent">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
              <router-link class="nav-link" :to="{name: 'UserPage', params: {id: $store.state.userId}}">Home</router-link>
            </li>
            <li>{{$store.state.userId}}</li>
          </ul>
          <div v-if="$store.state.userId">
<!--            <router-link class="nav-link" to="/Login" v-on:click="logout">Log out</router-link>&ndash;&gt;-->
            <b-button variant="outline-primary" v-on:click="logout">Log out</b-button>
          </div>
          <div v-else>
            <form class="d-flex">
              <router-link class="nav-link" to="/Login">Log in</router-link>
              <router-link class="btn btn-outline-primary my-2 my-sm-0" to="/Register">Sign Up</router-link>
            </form>
          </div>
        </div>
      </div>
    </nav>
    <router-view :key="$route.fullPath"/>
  </div>
</template>

<script>



export default {
  methods: {
    logout () {
      console.log('logging out')
      this.$store.commit('logout')
      this.$router.push(('login'))

    }
  }
}

</script>

<style>
#app {
  font-family: Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  /*-moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;*/
  background-color: #fff;
}

#nav {
  padding: 10px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}

</style>
