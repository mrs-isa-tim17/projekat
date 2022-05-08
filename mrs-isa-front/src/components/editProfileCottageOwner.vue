<template>
  <div class="container">
  <cottageOwnerHeader></cottageOwnerHeader>
    <div class="alert alert-success alert-dismissible fade show" id="successChangePass" role="alert" style="visibility: hidden;">
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      Lozinka uspesno izmenjena!
    </div>
    <div class="alert alert-danger alert-dismissible fade show" id="notSuccessChangePass" role="alert" style="visibility: hidden;">
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
      Lozinka nije uspesno izmenjena!
    </div>
    <div class="row">
      <div class="col-4  d-flex justify-content-center" style=" border-style: solid; border-width: medium; background-color: #CDCDCD;">
        <div>
          <disabledInputField :label="numLoyaltyPointsLabel" :info="owner.loyaltyPoints"> </disabledInputField>
          <disabledInputField :label="userCategoryLabel" :info="owner.userType"> </disabledInputField>
          <label>Pogodnosti</label>
          <textarea disabled class="my-4 text-center" rows="5" cols="40" style="background-color: #BBC4CC;" name="Pogodnosti" :value="owner.benefits">
          </textarea>
        </div>
      </div>


      <div class="col-8 d-flex justify-content-center" style="border-style: solid; border-width: medium;
                                                background-color: #88BBD6;">
        <div class="row d-flex justify-content-center" style="">
          <div class="col-4 d-flex justify-content-center">
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
                <deleteAccountModal :index="deleteAcc" :header="deleteHeader" :textModal="text"></deleteAccountModal>
            </div>
          </div>
        </div>

          <div class="col-4 d-flex justify-content-right">
            <div>

              <open-maps :lon="owner.longitude" :lat="owner.latitude" @coordinate-changed="updateCoordinats" style="width: 300px; height: 300px; margin-left:30px;"></open-maps>
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
import cottageOwnerHeader from "@/components/cottageOwnerHeader";
import OpenMaps from "@/components/VueMaps";
import disabledInputField from "@/components/disabledInputField";
import deleteAccountModal from "@/components/deleteAccountModal"
import CottageOwnerService from "@/servieces/CottageOwnerService";
import PasswordService from "@/servieces/PasswordService";
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
        latitude: ""
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
      CottageOwnerService.updateOwner(this.coID,this.owner).then("Success");
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
      console.log("menjam lozinku");
      this.coID = JSON.parse(localStorage.user).id;
      this.passwords.old_password=old_password;
      this.passwords.new_password = new_password;
      PasswordService.matchPassword(this.passwords,this.coID)
          .then((response)=>{
                if(response.data){
                  document.getElementById("successChangePass").style.visibility = 'visible';
                }
                else{
                  document.getElementById("notSuccessChangePass").style.visibility = 'visible';
                }
              }
          )
      console.log(new_password);
    }
  }
}
</script>

<style scoped>
.editProfile{
  text-align:center;
  border: 2px solid black;
  background-color:#E8E8E8;
  font-size: 20px;
  padding: 20px;
  width: 50%;
  height:75%;
  position: absolute;
  left: 65%;
  top: 55%;
  transform: translate(-50%, -50%);
  color: #5F9F9F;

}

body{
  background-color:#00CED1;
}

#box{
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);

}

#editButton{
  font-size:25px;
  color: white;
  background-color:#5F9F9F;
  float:right;
}
#cancelButton{
  font-size:25px;
  color: white;
  background-color:red;
  float:left;
}
#buttonSubmit{
  width:100%;
  padding: 30px;
}
.editProfile form .formdetails{
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin: 20px 0 12px 0;
}

form .formdetails .input-box{
  margin-bottom: 15px;
  width: calc(100% / 2 - 20px);
}
form .input-box span .details{
  display: block;
  font-weight: 450;
  margin-bottom: 10px;
}
.formdetails .input-box input{
  height: 45px;
  width: 100%;
  outline: none;
  font-size: 16px;
  border-radius: 5px;
  padding-left: 15px;
  border: 1px solid #ccc;
  border-bottom-width: 2px;
  /* transition: all 0.3s ease;*/
}
.formdetails .input-box input:focus,
.formdetails .input-box input:valid{
  border-color: #9b59b6;
}

.input-box{
  margin-left:20px;
}
form .details .input-box{
  margin-bottom: 15px;
  width: 100%;
}

.details{
  float: left;
  font-size: 20px;
}

.header{
  text-align:center;
  font-size: 35px;
  color: white;
  font-weight: bold;
}
#profile{
  margin-left:200px;
}

#buttonSubmit{
  margin: 0;
  position: absolute;
  top: 90%;
  left: 50%;
  -ms-transform: translate(-50%, -50%);
  transform: translate(-50%, -50%);
}


.loyality{
  text-align:center;
  border: 2px solid black;
  background-color:#687864;
  font-size: 20px;
  padding: 20px;
  width: 25%;
  height:75%;
  position: absolute;
  left: 28%;
  top: 55%;
  transform: translate(-50%, -50%);
  color: white;
}

.loyality .input-box input{
  height: 40px;
  width: 100%;
  outline: none;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
  border-bottom-width: 2px;
  /* transition: all 0.3s ease;*/
}

.loyalityDetails{
  float:left;

}

.headerLoyality{
  margin-bottom: 20px;
  font-weight: bold;
  font-size:28px;
}

.loyality .input-box{
  margin-bottom: 15px;
  width: calc(100% / 2);
  font-size:20px;
}

#benefits{
  border:1px solid white;

}


</style>