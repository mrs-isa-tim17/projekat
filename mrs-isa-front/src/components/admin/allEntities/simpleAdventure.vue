<template>
  <div class="card mb-3 d-flex justify-content-center" align="center" style="max-width: 1000px;">
    <div class="row g-0">
      <div class="col-md-12">
        <div class="card-body">
          <div class="row">
            <div class="col-4" align="left">
              <h4 class="card-title"><b> {{ adventure.name }} </b></h4>
              <h5 >{{ adventure.description }} </h5>
              <br>
              <h6>{{ownerLabel}} {{ adventure.ownerName }} {{adventure.ownerSurname}} </h6>
              <h6> {{emailLabel}} {{ adventure.ownerEmail }} </h6>
            </div>

            <div class="col-4" align="left">
              <br>

              <h6 class="card-text">{{capacityLabel}} {{adventure.capacity}}</h6>
              <h6 class="card-text">{{numberReservationsLabel}} {{ adventure.totalNumberOfReservations }}</h6>
              <h6 class="card-text">{{rateLabel}} {{ adventure.rate.toFixed(2) }}</h6>
              <br>
              <br>
              <div class="d-grid gap-2 d-md-flex justify-content-md-end" v-if="adventure.deleted===false">
                <button class="btn btn-danger  btn-sm  me-md-2 " @click="deleteAdventure"> Obriši</button>
              </div>
            </div>
            <div class="col-4">

              <bird-vue-map :lon="adventure.longitude" :lat="adventure.latitude" :index="adventure.id" style="height: 200px"
              ></bird-vue-map>

            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>


import BirdVueMap from "@/components/main_site/birdVueMap";
import swal from "sweetalert2";
import AdventureService from "@/services/AdventureService";
export default {
  name: "simpleAdventure",
  props: ['adventure'],
  components:{
    BirdVueMap
  },



  methods:{
    updateCoordinats(lon, lat) {

      console.log(lon, lat)
    },

    fireAlertOn(eventText){
      swal.fire({
        title: "Obaveštenje",
        text: eventText,
        background:'white',
        color:'black',
        confirmButtonColor:'#FECDA6'});
    },

    deleteAdventure(){
      AdventureService.deleteAdventure(this.adventure.id).then((response)=>{
        this.answer = response.data;
        if(this.answer){
          this.fireAlertOn("Obrisali ste avanturu.");
          this.$emit('delete-adventure');

        }else{
          this.fireAlertOn("Brisanje avanture nije uspelo. Avantura je ima rezervacije");

        }

      }).catch(function (error) {
        console.log(error.toJSON());
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
        } else if (error.request) {
          // The request was made but no response was received
          // error.request is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          console.log(error.request);
        } else {
          // Something happened in setting up the request that triggered an Error
          console.log('Error', error.message);
        }
        console.log(error.config);
      });
    }
  },

  data() {
    return {
      nameLabel:"Naziv: ",
      descriptionLabel: "Opis: ",
      capacityLabel: "Kapacitet: ",
      ownerLabel: "Vlasnik: ",
      emailLabel: "E-mail: ",
      numberReservationsLabel: "Ukupan broj rezervacija: ",
      rateLabel: "Ocena: ",
      answer:false

    }
  }
}
</script>

<style scoped>

</style>
