<template>
  <!-- Button trigger modal -->
  <button @click="openModal" v-show="verifiedClient" type="button" style="min-width: 150px; margin-left: 0;" class="btn btn-secondary" data-bs-toggle="modal" data-bs-target="#reserveModal">
    Rezerviši
  </button>

  <!-- Modal -->
  <div class="modal fade" id="reserveModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content" style="background-color: #31708E;">
        <div class="modal-header text-white" >
          <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" style="background-color: white; margin-right: 10px; margin-left: 10px;">
          <div class="row" v-if="searchHappened">
            <div class="col">
              <p>Osnovne informacije</p>
              Od datuma: {{this.getFromDate()}}
              <br>
              Od vreme: {{this.getFromTime()}}
              <br>
              <br>
              Do datuma: {{this.getUntilDate()}}
              <br>
              Do vreme: {{this.getUntilTime()}}
              <br>
              <br>
              Cena: {{currentPrice}}
            </div>

            <div class="col">
              <label>Izaberi dodatne usluge</label>
              <div class="m-1" style="border-style: solid; border-width: 2px;">
                <div class="p-2">
                <div align="left" class="form-check" v-for="add in this.additionalServices" :key="add">
                  <input class="form-check-input" type="checkbox" :value=add id="flexCheckDefault"
                         v-model="additionalServicesModal" @click="checkBoxClick(add)">
                  <label class="form-check-label" :for=add>
                    {{ add }}
                  </label>
                </div>
              </div>
              </div>
              <input class="form-check-input" type="checkbox" v-show="shipOwnerCanBePresent" v-model="shipOwnerWanted">
              <label v-show="shipOwnerCanBePresent">Prisutnost vlasnika</label>
            </div>

          </div>
          <div v-else>
            <p class="text-danger"> Prvo morate da pretražujete da biste mogli da rezervišete </p>
          </div>
          <br>
          <p v-if="noReservationPossible" class="text-danger">Pre rezervacije morate da pretražite entitete da biste našli slobodne.</p>
        </div>

        <div class="modal-footer" style="background-color: #31708E;">
          <button @click="closeModal" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
          <button v-show="!noReservationPossible" @click.prevent="reserveEntity" type="button" class="btn btn-primary">Rezerviši</button>

        </div>
      </div>
      </div>
    </div>
</template>

<script>
import offerService from "@/servieces/OfferService";
import reservationServce from "@/servieces/ReservationServce";
import swal from "sweetalert2";
import shipOwnerService from "@/servieces/ship_owner/ShipOwnerService";

