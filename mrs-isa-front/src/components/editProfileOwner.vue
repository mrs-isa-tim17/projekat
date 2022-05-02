<template>
  <ownerHeader></ownerHeader>
  <div><div class="header">
   <deleteAccountModal :index="deleteAcc" :header="deleteHeader"></deleteAccountModal>
    <div id="profile">Moj profil</div>
  </div>
    <!--  Loyality  -->
    <div class="loyality">
      <div class="headerLoyality">Loyality program</div>
      <div class="input-box">
        <span class="loyalityDetails">Broj poena</span><br>
        <input type="text" id="name" v-model="owner.loyaltyPoints"/>
      </div>

      <div class="input-box">
        <span class="loyalityDetails">Kategorija</span><br>
        <input type="text" id="name"/>
      </div>
      <br>
      <div id="benefits">
        <p>Pogodnosti</p>
        <div ></div>
        <br>
        <br>
        <br>


      </div>
    </div>

    <!--  Edit profile  -->
    <div class="editProfile">

      <form action="">
        <div class="formdetails">
          <div class="input-box">
            <span class="details">Ime</span><br>
            <input type="text" id="name" v-model="owner.name"/>
          </div>
          <div class="input-box">
            <span class="details">Adresa</span><br>
            <input type="text" id="address" />
          </div>
          <div class="input-box">
            <span class="details">Prezime</span><br>
            <input type="text" id="surname" v-model="owner.surname" />
          </div>
          <div class="input-box">
            <span class="details">Grad</span><br>
            <input type="text" id="city" />
          </div>
          <div class="input-box">
            <span class="details">Email</span><br>
            <input type="text" id="email" v-model="owner.email"/>
          </div>
          <div class="input-box">
            <span class="details">Država</span><br>
            <input type="text" id="country" />
          </div>
          <div class="input-box">
            <span class="details">Lozinka</span><br>
            <changePasswordModal
             :index="buttonId" :header="changePassHeader"></changePasswordModal>

          </div>

          <div class="input-box">
            <span class="details">Broj telefona</span><br>
            <input type="text" id="telNumber" v-model="owner.phoneNumber"/>
          </div>
          <div id="buttonSubmit">
            <input type="submit" value="Potvrdi izmene" id="editButton">
            <input type="submit" value="Otkaži izmene" id="cancelButton">
          </div>

        </div>
      </form>
    </div>
  </div>
</template>

<script>
import changePasswordModal from "@/components/changePasswordModal";
import deleteAccountModal from "@/components/deleteAccountModal"
import CottageOwnerService from "@/servieces/CottageOwnerService";
import ownerHeader from "@/components/ownerHeader";
export default {
  name: "edit-profile-owner",
  components:{changePasswordModal, deleteAccountModal, ownerHeader},
  created:
      function () {
        this.coID = JSON.parse(localStorage.user).id;//this.$route.params.id;
        CottageOwnerService.getClient(this.coID)
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
        longitude: "",
        latitude: ""
      }
    }
  },
  methods: {

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
  font-size: 30px;
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