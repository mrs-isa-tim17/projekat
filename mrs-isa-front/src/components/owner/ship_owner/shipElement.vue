<template>

  <div class="d-flex justify-content-center mw-90" >
    <div class="card mb-3 mw-90" style="width: 90%;">
      <div class="row g-0" style="background-color: #31708E;color:#F7F9FB;">
        <div class="col-4">
          <img :src= "image" class="img-fluid p-2 " @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
        </div>
        <div class="col-8">
          <div class="row">
            <div class="col-8">
              <div style="font-size:26px;text-align:left;font-weight: bold;">{{ship.name}}</div>
            </div>
            <div class="col-4">
                zvezdice
            </div>
        </div>
          <div class="row">
            <div class="col-6">
              <div style="text-align:left;">
                Adresa
              </div>
            </div>
          </div>
          <div class="row">
            <div class="col-8" style="text-align:left;">
              {{ship.description}}<br><br>
            </div>
            <div class="col-4">
              <b>Cena: </b>{{ship.price}} din.
            </div>
          </div>
          <div class="row">
            <div class="col-4">
              <button class="btn" style="font-size: 15px;font-weight: bold;color:white;" @click="detailPage"> Detalji i izmene</button>
            </div>
            <div class="col-4">
              <deleteOfferModal :header="DeleteHeader" :index="deleteId" :offer="ship" :button_name="btnDeleteName"></deleteOfferModal>
            </div>
            <div class="col-4">
              <div class="dropdown">
                <button style="background-color: #687864;" class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                  Istorija rezervacija
                </button>
                <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                  <button style="color:white" class="dropdown-item" @click="pastHistoryReservationsPage">Realizovane</button>
                  <button style="color:white" class="dropdown-item" @click="futureHistoryReservationsPage">Nerealizovane</button>
                </div>
              </div>
          </div>
          </div>
        </div>
        </div>
      </div>
  </div>

</template>

<script>
import $ from "jquery";
import ShipService from "@/servieces/ship_owner/ShipService";
import deleteOfferModal from "@/components/owner/deleteOfferModal";
export default {
  name: "shipElement",
  props: ["ship"],
  components:{deleteOfferModal},
  data(){
    return{
      image:"",
      deleteId:"deleteShip",
      deleteHeader:"Brisanje broda",
      btnDeleteName:"Izbriši brod"
    }
  },
  created() {
    if (this.ship.images[0] != null) {
      this.image = this.ship.images[0];
    }else {
      this.image = "icons/ship.png";
    }
  },
  methods:{
    openModel() {

      $('#' + this.index).modal('show');
    },
    deleteShip(){
      ShipService.deleteShip(this.ship.id);
    },
    updateDataPage(){
      this.$router.push('/ship/update/' + this.ship.id);
    },
    pastHistoryReservationsPage(){
      this.$router.push('/ship/reservations/past/' + this.ship.id);
    },
    futureHistoryReservationsPage(){
      this.$router.push('/ship/reservations/future/' + this.ship.id);
    }
  }
}
</script>

<style scoped>
button{
  width:165px;
  background-color: #687864;
}

</style>