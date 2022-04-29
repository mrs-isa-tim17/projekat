<template>
  <div class="container">
    <instructor-header></instructor-header>

    <div class="row">
      <div class="col-8">
      <!--  <h1>{{ adventure }}</h1>  -->

        <h1 align="left">{{ adventure.name }}</h1>
        <h3 align="left" class="px-5">adresa: {{ adventure.streetName }} {{ adventure.serialNumber }},
          {{ adventure.city }}, {{ adventure.country }}</h3>
        <h3 align="left" class="px-5"><em>{{ adventure.description }} </em></h3>

        <div class="px-5">
          <images-carousel :image_paths=adventure.images></images-carousel>
        </div>


        <h4 align="left" class="px-5"> cena: {{ adventure.price }} </h4>
        <h4 align="left" class="px-5"> maksimalan broj osoba: {{ adventure.capacity }} </h4>
        <hr>
        <h4 align="left" class="px-5"> Pravila ponašanja </h4>
        <ul align="left" class="px-5">
          <li align="left" class="px-10" v-for="rule in adventure.behavioralRules" :key="rule">{{ rule }}</li>
        </ul>
        <hr>

        <h4 align="left" class="px-5"> Pecaroška oprema </h4>
        <ul align="left" class="px-5">
          <li align="left" class="px-10" v-for="equipment in adventure.fishingEquipment" :key="equipment">
            {{ equipment }}
          <!--  {{ equipment.quantity }} {{ equipment.units }} -->
          </li>
        </ul>
        <hr>
        <h4 align="left" class="px-5"> Uslovi otkaza </h4>
        <ul align="left" class="px-5">
          <li align="left" class="px-10" v-for="cancel in adventure.cancelConditions" :key="cancel">
            {{ cancel }}
          </li>
        </ul>
        <hr>

        <h4 align="left" class="px-5">Biografija instruktora</h4>
        <p align="left" class="px-5">{{ adventure.instructorBiography }}</p>
        <hr>
        <h4 align="left" class="px-5"> Iskustva avanturista </h4>
        <div align="left" class="px-5">
          <div align="left" border-style="solid" v-for="review in adventure.experienceReviews" :key="review">
            <em><p class="px-10">{{ review }}</p></em>
            <br/>
          </div>
        </div>

        <div align="left" class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button class="btn btn-secondary me-md-2" type="button">Izmeni</button>
        </div>

      </div>

      <div class="col">
        <p>ovde kalendar</p> -->
      </div>

    </div>
  </div>

</template>

<script>
import instructorHeader from "@/components/insrtuctorHeader"
import imagesCarousel from "@/components/imagesCarousel";
import AdventureService from "@/services/AdventureService";

export default {
  name: "adventure-instructor",
  components: {
    instructorHeader,
    imagesCarousel,
  },
  created:
      function () {
        let type = this.$route.params.type
        AdventureService.getAdventure(type).then((response) => {
          this.adventure = response.data;
          console.log(this.adventure)
        })
            .catch(function (error) {
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
  data() {
    return {
      adventure: {
        name: "",
        streetName: "",
        serialNumber: "",
        country: "",
        description: "",
        city: "",
        behavioralRules: [],
        images: [],
        fishingEquipment: [],
        cancelConditions: [],
        experienceReviews: [],
        price: "",
        capacity: "",
        instructorBiography: "",
        additionalEquipment:[],
        days : [5,10,15,20],
        percentage : [0, 0, 0, 0],
      },

      /* adventure1: {
         name: "Rafting Drinom",
         address: {
           streetName: "Gogoljeva",
           serialNumber: "22",
           place: {postNumber: 22240, name: "Bajina Bašta", country: "Srbija"}
         },
         description: 'Uzbudljvo putovanje za porodicu i prijatelje',

         images: [{path: require("@/assets/img/adventure/Drina1.jpg")}, {path: require("@/assets/img/adventure/Drina2.jpg")}, {path: require("@/assets/img/adventure/Drina3.jpg")}, {path: require("@/assets/img/adventure/Drina4.jpg")}],
         priceList: [{startDate: "start", endDate: "end", price: 12000}],
         behavioralRule: ["dozvoljeno pecanje", "zabranjeno kupanje", "pet friendly"],
         cancelCondition: [{days: 5, double: 30}, {days: 30, double: 5}],
         additionalServices: ["prevoz do plaze", "dorucak u kampu", "baterijska lampa"],

         instructorBiography: "Avanturisticki duh, zastitom reke Drine se bavi od 2008. godine...",
         capacity: 5,
         fishingEquipment: [{name: "stap", units: "komada", quantity: 10}, {name: "crvi", units: "kg", quantity: "2"}],
         averageRaiting: 4.35,
         experienceReview: [{
           text: "odlicno   Pravila ponašanja dozvoljeno pecanje zabranjeno kupanjepet friendlyPecaroška opremastap ima 10 komadacrvi ima 2 kglovi otkaza otkažete u roku od 5 dana pre početka događaja, plaćate 30% od ukupne cene rezervacije.roku od 30 dana pre početka događaja, plaćate 5% od ukupne cene rezervacijeBiografija instruktora Avanturisticki duh, zastitom reke Drine se bavi od 2008. godine...",
           client: {name: "Milan", surname: "Milic"}
         }, {text: "onako", client: {name: "Jelena", surname: "Jankovic"}}, {
           text: "prelepo",
           client: {name: "Pera", surname: "Peric"}
         }]
       }  */
    }
  }
}
</script>

<style scoped>

</style>