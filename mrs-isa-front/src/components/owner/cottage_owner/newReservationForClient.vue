<template>
  <cottageOwnerHeader></cottageOwnerHeader>
  <div class="container p-3">
    <div class="pb-5 justify-content-center" style="text-align: center;font-weight: bold;font-size: 30px;">Kreiranje nove rezervacije na zahtev klijenta</div>
    <div  style="border:1px solid #31708E;background-color:#31708E;color:white; width: 1000px; display: inline-block;">
      <div class="row" >
        <div class="col-1"></div>
        <div class="col-5">
          <br>
          <br>
          <label >Klijenti koji su trenutno u Vašim vikendicama</label>
          <Multiselect style="width:400px;margin-top:7px;color:#31708E;"
                       v-model="value"
                       :options="clientEmails" placeholder="Odaberite klijenta" @select="selectedClient"/>
          <br><br><br>
         </div>
        <div class="col-1">
        </div>
        <div class="col-4" style="text-align: center">
          <div id="data" class="pt-5" style="margin-left:50pt;">
            <label id="clientName" ></label><br>
            <label id="offer"></label><br>
          </div>
        </div>
        <div class="col-1"></div>
      </div>
      <div class="row">
      <!-- <calendar-for-one-cottage :cottageId="cottageId" :index="calendarIndex" :header="calendarHeader"></calendar-for-one-cottage>-->
        <div class="col-3" style="margin-left: 40pt">

        <label><b>Početni datum</b></label><br>
          <Datepicker v-model="startDate" @change="enteredStartDate" style="max-width:300px;margin-left:55px;"></Datepicker>
        </div>
        <div class="col-3">
          <label><b>Krajnji datum</b></label><br>
          <Datepicker v-model="endDate" @change="enteredEndDate" style="max-width:300px;margin-left:55px;"></Datepicker>
        </div>
        <div class="col-5">
          <label><b>Dodatne usluge</b></label><br>
          <div class="" style="display: inline-block;" v-for="(s,i) in this.additionalServices" :key="i">
            <input type="checkbox" width="20" height="20"  @change="checkService(s)">{{s}} &nbsp;
          </div>
        </div>
      </div><br><br>
      <div class="row">
        <div class="col-5"></div>
        <div class="col-3">
          <button @click="createNewRes" style="width:200px;font-size:20px;border:none;color:#31708E;font-weight: bold; ">Kreiraj novu rezervaciju</button>
        </div>

      </div>
      <br>
      <br>
      <br>



</div>
  </div>
</template>

<script>
import cottageOwnerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
import ClientServce from "@/servieces/ClientServce";
import Multiselect from "@vueform/multiselect";
import PeriodAvailabilityUnavailabilityService from "@/servieces/PeriodAvailabilityUnavailabilityService";
import AdditionalServicesService from "@/servieces/AdditionalServicesService";
import reservationServce from "@/servieces/ReservationServce";
import swal from "sweetalert2";
import Datepicker from "@vuepic/vue-datepicker";
//import CalendarForOneCottage from "@/components/owner/cottage_owner/calendarForOneOffer";

//import swal from "sweetalert2";



export default {
  name: "newReservationForClient",
  components: {//CalendarForOneCottage,
    cottageOwnerHeader,Multiselect,Datepicker},
  created() {
    console.log(this.index);
    this.modalId = "#" + this.index;
    this.coID = JSON.parse(localStorage.user).id;
    ClientServce.getCurrentClients(this.coID).then((response)=>
    {
      this.currentClients = response.data;
      console.log(this.currentClients);
      for(let i=0;i<response.data.length;i++) {
        var emailCottage = response.data[i].clientEmail + " " + response.data[i].offerName;
        this.clientEmails.push(emailCottage);
      }
    })
    console.log(this.clientEmails);

    AdditionalServicesService.getAll().then((response)=> {
          this.additionalServices = response.data;
        }
    )


  },
  data() {
    return {
      currentClients:[],
      clientEmails: [],
      cottagesName: [],
      classes: [],
      value: null,
      price: null,
      date: null,
      duration: null,
      checked_services: [],
      endDate:"",
      startDate:"",
      icons: {
        "restoran": "fas fa-utensils text-black",
        "doručak": "fas fa-bread-slice text-black",
        "bar": "fas fa-glass-martini-alt text-black",
        "wifi": "fas fa-wifi text-black",
        "bazen": "fas fa-swimming-pool text-black",
        "spa": "fas fa-spa text-black",
        "teretana": "fas fa-dumbbell text-black",
      },
      allSystemServices: ["restoran", "doručak", "bar", "wifi", "bazen", "spa", "teretana"],
      selectedClientData:[],
      availabilityPeriod:[],
      saleAppointmentDTO: {
        id: "",
        offerId: "",
        startDateTime: "",
        longitude: "",
        latitude: "",
        duration: 0.0,
        peopleQuantity: "",
        additionalServices: [],
        price: "",
      },
      message: "",
      calendarHeader:"Kalendar zauzeca",
      calendarIndex:"calendarId",
      cottageId:"1",
      additionalServices:[],

    }
  },
  methods: {

    selectedClient(){
      console.log("selektovanoo");
      console.log(this.value);
      for(let i=0;i<this.currentClients.length;i++){

        console.log(this.value);

        if(this.currentClients[i].clientEmail + " " + this.currentClients[i].offerName === this.value){
          this.selectedClientData = this.currentClients[i];
        }
      }
      var clientNameLabel = document.getElementById("clientName");
      clientNameLabel.innerText = "Klijent: " + this.selectedClientData.clientName + " " + this.selectedClientData.clientSurname;
      var offerLabel = document.getElementById("offer");
      offerLabel.innerText = "Vikendica: " + this.selectedClientData.offerName;
      this.calendarHeader = "Kalendar zauzeća za vikendicu " + this.selectedClientData.offerName;
      console.log(this.cottageId);
      this.cottageId = this.selectedClientData.cottageId;
      console.log(this.cottageId);
    },
    createNewRes(){
      let resObj = {
        clientId: this.selectedClientData.clientId,
        offerId: this.cottageId,
        offerType: 'cottage',
        chosenAdditionalServices: this.checked_services,
        fromDate: this.startDate,
        untilDate: this.endDate,
        shipOwnerPresent: false
      };
      reservationServce.reserveEntity(resObj)
          .then((response) =>{
            console.log("RESERVE ");
            let res = response.data
            if (res.successfull){
              swal.fire({
                title: "Uspešsno",
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
          });
    },
    enteredStartDate(){
      console.log(this.startDate);
      PeriodAvailabilityUnavailabilityService.getAvailabilityPeriods(this.cottageId).then((response) => {
        this.availabilityPeriod = response.data;
        console.log(this.availabilityPeriod);
        console.log(response.data);
      //proveraaa
      });
    },
    enteredEndDate(){

    },
    checkService(s){
      console.log(s);
      if(this.checked_services.includes(s)){
        var i = this.checked_services.indexOf(s);
        console.log(i);
        console.log(this.checked_services[i]);
        this.checked_services.splice(i,1);
        console.log("odcekirano");
        console.log(s);
        console.log(this.checked_services);
      }
      else{
        this.checked_services.push(s);
      }
      console.log(this.checked_services);
    }
  }
}
</script>
<style src="@vueform/multiselect/themes/default.css">

button:hover{
  background-color:red;
}




</style>