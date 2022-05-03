<template>
  <div class="container">
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" :href="siteHomePage">
          <img src="../assets/icons/logo.png" alt="" width="30" height="30" class="d-inline-block align-text-top">
          Book
        </a>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" :href="siteHomePage">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link  text-dark" :href="cottagesLink">Vikendice</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-dark" :href="shipsLink">Brodovi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-dark" href="...">Avanture</a>
          </li>
        </ul>
        <button class="btn btn-outline-dark" @click="login">Log in</button>
        <button class="btn btn-outline-dark" @click="register">Sign up</button>
      </div>
    </nav>
  </div>
</template>

<script>

export default {
  name: "basicHeader",
  methods: {
    login(){
      this.$router.push('/book/site/login');
    },
    register(){
      this.$router.push('/book/site/register')
    }
  },
  data() {
    return {
      siteHomePage: "/book/site/home",
      cottagesLink: "/book/cottage/site",
      shipsLink: "/book/ship/site"
    }
  }
}
</script>

<style scoped>

</style>
