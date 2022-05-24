<template>
  <div class="container" >
    <shipOwnerHeader></shipOwnerHeader>
    <div v-if="!allReservations" style="color:red;">Nema trenutnih rezervacija</div> <!--??????????-->
    <div class="p-2" v-for="(r) in allReservations"  :key="r.id">
      <shipOwnerPastReservationsElement :reservation="r"></shipOwnerPastReservationsElement>
    </div>
  </div>
</template>

<script>
import shipOwnerPastReservationsElement from "@/components/ship_owner/shipOwnerPastReservationsElement";
import shipOwnerHeader from "@/components/ship_owner/shipOwnerHeader";
import ReservationService from "@/servieces/ReservationService";
export default {
  name: "shipOwnerPastReservationsList",
  components:{shipOwnerPastReservationsElement,shipOwnerHeader},
  data(){
    return{
      allReservations:null
    }
  },
  mounted(){
    let offerId = this.$route.params.id;
    console.log(offerId);
    ReservationService.getPastReservations(offerId)
        .then((response)=>{
          this.allReservations= response.data;
          console.log(this.allReservations);
        })
  },
}
</script>

<style scoped>

</style>