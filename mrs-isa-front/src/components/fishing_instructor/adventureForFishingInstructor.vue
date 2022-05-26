<template>
  <div class="container">
    <instructor-header></instructor-header>

    <div class="row">
      <div class="col-7">

        <h1 align="left">{{ adventure.name }}</h1>
        <br>
        <div class="row">
          <openLayers :lon="adventure.longitude" :lat="adventure.latitude" @coordinate-changed="updateCoordinats"
                      style="width: 750px; height: 380px;  visibility: visible"></openLayers>
        </div>
        <br>
        <h3 align="left"><em>Opis: {{ adventure.description }} </em></h3>
        <br>
        <div>
          <images-carousel :image_paths=adventure.images></images-carousel>
        </div>

        <hr>
        <h4 align="left"> cena: {{ adventure.price }} </h4>
        <h4 align="left"> maksimalan broj osoba: {{ adventure.capacity }} </h4>
        <hr>
        <h4 align="left"> Pravila ponašanja </h4>
        <ul align="left">
          <li align="left" class="px-10" v-for="rule in adventure.behaviorRules" :key="rule">{{ rule }}</li>
        </ul>
        <hr>

        <h4 align="left"> Pecaroška oprema </h4>
        <ul align="left">
          <li align="left" class="px-10" v-for="equipment in adventure.fishingEquipment" :key="equipment">
            {{ equipment }}
          </li>
        </ul>
        <hr>

        <h4 align="left"> Dodatna oprema </h4>
        <ul align="left">
          <li align="left" class="px-10" v-for="add in adventure.additionalServices" :key="add">
            {{ add }}
          </li>
        </ul>
        <hr>

        <h4 align="left"> Uslovi otkaza </h4>
        <ul align="left">
          <li align="left" class="px-10" v-for="cancel in adventure.cancelConditions" :key="cancel">
            {{ cancel }}
          </li>
        </ul>
        <hr>

        <h4 align="left">Biografija instruktora</h4>
        <p align="left">{{ adventure.instructorBiography }}</p>
        <hr>
        <h4 align="left"> Iskustva avanturista </h4>
        <div align="left">
          <div align="left" border-style="solid" v-for="review in adventure.experienceReviews" :key="review">
            <em><p class="px-10">{{ review }}</p></em>
            <br/>
          </div>
        </div>

        <div align="left" class="d-grid gap-2 d-md-flex justify-content-md-end">
          <button class="btn btn-secondary me-md-2" type="button" @click="goToUpdateAdventure">Izmeni</button>
        </div>

      </div>

      <div class="col">
        <calendar :key="calendarKey" :availability-period="this.availabilityPeriod"
                  :unavailability-period="this.unavailabilityPeriod" :my-events="this.reservations"></calendar>
        <br>
        <div class="row p-3">
          <div class="col-4">
            <label>{{ this.labelStartDate }}</label>
            <Datepicker v-model="availabilityDate.start"></Datepicker>
          </div>
          <div class="col-4">
            <label>{{ this.labelEndDate }}</label>
            <Datepicker v-model="availabilityDate.end"></Datepicker>
          </div>
          <div class="col-4">
            <button class="btn btn-primary me-md-2" type="button" @click="DefinePeriodAvailability">Definiši period
              dostupnosti
            </button>
          </div>
        </div>

        <div class="row p-3">
          <div class="col-4">
            <label>{{ this.labelStartDate }}</label>
            <Datepicker v-model="unavailabilityDate.start"></Datepicker>
          </div>
          <div class="col-4">
            <label>{{ this.labelEndDate }}</label>
            <Datepicker v-model="unavailabilityDate.end"></Datepicker>
          </div>
          <div class="col-4">
            <button class="btn btn-primary me-md-2" type="button" @click="DefinePeriodUnavailability">Definiši period
              nedostupnosti
            </button>
          </div>
        </div>
        <div class="row p-3">
          <div class="col-8">
          </div>
          <div class="col-4">
            <button class="btn btn-primary me-md-2" style="min-width: 137px" type="button" @click="ShowReservations">Rezervacije
            </button>
          </div>
        </div>

        <div class="row p-3">
          <div class="col-8">
          </div>
          <div class="col-4">
            <actionModal :index="generateModalId(this.currentId)" :header="defineActionModalHeader"
                         :adventure="this.adventure"
                         :btnId="generateButtonId(this.currentId)" btnText="Definiši akciju"
                         :key="this.key"></actionModal>
          </div>
        </div>
      </div>
    </div>
  </div>


</template>

