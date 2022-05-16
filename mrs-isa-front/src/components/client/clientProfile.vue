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
          <b>Ime: </b>{{clientPr.name}}<br>
          <b>Prezime: </b>{{clientPr.surname}}<br>
          <b>Email: </b>{{clientPr.email}}<br>
          <b>Broj telefona: </b>{{clientPr.phoneNumber}}<br>
          <b>Kategorija: </b>{{clientPr.userType}}<br>
          <b>Broj loyality poena: </b>{{clientPr.loyalityPoints}}<br>
          <b>Penali: </b>{{clientPr.penaltyNumber}}<br>
        </div>
        <div class="modal-footer">

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";


export default {
  name: "clientProfile",
  props:["index","header","clientPr"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#"+this.index;
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

    }
  }
}
</script>

<style scoped>
button{
  width:170px;
}
</style>