<template>
  <button style="border:none;background:none;" @click="openForm" class="m-lg-2" data-bs-toggle="modal" :data-bs-target=modalId><i style="font-size: 24px;" class="fa fa-edit"></i></button>

  <div class="modal fade" :id=index tabindex="-1" aria-labelledby="editNameModal" aria-hidden="true">
    <div class="modal-dialog">
      <div class="modal-content"  style="background-color:#31708E;color:whitesmoke;" >
        <div class="modal-header">
          <h5 class="modal-title" id="deleteCottageModal" >{{header}}</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body" >
          <calendar :key="calendarKey" :availability-period="this.availabilityPeriod"
                    :unavailability-period="this.unavailabilityPeriod" :my-events="this.reservations"></calendar>
          <div class="modal-footer" style="background-color:#31708E">
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<script>
import calendar from "@/components/calendar";
import CottageService from "@/servieces/cottage_owner/CottageService";
import PeriodAvailabilityUnavailabilityService from "@/servieces/PeriodAvailabilityUnavailabilityService";
import $ from "jquery";
export default {
  name: "calendarForOneCottage",
  components:{calendar},
  props:["cottageId","header","index"],
  data(){
    return{
      availabilityPeriod:[],
      unavailabilityPeriod:[],
      reservations:[],
      allCottages:[],
      calendarKey:0,
      modalId:""
    }},
    methods: {
      openForm() {
        console.log("forma");
        this.numberOffer = this.number;
        var modalToggle = document.getElementById(this.index);
        ///myModal.show(modalToggle)
        $('#' + this.index).show(modalToggle);
        $('#' + this.index).focus(modalToggle);
      },
      created() {
        console.log(this.index);
        this.modalId = "#" + this.index;
        PeriodAvailabilityUnavailabilityService.getAvailabilityPeriods(this.cottageId).then((response) => {
          this.availabilityPeriod = response.data;
          console.log(this.availabilityPeriod);
          console.log(response.data);
          this.calendarKey++;
        });
        PeriodAvailabilityUnavailabilityService.getUnavailabilityPeriods(this.cottageId).then((response) => {
          this.unavailabilityPeriod = response.data;
          console.log("nedostupno iz fish");
          console.log(this.unavailabilityPeriod);
          console.log(response.data);
          this.calendarKey--;

        })
        CottageService.getReservationPeriods(this.cottageId).then((response) => {
          this.reservations = response.data;
          console.log(this.reservations);
          this.calendarKey++;
        })


      }
    }
}
</script>

<style scoped>

</style>