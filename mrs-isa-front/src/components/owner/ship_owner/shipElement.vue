<template>

  <div class="d-flex justify-content-center mw-90" >
    <div class="card mb-3 mw-90" style="width: 90%;">
      <div class="row g-0"  style="background-color: whitesmoke;color:#31708E;border:2px solid #31708E;">
        <div class="col-4">
          <img :src= "image" class="img-fluid p-2 " @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
        </div>
        <div class="col-8">
          <div class="row">
            <div class="col-8">
              <div style="font-size:26px;text-align:left;font-weight: bold;">{{ship.name}}</div>
            </div>
            <div class="col-4">
              <br>
              <star-rating :rating="rating" :round-start-rating="false" :star-size="30" ></star-rating>
            </div>
        </div>
          <div class="row">
            <div class="col-6">
              <div style="text-align:left;">
                Adresa
              </div>
            </div>
          </div>
          <br>
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
              <deleteOfferModal @accept="deleteShip" :header="DeleteHeader" :index="deleteId" :offer="ship" :button_name="btnDeleteName" :type="TypeOffer"></deleteOfferModal>
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
import deleteOfferModal from "@/components/owner/deleteOfferModal";
import StarRating from 'vue-star-rating'
import ReviewServce from "@/servieces/ReviewServce";
export default {
  name: "shipElement",
  props: ["ship"],
  components:{deleteOfferModal,StarRating},
  data(){
    return{
      image:"",
      deleteId:"",
      deleteHeader:"Brisanje broda",
      btnDeleteName:"Izbriši brod",
      TypeOffer:"ship",
      deleteKey:0
    }
  },
  created() {
    if (this.ship.images[0] != null) {
      this.image = this.ship.images[0];
    }else {
      this.image = "icons/ship.png";
    }
    ReviewServce.getRating(this.ship.id).then((response) =>
    {
      this.rating = response.data;
    })
    console.log(this.ship.name);
    this.deleteKey++;
    this.deleteId = "delete" + this.ship.id;

  },
  methods:{
    openModel() {

      $('#' + this.index).modal('show');
    },
    deleteShip(){
        console.log("emituj");
        this.$emit("rerender");

    },
    updateDataPage(){
      this.$router.push('/ship/update/' + this.ship.id);
    },
    pastHistoryReservationsPage(){
      this.$router.push('/ship/reservations/past/' + this.ship.id);
    },
    futureHistoryReservationsPage(){
      this.$router.push('/ship/reservations/future/' + this.ship.id);
    },
    detailPage(){
      this.$router.push('/ship/detail/' + this.ship.id);
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