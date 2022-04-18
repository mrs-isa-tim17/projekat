<template>

  <div class="container">
    <instructor-header></instructor-header>
    <div class="row d-flex justify-content-center">
      <div class="col-8 d-flex justify-content-center">
        <div>
          <div class="input-group mb-3">
            <input type="text" class="form-control" aria-label="Text input with dropdown button">
            <button class="btn btn-primary me-md-2" type="button" aria-expanded="false">Search</button>
            <button class="btn btn-secondary  btn-sm  me-md-2" @click="goToAddAdventure"> Dodaj novu avanturu</button>
          </div>
          <div v-for="item  in adventures" :key="item"> <!-- v-if="index < 10"> -->
            <simple-adventure :adventure="item" :path=item.images[0].path></simple-adventure>
          </div>

          <nav aria-label="...">

            <nav aria-label="Page navigation example">
              <ul class="pagination d-flex justify-content-center">
                <li class="page-item">
                  <a class="page-link" href="#" aria-label="Previous">
                    <span aria-hidden="true">&laquo;</span>
                  </a>
                </li>
                <li class="page-item"><a class="page-link" href="#">1</a></li>
                <li class="page-item"><a class="page-link" href="#">2</a></li>
                <li class="page-item"><a class="page-link" href="#">3</a></li>
                <li class="page-item">
                  <a class="page-link" href="#" aria-label="Next">
                    <span aria-hidden="true">&raquo;</span>
                  </a>
                </li>
              </ul>
            </nav>
          </nav>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import instructorHeader from "@/components/insrtuctorHeader"
import simpleAdventure from "./simpleAdventure";

export default {
  name: "adventure-all",
  components: {
    instructorHeader,
    simpleAdventure
  },
  methods: {
    goToAddAdventure() {
      this.$router.push('/adventure/add');
    }
  },

  data() {
    return {
      adventures: [
        {
          id: 1,
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
        },
        {
          id: 2,
          name: "Planinarenje na Bjelasnici",
          address: {
            streetName: "Danila Kisa",
            serialNumber: "3",
            place: {postNumber: 22240, name: "Novi Pazar", country: "Srbija"}
          },
          description: 'Uzbudljvo putovanje za porodicu i prijatelje',

          images: [{path: require("@/assets/img/adventure/Drina1.jpg")}, {path: require("@/assets/img/adventure/Drina2.jpg")}, {path: require("@/assets/img/adventure/Drina3.jpg")}, {path: require("@/assets/img/adventure/Drina4.jpg")}],

          priceList: [{startDate: "start", endDate: "end", price: 12000}],
          behavioralRule: ["zabranjeno branje zasticenih biljnih vrsta", "cuvamo prirodu", "pet friendly"],
          cancelCondition: [{days: 5, double: 30}, {days: 30, double: 5}],
          additionalServices: ["prevoz do podnozja", "dorucak u kampu", "stapovi za penjanje"],

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
        }
      ]
    }
  }
}


</script>

<style scoped>

</style>