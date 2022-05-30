<template>
  <div class="card mb-3 d-flex justify-content-center" align="center" style="max-width: 800px;">
    <div class="row g-0">
      <div class="col-md-12">
        <div class="card-body">
          <div class="row">
            <div class="col" align="left">
              <h5 class="card-title">{{nameLabel}} {{ user.name }} </h5>
              <h5 class="card-title">{{surnameLabel}} {{ user.surname }} </h5>
              <p>{{telephoneLabel}} {{ user.phoneNumber }} </p>
              <p> {{emailLabel}} {{ user.email }} </p>
            </div>

            <div class="col" align="left">
              <h6 class="card-text">{{roleLabel}} {{returnStringRole(user.role)}}</h6>
              <h6 class="card-text">{{typeLabel}} {{ returnStringUserType(user.userType) }}</h6>
              <h6 class="card-text">{{loyaltyPointsLabel}} {{ user.loyaltyPoints }}</h6>
              <h6 class="card-text">{{penaltiesLabel}} {{ user.penalties }}</h6>
              <div class="d-grid gap-2 d-md-flex justify-content-md-end" v-if="user.deleted===false">
                <button class="btn btn-danger  btn-sm  me-md-2 " @click="deleteUser"> Obriši</button>
              </div>
            </div>
          </div>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import UserService from "@/servieces/UserService";
import swal from "sweetalert2";

export default {
  name: "simpleUser",
  props: ['user'],

  methods:{
    returnStringUserType(type) {
      if (type === 'REGULAR')
        return 'Regular'
      else if (type === 'SILVER')
        return 'Silver'
      else
        return 'Gold';
    },

    returnStringRole(role) {
      if (role === 'ROLE_CLIENT')
        return 'Klijent'
      else if (role === 'ROLE_ADMIN')
        return 'Administrator'
      else if (role === 'ROLE_COTTAGE_OWNER')
        return 'Vlasnik vikendice'
      else if (role === 'ROLE_SHIP_OWNER')
        return 'Vlasnik broda'
      else
        return 'Instruktor pecanja'
    },

    fireAlertOn(eventText){
      swal.fire({
        title: "Obaveštenje",
        text: eventText,
        background:'white',
        color:'black',
        confirmButtonColor:'#FECDA6'});
    },

    deleteUser(){
      UserService.deleteUser(this.user.id).then((response)=>{
      this.answer = response.data;
      if(this.answer){
        this.fireAlertOn("Uspešno ste obrisali korisnika.");
        this.$emit('delete-user');

      }else{
        this.fireAlertOn("Brisanje korisnika nije uspelo.");

      }

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

    }

  },

  data() {
    return {
      nameLabel: "Ime: ",
      surnameLabel: "Prezime: ",
      telephoneLabel: "Broj telefona: ",
      emailLabel: "E-mail korisnika: ",
      roleLabel: "Uloga korisnika: ",
      typeLabel: "Tip korisnika: ",
      loyaltyPointsLabel: "Loyalty poeni: ",
      penaltiesLabel : "Penali korisnika: ",
      answer:false

    }
  }
}
</script>

<style scoped>

</style>