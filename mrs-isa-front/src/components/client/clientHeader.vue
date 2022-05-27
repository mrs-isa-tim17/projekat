<template>
  <div  style="background-color: #31708E;">
  <div class="container">
    <div style="text-align: left; font-size:30px;font-weight: bold;padding-left:1%;color:white;">ENJOY.com</div>
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" style="color:white;font-size: 20px;" aria-current="page" :href="clientHomeLink">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="cottagesLink">Vikendice</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="shipsLink">Brodovi</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="adventuresLink">Avanture</a>
          </li>
        </ul>
        <button class="btn " style="color:white;font-size: 20px;border:1px solid white;"  @click="logout">Odjava</button>
      </div>
    </nav>
  </div>
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
      clientHomeLink: "/client/home",
      cottagesLink: "/book/cottage/site",
      shipsLink: "/book/ship/site",
      adventuresLink: "/book/adventure/site"
    }
  }
}
</script>

<style scoped>

</style>