export default {
  name: "resMod",
  props: ["verifiedClient", "offerId", "additionalServices", "offerType", "price",
    "additionalServicesPrice"],
  created() {
    this.additionalServicesModal = [];//this.additionalServices;
    this.modalOfferType = this.offerType;

    if (this.modalOfferType === "cottage"){
      try {
        this.offerFilter = JSON.parse(localStorage.cottage);
        this.callOfferService();
        this.searchHappened = true;
      }catch {
        this.searchHappened = false;
      }
    }else if (this.modalOfferType === "ship"){
      try {
        this.offerFilter = JSON.parse(localStorage.ship);
        this.callOfferService();
        this.searchHappened = true;
        //check if vlasnik can be prisutan

        shipOwnerService.getIfFreeInPeriod(this.offerId, {fromDate: this.offerFilter.dateFrom, untilDate: this.offerFilter.dateUntil})
            .then((response) =>{
              console.log("SHIP OWNER");
              console.log(response.data);
              this.shipOwnerCanBePresent = response.data;
            })
      }catch {
        this.searchHappened = false;
      }
    }else if (this.modalOfferType === "adventure"){
      try {
        this.offerFilter = JSON.parse(localStorage.adventure);
        this.callOfferService();
        this.searchHappened = true;
      }catch {
        this.searchHappened = false;
      }
    }
  },
  methods: {
    reserveEntity(){
      let clientID = JSON.parse(localStorage.user).id;
      //additionalServicesModal
      let resObj = {
        clientId: clientID,
        offerId: this.offerId,
        offerType: this.offerType,
        chosenAdditionalServices: this.additionalServicesModal,
        fromDate: this.offerFilter.dateFrom,
        untilDate: this.offerFilter.dateUntil,
        shipOwnerPresent: this.shipOwnerWanted
      };
      reservationServce.reserveEntity(resObj)
          .then((response) =>{
            console.log("RESERVE ");
            let res = response.data
            if (res.successfull){
              swal.fire({
                title: "Uspešno",
                text: "Uspešno je sačvana rezervacija, mejl je poslatu na vaš mejl, za potvrdu.",
                background:'white',
                color:'black',
                confirmButtonColor:'green'});
            }else{
              swal.fire({
                title: "Neuspešno",
                text: res.text,
                background:'white',
                color:'black',
                confirmButtonColor:'#FECDA6'});
            }
            this.closeModal();
          });
    },
    callOfferService(){
      let obj = {
        fromDate: this.offerFilter.dateFrom,
        untilDate: this.offerFilter.dateUntil,
        price: this.price,
        type: this.modalOfferType
      }
      offerService.getPrice(obj)
          .then((response) =>{
            this.currentPrice = response.data;
          });
    },

    checkBoxClick(add){
      if (this.noReservationPossible)
        return;
      let index = 0;
      for (let a in this.additionalServices){
        if (this.additionalServices[a] == add){
          index = a;
        }
      }
      if (this.additionalServicesModal.includes(this.additionalServices[index])){
        this.currentPrice = this.currentPrice - this.additionalServicesPrice[index];
        let modalIndex = this.additionalServicesModal.indexOf(add);
        this.additionalServicesModal.splice(modalIndex, 1);
      }else{
        this.currentPrice = this.currentPrice + this.additionalServicesPrice[index];
        this.additionalServicesModal.push(add);
      }
      console.log("CHECKBOX CLICK");
      console.log(add);
    },
    getUntilDate() {
      if (this.offerFilter.dateUntil !== "" && this.offerFilter.dateUntil !== undefined && this.offerFilter.dateUntil !== null) {
        return this.formatDate(this.offerFilter.dateUntil);
      }else{
        this.noReservationPossible = true;
      }
    },
    getUntilTime() {
      if (this.offerFilter.dateUntil !== "" && this.offerFilter.dateUntil !== undefined && this.offerFilter.dateUntil !== null) {
        return this.formatTime(this.offerFilter.dateUntil);
      }else{
        this.noReservationPossible = true;
      }
    },
    getFromDate() {
      if (this.offerFilter.dateFrom !== "" && this.offerFilter.dateFrom !== undefined  && this.offerFilter.dateFrom !== null) {
        return this.formatDate(this.offerFilter.dateFrom);
      }else{
        this.noReservationPossible = true;
      }
    },
    getFromTime() {
      if (this.offerFilter.dateFrom !== "" && this.offerFilter.dateFrom !== undefined  && this.offerFilter.dateFrom !== null) {
        return this.formatTime(this.offerFilter.dateFrom);
      }else{
        this.noReservationPossible = true;
      }
    },
    formatDate(date){
      if (date === "" || date === undefined)
        return;
      let splitedDate = date.split("T");
      let splitedDatePart = splitedDate[0].split("-");
      //let splitedTimePart = splitedDate[1].split(":");
      return splitedDatePart[2] + ". " + splitedDatePart[1] + ". " + splitedDatePart[0] + ".";
    },
    formatTime(date){
      if (date === "" || date === undefined)
        return;
      let splitedDate = date.split("T");
      let splitedTimePart = splitedDate[1].split(":");
      return (parseInt(splitedTimePart[0]) + 2) + ":" + splitedTimePart[1];
    },
    closeModal(){

      const modal = document.getElementById(this.index);

      // change state like in hidden modal
      modal.classList.remove('show');
      modal.setAttribute('aria-hidden', 'true');
      modal.setAttribute('style', 'display: none');

      // get modal backdrop
      const modalBackdrops = document.getElementsByClassName('modal-backdrop');

      // remove opened modal backdrop
      document.body.removeChild(modalBackdrops[0]);
      document.body.style.overflow = 'auto';

    },
  },
  data(){
    return{
      additionalServicesModal: [],
      offerFilter: {
        dateFrom:"",
        dateUntil:""
      },
      currentPrice: "",
      searchHappened: false,
      noReservationPossible : false,
      shipOwnerCanBePresent: false,
      shipOwnerWanted: false
    }
  }
}//MODIFIKACIJA DA UDE OTPORAN DA NEMA PRETRAGA
</script>

<style scoped>

</style>