<script>
import instructorHeader from "@/components/insrtuctorHeader"
import imagesCarousel from "@/components/imagesCarousel";
import AdventureService from "@/services/AdventureService";
import openLayers from "@/components/VueMaps";
import Calendar from "@/components/calendar";
import Datepicker from "@vuepic/vue-datepicker";
import PeriodAvailabilityUnavailabilityService from "@/servieces/PeriodAvailabilityUnavailabilityService";
import ActionModal from "@/components/fishing_instructor/actionModal";
import swal from "sweetalert2";


export default {
  name: "adventure-instructor",
  components: {
    Calendar,
    instructorHeader,
    imagesCarousel,
    openLayers,
    Datepicker,
    ActionModal,
  },
  created:
      function () {
        let type = this.$route.params.type
        this.currentId = type
        AdventureService.getAdventure(type).then((response) => {
          this.adventure = response.data;
          console.log(this.adventure);
          this.key++;

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
  mounted() {

    PeriodAvailabilityUnavailabilityService.getAvailabilityPeriods(this.currentId).then((response) => {
      this.availabilityPeriod = response.data;
      console.log("dostupno iy fish");
      console.log(this.availabilityPeriod);
      console.log(response.data);
      this.calendarKey++;
    });

    PeriodAvailabilityUnavailabilityService.getUnavailabilityPeriods(this.currentId).then((response) => {
      this.unavailabilityPeriod = response.data;
      console.log("nedostupno iz fish");
      console.log(this.unavailabilityPeriod);
      console.log(response.data);
      this.calendarKey--;
    })

    AdventureService.getReservationPeriods(this.currentId).then((response) => {
      this.reservations = response.data;
      this.calendarKey++;
    })

  },

  methods: {

    goToUpdateAdventure() {
      console.log(this.adventure.id);
      this.$router.push('/adventure/update/' + this.adventure.id + '/2');
    },

    updateCoordinats(lon, lat) {
      this.adventure.longitude = lon;
      this.adventure.latitude = lat;
      console.log(lon, lat)
    },
    fireAlertOn(eventText, ok, title) {
      if (ok === true) {
        swal.fire({
          title: title,
          text: eventText,
          background: 'white',
          color: 'black',
          confirmButtonColor: '#8DF172'
        });
      } else {
        swal.fire({
          title: title,
          text: eventText,
          background: 'white',
          color: 'black',
          confirmButtonColor: '#FECDA6'
        });
      }
    },
    DefinePeriodAvailability() {
      if (this.availabilityDate.start === "" || this.availabilityDate.end === "") {
        this.fireAlertOn("Unesite početni i krajnji datum", false, "Upozorenje");
      } else {
        PeriodAvailabilityUnavailabilityService.defineAvailability(this.currentId, this.availabilityDate).then((response) => {
          this.availDateAns = response.data;
          this.calendarKey++;
          console.log(this.availDateAns)

          if (this.availDateAns === true) {
            this.fireAlertOn("Uspešno ste dodali period dostupnosti.", true, "Obaveštenje");
          } else {
            this.fireAlertOn("Niste uspeli da dodate period dostupnosti.", false, "Obaveštenje");
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

    DefinePeriodUnavailability() {

      if (this.unavailabilityDate.start === "" || this.unavailabilityDate.end === "") {
        this.fireAlertOn("Unesite početni i krajnji datum", false, "Upozorenje");
      } else {

        PeriodAvailabilityUnavailabilityService.defineUnavailability(this.currentId, this.unavailabilityDate).then((response) => {
          this.unavailDateAns = response.data;
          this.calendarKey--;

          if (this.unavailDateAns === true) {
            this.fireAlertOn("Uspešno ste dodali period nedostupnosti.", true, "Obaveštenje");
          } else {
            this.fireAlertOn("Niste uspeli da dodate period nedostupnosti.", false, "Obaveštenje");
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

    ShowReservations() {
      console.log(this.currentId);
      this.$router.push('/adventure/reservations/' + this.currentId);
    },

    generateModalId(id) {
      return "m" + id;
    },

    generateButtonId(id) {
      return "button" + id;
    },


  },
  data() {
    return {
      currentId: 0,
      adventure: {
        id: 0,
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
      availabilityDate: {
        start: "",
        end: "",
      },
      unavailabilityDate: {
        start: "",
        end: "",
      },
      labelStartDate: "Početni datum",
      labelEndDate: "Krajnji datum",

      unavailDateAns: false,
      availDateAns: false,

      availabilityPeriod: [],
      unavailabilityPeriod: [],
      reservations: [],
      calendarKey: 1,
      period: {
        start: "",
        end: "",
        color: "",
        title: "",
      },

      defineActionModalHeader: "Definisanje akcije",
      key: 0

    }
  }
}
</script>

<style scoped>

</style>