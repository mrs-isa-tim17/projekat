<template>
  <ownerHeader></ownerHeader>
  <div class="container mt-2" >
    <p style="font-size: 30px;font-weight: bold;">Nerealizovane rezervacije za brod {{this.name}}</p>
    <div class="p-2" v-for="(r) in allReservations"  :key="r.id">
      <shipOwnerFutureReservationsElement :reservation="r"></shipOwnerFutureReservationsElement>
    </div>
    <p  style="font-size: 30px;">{{this.message}}</p>
  </div>
</template>

<script>
import ownerHeader from "@/components/ship_owner/shipOwnerHeader"
import shipOwnerFutureReservationsElement from "@/components/ship_owner/shipOwnerFutureReservationsElement"
import ReservationService from "@/servieces/ReservationService";
export default {
  name: "shipOwnerFutureReservationsList",
  components:{ownerHeader,shipOwnerFutureReservationsElement},
  data(){
    return {
      allReservations: null,
      name:"",
      message:""
    }
  },
  created(){
    this.message="";
    let offerId = this.$route.params.id;
    console.log(offerId);
    ReservationService.getFutureReservations(offerId)
        .then((response)=>{
          this.allReservations= response.data;
          console.log(this.allReservations);
          if(this.allReservations.length == 0){
            this.message="Nema budućih rezervacija!";
          }else {
            this.name = this.allReservations[0].name;
            console.log(this.allReservations);
          }
        })

  },

}
</script>

<style scoped>

</style>