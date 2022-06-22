<template>
  <div style="overflow-scrolling: auto">
    <client-header></client-header>

    <div class="alert alert-secondary  alert-dismissible fade show" role="alert" >
      Otkazivanje je moguće najkasnije 3 dana do početka rezervacije.
      <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
    </div>
    <div>
      <client-upcoming-reservation-list @rerender="forceRerender" :key="myKey" :offers="upcomingReservations"> </client-upcoming-reservation-list>
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
    },
    forceRerender(){
      this.getUpcomingReservations();
      //this.myKey++;
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
