<template>

  <!-- Button trigger modal -->
  <button @click="openModal" class="btn btn-outline-secondary" type="button" data-bs-toggle="modal" data-bs-target="#calendarModal">
    Detalji
  </button>

  <!-- Modal -->
  <div class="modal fade" id="calendarModal" tabindex="-1" aria-labelledby="calendarModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-scrollable" style="min-width: 70%;">
      <div class="modal-content" >
        <div class="modal-header" style="background-color: #31708E;">
          <h5 class="modal-title text-white" id="calendarModalLabel">Slobodni termini</h5>
          <button data-bs-dismiss="modal" type="button" class="btn-close"></button>
        </div>
        <div class="modal-body" style="background-color: white; margin-left: 10px;margin-right: 10px;">

          <CalendarMrs :reservations="reservations" id="calendar" style="width: 100%;" :key="calendarKey" :availability-period="availabilityPeriod" :unavailability-period="unavailabilityPeriod" :actions="reservations"></CalendarMrs>  <!--  Ovde posalji u props events  -->

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
//import $ from "jquery";
export default {
  name: "calendarModal",
  components: {CalendarMrs},
  props: ["availabilityPeriod"],
  methods:{
    openModal(){
      //var modalToggle = document.getElementById("calendarModal");
      //$('#calendarModal').show(modalToggle);
      //$('#calendarModal').focus(modalToggle);
      const modal = document.getElementById("calendarModal");

      // change state like in hidden modal
      modal.classList.add('show');
      modal.classList.remove('hide');
      modal.setAttribute('aria-hidden', 'false');
      modal.setAttribute('style', 'display: inline');

      // get modal backdrop
      //const modalBackdrops = document.getElementsByClassName('modal-backdrop');

      // remove opened modal backdrop
      //document.body.appendChild(modalBackdrops[0]);
      //document.body.style.overflow = 'auto';
      setTimeout(this.rerenderCalendar, 400);
    },
    rerenderCalendar(){
      console.log(this.availabilityPeriod);
      this.calendarKey++;
    },
  closeModal(){

    const modal = document.getElementById("calendarModal");

    // change state like in hidden modal
    modal.classList.remove('show');
    modal.setAttribute('aria-hidden', 'true');
    modal.setAttribute('style', 'display: none');

    // get modal backdrop
    const modalBackdrops = document.getElementsByClassName('modal-backdrop');

    // remove opened modal backdrop
    document.body.removeChild(modalBackdrops[0]);
    document.body.style.overflow = 'auto';
    this.calendarKey++;
    }

  },
  data(){
    return{
      unavailabilityPeriod: [],
      reservations: [],
      calendarKey: 0,
      actions: []
    }
  }
}
</script>

<style scoped>

</style>
