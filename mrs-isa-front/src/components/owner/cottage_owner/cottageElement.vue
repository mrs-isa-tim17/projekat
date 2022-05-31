<template>

  <div class="d-flex justify-content-center mw-90" >
    <div class="card mb-3 mw-90" style="width: 80%;">
      <div class="row g-0" style="background-color: whitesmoke;color:#31708E;border:2px solid #31708E;">

        <div class="col-4" style="float:left">
          <img :src="image" class="img-fluid p-2" @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
        </div>
        <div class="col-8">
          <div class="row">
            <div class="col-8">
              <div style="font-size:26px;text-align:left;font-weight: bold;">{{cottage.name}}</div>
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

          <div class="row">
            <div class="col-8" style="text-align:left;">
              {{cottage.description}}<br><br>
              </div>
            <div class="col-4">
              <b>Cena: </b>{{cottage.price}} din.
            </div>
          </div>
        <br>
          <div class="row">
            <div class="col-4">
              <button class="btn" style="font-size: 15px;font-weight: bold;color:white;" @click="detailPage"> Detalji i izmene</button>
            </div>
            <div class="col-4">
              <deleteCottageModal :header="DeleteHeader" :index="deleteId" :offer="cottage" :button_name="btnDeleteName"></deleteCottageModal>
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
import deleteCottageModal from "@/components/owner/deleteOfferModal"
import StarRating from 'vue-star-rating'
import ReviewServce from "@/servieces/ReviewServce";
export default {
  name: "cottageElement",
  props: ["cottage"],
  components:{deleteCottageModal,StarRating},
  data(){
    return{
      updateId:"updateCottage",
      image:"",
      DeleteHeader:"Brisanje vikendice",
      deleteId:"deleteCottage",
      btnDeleteName:"Obriši vikendicu",
      rating:null

    }
  },
  created() {
    if (this.cottage.images[0] != null) {
      this.image = this.cottage.images[0];
    }else {
      this.image = "icons/ship.png";
    }
    ReviewServce.getRating(this.cottage.id).then((response) =>
    {
      this.rating = response.data;
    })
  },
  methods:{
    openModel() {

      $('#' + this.index).modal('show');
    },
    updateDataPage(){
      this.$router.push('/cottage/update/' + this.cottage.id);
    },
    pastHistoryReservationsPage(){
      this.$router.push('/cottage/reservations/past/' + this.cottage.id);
    },
    futureHistoryReservationsPage(){
      this.$router.push('/cottage/reservations/future/' + this.cottage.id);
    },
    detailPage(){
      this.$router.push('/cottage/detail/' + this.cottage.id);
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