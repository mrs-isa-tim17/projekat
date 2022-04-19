<template>
  <div class="container">

    <clientHeader></clientHeader>
    <div class="row">
      <div class="col-4  d-flex justify-content-center" style="border-style: solid; border-width: medium; background-color: #778899;">
        <div>
          <disabledInputField :label="numLoyaltyPointsLabel" :info="client.loyaltyPoints"> </disabledInputField>
          <disabledInputField :label="userCategoryLabel" :info="client.userType"> </disabledInputField>
          <label>Pogodnosti</label>
          <textarea disabled class="my-4 text-center" rows="5" cols="40" style="background-color: #BBC4CC;" name="Pogodnosti" :value="client.benefits">

          </textarea>
          <disabledInputField :label="numPenaltiesLabel" :info="client.penaltyNumber"> </disabledInputField>
        </div>
      </div>

      <div class="col-8 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #B0C4DE;">
        <div class="row d-flex justify-content-center" style="">
          <div class="col-4 d-flex justify-content-center">
            <div>

              <div class="p-2">
                <label >{{nameLabel}}</label>
                <br>
                <input type="text" ref="input" v-model="client.name" size="25" required>
                <br>
                <p id="nameEmpty" v-if="this.client.name == ''" style="color: red;"> Ime mora da postoji </p>
                <p v-if="this.client.name[0] == this.client.name[0].toLowerCase()" style="color: red;"> Ime mora da počinje velikom slovom </p>

              </div>

              <div class="p-2">
                <label >{{surnameLabel}}</label>
                <br>
                <input type="text" ref="input" v-model="client.surname" size="25" required>
                <p v-if="this.client.surname == ''" style="color: red;"> Prezime mora da postoji</p>
                <p  v-if="this.client.surname[0] == this.client.surname[0].toLowerCase()" style="color: red;"> Prezime mora da počinje velikom slovom </p>
              </div>


              <disabled-input-field :label="emailLabel" :info="client.email"> </disabled-input-field>

              <div class="p-2">
                <label >{{phoneNumberLabel}}</label>
                <br>
                <input type="text" ref="input" v-model="client.phoneNumber" size="25" required>
                <p v-if="this.client.surname == ''" style="color: red;"> Broj telefona mora da postoji</p>
              </div>

              <div class="p-2">
                <label >{{passwordLabel}}</label>
                <br>
                <input type="password" ref="input" v-model="client.password" size="25">
              </div>
            </div>
          </div>
        </div>
        <div class="col-4 d-flex justify-content-center">
          <div>
            <div class="p-2">
              <label >{{countryLabel}}</label>
              <br>
              <input type="text" ref="input" v-model="client.country" size="25" required>
              <p v-if="this.client.country == ''" style="color: red;"> Država mora da postoji</p>
              <p v-if="this.client.country[0] == this.client.country[0].toLowerCase()" style="color: red;"> Država mora da počinje velikom slovom </p>
            </div>

            <div class="p-2">
              <label >{{cityLabel}}</label>
              <br>
              <input type="text" ref="input" v-model="client.city" size="25" required>
              <p v-if="this.client.city == ''" style="color: red;"> Grad mora da postoji</p>
              <p v-if="this.client.city[0] == this.client.city[0].toLowerCase()" style="color: red;"> Grad mora da počinje velikom slovom </p>
            </div>

            <div class="p-2">
              <label >{{addressLabel}}</label>
              <br>
              <input type="text" ref="input" v-model="client.address" size="25" required>
              <p v-if="this.client.address == ''" style="color: red;"> Adresa mora da postoji</p>
            </div>

            <div class="p-2">
              <label >{{addressSerialNumberLabel}}</label>
              <br>
              <input type="text" ref="input" v-model="client.serialNumber" size="25" required>
              <p v-if="this.client.serialNumber == ''" style="color: red;"> Kućni broj mora da postoji</p>
            </div>
            <br>
            <br>
            <br>
            <label id="emptyError" style="color: red; visibility: hidden;"> {{message}}</label>
            <button type="button" class="btn" @click="cancel" style="background-color:#3399FF;">Odustani</button>
            <button type="button" class="btn" @click="applyChanges" style="background-color:#3399FF;">Promeni</button>
          </div>
        </div>

      </div>
    </div>
  </div>
</template>

<script>
import clientHeader from "@/components/clientHeader";
import disabledInputField from "@/components/disabledInputField";
import ClientServce from "@/servieces/ClientServce";
export default {
  name: "client-profile-change",
  components: {
    clientHeader,
    disabledInputField,
  },
  created:
      function () {
        this.clientID = this.$route.params.id;
        ClientServce.getClient(this.clientID)
            .then((response) => {
              console.log(response);
              this.client = response.data;
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
                // `error.request` is an instance of XMLHttpRequest in the browser and an instance of
                // http.ClientRequest in node.js
                console.log(error.request);
              } else {
                // Something happened in setting up the request that triggered an Error
                console.log('Error', error.message);
              }
              console.log(error.config);
            });
      }
  ,
  methods: {

    applyChanges(){
      if (this.client.name == "" || this.client.name[0] == this.client.name[0].toLowerCase()){
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
      else if (this.client.serialNumber == ""){
        this.message = this.errorMessage;
        document.getElementById("emptyError").style.color = "red";
        document.getElementById("emptyError").style.visibility = "visible";
      }
      else{
        ClientServce.updateClient(this.clientID, this.client)
            .then("Success");
        //this.client.name, this.client.surname, this.client.password,
        //this.client.phoneNumber, this.client.city, this.client.country, this.client.address
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
        this.backup[7] = this.client.serialNumber;
      }

    },cancel(){
      this.client.name = this.backup[0];
      this.client.surname = this.backup[1];
      this.client.phoneNumber = this.backup[2];
      this.client.password = this.backup[3];
      this.client.country = this.backup[4];
      this.client.city = this.backup[5];
      this.client.address = this.backup[6];
      this.client.serialNumber = this.backup[7];
      document.getElementById("emptyError").style.visibility = "hidden";
    },

  }
  ,mounted() {

    this.backup = [this.client.name, this.client.surname, this.client.phoneNumber, this.client.password, this.client.country,
      this.client.city, this.client.address];

  },
  data() {
    return {
      message: "Obavezne polje mora da postoje",
      errorMessage: "Obavezne polje mora da postoje",
      successMessage: "Uspešno sačuvano podaci",

      numLoyaltyPointsLabel: "Broj lojaltih bodova",
      userCategoryLabel: "Kategorija",
      benefitsLabel: "Pogodnosti",
      numPenaltiesLabel: "Penale",
      nameLabel: "Ime*",
      surnameLabel: "Prezime*",
      emailLabel: "Email*",
      phoneNumberLabel: "Broj telefona*",
      passwordLabel: "Lozinka",
      countryLabel: "Država*",
      cityLabel: "Grad*",
      addressLabel: "Adresa*",
      addressSerialNumberLabel: "Kućni broj*",

      client: {}

    }
  }
}
</script>

<style scoped>

</style>