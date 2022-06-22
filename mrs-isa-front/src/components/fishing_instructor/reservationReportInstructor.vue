<template>
  <button :id="buttonId" type="button" @click="openForm"  class="btn btn-secondary" data-bs-toggle="modal" :data-bs-target=modalId>
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
              <textarea  rows="5" cols="35" name="text" v-model="reservationReport.report" placeholder="Unesite komentar"></textarea>
              <notifications
                  group="foo-css"
                  position="bottom left"
                  :speed="500"
              />
            </div>
            <div class="col" style="text-align: left">
              <input  style="height:18px;width:18px;" type="checkbox" id="penallty"  v-model="reservationReport.suggestedPenallty">
              <label for="penallty"> Predlažem da klijent dobije 1 penal</label><br><br>
              <input style="height:18px;width:18px;" type="checkbox" id="unarrived"  v-model="reservationReport.unarrivedClient">
              <label for="unarrived">Klijent nije došao</label>
            </div>
          </div>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Otkaži</button>
          <button type="button" class="btn btn-primary" @click="sendReport">Pošalji izveštaj</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";


import ReservationReportService from "@/servieces/ReservationReportService";
import swal from "sweetalert2";
export default {
  name: "reservationReportInstructor",
  props:["index","header","reservation","client"],
  created(){
    console.log(this.index);
    this.buttonId = "button" + this.reservation.id;
    this.modalId = "#"+this.index;

    ReservationReportService.haveReservationReport(this.reservation.id).then((response)=>
    {
      console.log(this.reservation.id);
      if(response.data){
        console.log(response.data);
        var button = document.getElementById(this.buttonId);
        button.disabled = true;
        console.log(button.disabled);
      }
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
    sendReport(){
      console.log(this.reservation);
      ReservationReportService.saveReservationReport(this.reservationReport).then((response)=>
          {console.log(response.data);
            var button = document.getElementById(this.buttonId);
            button.disabled = true;
            swal.fire("Uspesno poslat izvestaj!")
          }
      );


      const modal = document.getElementById(this.index);
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';

    }

  },
  data(){
    return{
      reservationReport: {
        clientId:this.reservation.clientId,
        reservationId:this.reservation.id,
        report:"",
        suggestedPenallty:false,
        unarrivedClient:false,
        buttonId:""
      }
    }
  }
}
</script>

<style scoped>

</style>