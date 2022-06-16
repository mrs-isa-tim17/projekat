<template>
  <instructor-header></instructor-header>
  <div>
    <simpleReservation :reservations="this.reservations"></simpleReservation>
  </div>

</template>

<script>
import simpleReservation from "@/components/fishing_instructor/simpleReservation";
import instructorHeader from "@/components/insrtuctorHeader";
import AdventureService from "@/services/AdventureService";

export default {
  name: "historyReservations",
  components: {
    simpleReservation,
    instructorHeader,
  },
  created: function () {
    this.coID = JSON.parse(localStorage.user).id;
    console.log("owner id");
    console.log(this.coID);
    AdventureService.getAllReservationsForOwner((this.coID)).then((response) => {
      this.reservations = response.data;
      console.log(this.reservation);
    });
  },
  data() {
    return {
      currentAdventureId: "",
      reservations: [],
      reservation: {
        id: "",
        clientId: "",
        clientName: "",
        clientSurname: "",
        startDate: "",
        endDate: "",
        quick: "",
        offerName: "",
      }

    }
  }
}
</script>

<style scoped>

</style>