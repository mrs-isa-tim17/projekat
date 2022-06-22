<template>
  <div  style="background-color: #31708E;" class="container-fluid mb-2">
    <div class="container-fluid mb-2">
      <a type="button" id="logo" style="cursor: pointer; text-decoration: none; text-align: left; display: block; font-size:30px;font-weight: bold;padding-left:1%;color:white;" :href="siteHomePage">ENJOY.com</a>
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">

        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link "  style="color:white;font-size: 20px;" :href="cottagesLink">Vikendice</a>
          </li>
          <li class="nav-item">
            <a class="nav-link "  style="color:white;font-size: 20px;" :href="shipsLink">Brodovi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link "  style="color:white;font-size: 20px;" :href="adventuresLink">Avanture</a>
          </li>
        </ul>
        <button class="btn" @click="login" style="color:white; margin-right:30px;border:1px solid white;">Prijavite se</button>
        <button class="btn" @click="register" style="color:white;border:1px solid white;">Registrujte se</button>
      </div>
    </nav>
  </div>
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
      shipsLink: "/book/ship/site",
      adventuresLink: "/book/adventure/site"
    }
  }
}
</script>

<style scoped>
</style>
