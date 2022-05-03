<template>
  <div class="container">
    <instructor-header></instructor-header>
    <div class="row">

      <div class="col-4  d-flex justify-content-center"
           style="border-style: solid; border-width: medium; background-color: #CDCDCD;">
        <div>
          <div class="p-2">
            <label>{{ adventureNameLabel }}</label>
            <br>
            <input type="text" ref="input" v-model="adventure.name" size="25">
          </div>
          <div class="p-2">
            <label>{{ priceLabel }}</label>
            <br>
            <input type="text" ref="input" v-model="adventure.price" size="25">
          </div>
          <div class="p-2">
            <label>{{ descriptionLabel }}</label>
            <br>
            <textarea type="text" ref="input" v-model="adventure.description" size="25">
            </textarea>


          </div>
          <div>
            <label>{{}}</label>
          <openLayers :lon="adventure.longitude" :lat="adventure.latitude" @coordinate-changed="updateCoordinats"
                      style="width: 300px; height: 380px; visibility: visible"></openLayers>
          </div>

        </div>
      </div>
      <div class="col-4 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #CDCDCD;">
        <div>


          <div class="p-2">
            <div class="mb-3">
              <label for="formFile" class="form-label">{{ imgLabel }}</label>
              <input class="form-control" type="file" :value="adventure.images" id="formFile" @change="onFileSelected">
            </div>
          </div>

          <div class="p-2">
            <label>{{ capacityLabel }}</label>
            <br>
            <input type="number" ref="input" v-model="adventure.capacity" size="25">
          </div>

          <div class="p-2">
            <label>{{ biographyLabel }}</label>
            <br>
            <textarea type="text" ref="input" v-model="adventure.instructorBiography" size="25">
            </textarea>
          </div>
          <br>
          <label>{{ ruleLabel }}</label>
          <br>
          <div class="p-2" style="border-style: solid; border-width: medium;">
            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" :value=rule1 id="flexCheckDefault"
                     v-model="adventure.behaviorRules">
              <label :for=rule1 class="form-check-label">
                {{ rule1 }}
              </label>
            </div>
            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" :value=rule2 id="flexCheckDefault"
                     v-model="adventure.behaviorRules">
              <label class="form-check-label" :for=rule2>
                {{ rule2 }}
              </label>
            </div>

            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" :value=rule3 id="flexCheckDefault"
                     v-model="adventure.behaviorRules">
              <label class="form-check-label" :for=rule3>
                {{ rule3 }}
              </label>
            </div>

            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" :value=rule4 id="flexCheckDefault"
                     v-model="adventure.behaviorRules">
              <label class="form-check-label" :for=rule4>
                {{ rule4 }}
              </label>
            </div>

            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" :value=rule5 id="flexCheckDefault"
                     v-model="adventure.behaviorRules">
              <label class="form-check-label" :for=rule5>
                {{ rule5 }}
              </label>
            </div>
          </div>

          <br>
          <label>{{ additionalEquipmentLabel }}</label>
          <br>
          <div class="p-2" style="border-style: solid; border-width: medium;">
            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" :value=additionalEquipment1 id="flexCheckDefault"
                     v-model="adventure.additionalServices">
              <label class="form-check-label" :for=additionalEquipment1>
                {{ additionalEquipment1 }}
              </label>
            </div>
            <div align="left" class="form-check">
              <input class="form-check-input" type="checkbox" :value=additionalEquipment2 id="flexCheckDefault"
                     v-model="adventure.additionalServices">
              <label class="form-check-label" :for=additionalEquipment2>
                {{ additionalEquipment2 }}
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
              <div align="left" class="form-check">
                <input class="form-check-input" type="checkbox" :value=fishingEquipment1 id="flexCheckDefault"
                       v-model="adventure.fishingEquipment">
                <label class="form-check-label" :for=fishingEquipment1>
                  {{ fishingEquipment1 }}
                </label>
              </div>
              <div align="left" class="form-check">
                <input class="form-check-input" type="checkbox" :value=fishingEquipment2 id="flexCheckDefault"
                       v-model="adventure.fishingEquipment">
                <label class="form-check-label" :for=fishingEquipment2>
                  {{ fishingEquipment2 }}
                </label>
              </div>
              <div align="left" class="form-check">
                <input class="form-check-input" type="checkbox" :value=fishingEquipment3 id="flexCheckDefault"
                       v-model="adventure.fishingEquipment">
                <label class="form-check-label" :for=fishingEquipment3>
                  {{ fishingEquipment3 }}
                </label>
              </div>
              <div align="left" class="form-check">
                <input class="form-check-input" type="checkbox" :value=fishingEquipment4 id="flexCheckDefault"
                       v-model="adventure.fishingEquipment">
                <label class="form-check-label" :for=fishingEquipment4>
                  {{ fishingEquipment4 }}
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
      <botton class="col-1 btn btn-primary  btn-md  me-md-2" @click="addAdventure">Dodaj</botton>
      <botton class="col-1 btn btn-secondary  btn-md  me-md-2" @click="back">Odustani</botton>
    </div>
  </div>

</template>

<script>
import InstructorHeader from "@/components/insrtuctorHeader"
import AdventureService from "@/services/AdventureService";
import openLayers from "@/components/VueMaps";
export default {
  name: "addAdventure",
  components: {
    InstructorHeader,
    openLayers,
  },
  methods: {
    updateCoordinats(lon, lat) {
      this.adventure.longitude = lon;
      this.adventure.latitude = lat;
      console.log(lon, lat)
    },

    addAdventure() {
      console.log(this.adventure);
      if (this.adventure.name === "" || this.adventure.capacity == null || this.adventure.instructorBiography === "" || this.adventure.description === "") {
        document.getElementById("message").innerText = this.message;
        document.getElementById("message").style.color = 'red';
      } else {
        AdventureService.saveAdventure(this.adventure).then((response) => {
          this.adventure = response.data;
          document.getElementById("message").innerText = this.successMessage;
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
      }

    },
    back() {
      this.$router.push('/instructor/adventures');
    }
    /* onFileSelected(){
         document.querySelector("#formFile").addEventListener("change", function(){
           const reader = new FileReader();
           reader.addEventListener("load", () =>{
             localStorage.setItem("assets/img/adventure", reader.result);
           })
         })

   }, */

  },
  data() {
    return {
      selectedFile: null,

      message: "Popunite obavezna polja",
      errorMessage: "Obavezno polje",
      successMessage: "Uspešno sačuvana avantura",

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
        id:2,
        name: "",
        latitude:"",
        longitude:"",
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
        p1:"0",
        p2:"0",
        p3:"0",
        p4:"0",
        experienceReviews: [],
      }
    }
  }
}
</script>


<style scoped>
</style>
