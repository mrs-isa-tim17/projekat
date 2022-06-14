<template>
  <!-- Button trigger modal -->
  <button @click="openModal" v-show="verifiedClient" style="min-width: 150px;" class="btn btn-secondary" type="button" data-bs-toggle="modal" data-bs-target="#exampleModal"> Brze rezervacije </button>

  <!-- Modal -->
  <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" style="max-width: 80%;">
      <div class="modal-content" style="background-color: #31708E;">
        <div class="modal-header">
          <h5 class="modal-title text-white" id="exampleModalLabel">Brze rezervacije</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">

          <quick-reservation-list @remount="forceRemount" :quick-reservations="quickReservations" :key="quickReservationsKey"></quick-reservation-list>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import saleAppointmentService from "@/servieces/SaleAppointmentService";
import QuickReservationList from "@/components/client/quickReservationList";
import $ from "jquery";
export default {
  name: "quickReservationModal",
  components: {QuickReservationList},
  //components: {QuickSaleElement},
  props: ["verifiedClient", "offerId"],
  mounted() {
    if (this.offerId !== "" && this.offerId !== undefined){
      saleAppointmentService.getSaleAppointmentsById(this.offerId)
          .then((response) =>{
            this.quickReservations = response.data;
            this.elementKey++;
            this.quickReservationsKey++;
            console.log(this.quickReservations);
          });
    }
  },
  methods: {
    forceRemount(){

      if (this.offerId !== "" && this.offerId !== undefined){
        saleAppointmentService.getSaleAppointmentsById(this.offerId)
            .then((response) =>{
              this.quickReservations = response.data;
              this.quickReservationsKey++;
            });
      }
    },
    openModal() {
      var modalToggle = document.getElementById("exampleModal");
      ///myModal.show(modalToggle)
      $('#exampleModal').show(modalToggle);
      $('#exampleModal').focus(modalToggle);
      setTimeout(this.rerenderList, 100);
    },
    rerenderList(){
      this.quickReservationsKey++;
    }
  },
  data(){
    return {
      quickReservations: [],
      quickReservationsKey: 0,
      elementKey: [0,1,2,3,4,5,6,7,8,9,10]
    }
  }
}
</script>

<style scoped>

</style>
