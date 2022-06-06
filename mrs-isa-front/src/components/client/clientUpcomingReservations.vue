<template>
  <div style="overflow-scrolling: auto">
    <client-header></client-header>

    <div>
      <client-upcoming-reservation-list :key="myKey" :offers="upcomingReservations"> </client-upcoming-reservation-list>
    </div>


  </div>
</template>

<script>
import ClientHeader from "@/components/client/clientHeader";
import ClientUpcomingReservationList from "@/components/client/clientUpcomingReservationList";
import reservationServce from "@/servieces/ReservationServce";
export default {
  name: "clientUpcomingReservations",
  components: {ClientUpcomingReservationList, ClientHeader},
  created: function() {
    this.getUpcomingReservations();
  },
  methods:{
    getUpcomingReservations(){
      reservationServce.getUpcomingReservationsForClient(JSON.parse(localStorage.user).id)
          .then(response =>{
            this.upcomingReservations = response.data;
            this.myKey++;
          })
    }
  },
  data(){
    return{
      upcomingReservations: [],
      myKey: 0
    }
  }
}
</script>

<style scoped>

</style>
