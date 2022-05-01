<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarTogglerDemo01" aria-controls="navbarTogglerDemo01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarTogglerDemo01">
        <a class="navbar-brand" href="#" style="color:white;font-weight: bold; margin-right:150px; ">
          <img src="../assets/icons/logo_new.png" alt="" width="30" height="30" class="d-inline-block align-text-top">
          Enjoy
        </a>
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" id="first" aria-current="page" :href="myOffers">Moje ponude</a>
          </li>
          <li class="nav-item">
            <a class="nav-link  text-dark" :href="calendar" >Kalendar</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-dark" :href="historyReservations">Istorija rezervacija </a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-dark" :href="reports">Izveštaji</a>
          </li>
          <li class="nav-item">
            <a class="nav-link text-dark" :href="sales">Brze rezervacije</a>
          </li>
        </ul>
        <div class="dropdown">
          <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Profil
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" :href="editProfile">Izmena profila</a>
            <a class="dropdown-item" href="#">Brisanje naloga</a>
            <a class="dropdown-item" href="#">Odjava</a>
          </div>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
export default {
  name: "client-header",
  data() {
    return {
      myOffers: "/client/history/cottage",
      editProfile: "/owner/profile",

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