<template>
  <div style="margin-left: 20px; background-color: #CDCDCD; padding-right: 270px; padding-left: 10px;padding-bottom: 10px; padding-top: 10px;">
    <ul class="nav flex-column" style="width: 240px;">
      <li class="nav-item pt-2" style="width: 140px;">
        <div class="row">
          <div class="col-8">

            Od datuma
          </div>
          <div class="col-4">
            <Datepicker v-model="dateFrom" style="width: 130px;"></Datepicker>
          </div>
        </div>
      </li>
      <li class="nav-item  pb-2" style="width: 140px;">
        <div class="row">
          <div class="col-8">
            Do datuma
          </div>
          <div class="col-4">
            <Datepicker v-model="dateUntil" style="width: 130px;"></Datepicker>
            </div>
        </div>
      </li>
      <li class="nav-item  py-2" style="display: inline; width: 250px;" >
        <div class="row">
          <div class="col-3">
            Ocena
          </div>
          <div class="col-3">
            <select id="ratingBy" @change="ratingRelOpChanged" class="form-select" style="width: 70px"  aria-label="Default select1">
              <option value="0" selected=""></option>
              <option value="1">==</option>
              <option value="2">&#60;=</option>
              <option value="3">&#60;</option>
              <option value="4">&#62;=</option>
              <option value="5">&#62;</option>
            </select>
          </div>
          <div class="col-4">
            <select @change="ratingChanged" id="rating" class="form-select" style="width: 120px" aria-label="Default select2">
              <option value="0" selected=""></option>
              <option value="1">1</option>
              <option value="2">2</option>
              <option value="3">3</option>
              <option value="4">4</option>
              <option value="5">5</option>
            </select>
          </div>
        </div>

      </li>
      <li class="nav-item  py-2" style="display: inline; width: 250px;" >
        <div class="row">
          <div class="col-3">
            Kapacitet
          </div>
          <div class="col-3">

            <select @change="capacityRelOpChanged" id="capacityRelOpId" class="form-select" style="width: 70px;"  aria-label="Default select1">
              <option value="0" selected=""></option>
              <option value="1">==</option>
              <option value="2">&#60;=</option>
              <option value="3">&#60;</option>
              <option value="4">&#62;=</option>
              <option value="5">&#62;</option>
            </select>
          </div>
          <div class="col-4">

            <input v-model="capacity" type="number" style="width: 120px;  height: 38px;">
          </div>
        </div>
      </li>
      <li class="nav-item py-2" style="display: inline; width: 250px;" >

        <div class="row">
          <div class="col-3">
            Cena
          </div>
          <div class="col-3">

            <select id="priceRelOpId" @change="priceRelOpChanged" class="form-select" style="width: 70px;"  aria-label="Default select1">
              <option value="0" selected=""></option>
              <option value="1">==</option>
              <option value="2">&#60;=</option>
              <option value="3">&#60;</option>
              <option value="4">&#62;=</option>
              <option value="5">&#62;</option>
            </select>
          </div>
          <div class="col-4">

            <input v-model="price" type="number" style="width: 120px;  height: 38px;">
          </div>
        </div>
      </li>
      <li class="nav-item py-2" style="display: inline; width: 120px;" >

        <div class="row">
          <div class="col-8">Adresa</div>
          <div class="col-2">
            <button style="width: 150px;" @click="resetLocation"> Poništi lokaciju </button> </div>
        </div>
        <open-maps :key="rerenderMapKey" @coordinate-changed="updateCoordinats" :lon="lon" :lat="lat" style="width: 250px;"></open-maps>
      </li>

      <li class="nav-item pt-3">
        <button @click="filterEntity" style="margin-left: 80px;">Pretraži</button>
      </li>
    </ul>
  </div>

  <div id="liveToastDiv" class="position-fixed bottom-0 end-0 p-3" style="z-index: 11">
    <div style="background-color: #F18F86;" id="liveToast" class="toast hide" role="alert" aria-live="assertive" aria-atomic="true">
      <div class="toast-header">
        <strong class="me-auto">Upozorenje</strong>
        <button type="button" @click.prevent="closeToast" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
      </div>
      <div class="toast-body text-black">
        {{ message }}
      </div>
    </div>
  </div>
