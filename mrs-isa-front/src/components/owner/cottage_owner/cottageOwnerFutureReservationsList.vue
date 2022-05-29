<template>
  <div class="container" >
    <ownerHeader></ownerHeader>
    <div v-if="!allReservations" style="color:red;">Nema trenutnih rezervacija</div> <!--??????????-->
    <div class="p-2" v-for="(r) in allReservations"  :key="r.id">
      <cottageOwnerFutureReservationsElement :reservation="r"></cottageOwnerFutureReservationsElement>
    </div>
  </div>
</template>

<script>
import cottageOwnerFutureReservationsElement from "@/components/owner/cottage_owner/cottageOwnerFutureReservationsElement";
import ownerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
import ReservationService from "@/servieces/ReservationService";


export default {
  name: "cottageOwnerFutureReservationsList",
  components:{cottageOwnerFutureReservationsElement,ownerHeader},
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