<template>

  <adminHeader></adminHeader>
  <div class="container">
    <!--
    <div clas="col-4">
      <botton @click="toggleClienScale" > Skala za klijenta </botton>
      <botton @click="toggleCottageOwnerScale"> Skala za vlasnika vikendice </botton>
      <botton @click="toggleShipOwnerScale"> Skala za vlasnika broda </botton>
      <botton @click="toggleFishInstScale"> Skala za instruktora pecanja</botton>

    </div>
  <button @click="toggleExperienceReview" class="btn btn-outline-secondary mb-4" style="max-width: 400px;">Pročitaj mišljenje klijenata</button>
  <experience-review-view :ket="reviewsKey" @remountReviews="remountReviews" :id="offerId" :offer-type="offerType" :key="commentsKey" v-show="showExperienceReview"></experience-review-view>
  -->

    <br>

    <div class="row-2">
      <ul class="nav justify-content-center m-2">
        <li class="nav-tabs active" id="ROLE_CLIENT">
          <a class="nav-link" aria-current="page" href="#" @click="loadScales('ROLE_CLIENT')">Klijent</a>
        </li>
        <li class="nav-tabs" id="cottage">
          <a class="nav-link" href="#" id="ROLE_COTTAGE_OWNER" @click="loadScales('ROLE_COTTAGE_OWNER')">Vlasnik vikendice</a>
        </li>
        <li class="nav-tabs" id="ship">
          <a class="nav-link" href="#" id="ROLE_SHIP_OWNER" @click="loadScales('ROLE_SHIP_OWNER')">Vlasnik broda</a>
        </li>
        <li class="nav-tabs" id="fish">
          <a class="nav-link" href="#" id="ROLE_FISHINSTRUCTOR" @click="loadScales('ROLE_FISHINSTRUCTOR')">Instruktor pecanja</a>
        </li>
      </ul>
    </div>

    <br>

    <div class="row">
      <div class="col" v-for="scale in this.scales" :key="scale">
        <loyalty-card :scale="scale" :key="this.myKey"></loyalty-card>
      </div>

    </div>
  </div>
</template>

<script>
import LoyaltyCard from "@/components/admin/loyaltyCard";
import LoyaltyProgramService from "@/servieces/LoyaltyProgramService";
import adminHeader from "@/components/admin/adminHeader";
export default {
  name: "loyaltyScale",
  components: {
    LoyaltyCard,
    adminHeader,

  },
  created: function () {
    LoyaltyProgramService.getLoyaltyScaleForRole('ROLE_CLIENT').then((response) =>{
      this.scales=response.data;
      this.myKey++;
      console.log(this.scales)
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


  },


  methods:{
    loadScales(role){
      document.getElementById(this.last).classList.remove("active");
      if(role==="ROLE_CLIENT"){
        document.getElementById("ROLE_CLIENT").classList.add("active");
        document.getElementById(this.last).classList.remove("active");
        this.last="ROLE_CLIENT";
      }else if(role==="ROLE_COTTAGE_OWNER"){
        document.getElementById("ROLE_COTTAGE_OWNER").classList.add("active");
        document.getElementById(this.last).classList.remove("active");
        this.last="ROLE_COTTAGE_OWNER";
      }else if(role === "ROLE_SHIP_OWNER"){
        document.getElementById("ROLE_SHIP_OWNER").classList.add("active");
        document.getElementById(this.last).classList.remove("active");
        this.last="ROLE_SHIP_OWNER";
      }else{
        document.getElementById("ROLE_FISHINSTRUCTOR").classList.add("active");
        document.getElementById(this.last).classList.remove("active");
        this.last="ROLE_FISHINSTRUCTOR";
      }

      LoyaltyProgramService.getLoyaltyScaleForRole(role).then((response=>{
        this.scales=response.data;
        this.myKey++;
        console.log(this.scales)
      })).catch(function (error) {
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

  data(){
    return{
      scales:[],
      scale:{
        Id:"",
        discount:"",
        startDate:"",
        endDate:"",
        trashold:"",
        userType:"",
        role:""
      },
      myKey:0,
      last:"ROLE_CLIENT"
    }
  }

}
</script>

<style scoped>
  .active{
    border-color: #0645AD;
    border-width: 1px;
    border-radius: 10px;
    border-style: solid;
  }
</style>