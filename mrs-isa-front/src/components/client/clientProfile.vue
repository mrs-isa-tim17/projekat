<template>
  <button id="client" type="button" @click="openForm"  class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target=modalId>
    Profil klijenta
  </button>
  <br>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
      <div class="modal-content" style="background-color:#687864;width:400px;">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" style="text-align: left;" >
          <b>Ime: </b>{{client.name}}<br>
          <b>Prezime: </b>{{client.surname}}<br>
          <b>Email: </b>{{client.email}}<br>
          <b>Broj telefona: </b>{{client.phoneNumber}}<br>
          <b>Kategorija: </b>{{client.userType}}<br>
          <b>Broj loyality poena: </b>{{client.loyaltyPoints}}<br>
          <b>Penali: </b>{{client.penaltyNumber}}<br>
        </div>
        <div class="modal-footer">

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import ClientServce from "@/servieces/ClientServce";


export default {
  name: "clientProfile",
  props:["index","header","clientPr"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#"+this.index;
        console.log(this.clientPr);

        ClientServce.getClient(this.clientPr).then((response)=>{
          this.client = response.data;
          console.log(this.client);
        });

      },
  methods: {
    openForm() {
      console.log("forma");
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    }
  },
  data(){
    return{
      modalId:"",
      client:{
        name:"",
        surname:"",
        email:"",
        password:"",
        phoneNumber:"",
        userType:"",
        loyaltyPoints:"",
        penaltyNumber:"",
        benefits:"",
        longitude:"",
        latitude:""
      },

    }
  }
}
</script>

<style scoped>
button{
  width:170px;
}
</style>