<template>
  <div class="container" >
    <ownerHeader></ownerHeader>
    <div v-if="!allReservations" style="color:red;">Nema trenutnih rezervacija</div> <!--??????????-->
    <div class="p-2" v-for="(r) in allReservations"  :key="r.id">
      <shipOwnerFutureReservationsElement :reservation="r"></shipOwnerFutureReservationsElement>
    </div>
  </div>
</template>

<script>
import ownerHeader from "@/components/shipOwnerHeader"
import shipOwnerFutureReservationsElement from "@/components/shipOwnerFutureReservationsElement"
import ReservationService from "@/servieces/ReservationService";
export default {
  name: "shipOwnerFutureReservationsList",
  components:{ownerHeader,shipOwnerFutureReservationsElement},
  data(){
    return {
      allReservations: null,
    }
  },
  mounted(){
    let offerId = this.$route.params.id;
    console.log(offerId);
    ReservationService.getFutureReservations(offerId)
        .then((response)=>{
          this.allReservations= response.data;
          console.log(this.allReservations);
        })
  },

}
</script>

<style scoped>

</style>