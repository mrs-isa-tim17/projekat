<template>

  <div class="row">
    <div class="col" style="min-width: 300px;">
      <label class="my-1" style="color: #31708E;"> Osnovne informacije: </label>
      <br>
      <label class="my-1">&nbsp;&nbsp;Date: {{getDateTimeFormat(saleAppointment.startDateTime)}}</label>
      <br>
      <label class="my-1">&nbsp;&nbsp;Duration: {{saleAppointment.duration}}</label>
      <br>
      <label class="my-1">&nbsp;&nbsp;People quantity: {{saleAppointment.peopleQuantity}}</label>
      <br>
      <label class="my-1">&nbsp;&nbsp;Cena: {{saleAppointment.price}}</label>
    </div>
    <div class="col">
      <label class="my-1" style="color: #31708E;"> Dodatne usluge: </label>
      <br>
      <div v-if="saleAppointment.additionalServices.length !== 0">
      <div v-for="(text, index) of saleAppointment.additionalServices" :key="index">
        <label  class="my-1">&nbsp;&nbsp;{{text}}</label>
      </div>
      </div>
      <div v-else>
        Nema dodatnih usluga
      </div>
    </div>

    <div class="col">
      <bird-vue-map :key="mapKey" style="width: 300px; height: 150px; margin-bottom: 5px; visibility: visible" :lon="saleAppointment.longitude" :lat="saleAppointment.latitude"></bird-vue-map>
    </div>
  </div>


  <div class="row">

    <div class="col d-flex justify-content-end">
      <button class="btn btn-secondary" @click="reserveSaleAppointment">Rezerviši</button>
    </div>
  </div>
</template>

<script>
import BirdVueMap from "@/components/main_site/birdVueMap";
import saleAppointmentService from "@/servieces/SaleAppointmentService";
import swal from "sweetalert2";

export default {
  name: "quickSaleElement",
  components: {BirdVueMap},
  props: ["saleAppointment"],
  mounted() {
    this.mapKey++;
  },
  methods:{
    getDateTimeFormat(date) {
      let d =  "";
      if (date !== null && date !== undefined && date !== "")
        d = date[2] +"." +date[1]+"." + date[0] + " " + date[3] + ":"+ date[4];
      return d;
    },
    forceRemount(){
      this.$emit('remount');
    },
    reserveSaleAppointment(){
      this.lodingStatus = true;
      setTimeout(() => (this.isLoading = false), 3000);
      let reserveObj = {
        saleAppointmentId: this.saleAppointment.id,
        clientId: JSON.parse(localStorage.user).id
      }
      saleAppointmentService.reserveSaleAppointment(reserveObj)
          .then((response)=>{
            let successful = response.data.successful;
            if (successful){
              swal.fire({
                title: "Uspešno",
                text: "Uspešno ste rezervisali, poslali smo mejl za potvrdu",
                background:'white',
                color:'black',
                confirmButtonColor: "green"});
              this.forceRemount();
            }else{
              swal.fire({
                title: "Upozerenje",
                text: response.data.explanation,
                background:'white',
                color:'black',
                confirmButtonColor:'#FECDA6'});
            }
          });
    }
  },
  data(){
    return {
      mapKey: 0,
      lodingStatus: false
    }
  }
}
</script>

<style scoped>

</style>
