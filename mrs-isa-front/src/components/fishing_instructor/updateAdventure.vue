<template>
  <instructor-header></instructor-header>
  <div class="container">


    <div class="row">
      <div class="col-4  d-flex justify-content-center"
           style="border-style: solid; border-width: medium; background-color: #CDCDCD;">
        <div>
          <div class="p-2">
            <label>{{ adventureNameLabel }}</label>
            <br>
            <input type="text" ref="input" v-model="adventure.name" size="25" style="max-width: 200px;">
          </div>
          <div class="p-2">
            <label>{{ priceLabel }}</label>
            <br>
            <input type="number" step="any" v-model="adventure.price" size="25" style="max-width: 200px;">
          </div>
          <div class="p-2">
            <label>{{ descriptionLabel }}</label>
            <br>
            <textarea type="text" ref="input" v-model="adventure.description" size="25" style="max-width: 200px; max-height: 400px;">
            </textarea>
          </div>

          <div>
            <label>{{addressLabel}} </label>
          <openLayers :lon="adventure.longitude" :lat="adventure.latitude" @coordinate-changed="updateCoordinats"
                      style="width: 400px; height: 400px; visibility: visible"></openLayers>

          </div>

        </div>
      </div>
      <div class="col-4 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #CDCDCD;">
        <div>


          <div class="p-2">
            <div class="mb-3">
              <label for="formFile" class="form-label">{{ imgLabel }}</label>
              <input class="form-control" type="file"  id="formFile" >
            </div>
          </div>

          <div class="p-2">
            <label>{{ capacityLabel }}</label>
            <br>
            <input type="number" ref="input" v-model="adventure.capacity" size="25" style="max-width: 200px;">
          </div>

          <div class="p-2">
            <label>{{ biographyLabel }}</label>
            <br>
            <textarea type="text" ref="input" v-model="adventure.instructorBiography" size="25" style="max-width: 200px; max-height: 400px">
            </textarea>
          </div>
          <br>
          <label>{{ ruleLabel }}</label>
          <br>

          <div class="p-2" style="border-style: solid; border-width: medium;">
            <div align="left" class="form-check" v-for="br in this.behRules" :key="br">
              <input class="form-check-input" type="checkbox" :value=br id="flexCheckDefault"
                     v-model="adventure.behaviorRules">
              <label class="form-check-label" :for=br>
                {{ br }}
              </label>
            </div>
          </div>

          <br>
          <label>{{ additionalEquipmentLabel }}</label>
          <br>

          <div class="p-2" style="border-style: solid; border-width: medium;">
            <div align="left" class="form-check" v-for="add in this.addServices" :key="add">
              <input class="form-check-input" type="checkbox" :value=add id="flexCheckDefault"
                     v-model="adventure.additionalServices">
              <label class="form-check-label" :for=add>
                {{ add }}
              </label>
            </div>
          </div>

          <br>
          <h5 id="message"></h5>

        </div>


      </div>

      <div class="col-4 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #CDCDCD;">
        <div>
          <div class="p-2">
            <br>
            <label>{{ fishingEquipmentLabel }}</label>
            <br>

            <div class="p-2" style="border-style: solid; border-width: medium;">
              <div align="left" class="form-check" v-for="fi in this.fishEquip" :key="fi">
                <input class="form-check-input" type="checkbox" :value=fi id="flexCheckDefault"
                       v-model="adventure.fishingEquipment">
                <label class="form-check-label" :for=fi>
                  {{ fi }}
                </label>
              </div>
            </div>

          </div>
          <br>
          <label> {{ cancelConditionsLabel }} </label>
          <br>

          <div class="p-2" style="border-style: solid; border-width: medium;">

            <p>Za odustanak od rezervacije u roku<b> 0 - 5 </b>dana plaća se <input type="number"
                                                                                    v-model="adventure.p1"
                                                                                    size="15"/> %
              ukupnog iznosa</p>
            <p>Za odustanak od rezervacije u roku <b>6 - 10</b> dana plaća se <input type="number"
                                                                                     v-model="adventure.p2"
                                                                                     size="15"/> %
              ukupnog iznosa</p>
            <p>Za odustanak od rezervacije u roku <b>11 - 15</b> dana plaća se <input type="number"
                                                                                      v-model="adventure.p3"
                                                                                      size="15"/> %
              ukupnog iznosa</p>
            <p>Za odustanak od rezervacije <b>16</b> i više dana pre termina plaća se <input type="number"
                                                                                      v-model="adventure.p4"
                                                                                      size="15"/> %
              ukupnog iznosa</p>

          </div>


        </div>
      </div>
    </div>
    <div align="right">
      <botton id="update" class="col-1 btn btn-primary  btn-md  me-md-2" @click="updateAdventure">Izmeni</botton>
      <botton class="col-1 btn btn-secondary  btn-md  me-md-2" @click="back">Odustani</botton>
    </div>
  </div>

</template>


<script>

