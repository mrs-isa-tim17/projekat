<template>
  <a class="nav-link " @click="openForm" style="color:white;font-size: 20px;" data-bs-toggle="modal" :data-bs-target=modalId>Nova rezervacija</a>
  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="editNameModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;" >
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" style="text-align: left;" >
        <label>Odaberite klijenta</label><br>
          <select v-for="(c,i) in this.currentClients" :key="i" @click="setCottage" id="cbClients">
            <option>{{c.clientEmail}}</option>
          </select>
          <p style="font-size:20px;" id="cottageName"></p>
          <p>Kalendar</p>
          <div class="modal-footer" style="background-color:#31708E">
            <button type="button"  class="btn btn-secondary" data-dismiss="modal">Otkaži</button>
            <button type="button" @click="submitImages" class="btn btn-primary">Dodaj</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

import $ from "jquery";
import ClientServce from "@/servieces/ClientServce";

export default {
  name: "newReservations",
  props: ["index", "header"],
  created:
      function () {
        console.log(this.index);
        this.modalId = "#" + this.index;
        this.coID = JSON.parse(localStorage.user).id;
        ClientServce.getCurrentClients(this.coID).then((response)=>
        {
          this.currentClients = response.data;
          console.log(this.currentClients);
          console.log(response.data);
        })

      },
  methods: {
    openForm() {
      console.log("forma");
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
    },
    setCottage(){
      var selectedIndex = document.getElementById('cbClients').selectedIndex;
      this.cottageName = this.currentClients[parseInt(selectedIndex)].offerName;
      console.log(this.currentClients[parseInt(selectedIndex)]);
      console.log(this.cottageName);
      var name = document.getElementById('cottageName');
      name.innerText = 'Vikendica: ' + this.cottageName;
    }
  },
  data(){
    return{
      currentClients:null,
      cottageName:""
    }
  }
}
</script>

<style scoped>

</style>