<template>

  <div class="d-flex justify-content-center mw-90" >
    <div class="card mb-3 mw-90" style="width: 90%;">
      <div class="row g-0" style="background-color: #31708E;color:#F7F9FB;">
        <div class="col-4">
          <img :src="require('@/assets/' + image)" class="img-fluid " @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
        </div>
        <div class="col-8">
          <div class="row">
            <div class="col-4">
              <div class="card-body">
                <h5 class="card-title" style="border:1px solid #687864;background-color:#687864">{{ship.name}}</h5>
                <div class="card-text" style="text-align: left;">
                  <b>Kapacitet: </b>{{ship.capacity}} osoba<br>
                  <b>Cena: </b>{{ship.price}} din.<br>
                  {{ship.description}}
                </div>
              </div>
            </div>

            <div class="col-4">
              <div class="card-body">

                <bird-vue-map :index="ship.id" style="width: 280px; height: 200px;" :lon="ship.longitude" :lat="ship.latitude"></bird-vue-map>
              </div>
            </div>

            <div class="col-4">
              <div class="card-body">
                <deleteShipModal :header="deleteHeader" :ship="ship" :index="deleteId"></deleteShipModal>
                <br>
                <br>
                <button @click="updateDataPage">Izmeni podatke</button><br><br>
                <div class="dropdown">
                  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Istorija rezervacija
                  </button>
                  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                    <button class="dropdown-item" @click="pastHistoryReservationsPage">Realizovane</button>
                    <button class="dropdown-item" @click="futureHistoryReservationsPage">Nerealizovane</button>
                  </div>
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
import BirdVueMap from "@/components/main_site/birdVueMap";
import ShipService from "@/servieces/ship_owner/ShipService";
import deleteShipModal from "@/components/ship_owner/deleteShipModal";
export default {
  name: "shipElement",
  props: ["ship"],
  components:{BirdVueMap,deleteShipModal},
  data(){
    return{
      image:"",
      deleteId:"deleteShip",
      deleteHeader:"Brisanje broda"

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