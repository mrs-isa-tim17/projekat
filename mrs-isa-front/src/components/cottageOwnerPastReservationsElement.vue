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
                <h5 class="card-title" style="border:1px solid #687864;background-color:#687864">{{reservation.name}}</h5>
                <div class="card-text" style="text-align: left;">
                  <b>Broj osoba: </b><br>
                  <b>Broj dana: </b>{{reservation.duration}}<br>
                  <b>Početak rezervacije: </b>{{reservation.startDate}}<br>
                  <b>Kraj rezervacije: </b>{{reservation.endDate}}<br>
                  <b>Cena: </b>{{reservation.price}} din.<br>
                </div>
              </div>
            </div>

            <div class="col-4">
              <div class="card-body">
                <p v-if="reservation.cancelled" style="color:red;font-weight: bold;">Otkazano</p>
                <p v-if="reservation.quickReservation" style="color:red;font-weight: bold;">Brza rezervacija</p>
              </div>
            </div>

            <div class="col-4">
              <div class="card-body">
                <clientProfile :header="clientProfileHeader" :index="clientProfileId" ></clientProfile><br>
                <reservationReport></reservationReport>
              </div>
            </div>


          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import clientProfile from "@/components/client/clientProfile";
import reservationReport from "@/components/reservationReport";
//import ClientServce from "@/servieces/ClientServce";
export default {
  name: "cottageOwnerPastReservationsElement",
  components:{clientProfile,
    reservationReport},
  props:["reservation"],
  created() {

    if (this.reservation.images[0] != null) {
      this.image = this.reservation.images[0];
    } else {
      this.image = "icons/ship.png";
    }

    /*ClientServce.getClient(this.reservation.clientId).then((response)=>
    {
      this.client = response.data;
      console.log(this.client);
    }
    );*/
  },

  data(){
    return{
      image:"",
      clientProfileHeader:"Profil klijenta",
      clientProfileId:"clientProfile",
      client:{
        name: "",
        surname: "",
        email: "",
        password: "",
        phoneNumber: "",
        userType: "",
        loyaltyPoints: "",
        penaltyNumber: "",
        benefits: "",
        longitude: "",
        latitude: ""
      }
    }
    }
}
</script>

<style scoped>

</style>
