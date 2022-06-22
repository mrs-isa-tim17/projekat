<template>
  <div  style="background-color: #31708E;">
  <div class="container-fluid mb-2">
    <a type="button" id="logo" style="text-decoration: none; text-align: left; display: block; font-size:30px;font-weight: bold;padding-left:1%;color:white;" :href="homeLink">ENJOY.com</a>
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <!--<li class="nav-item">
            <a class="nav-link active" style="color:white;font-size: 20px;" aria-current="page" :href="clientHomeLink">Home</a>
          </li>-->
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


            <div class="dropdown">
              <button class="dropbtn nav-link" style="color:white;font-size:20px;">Istorija rezervacija</button>
              <div class="dropdown-content">
                  <a class="dropdown-item" :href="historyOfCottageReservationsLink">Vikendice</a>
                  <a class="dropdown-item" :href="historyOfShipReservationsLink">Brodovi</a>
                  <a class="dropdown-item" :href="historyOfAdventureReservationsLink">Avanture</a>
              </div>
            </div>

          </li>
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="subscriptionLink">Pretplaćeni entiteti</a>
          </li>
        </ul>
        <div class="dropdown">
        <button class="btn btn-secondary dropdown-toggle mx-5" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="color:white;font-size: 20px;border:1px solid white;">Profil</button>
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
      this.$router.push("/book/site/home");
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
      subscriptionLink: "/client/subscribed",


    }
  }
}
</script>

<style scoped>
#logo {
  cursor: pointer;
}
.dropbtn {
  background:none;
  color: white;
  font-size: 20px;
  border: none;
}

.dropdown {
  position: relative;
  display: inline-block;
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #f1f1f1;
  min-width: 160px;
  box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
  z-index: 1;
  border-radius: 4px;
}

.dropdown-content a {
  color: black;
  padding: 12px 16px;
  text-decoration: none;
  display: block;
}

.dropdown-content a:hover {background-color: #ddd;}

.dropdown:hover .dropdown-content {display: block;}

.dropdown:hover .dropbtn {}
</style>
