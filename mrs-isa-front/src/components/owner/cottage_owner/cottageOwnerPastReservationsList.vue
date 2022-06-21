<template>
  <cottageOwnerHeader></cottageOwnerHeader>
  <div class="container" >

    <div v-if="!allReservations" style="color:red;">Nema trenutnih rezervacija</div>
    <div class="p-2" v-for="(r) in allReservations"  :key="r.id">
      <cottageOwnerPastReservationsElement :reservation="r"></cottageOwnerPastReservationsElement>
    </div>
    <p id="noPastC" style="font-size: 30px;">{{this.message}}</p>
  </div>
</template>

<script>
import ReservationService from "@/servieces/ReservationService";
import cottageOwnerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
import cottageOwnerPastReservationsElement from "@/components/owner/cottage_owner/cottageOwnerPastReservationsElement";
export default {
  name: "cottageOwnerPastReservationsList",
  components:{cottageOwnerHeader,cottageOwnerPastReservationsElement},
  data(){

    return {
      allReservations: null,
      empty: "",
      name:"",
      message:""
    }
  },
  created(){
    let offerId = this.$route.params.id;
    console.log(offerId);
    ReservationService.getPastReservations(offerId)
        .then((response)=>{
          this.allReservations= response.data;
          if(this.allReservations.length == 0){
            this.message="Nema prošlih rezervacija";
          }
          else {
            this.name = this.allReservations[0].name;
            console.log(this.allReservations);
          }

        })

  },
}
</script>

<style scoped>

</style>