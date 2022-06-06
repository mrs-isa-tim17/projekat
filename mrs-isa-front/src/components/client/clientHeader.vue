<template>
  <div  style="background-color: #31708E;">
  <div class="container-fluid">
    <div style="text-align: left; font-size:30px;font-weight: bold;padding-left:1%;color:white;" @click="onBookClick">ENJOY.com</div>
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
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="upcomingLink">Zakazane rezervacije</a>
          </li>
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" style="color:white;font-size: 20px;" href="#" id="navbarDarkDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Istorija rezervacija
            </a>
            <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDarkDropdownMenuLink">
              <li><a class="dropdown-item" :href="historyOfCottageReservationsLink">Vikendice</a></li>
              <li><a class="dropdown-item" :href="historyOfShipReservationsLink">Brodovi</a></li>
              <li><a class="dropdown-item" :href="historyOfAdventureReservationsLink">Avanture</a></li>
            </ul>
          </li>
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="subscriptionLink">Pretplaćeni entiteti</a>
          </li>
        </ul>
        <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:white;font-size: 20px;border:1px solid white;">Profil</button>
        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
          <button class="dropdown-item btn-link" @click="editProfile">Izmena profila</button>
          <button class="dropdown-item" @click="logout">Odjava</button>
        </div>
        </div>
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
    editProfile(){
      this.$router.push(this.profileLink);
    },
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
      adventuresLink: "/book/adventure/site",
      profileLink: "/client/profile",

      historyOfCottageReservationsLink: "/client/history/cottage",
      historyOfShipReservationsLink: "/client/history/ship",
      historyOfAdventureReservationsLink: "/client/history/adventure",
      upcomingLink: "/client/upcoming/reservations",
      subscriptionLink: "...",


    }
  }
}
</script>

<style scoped>

</style>
