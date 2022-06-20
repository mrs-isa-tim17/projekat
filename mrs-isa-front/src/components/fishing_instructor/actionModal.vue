<template>
  <button :id="btnId" type="button" @click="openForm" style="min-width: 137px" class="btn btn-primary me-md-2" data-bs-toggle="modal"
          :data-bs-target="modalId">
    {{ btnText }}
  </button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable modal-xl">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">{{ header }}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <div class="row p-2" align="left">
            <div class="col-3">
              <label>{{ this.labelStartDateTime }}</label>
            </div>
            <div class="col-9">
              <Datepicker v-model="saleAppointmentDTO.startDateTime"></Datepicker>
            </div>
          </div>
          <br>
          <div class="row p-2" align="left">
            <div class="col-3">
              <label> {{ labelPlace }}</label>
            </div>
            <div class="col-9">
              <openLayers :key="mapKey" :lon="adventure.longitude" :lat="adventure.latitude" @coordinate-changed="updateCoordinats"
                          style="width: 300px; height: 380px; visibility: visible"></openLayers>
            </div>
            <br>

            <div class="row p-2" align="left">
              <div class="col-3">
                <label>{{ labelDuration }}</label>
              </div>
              <div class="col-9">
                <input type="number" step="any" v-model="saleAppointmentDTO.duration"/>
              </div>
            </div>
            <br>
            <div class="row p-2" align="left">
              <div class="col-3">
                <label>{{ labelMaxPeople }}</label>
              </div>
              <div class="col-9">
                <input type="number" v-model="saleAppointmentDTO.peopleQuantity"/>
              </div>
            </div>

            <br>
            <div class="row p-2" align="left">
              <div class="col-3">
                <label>{{ labelAdditionsServices }}</label>
              </div>
              <div class="col-9">
                <div class="p-2">
                  <div align="left" class="form-check" v-for="add in this.listAddition" :key="add">
                    <input class="form-check-input" type="checkbox" :value=add id="flexCheckDefault"
                           v-model="saleAppointmentDTO.additionalServices">
                    <label class="form-check-label" :for=add>
                      {{ add }}
                    </label>
                  </div>
                </div>
              </div>
            </div>

            <br>
            <div class="row p-2" align="left">
              <div class="col-3">
                <label>{{ labelPrice }}</label>
              </div>
              <div class="col-9">
                <input type="number" step="any" v-model="saleAppointmentDTO.price">
              </div>
            </div>


          </div>


        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Odustani</button>
          <button type="button" class="btn btn-primary" @click="Define">Definiši</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import $ from "jquery";
import Datepicker from "@vuepic/vue-datepicker";
import openLayers from "@/components/VueMaps";
import SaleAppointmentService from "@/servieces/SaleAppointmentService";
import swal from "sweetalert2";

export default {
  name: "actionModal",
  props: ["index", "header", "adventure", "btnId", "btnText"],
  components: {
    Datepicker,
    openLayers,
  },

  created:
      function () {
        this.modalId = "#" + this.index;
        console.log(this.index);
        console.log(this.modalId);
        this.saleAppointmentDTO.additionalServices = this.adventure.additionalServices
        this.saleAppointmentDTO.offerId = this.adventure.id
        this.listAddition = this.adventure.additionalServices
        this.saleAppointmentDTO.latitude = this.adventure.latitude
        this.saleAppointmentDTO.longitude = this.adventure.longitude

        console.log("Milicaaa");
        console.log(this.listAddition);
        console.log(this.adventure);
        this.key = this.modalKey;
        this.key++;
      },

  methods: {
    openForm() {
      console.log("forma");
      //    $('#'+this.index).modal('show');
      var modalToggle = document.getElementById(this.index);
      ///myModal.show(modalToggle)
      $('#' + this.index).show(modalToggle);
      $('#' + this.index).focus(modalToggle);
      this.mapKey++;
    },

    fireAlertOn(eventText){
        swal.fire({
          title: "Obaveštenje",
          text: eventText,
          background: 'white',
          color: 'black',
          confirmButtonColor: '#D4F172'
        });
    },

    Define() {
      console.log(this.saleAppointmentDTO.startDateTime);
      if (this.Validate()) {
        SaleAppointmentService.defineSaleAppointmentForOffer(this.adventure.id, this.saleAppointmentDTO).then((response) => {
          this.returnMessage = response.data;
          this.fireAlertOn(this.returnMessage.text);
          console.log(this.returnMessage);

          if(this.returnMessage.successfull === true) {
            const modal = document.getElementById(this.index);
            modal.classList.remove('show');
            modal.setAttribute('aria-hidden', 'true');
            modal.setAttribute('style', 'display: none');
            const modalBackdrops = document.getElementsByClassName('modal-backdrop');
            // remove opened modal backdrop
            document.body.removeChild(modalBackdrops[0]);
            document.body.style.overflow = 'auto';

            this.$emit('rerender-def');
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
      } else {
        this.fireAlertOn("Unesite pravilno podatke");
      }

    },

    Validate() {
      let today = new Date();
      if (this.saleAppointmentDTO.offerId === "" || this.saleAppointmentDTO.price === "" ||
          this.saleAppointmentDTO.duration === "" || this.saleAppointmentDTO.peopleQuantity === ""
          || this.saleAppointmentDTO.startDateTime === "" || this.saleAppointmentDTO.startDateTime<today) {
        return false;
      } else if (this.isInt(this.saleAppointmentDTO.peopleQuantity) === false) {
        return false;
      } else if (this.isDouble(this.saleAppointmentDTO.price) === false) {
        return false;
      } else if (this.isDouble(this.saleAppointmentDTO.duration) === false) {
        return false;
      } else {
        return true;
      }
    },

    isInt(val) {
      return Number.isInteger(val)
    },

    isDouble(x) {
      if(typeof x == 'number' && !isNaN(x)){
        if (Number.isInteger(x)) {
          return true;
        }
        else {
          return true;
        }
      } else {
        return false;
      }
    },

    updateCoordinats(lon, lat) {
      this.saleAppointmentDTO.longitude = lon;
      this.saleAppointmentDTO.latitude = lat;
      console.log(lon, lat)
    }
  },

  data() {
    return {
      labelStartDateTime: "Datum i vreme početka rezervacije: ",
      labelPlace: "Mesto: ",
      labelDuration: "Trajanje (u satima): ",
      labelMaxPeople: "Maksimalan broj osoba: ",
      labelAdditionsServices: "Dodatne usluge: ",
      labelPrice: "Cena",

      saleAppointmentDTO: {
        id: "",
        offerId: "",
        startDateTime: "",
        longitude: "",
        latitude: "",
        duration: 0.0,
        peopleQuantity: "",
        additionalServices: [],
        price: ""
      },
      listAddition: [],
      returnMessage: {
        text:"",
        successfull:false
      },
      mapKey: 0,
      key:0,
    }
  }
}
</script>

<style scoped>

</style>