<template>
  <cottageOwnerHeader></cottageOwnerHeader>
  <div class="container p-3">
    <div style="display: inline-block" v-for="(c,i) in allCottages" :key="i" data-inline="true">
      <button @click="showCalendar(i)">{{c.name}}</button>&nbsp;
    </div>

  <calendar :key="calendarKey" :availability-period="this.availabilityPeriod"
            :unavailability-period="this.unavailabilityPeriod" :my-events="this.reservations"></calendar>
  </div>
</template>

<script>
import cottageOwnerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
import calendar from "@/components/calendar";
import CottageService from "@/servieces/cottage_owner/CottageService";
import PeriodAvailabilityUnavailabilityService from "@/servieces/PeriodAvailabilityUnavailabilityService";
export default {
  name: "calendarCottageOwner",
  components:{calendar,cottageOwnerHeader},
  created() {
    this.coID = JSON.parse(localStorage.user).id;
    CottageService.getCottageByOwner(this.coID)
      .then((response) => {
          this.allCottages = response.data;
          console.log(this.allCottages);
        })
    this.cottageId = 3;
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


  },
  mounted(){
    console.log("pomereno");
    PeriodAvailabilityUnavailabilityService.getAvailabilityPeriods(this.cottageId).then((response) => {
      this.availabilityPeriod = response.data;
      console.log(this.availabilityPeriod);
      console.log(response.data);
    });
    PeriodAvailabilityUnavailabilityService.getUnavailabilityPeriods(this.cottageId).then((response) => {
      this.unavailabilityPeriod = response.data;
      console.log("nedostupno iz fish");
      console.log(this.unavailabilityPeriod);
      console.log(response.data);

    })
   CottageService.getReservationPeriods(this.cottageId).then((response) => {
      this.reservations = response.data;
      console.log(this.reservations);
    })
  },
  data(){
    return{
      availabilityPeriod:[],
      unavailabilityPeriod:[],
      reservations:[],
      allCottages:[],
      cottageId:"",
      calendarKey:0
    }
  },
  methods:{
    showCalendar(i){
      console.log(i);
      console.log(this.allCottages[i]);
      this.cottageId=this.allCottages[i].id;
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
        this.calendarKey++;

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