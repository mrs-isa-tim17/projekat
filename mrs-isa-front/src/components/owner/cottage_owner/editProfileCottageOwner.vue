<template>
  <cottageOwnerHeader></cottageOwnerHeader>
  <div class="container">

    <div class="row mt-5">
      <div class="col-3">
      </div>
      <div class="col-3">
        <changePasswordModal
            :index="buttonId" :owner="updatedOwner" :header="changePassHeader" @input-new-password="changePassword"></changePasswordModal>
      </div>
      <div class="col-3">
        <deleteAccountModal :index="deleteAcc" :header="deleteHeader" :textModal="text"></deleteAccountModal>
      </div>
    </div>
    <div class="row mt-5">
      <div class="col-4  d-flex justify-content-center" style=" border-style: solid; border-width: medium; background-color: #CDCDCD;">
        <div>
          <b>Loyality program</b><br>
          <disabledInputField :label="numLoyaltyPointsLabel" :info="owner.loyaltyPoints"> </disabledInputField>
          <disabledInputField :label="userCategoryLabel" :info="owner.userType"> </disabledInputField>
          <label>Pogodnosti</label>
          <textarea disabled class="my-4 text-center" rows="5" cols="40" style="background-color: #BBC4CC;" name="Pogodnosti" :value="owner.benefits">
          </textarea>
        </div>
      </div>


      <div class="col-8 d-flex " style="border-style: solid; border-width: medium;
                                                background-color: #88BBD6;">
        <div class="row d-flex " style="">
          <div class="col-2"></div>
          <div class="col-4 d-flex ">
            <div>

              <div class="p-2">
                <label >Ime</label>
                <br>
                <input type="text" ref="input" v-model="updatedOwner.name" size="25" required>
                <br>
              </div>

              <div class="p-2">
                <label >Prezime</label>
                <br>
                <input type="text" ref="input" v-model="updatedOwner.surname" size="25" required>

              </div>


              <disabled-input-field :label="emailLabel" :info="owner.email"> </disabled-input-field>

              <div class="p-2">
                <label >Broj telefona</label>
                <br>
                <input type="text" ref="input" v-model="updatedOwner.phoneNumber" size="25" required>

              </div>
            </div>
          </div>
          <div class="col-1"></div>
          <div class="col-4 d-flex justify-content-right mt-3">
            <div>

              <open-maps :lon="updatedOwner.longitude" :lat="updatedOwner.latitude" @coordinate-changed="updateCoordinats" style="width: 300px; height: 300px; margin-left:30px;"></open-maps></div>
          </div>

          <div class="row mt-3 mb-2">
            <div class="col-3"></div>
            <div class="col-2"></div>

            <button type="button" class="btn" @click="cancel" style="background-color:#3399FF; max-width: 100pt;margin-right: 20pt;">Odustani</button>
            <button type="button" class="btn" @click="updateProfile" style="background-color:#3399FF;max-width: 100pt;">Promeni</button>

          </div>
          <br>
          <br>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import changePasswordModal from "@/components/changePasswordModal";
import cottageOwnerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
import OpenMaps from "@/components/VueMaps";
import disabledInputField from "@/components/disabledInputField";
import deleteAccountModal from "@/components/deleteAccountModal"
import CottageOwnerService from "@/servieces/cottage_owner/CottageOwnerService";
import PasswordService from "@/servieces/PasswordService";
import swal from "sweetalert2";
export default {
  name: "edit-profile-owner",
  components:{changePasswordModal,
    deleteAccountModal, disabledInputField,
    cottageOwnerHeader, OpenMaps},
  created:
      function () {
        this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
        console.log(this.coID);
        CottageOwnerService.getOwner(this.coID)
            .then((response) => {
              console.log(response);
              this.owner = response.data;
              this.updatedOwner = response.data;
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
      },
  mounted() {
    this.updatedOwner.name = this.owner.name;
    this.updatedOwner.surname = this.owner.surname;
    this.updatedOwner.phoneNumber = this.owner.phoneNumber;
    this.updatedOwner.password = this.owner.password;
    this.updatedOwner.longitude = this.owner.longitude;
    this.updatedOwner.latitude = this.owner.latitude;
  },
  data(){
    return{
      showDialog:false,
      buttonId : "changePass",
      deleteAcc: "deleteAcc",
      deleteHeader:"Zahtev za brisanje naloga",
      changePassHeader:"Promena lozinke",
      text:"Razlog za brisanje naloga:",
      numLoyaltyPointsLabel:"Broj poena",
      userCategoryLabel:"Tip korisnika",
      emailLabel:"Email",
      updatedOwner:{
        name: "",
        surname: "",
        email: "",
        password: "",
        phoneNumber: "",
        userType: "",
        loyaltyPoints: "",
        penaltyNumber: "",
        benefits: "",
        longitude: 0,
        latitude: 0
      },
      owner:{
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
        latitude: "",
        price: "",
        images:[]
      },
      passwords:{
        old_password:"",
        new_password:""
      }
    }
  },
  methods: {
    updateCoordinats(lon, lat){
      this.owner.longitude = lon;
      this.owner.latitude = lat;
      console.log(lon, lat)
    },
    updateProfile(){
      CottageOwnerService.updateOwner(this.coID,this.updatedOwner).then((response)=>
      {
        if(response.data.name === this.updatedOwner.name && response.data.surname === this.updatedOwner.surname && response.data.phoneNumber === this.updatedOwner.phoneNumber){
          swal.fire("Uspešno izmenjeni podaci!");
        }
        else{
          swal.fire("Podaci nisu uspešno izmenjeni!");
        }
      })
    },
    cancel(){
      console.log(this.owner.name);
      this.updatedOwner.name = this.owner.name;
      this.updatedOwner.surname = this.owner.surname;
      this.updatedOwner.phoneNumber = this.owner.phoneNumber;
      this.updatedOwner.password = this.owner.password;
      this.updatedOwner.longitude = this.owner.longitude;
      this.updatedOwner.latitude = this.owner.latitude;
      this.$router.push('/shipOwner/home');
    },
    changePassword(old_password, new_password){
      this.coID = JSON.parse(localStorage.user).id;
      this.passwords.old_password=old_password;
      this.passwords.new_password = new_password;
      PasswordService.matchPassword(this.passwords,this.coID)
          .then((response)=>{
                if(response.data){
                  swal.fire({title:'Uspešno izmenjena lozinka! Molimo da se prijavite ponovo.',background:'white',color:'#687864',confirmButtonColor:'#687864', timer:2000});
                  this.$router.push('/book/site/login');
                }
                else{
                  swal.fire({title:'Neuspešne izmene!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
                }
              }
          )
      console.log(new_password);
    }
  }
}
</script>

<style scoped>


body{
  background-color:#00CED1;
}


</style>