</template>

<script>
import OpenMaps from "@/components/VueMaps";
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css';
export default {
  name: "siteAdventureSearchNav",
  components: {
    OpenMaps,
    Datepicker
  },
  methods: {
    updateCoordinats(lon, lat){
      this.lon = lon;
      this.lat = lat;
    },
    resetLocation(){
      this.lon = 0;
      this.lat = 0;
      this.rerenderMapKey++;
      console.log(this.lon, this.lat);
    },
    closeToast(){
      this.message = "";
      document.getElementById("liveToast").classList.remove("show");
      document.getElementById("liveToast").classList.add("hide");
    },
    showToast(){
      document.getElementById("liveToast").classList.remove("hide");
      document.getElementById("liveToast").classList.add("show");
    },
    priceRelOpChanged(){
      this.priceRelOp = this.getRelOp(document.getElementById("priceRelOpId").value);
    },
    capacityRelOpChanged(){
      this.capacityRelOp = this.getRelOp(document.getElementById("capacityRelOpId").value);
    },
    ratingRelOpChanged(){
      this.ratingRelOp = this.getRelOp(document.getElementById("ratingBy").value);
    },
    ratingChanged(){
      this.rating =document.getElementById("rating").value;
    },
    getRelOp(value){
      switch (value){
        case "0":
          return "";
        case "1":
          return  "==";
        case "2":
          return "<=";
        case "3":
          return "<";
        case "4":
          return ">=";
        case "5":
          return ">";
      }
    },
    addMessage(msg){
      if (this.message != "")
        return;
      this.message += msg;
    },
    checkDates(){
      if (this.dateFrom !== null && this.dateUntil === null){
        this.addMessage("Dat je početni datum, ali krajnji datum nije dat.");
      }
      if (this.dateFrom === null && this.dateUntil !== null){
        this.addMessage("Dat je krajnji datum, ali početni datum nije dat.");
      }
      if (this.dateFrom !== null && this.dateUntil !== null && this.dateFrom > this.dateUntil){
        this.addMessage("Početni datum je veći od krajnjeg datuma");
        this.dateFrom = null;
        this.dateUntil = null;
      }
    },
    checkRating(){
      if (this.rating !== "" && this.ratingRelOp === ""){
        this.addMessage("Niste dali relacioni operator za ocenu.");
      }
      if (this.rating === "" && this.ratingRelOp !== ""){
        this.addMessage("Krenuli ste da date opciju za ocenu, pritom niste završili.");
      }
    },
    checkCapacity(){
      if (this.capacity !== "" && this.capacityRelOp === ""){
        this.addMessage("Niste dali relacioni operator za kapacitet.");
      }
      if (this.capacity === "" && this.capacityRelOp !== ""){
        this.addMessage("Krenuli ste da date opciju za kapacitet, pritom niste završili.");
      }

    },
    checkPrice(){
      if (this.price !== "" && this.priceRelOp === ""){
        this.addMessage("Niste dali relacioni operator za cenu.");
      }
      if (this.price === "" && this.priceRelOp !== ""){
        this.addMessage("Krenuli ste da date opciju za cenu, pritom niste završili.");
      }

    },
    filterEntity(){
      this.checkDates();
      this.checkRating();
      this.checkCapacity();
      this.checkPrice();
      console.log(this.message);
      if (this.message !== "")
        this.showToast();
      let filterDto = {
        longitude: this.lon,
        latitude: this.lat,
        dateFrom: this.dateFrom,
        dateUntil: this.dateUntil,
        ratingRelOp: this.ratingRelOp,
        rating: this.rating,
        capacityRelOp: this.capacityRelOp,
        capacity: this.capacity,
        priceRelOp: this.priceRelOp,
        price: this.price
      }
      this.$emit('filter', filterDto);
      console.log(filterDto);
    }
  },
  data(){
    return{
      lon: 0,
      lat: 0,
      dateFrom: null,
      dateUntil: null,

      ratingRelOp: "",
      rating: "",

      capacityRelOp: "",
      capacity: "",

      priceRelOp: "",
      price: "",

      message: "",
      rerenderMapKey: 0
    }
  }
}
</script>

<style scoped>

</style>
