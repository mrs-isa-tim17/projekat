<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" :href="siteHomePage" style="color:white;margin-right:150px;font-weight:bold;">
          <img src="../assets/icons/logo_new.png" alt="" width="30" height="30" class="d-inline-block align-text-top">
          <div style="display:inline-block;vertical-align:bottom;">ENJOY</div>
        </a>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item active">
            <a class="nav-link" id="first" style="color:white;font-size: 20px;" aria-current="page" :href="myOffers">Moje ponude</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="calendar" >Kalendar</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="historyReservations">Istorija rezervacija </a>
          </li>
          <li class="nav-item">
            <a class="nav-link " style="color:white;font-size: 20px;" :href="reports">Izveštaji</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " style="color:white;font-size: 20px;" :href="sales">Brze rezervacije</a>
          </li>
        </ul>
        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-user icon"></i> Profil
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <button class="dropdown-item" @click="editProfilePage">Izmena profila</button>
            <button class="dropdown-item" @click="logOut">Odjava</button>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
import loginServce from "@/servieces/LoginServce";

export default {
  name: "client-header",
  data() {
    return {
      myOffers: "/owner/cottages",
      editProfile: "/cottageOwner/profile",

    }
  },
  methods:
      {
        logOut(){
          loginServce.logout();
          this.$router.push('/book/site/home');
        },
        editProfilePage(){
          this.$router.push('/cottageOwner/profile');
        }
      }
}
</script>

<style scoped>
.dropdown{
  padding-right:100px;
}
a{
  color: red;
}
</style>