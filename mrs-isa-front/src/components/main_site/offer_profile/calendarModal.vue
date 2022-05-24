<template>

  <!-- Button trigger modal -->
  <button @click="openModal" class="btn btn-outline-secondary" type="button" data-bs-toggle="modal" data-bs-target="#calendarModal">
    Detalji
  </button>

  <!-- Modal -->
  <div class="modal fade" id="calendarModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" style="max-width: 70%;">
      <div class="modal-content" >
        <div class="modal-header" style="background-color: #31708E;">
          <h5 class="modal-title text-white" id="exampleModalLabel">Slobodni termini</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" style="background-color: white; margin-left: 10px;margin-right: 10px;">

          <CalendarMrs id="calendar" style="width: 100%;" :key="calendarKey" :availability-period="availabilityPeriod" :unavailability-period="unavailabilityPeriod" :my-events="reservations"></CalendarMrs>  <!--  Ovde posalji u props events  -->

        </div>
        <div class="modal-footer d-flex justify-content-start" align="left">
          <label style="color: green;"> Zelenom </label> bojom su označeni slobodni periodi
        </div>
      </div>
    </div>
  </div>


</template>

<script>
import CalendarMrs from "@/components/calendar";
import $ from "jquery";
export default {
  name: "calendarModal",
  components: {CalendarMrs},
  props: ["availabilityPeriod"],
  methods:{
    openModal(){
      var modalToggle = document.getElementById("calendarModal");
      ///myModal.show(modalToggle)
      $('#exampleModal').show(modalToggle);
      $('#exampleModal').focus(modalToggle);
      setTimeout(this.rerenderCalendar, 200)
    },
    rerenderCalendar(){
      console.log(this.availabilityPeriod);
      this.calendarKey++;
    }
  },
  data(){
    return{
      unavailabilityPeriod: [],
      reservations: [],
      calendarKey: 0
    }
  }
}
</script>

<style scoped>

</style>
