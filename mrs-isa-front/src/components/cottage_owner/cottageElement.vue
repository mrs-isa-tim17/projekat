<template>

  <div class="d-flex justify-content-center mw-90" >
    <div class="card mb-3 mw-90" style="width: 90%;">
      <div class="row g-0" style="background-color: #31708E;color:#F7F9FB;">
        <div class="col-4" style="float:left">
          <img :src="require('@/assets/' + image)" class="img-fluid " @click="goToOffer" alt="..." data-toggle="tooltip" data-placement="right" title="Poseti stranicu">
        </div>
        <div class="col-8">
          <div class="row">
          <div class="col-4">
              <div class="card-body">
                <h5 class="card-title" style="border:1px solid #687864;background-color:#687864">{{cottage.name}}</h5>
                <div class="card-text" style="text-align: left;">
                  <b>Kapacitet: </b>{{cottage.bedQuantity}} osoba<br>
                  <b>Cena: </b>{{cottage.price}} din.<br>
                  {{cottage.description}}
                </div>
              </div>
        </div>

          <div class="col-4">
            <div class="card-body">

              <bird-vue-map :index="cottage.id" style="width: 280px; height: 200px;" :lon="cottage.longitude" :lat="cottage.latitude"></bird-vue-map>
              </div>
            </div>

            <div class="col-4">
              <div class="card-body">
              <deleteCottageModal :header="DeleteHeader" :index="deleteId" :cottage="cottage"></deleteCottageModal>
              <br>
                <br>
              <button @click="updateDataPage">Izmeni podatke</button><br><br>
                <button @click="detailPage">Detalji</button>
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
import deleteCottageModal from "@/components/cottage_owner/deleteCottageModal"
export default {
  name: "cottageElement",
  props: ["cottage"],
  components:{BirdVueMap,deleteCottageModal},
  data(){
    return{
      updateId:"updateCottage",
      image:"",
      DeleteHeader:"Brisanje vikendice",
      deleteId:"deleteCottage"

    }
  },
  created() {
    if (this.cottage.images[0] != null) {
      this.image = this.cottage.images[0];
    }else {
      this.image = "icons/ship.png";
    }
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