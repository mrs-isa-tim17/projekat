<template>
  <button id="client" type="button" @click="openForm"  class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target=modalId>
    Izveštaj o rezervaciji
  </button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
      <div class="modal-content" style="background-color:#687864; width:800px">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel" >{{header}} <b>{{reservation.name}}</b></h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" >
          <div class="row">
            <div class="col">
            <textarea  rows="5" cols="35" name="text" v-model="report" placeholder="Unesite komentar"></textarea>
              </div>
            <div class="col" style="text-align: left">
            <input  style="height:18px;width:18px;" type="checkbox" id="penallty"  v-model="checkedPenallty">
            <label for="penallty"> Predlažem da klijent dobije 1 penal</label><br><br>
              <input style="height:18px;width:18px;" type="checkbox" id="unarrived"  v-model="unarrivedClient">
              <label for="unarrived">Klijent nije došao</label>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Otkaži</button>
          <button type="button" class="btn btn-primary" >Pošalji izveštaj</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";

export default {
  name: "reservationReport",
  props:["index","header","reservation","client"],
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

</style>