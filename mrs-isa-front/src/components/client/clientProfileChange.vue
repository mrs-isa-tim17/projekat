<template>
  <div class="containter h-100">

    <clientHeader></clientHeader>
    <div class="row mt-2">
      <div class="col  d-flex justify-content-center" style="margin-left: 5%; border-style: solid; border-width: medium; background-color: #CDCDCD;">
        <div class=" mt-3">
          <disabledInputField :label="numLoyaltyPointsLabel" :info="client.loyaltyPoints"> </disabledInputField>
          <disabledInputField :label="userCategoryLabel" :info="client.userType"> </disabledInputField>
          <label>Pogodnosti</label>
          <textarea disabled class="my-4 text-center" rows="5" cols="40" style="background-color: #BBC4CC;" name="Pogodnosti" :value="client.benefits">

          </textarea>
          <disabledInputField :label="numPenaltiesLabel" :info="client.penaltyNumber"> </disabledInputField>
        </div>
      </div>

          <div class="col mt-3" style="margin-left: 5%;">
            <div>

              <div class="p-2">
                <label >{{nameLabel}}</label>
                <br>
                <input type="text" ref="input" v-model="client.name" size="25" required>
                <br>
                <p id="nameEmpty" v-if="this.client.name === ''" style="color: red;"> Ime mora da postoji </p>
                <p v-if="this.client.name[0] === this.client.name[0].toLowerCase()" style="color: red;"> Ime mora da počinje velikom slovom </p>

              </div>

              <div class="p-2">
                <label >{{surnameLabel}}</label>
                <br>
                <input type="text" ref="input" v-model="client.surname" size="25" required>
                <p v-if="this.client.surname === ''" style="color: red;"> Prezime mora da postoji</p>
                <p  v-if="this.client.surname[0] === this.client.surname[0].toLowerCase()" style="color: red;"> Prezime mora da počinje velikom slovom </p>
              </div>


              <disabled-input-field :label="emailLabel" :info="client.email"> </disabled-input-field>

              <div class="p-2">
                <label >{{phoneNumberLabel}}</label>
                <br>
                <input type="text" ref="input" v-model="client.phoneNumber" size="25" required>
                <p v-if="this.client.surname === ''" style="color: red;"> Broj telefona mora da postoji</p>
              </div>

              <div class="p-2">

                <changePasswordModal
                    :index="buttonId" :header="changePassHeader" @input-new-password="changePassword"></changePasswordModal>
              </div>


              <label id="emptyError" style="color: red; visibility: hidden; margin-top: 25%;"> {{message}}</label>

            </div>
          </div>

          <div class="col mt-3" style="margin-left: 5%;">
            <div>

              <openLayers :lon="client.longitude" :lat="client.latitude" @coordinate-changed="updateCoordinats" style="width: 300px; height: 380px; visibility: visible"></openLayers>
              <div style="margin-top: 20px;">

                <button type="button" class="btn" @click="cancel" style="background-color:#3399FF; margin-right: 2%;">Odustani</button>
                <button type="button" class="btn" @click="applyChanges" style="background-color:#3399FF;">Promeni</button>
              </div>
            </div>
        </div>

    </div>
  </div>
</template>

<script>
import clientHeader from "@/components/client/clientHeader";
import disabledInputField from "@/components/disabledInputField";
import ClientServce from "@/servieces/ClientServce";
import vueOpenLayerMap from "@/components/VueMaps";
import PasswordService from "@/servieces/PasswordService";
import changePasswordModal from "@/components/changePasswordModal";

export default {
  name: "client-profile-change",
  components: {
    changePasswordModal,
    clientHeader,
    disabledInputField,
    openLayers: vueOpenLayerMap
  },
  /*
  computed:{
    currentUser(){
      return this.$store.state.auth.user;
    }
  },*/
  created:
      function () {
        this.clientID = JSON.parse(localStorage.user).id;//this.$route.params.id;
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
    changePassword(old_password, new_password){
      this.coID = JSON.parse(localStorage.user).id;
      this.passwords.old_password=old_password;
      this.passwords.new_password = new_password;
      PasswordService.matchPassword(this.passwords,this.coID)
          .then((response)=>{
                if(response.data){
                  document.getElementById("successChange").style.visibility = 'visible';
                }
                else{
                  document.getElementById("notSuccessChangePass").style.visibility = 'visible';
                }
              }
          )
      console.log(new_password);
    },
    updateCoordinats(lon, lat){
      this.client.longitude = lon;
      this.client.latitude = lat;
      console.log(lon, lat)
    },
    applyChanges(){
      if (this.client.name === "" || this.client.name[0] === this.client.name[0].toLowerCase()){
        this.message = this.errorMessage;
        document.getElementById("emptyError").style.color = "red";
        document.getElementById("emptyError").style.visibility = "visible";
      }
      else if (this.client.surname === "" || this.client.surname[0] === this.client.surname[0].toLowerCase()){
        this.message = this.errorMessage;
        document.getElementById("emptyError").style.color = "red";
        document.getElementById("emptyError").style.visibility = "visible";
      }
      else if (this.client.phoneNumber === ""){
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
        this.backup[4] = this.client.longitude;
        this.backup[5] = this.client.latitude;
      }

    },
    cancel(){
      this.client.name = this.backup[0];
      this.client.surname = this.backup[1];
      this.client.phoneNumber = this.backup[2];
      this.client.password = this.backup[3];
      this.client.longitude = this.backup[4];
      this.client.latitude = this.backup[5];
      document.getElementById("emptyError").style.visibility = "hidden";
    },

  }
  ,mounted() {
    this.backup = [this.client.name, this.client.surname, this.client.phoneNumber, this.client.password,
      this.client.country, this.client.longitude, this.client.latitude];

  },
  data() {
    return {
      buttonId : "changePass",
      changePass : "changePassword",
      changePassHeader:"Promena lozinke",

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

      client: {
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
      },

      passwords:{
        old_password:"",
        new_password:""
      }
    }
  }
}
</script>

<style scoped>

</style>
