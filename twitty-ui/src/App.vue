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
            <li>
              <div class="searchBox" v-if="$store.state.userId">
                <input class="search-txt" type="text" placeholder="search" v-model="search"/>
                <a class="search-btn">
                  <svg viewBox="0 0 24 24" height="70px" v-on:click="searchPeople">
                    <g class="search-btn">
                      <path d="M15.5 14h-.79l-.28-.27C15.41 12.59 16 11.11 16 9.5 16 5.91 13.09 3 9.5 3S3 5.91
               3 9.5 5.91 16 9.5 16c1.61 0 3.09-.59 4.23-1.57l.27.28v.79l5 4.99L20.49 19l-4.99-5zm-6
                0C7.01 14 5 11.99 5 9.5S7.01 5 9.5 5 14 7.01 14 9.5 11.99 14 9.5 14z" class="search-btn"/>
                    </g>
                  </svg>
                  <i class="fas fa-search"></i>
                </a>
              </div>

            </li>
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
  data() {
    return {
      search: ''
    }
  },
  methods: {
    logout() {
      this.$store.commit('logout')
      this.$router.push({name: 'Login'})
    },
    searchPeople() {
      this.$router.push({name: 'FindUsers', params: {search: this.search}})}
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
