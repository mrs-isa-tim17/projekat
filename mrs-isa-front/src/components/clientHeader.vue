<template>
  <div class="container">
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" :href="homeLink" style="color:white;margin-right:150px;font-weight:bold;">
          <img src="../assets/icons/logo_new.png" alt="" width="30" height="30" class="d-inline-block align-text-top">
          <div style="display:inline-block;vertical-align:bottom;">ENJOY</div>
        </a>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" :href="clientHomeLink">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link  text-dark" href="#">Vikendice</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-dark" href="#">Brodovi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-dark" href="#">Avanture</a>
          </li>
        </ul>
        <button class="btn btn-outline-dark" @click="logout">Odjava</button>
      </div>
    </nav>
  </div>
</template>

<script>
import loginServce from "@/servieces/LoginServce";
export default {
  name: "client-header",
  components:[
      loginServce
  ],
  methods:{
    onBookClick(){
      this.$router.push(this.homeLink);
    },
    logout(){
      loginServce.logout();
      this.$router.push('/book/site/home');
    }
  },
  data() {
    return {
      homeLink: "/client/site/home",
      clientHomeLink: "/client/home"
    }
  }
}
</script>

<style scoped>

</style>
