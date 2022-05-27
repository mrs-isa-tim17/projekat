<template>
  <div  style="background-color: #31708E;color:white;">
    <div class="container ">
        <div style="text-align: left; font-size:30px;font-weight: bold;padding-left:1%;" >ENJOY.com</div>
    <nav class="navbar navbar-expand-lg navbar-light" aria-current="page" >
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">

        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item active">
            <a class="nav-link" id="first" style="color:white;font-size: 20px;" aria-current="page" :href="myOffers">Moje ponude</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" style="color:white;font-size: 20px;" :href="calendar" aria-current="page" >Kalendar</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " style="color:white;font-size: 20px;" :href="reports">Izveštaji</a>
          </li>
          <li class="nav-item">
            <a class="nav-link " style="color:white;font-size: 20px;" :href="sales">Brze rezervacije</a>
          </li>
          <li class="nav-item">
            <NewReservations :header="reservationsHeader" :index="reservationsIndex" :key="newResKey"></NewReservations>
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
import NewReservations from "@/components/cottage_owner/newReservations";

export default {
  name: "client-header",
  components: {NewReservations},
  created:
    function (){
    this.newResKey++;
  },
  data() {
    return {
      myOffers: "/cottageOwner/home",
      editProfile: "/cottageOwner/profile",
      reservationsHeader:"Napravite novu rezervaciju",
      reservationsIndex:"resId",
      newResKey:0

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