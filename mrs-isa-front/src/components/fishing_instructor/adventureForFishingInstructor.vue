<template>
  <div class="container">
    <instructor-header></instructor-header>

    <div class="row">
      <div class="col-8">

        <h1 align="left">{{ adventure.name }}</h1>

        <openLayers :lon="adventure.longitude" :lat="adventure.latitude" @coordinate-changed="updateCoordinats"
                    style="width: 300px; height: 380px; visibility: visible"></openLayers>

        <h3 align="left" class="px-5"><em>{{ adventure.description }} </em></h3>

        <div class="px-5">
          <images-carousel :image_paths=adventure.images></images-carousel>
        </div>


        <h4 align="left" class="px-5"> cena: {{ adventure.price }} </h4>
        <h4 align="left" class="px-5"> maksimalan broj osoba: {{ adventure.capacity }} </h4>
        <hr>
        <h4 align="left" class="px-5"> Pravila ponašanja </h4>
        <ul align="left" class="px-5">
          <li align="left" class="px-10" v-for="rule in adventure.behaviorRules" :key="rule">{{ rule }}</li>
        </ul>
        <hr>

        <h4 align="left" class="px-5"> Pecaroška oprema </h4>
        <ul align="left" class="px-5">
          <li align="left" class="px-10" v-for="equipment in adventure.fishingEquipment" :key="equipment">
            {{ equipment }}
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
import openLayers from "@/components/VueMaps";


export default {
  name: "adventure-instructor",
  components: {
    instructorHeader,
    imagesCarousel,
    openLayers,
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
  methods: {
    updateCoordinats(lon, lat) {
      this.adventure.longitude = lon;
      this.adventure.latitude = lat;
      console.log(lon, lat)
    }
  },
  data() {
    return {
      adventure: {
        id: 2,
        name: "",
        latitude: "",
        longitude: "",
        description: "",
        behaviorRules: [],
        images: [],
        fishingEquipment: [],
        cancelConditions: [],
        price: "",
        capacity: "",
        instructorBiography: "",
        additionalServices: [],
        days: ['5', '10', '15', '20'],
        percentage: ['0', '0', '0', '0'],
        experienceReviews: [],
      },
    }
  }
}
</script>

<style scoped>

</style>