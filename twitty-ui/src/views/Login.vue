<template>
  <div class="container">
    <h1>Login To Twitty</h1>
    <br>
    <br>
    <div class="row" >
      <div class="col-md-3"></div>
      <div class="col-md-6"><form>
        <div class="form-group">
          <input type="username" class="form-control" id="UserName" placeholder="UserName">
        </div>
        <br>
        <div class="form-group">
          <input type="password" class="form-control" id="exampleInputPassword1" placeholder="Password">
        </div>
        <br>
        <button type="submit" class="btn btn-primary">Log in</button>
      </form>
      </div>
      <div class="col-md-3"></div>
    </div>
  </div>

</template>
<script>
export default {
  name: 'Login'
}
</script>

<style scoped>

</style>