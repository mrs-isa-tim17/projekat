<template>

    <instructor-header></instructor-header>
    <div>
      <simpleReservation :reservations="this.reservations"></simpleReservation>
    </div>

</template>

<script>
import simpleReservation from "@/components/fishing_instructor/simpleReservation";
import AdventureService from "@/services/AdventureService";
import instructorHeader from "@/components/insrtuctorHeader";

export default {
  name: "reservationsForOwner",
  components:{
    simpleReservation,
    instructorHeader,
  },

  created() {
    let type = this.$route.params.type
    this.currentAdventureId = this.$route.params.type
    console.log("adventure id");
    console.log(type);
    AdventureService.getReservationsForAdventure(type).then((response) =>{
    this.reservations = response.data;
    console.log(this.reservation);
  });
  },
  data(){
    return{
      currentAdventureId:"",
      reservations: [],
      reservation:{
        id:"",
        clientId:"",
        clientName : "",
        clientSurname : "",
        startDate:"",
        endDate:"",
        quick:""
      }

    }
  }
}
</script>

<style scoped>

</style>