import InstructorHeader from "@/components/insrtuctorHeader"
import AdventureService from "@/services/AdventureService";
import openLayers from "@/components/VueMaps";
import BehaviorRulesService from "@/servieces/BehaviorRulesService";
import AdditionalServicesService from "@/servieces/AdditionalServicesService";
import FishingEquipmentService from "@/servieces/FishingEquipmentService";

export default {
  name: "updateAdventure",
  components: {
    InstructorHeader,
    openLayers
  },

  created:
      function () {
        console.log("bar je usao u funkciju");

        let type = this.$route.params.type
        AdventureService.getAdventure(type).then((response) => {
          this.adventure = response.data;
          console.log(this.adventure);
          console.log(this.adventure.behavioralRules);
          console.log("capacity" + this.adventure.capacity)
        })
      }
  ,

  mounted() {
    BehaviorRulesService.getAll().then((response) => {
      this.behRules = response.data;
      console.log(this.behRules);
    });

    AdditionalServicesService.getAll().then((response) => {
      this.addServices = response.data;
      console.log(this.addServices);
    });

    FishingEquipmentService.getAll().then((response) => {
      this.fishEquip = response.data;
      console.log(this.fishEquip);
    })
  },

  methods: {
    updateCoordinats(lon, lat) {
      this.adventure.longitude = lon;
      this.adventure.latitude = lat;
      console.log(lon, lat)
    },

    isDouble(x) {
      if (typeof x == 'number' && !isNaN(x)) {
        if (Number.isInteger(x)) {
          return true;
        } else {
          return true;
        }
      } else {
        return false;
      }
    },
    updateAdventure() {
      if (this.adventure.name === "" || this.adventure.capacity == null || this.adventure.instructorBiography === "" || this.adventure.description === "") {
        document.getElementById("message").innerText = this.message;
        document.getElementById("message").style.color = 'red';
      } else if (this.isDouble(this.adventure.price)===false){
        document.getElementById("message").innerText = "Unesite cenu brojčano";
        document.getElementById("message").style.color = 'red';
      }
      else if (this.isDouble(this.adventure.capacity)===false){
        document.getElementById("message").innerText = "unesite kapacitet brojčano";
        document.getElementById("message").style.color = 'red';
      }
      else {
        //axios
        AdventureService.updateAdventure(this.adventure).then((response) => {
          this.adventure = response.data;
          document.getElementById("message").innerText = this.successMessage;
          console.log(this.adventure);
          //    this.$router.push('/instructor/adventures/')
          this.disable = false;
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


        /*  this.backup[0] = this.adventure.name;
          this.backup[1] = this.adventure.description;
          this.backup[2] = this.adventure.instructorBiography;
          this.backup[3] = this.adventure.capacity;
          this.backup[4] = this.adventure.price; */
        //    document.getElementById("message").innerText = this.successMessage;
      }

    },/* cancel() {
      this.adventure.name = this.backup[0];
      this.adventure.description = this.backup[1];
      this.adventure.instructorBiography = this.backup[2];
      this.adventure.capacity = this.backup[3];
      this.adventure.price = this.backup[4];

    },*/
    back() {
      if (this.$route.params.pre === "1") {
        this.$router.push('/instructor/adventures');
      } else if (this.$route.params.pre === "2") {
        let id = this.$route.params.type
        this.$router.push('/adventures/detail/' + id);
      }
    }
      /* }
     , mounted() {
       this.backup = [this.adventure.name, this.adventure.description, this.adventure.instructorBiography, this.adventure.capacity,
         this.adventure.price];*/

  },
    data() {
      return {
        disable: false,

        message: "Obavezno polje",
        errorMessage: "Obavezno polje",
        successMessage: "Uspešno izmenjena avantura",

        adventureNameLabel: "Naziv avanture*",
        addressLabel: "Adresa*",

        descriptionLabel: "Opis avanture*",
        imgLabel: "Fotografije",
        priceLabel: "Cena*",
        capacityLabel: "Kapacitet*",
        biographyLabel: "Biografija instruktora*",

        ruleLabel: "Pravila ponašanja*",
        fishingEquipmentLabel: "Pecaroška oprema*",
        additionalEquipmentLabel: "Dodatna oprema*",
        cancelConditionsLabel: "Uslovi otkaza*",


        rule1: "dozvoljeno pecanje",
        rule2: "zabranjeno pecanje",
        rule3: "dozvoljeno kupanje",
        rule4: "zabranjeno kupanje",
        rule5: "volimo ljubimce",

        fishingEquipment1: "štapovi",
        fishingEquipment2: "mamac",
        fishingEquipment3: "udice",
        fishingEquipment4: "čamac",

        additionalEquipment1: "kabanica",
        additionalEquipment2: "baterijska lampa",

        cancelRule1: "od manje 5",
        cancelRule2: "od više 5",
        cancelRule3: "od više 10",
        cancelRule4: "od više 15",
        cancelRule5: "od više 20",

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
          p1: 0,
          p2: 0,
          p3: 0,
          p4: 0,
          experienceReviews: [],
          priceListId:""
        },

        behRules: [],
        addServices: [],
        fishEquip: [],
      }
    }
}
</script>


<style scoped>

</style>