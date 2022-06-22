<template>
  <shipOwnerHeader></shipOwnerHeader>
  <div class="container p-3">

    <div class="pb-5" style="text-align: center;font-weight: bold;font-size: 30px;">Kreiranje akcije</div>
    <div style="border:1px solid #31708E;background-color:#31708E;color:white;">
      <div class="row">
        <div class="col-1"></div>
        <div class="col-4">
          <br>
          <br>
          <Multiselect style="width:300px;margin-top:7px;color:#31708E;"
                       v-model="value"
                       :options="shipsName" placeholder="Odaberite brod"
          />
          <label class="pt-4" style="font-size: 23px;text-align: left;">Datum početka rezervacije</label><br>
          <Datepicker v-model="date" style="max-width:300px;margin-left:55px;"></Datepicker>
          <label class="pt-4" style="font-size: 21px;text-align: left;">Trajanje rezervacije (u danima)</label><br>
          <input type="text" style="width:300px;height:40px;" v-model="duration">
        </div>
        <div class="col-1"></div>
        <div class="col-4" style="text-align: center">
          <br><br>
          <div style="border:2px solid white;">
            <label class="pb-3" style="font-size: 23px;text-align: left;">Dodatne usluge</label><br>
            <div class="" style="display: inline-block;font-size: 20px;color:white;"
                 v-for="(s,i) in this.allSystemServices" :key="i">
              <input type="checkbox" style="width:20px;height:20px;color:red" @change="checkService(s)">&nbsp;<i
                class=" mr-1 text-primary"
                :class="icons[s]"
                style="width:25px;height:25px;"
            ></i>&nbsp;{{ s }}&nbsp;&nbsp;&nbsp;
            </div>
          </div>
          <label class="pt-4" style="font-size: 23px;text-align: left;">Cena(din.)</label><br>
          <input type="number" min="0" step="100" style="width:300px;height:40px;" v-model="price">
        </div>
        <div class="col-1"></div>
      </div>
      <br><br>
      <div class="row">
        <div class="col-4"></div>
        <div class="col-3">
          <button @click="createQuickRes"
                  style="width:180px;font-size:23px;margin-left:20px;border:none;color:#31708E;font-weight: bold; --dp-border-color-hover:#31708E ">
            Kreiraj akciju
          </button>
        </div>
        <div class="col-4"></div>
      </div>
      <br>
      <br>
      <br>
    </div>


  </div>
</template>

<script>
import shipOwnerHeader from "@/components/ship_owner/shipOwnerHeader";
import Multiselect from '@vueform/multiselect'
import swal from "sweetalert2";
import SaleAppointmentService from "@/servieces/SaleAppointmentService";
import Datepicker from "@vuepic/vue-datepicker";
import ShipService from "@/servieces/ship_owner/ShipService";
import AdditionalServicesService from "@/servieces/AdditionalServicesService";


export default {
  name: "quickReservationShip",
  components: {shipOwnerHeader, Multiselect, Datepicker},
  created() {
    this.coID = JSON.parse(localStorage.user).id;
    console.log(this.coID);
    ShipService.getShipByOwner(this.coID)
        .then((response) => {
          this.allShips = response.data;
          for (let i = 0; i < response.data.length; i++)
            this.shipsName.push(response.data[i].name);
          console.log(this.shipsName);
        });

    AdditionalServicesService.getAll().then((response) => {
          this.allSystemServices = response.data;

        }
    );

  },
  data() {
    return {
      allShips: [],
      shipsName: [],
      classes: [],
      value: null,
      price: null,
      date: null,
      duration: null,
      checked_services: [],
      icons: {
        "restoran": "fas fa-utensils text-black",
        "doručak": "fas fa-bread-slice text-black",
        "bar": "fas fa-glass-martini-alt text-black",
        "wifi": "fas fa-wifi text-black",
        "bazen": "fas fa-swimming-pool text-black",
        "spa": "fas fa-spa text-black",
        "teretana": "fas fa-dumbbell text-black",
      },
      allSystemServices: [],
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
      message: {
        text:""
      }
    }
  },
  methods: {

    createQuickRes() {
      var ship = "";
      for (let i = 0; i < this.allShips.length; i++) {
        if (this.allShips[i].name === this.value) {
          ship = this.allShips[i];
          console.log(ship);
        }
      }
      this.saleAppointmentDTO.longitude = ship.longitude;
      this.saleAppointmentDTO.latitude = ship.latitude;
      this.saleAppointmentDTO.price = this.price;
      this.saleAppointmentDTO.peopleQuantity = ship.bedQuantity;
      this.saleAppointmentDTO.duration = this.duration;
      this.saleAppointmentDTO.offerId = ship.id;
      this.saleAppointmentDTO.startDateTime = this.date;
      this.saleAppointmentDTO.additionalServices = this.checked_services;
      console.log(this.saleAppointmentDTO);
      if (this.Validate()) {
        SaleAppointmentService.defineSaleAppointmentForShip(ship.id, this.saleAppointmentDTO).then((response) => {
          this.message = response.data;
          swal.fire({
            title: "Obaveštenje",
            text:this.message.text,
            background: 'white',
            color: '#31708E',
            confirmButtonColor: '#31708E'
          });
          this.value = null;
          this.date = null;
          this.duration = null;
          this.price = null;

        })
      }

    },
    checkService(s) {
      if (this.checked_services.includes(s)) {
        var i = this.checked_services.indexOf(s);
        console.log(i);
        console.log(this.checked_services[i]);
        this.checked_services.splice(i, 1);
        console.log(this.checked_services);
      } else {
        this.checked_services.push(s);
      }
    },
    Validate() {

      if(this.value === null){
        swal.fire("Niste odabrali brod!");
        return false;
      } else if (this.date === null) {
        swal.fire("Niste odabrali datum!");
        return false;
      } else if (this.duration === null) {
        swal.fire("Niste uneli trajanje akcije!");
        return false;
      } else if (this.price === null) {
        swal.fire("Niste uneli cenu akcije!");
        return false;
      } else {
        return true;
      }
    },


  }

}
</script>
<style src="@vueform/multiselect/themes/default.css">

button:hover {
  background-color: red;
}


</style>
