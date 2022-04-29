<template>
  <div class="container">
    <instructor-header></instructor-header>
    <div class="row">
      <div class="col-4  d-flex justify-content-center"
           style="border-style: solid; border-width: medium; background-color: #B0C4DE;">
        <div>
          <inputField :label="adventureNameLabel" :info="adventure.name"></inputField>
          <inputField :label="cityLabel" :info="adventure.address.place.name"></inputField>
          <inputField :label="streetLabel" :info="adventure.address.streetName"></inputField>
          <inputField :label="numberLabel" :info="adventure.address.serialNumber"></inputField>
          <inputField :label="countryLabel" :info="adventure.address.place.country"></inputField>

          <inputField :label="priceLabel" :info="adventure.priceList[0].price"></inputField>
          <div class="p-2">
            <label>{{ descriptionLabel }}</label>
            <br>
            <textarea type="text" ref="input" :value="adventure.description" size="25">
            </textarea>
          </div>

        </div>
      </div>
      <div class="col-4 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #B0C4DE;">
        <div>


          <div class="p-2">
            <div class="mb-3">
              <label for="formFile" class="form-label">{{ imgLabel }}</label>
              <input class="form-control" type="file" id="formFile">
            </div>
          </div>

          <inputField :label="capacityLabel" :info="adventure.capacity"></inputField>

          <div class="p-2">
            <label>{{ biographyLabel }}</label>
            <br>
            <textarea type="text" ref="input" :value="adventure.instructorBiography" size="25">
            </textarea>
          </div>
          <br>
          <label>{{ ruleLabel }}</label>
          <br>
          <div class="p-2" style="border-style: solid; border-width: medium;">
            <div align="left" class="form-check" v-for="rule in adventure.behavioralRule" :key="rule">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked>
              <label class="form-check-label" for="flexCheckDefault">
                {{ rule }}
              </label>
            </div>

          <br>
          <label>{{ additionalEquipmentLabel }}</label>
          <br>
          <div class="p-2" style="border-style: solid; border-width: medium;">
            <div align="left" class="form-check" v-for="equipment in adventure.additionalServices" :key="equipment">
              <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault" checked>
              <label class="form-check-label" for="flexCheckDefault">
                {{ equipment }}
              </label>
            </div>
          </div>

        </div>


      </div>


      <div class="col-4 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #B0C4DE;">
        <div>
          <div class="p-2">
            <br>
            <label>{{ fishingEquipmentLabel }}</label>
            <br>
            <div class="p-2" style="border-style: solid; border-width: medium;">
              <div align="left" class="form-check" v-for="fishingEquipment in adventure.fishingEquipment" :key="fishingEquipment">
                <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                  {{ fishingEquipment.name }}
                </label>
              </div>

            </div>

          </div>
          <br>
          <label> {{ cancelConditionsLabel }} </label>
          <br>

          <div class="p-2" style="border-style: solid; border-width: medium;" v-for="cancelRule in adventure.cancelCondition" :key="cancelRule">

            <p>Za odustanak od rezervacije u roku <b>{{ cancelRule.days}}</b> dana plaća se <input type="text" :value= cancelRule.double size="1"/> %
              ukupnog iznosa</p>

          </div>


        </div>
      </div>
    </div>
    <div align="right">
      <botton class="col-1 btn btn-primary  btn-md  me-md-2">Izmeni</botton>
      <botton class="col-1 btn btn-secondary  btn-md  me-md-2">Odustani</botton>
    </div>
  </div>
  </div>
</template>

<script>

import InputField from "@/components/inputField";
import InstructorHeader from "@/components/insrtuctorHeader"
//import AdventureService from "@/services/AdventureService";

export default {
  name: "addAdventure",
  components: {
    InputField,
    InstructorHeader,
  },

  created: {
   /* function() {
      let type = this.$route.params.type
      AdventureService.getAdventure(type).then((response) => {
        this.adventure = response.data;
        console.log(this.adventure)
      })
    }*/
  },
  methods:{
  applyChanges(){
    if (this.adventure.name == "" || this.adventure.name[0] == this.adventure.name[0].toLowerCase()){
      this.message = this.errorMessage;
      document.getElementById("emptyError").style.color = "red";
      document.getElementById("emptyError").style.visibility = "visible";
    }
    else if (this.client.surname == "" || this.client.surname[0] == this.client.surname[0].toLowerCase()){
      this.message = this.errorMessage;
      document.getElementById("emptyError").style.color = "red";
      document.getElementById("emptyError").style.visibility = "visible";
    }
    else if (this.client.phoneNumber == ""){
      this.message = this.errorMessage;
      document.getElementById("emptyError").style.color = "red";
      document.getElementById("emptyError").style.visibility = "visible";
    }
    else if (this.client.country == "" || this.client.country[0] == this.client.country[0].toLowerCase()){
      this.message = this.errorMessage;
      document.getElementById("emptyError").style.color = "red";
      document.getElementById("emptyError").style.visibility = "visible";
    }
    else if (this.client.city == "" || this.client.city[0] == this.client.city[0].toLowerCase()){
      this.message = this.errorMessage;
      document.getElementById("emptyError").style.color = "red";
      document.getElementById("emptyError").style.visibility = "visible";
    }
    else if (this.client.address == ""){
      this.message = this.errorMessage;
      document.getElementById("emptyError").style.color = "red";
      document.getElementById("emptyError").style.visibility = "visible";
    }
    else{
      //axios
      this.message = this.successMessage;
      document.getElementById("emptyError").style.color = "green";
      document.getElementById("emptyError").style.visibility = "visible";
      this.backup[0] = this.client.name;
      this.backup[1] = this.client.surname;
      this.backup[2] = this.client.phoneNumber;
      this.backup[3] = this.client.password;
      this.backup[4] = this.client.country;
      this.backup[5] = this.client.city;
      this.backup[6] = this.client.address;
    }

  },cancel(){
    this.client.name = this.backup[0];
    this.client.surname = this.backup[1];
    this.client.phoneNumber = this.backup[2];
    this.client.password = this.backup[3];
    this.client.country = this.backup[4];
    this.client.city = this.backup[5];
    this.client.address = this.backup[6];
    document.getElementById("emptyError").style.visibility = "hidden";
  },

}
,mounted() {
  this.backup = [this.client.name, this.client.surname, this.client.phoneNumber, this.client.password, this.client.country,
    this.client.city, this.client.address];
},

  data() {
    return {
      message: "Obavezno polje",
      errorMessage: "Obavezno polje",
      successMessage: "Uspešno sačuvana avantura",

      adventureNameLabel: "Naziv avanture*",
      cityLabel: "Grad*",
      streetLabel: "Adresa*",
      numberLabel: "Broj*",
      countryLabel: "Država*",

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
        instructorBiography: ""
      },
      /*   adventure: {
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
      }*/

    }
  }
}
</script>


<style scoped>

</style>