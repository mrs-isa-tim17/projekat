<template>
  <shipOwnerHeader></shipOwnerHeader>
  <div><div class="header">
    <deleteAccountModal :index="deleteAcc" :header="deleteHeader"></deleteAccountModal>
    <div id="profile">Moj profil</div>
  </div>
    <!--  Loyality  -->
    <div class="loyality">
      <div class="headerLoyality">Loyality program</div>
      <div class="input-box">
        <span class="loyalityDetails">Broj poena</span><br>
        <input type="text" id="loyalityPoints" v-model="owner.loyaltyPoints"/>
      </div>

      <div class="input-box">
        <span class="loyalityDetails">Kategorija</span><br>
        <input type="text" id="loyalityCategory" v-model="owner.userType"/>
      </div>
      <br>
      <div id="benefits">
        <p style="color:#5F9F9F;font-weight: bold;">Pogodnosti</p>
        {{owner.benefits}}
        <br>
        <br>
        <br>


      </div>
    </div>

    <!--  Edit profile  -->
    <div class="editProfile">

      <form action="">
        <div class="formdetails">
          <div id="left">
            <span class="details">Ime</span><br>
            <input type="text" id="name" v-model="owner.name"/><br>
            <span class="details">Prezime</span><br>
            <input type="text" id="surname" v-model="owner.surname"/><br>
            <span class="details">Email</span><br>
            <input type="text" id="email" v-model="owner.email"/><br>
            <span class="details">Broj telefona</span><br>
            <input type="text" id="telNumber" v-model="owner.phoneNumber"/><br>
            <br>
            <changePasswordModal
                :index="buttonId" :header="changePassHeader"></changePasswordModal>
            </div>
          <div id="right">
            <label>Adresa</label><br>
            <open-maps :lon="owner.longitude" :lat="owner.latitude" @coordinate-changed="updateCoordinats" style="width: 300px; height: 300px; margin-left:30px;"></open-maps>

          </div>

          <div id="buttonSubmit">
            <button type="button" class="btn" @click="cancel" style="background-color:#31708E;margin-right:50px;color:white;height:50px;width:150px;margin-bottom: 40px;">Odustani</button>
            <button type="button" class="btn" @click="updateProfile" style="background-color:#31708E;color:white;height:50px;width:150px;margin-bottom: 40px;">Potvrdi izmene</button>
          </div>

        </div>
      </form>
    </div>
  </div>
</template>

<script>
import changePasswordModal from "@/components/changePasswordModal";
import deleteAccountModal from "@/components/deleteAccountModal"
import ShipOwnerService from "@/servieces/ShipOwnerService";
import shipOwnerHeader from "@/components/shipOwnerHeader";
import OpenMaps from "@/components/VueMaps";
export default {
  name: "edit-profile-owner",
  components:{changePasswordModal, deleteAccountModal, shipOwnerHeader,OpenMaps},
  created:
      function () {
        this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
        console.log(this.coID);
        ShipOwnerService.getOwner(this.coID)
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
        longitude: 0,
        latitude: 0
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
      ShipOwnerService.updateOwner(this.coID,this.owner).then("Success");
      this.backup[0] = this.owner.name;
      this.backup[1] = this.owner.surname;
      this.backup[2] = this.owner.phoneNumber;
      this.backup[3] = this.owner.password;
      this.backup[4] = this.owner.longitude;
      this.backup[5] = this.owner.latitude;
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

  margin-bottom:50px;
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
#left{
  float:left;
}
#right{
  float:right;
  margin-right: 60px;
}


</style>