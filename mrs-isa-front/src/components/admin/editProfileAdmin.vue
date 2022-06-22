<template>
  <adminHeader></adminHeader>
  <div class="container m-2 d-flex justify-content-center ml-3" align="center">

    <div class="row ml-3">
      <div class="col-4  d-flex justify-content-center" style=" border-style: solid; border-width: medium; background-color: #CDCDCD;">
        <div>
          <disabledInputField :label="numLoyaltyPointsLabel" :info="owner.loyaltyPoints"> </disabledInputField>
          <disabledInputField :label="userCategoryLabel" :info="owner.userType"> </disabledInputField>
          <label>Pogodnosti</label>
          <textarea disabled class="my-4 text-center" rows="5" cols="40" style="background-color: #BBC4CC;" name="Pogodnosti" :value="owner.benefits">
          </textarea>
        </div>
      </div>


      <div class="col-7 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #88BBD6;">
        <div class="row d-flex justify-content-center" style="">
          <div class="col-6 d-flex justify-content-center">
            <div>

              <div class="p-2">
                <label >Ime</label>
                <br>
                <input type="text" ref="input" v-model="owner.name" size="25" required>
                <br>
                <p id="nameEmpty" v-if="this.owner.name == ''" style="color: red;"> Ime mora da postoji </p>
                <p v-if="this.owner.name[0] == this.owner.name[0].toLowerCase()" style="color: red;"> Ime mora da počinje velikim slovom </p>

              </div>

              <div class="p-2">
                <label >Prezime</label>
                <br>
                <input type="text" ref="input" v-model="owner.surname" size="25" required>
                <p v-if="this.owner.surname == ''" style="color: red;"> Prezime mora da postoji</p>
                <p  v-if="this.owner.surname[0] == this.owner.surname[0].toLowerCase()" style="color: red;"> Prezime mora da počinje velikim slovom </p>
              </div>


              <disabled-input-field :label="emailLabel" :info="owner.email"> </disabled-input-field>

              <div class="p-2">
                <label >Broj telefona</label>
                <br>
                <input type="text" ref="input" v-model="owner.phoneNumber" size="25" required>
                <p v-if="this.owner.phoneNumber == ''" style="color: red;"> Broj telefona mora da postoji</p>
              </div>

              <div class="p-2">
                <changePasswordModal
                    :index="buttonId" :header="changePassHeader" @input-new-password="changePassword"></changePasswordModal>
                <br><br>
              </div>
            </div>
          </div>

          <div class="col-6 d-flex justify-content-right">
            <div>
              <br>
              <open-maps :lon="owner.longitude" :lat="owner.latitude" @coordinate-changed="updateCoordinats" style="width: 250px; height: 300px; margin-left:30px;"></open-maps>
              <div style="margin-top: 20px;">
                <button type="button" class="btn" @click="cancel" style="background-color:#3399FF;">Odustani</button>
                <button type="button" class="btn" @click="updateProfile" style="background-color:#3399FF;">Promeni</button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import changePasswordModal from "@/components/changePasswordModal";
import OpenMaps from "@/components/VueMaps";
import disabledInputField from "@/components/disabledInputField";
import PasswordService from "@/servieces/PasswordService";
import swal from "sweetalert2";
import AdminService from "@/servieces/AdminService";
import adminHeader from "@/components/admin/adminHeader";
export default {
  name: "edit-profile-owner",
  components:{
    adminHeader, changePasswordModal,
    disabledInputField,
    OpenMaps},
  created:
      function () {
        this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
        console.log(this.coID);
        AdminService.getOwner(this.coID)
            .then((response) => {
              console.log(response);
              this.owner = response.data;
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
    this.backup = [this.owner.name, this.owner.surname, this.owner.phoneNumber, this.owner.password,
      this.owner.country, this.owner.longitude, this.owner.latitude];
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
      AdminService.updateOwner(this.coID,this.owner).then("Success");
      swal.fire({title:'Uspešno izmenjeno!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
      this.backup[0] = this.owner.name;
      this.backup[1] = this.owner.surname;
      this.backup[2] = this.owner.phoneNumber;
      this.backup[3] = this.owner.password;
      this.backup[4] = this.owner.longitude;
      this.backup[5] = this.owner.latitude;
    },
    cancel(){
      this.owner.name = this.backup[0];
      this.owner.surname = this.backup[1];
      this.owner.phoneNumber = this.backup[2];
      this.owner.password = this.backup[3];
      this.owner.longitude = this.backup[4];
      this.owner.latitude = this.backup[5];
    },
    changePassword(old_password, new_password){
      this.coID = JSON.parse(localStorage.user).id;
      this.passwords.old_password=old_password;
      this.passwords.new_password = new_password;
      PasswordService.matchPassword(this.passwords,this.coID)
          .then((response)=>{
                if(response.data){
                  swal.fire({title:'Uspešno izmenjeno!',background:'white',color:'#687864',confirmButtonColor:'#687864', timer:1500});
                  this.$router.push("/book/site/login");
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
