<template>
  <div  style="background-color: #31708E;">
  <div class="container-fluid">
    <div style="text-align: left; font-size:30px;font-weight: bold;padding-left:1%;color:white;">ENJOY.com</div>
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="this.adventures">Moje ponude</a>
          </li>

          <li class="nav-item">
            <a class="nav-link " style="color:white;font-size: 20px;" :href="this.sales">Istorija rezervacija</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " style="color:white;font-size: 20px;" :href="this.newReservation">Nova rezervacija</a>
          </li>
          <li class="nav-item">
            <div class="dropdown">
              <button class="dropbtn nav-link" style="color:white;font-size:20px;">Izveštaji</button>
              <div class="dropdown-content">
                <a :href="reservationsReports">Posećenost</a>
                <a :href="incomeReports">Prihodi</a>
              </div>
            </div>
          </li>
        </ul>
        <div class="dropdown" >
          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="border:1px solid white;">
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
  </div>
</template>

<script>
import loginServce from "@/servieces/LoginServce";
//import NewReservations from "@/components/owner/cottage_owner/newReservations";

export default {
  name: "instructor-header",
 // components:{NewReservations},
  methods:{
    logOut(){
      loginServce.logout();
      this.$router.push('/book/site/home');
    },

    editProfilePage(){
      this.$router.push('/instructor/profile/change');
    },

  },
  data() {
    return {
      adventures:"/instructor/home",
      sales:"/instructor/reservations/all",
      newReservation:"/instructor/new/reservation",
      reservationsReports:"/instructor/graph/reservations",
      incomeReports:"/instructor/graph/income",
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
