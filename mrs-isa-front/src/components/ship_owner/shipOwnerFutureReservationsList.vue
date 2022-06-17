<template>
  <ownerHeader></ownerHeader>
  <div class="container mt-2" >
    <p style="font-size: 30px;font-weight: bold;">Nerealizovane rezervacije za brod {{this.name}}</p>
    <div v-if="!allReservations" style="color:red;">Nema trenutnih rezervacija</div> <!--??????????-->
    <div class="p-2" v-for="(r) in allReservations"  :key="r.id">
      <shipOwnerFutureReservationsElement :reservation="r"></shipOwnerFutureReservationsElement>
    </div>
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
      name:""
    }
  },
  created(){
    let offerId = this.$route.params.id;
    console.log(offerId);
    ReservationService.getFutureReservations(offerId)
        .then((response)=>{
          this.allReservations= response.data;
          this.name = this.allReservations[0].name;
          console.log(this.allReservations);
        })
  },

}
</script>

<style scoped>

</style>