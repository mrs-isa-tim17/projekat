<template>
  <cottageOwnerHeader></cottageOwnerHeader>
  <div class="container p-3">
    <div style="display: inline-block" v-for="(c,i) in allCottages" :key="i" data-inline="true">
      <button @click="showCalendar(i)">{{c.name}}</button>&nbsp;
    </div><br><br>
    <div class="row">
      <div class="col-3"></div>
      <div class="col-3">
       <define-period :offerId="cottageId" :buttonName="buttonNameAvailability" :header="headerAvailability" :index="indexAvailability" @define-period="defineAvailabilityPeriod"></define-period>
      </div>
      <div class="col-3">
        <define-period :offerId="cottageId" :buttonName="buttonNameUnavailability" :header="headerUnavailability" :index="indexUnavailability" @define-period="defineUnavailabilityPeriod"></define-period>
    </div>
    </div>
  <calendar :key="calendarKey" :availability-period="this.availabilityPeriod"
            :unavailability-period="this.unavailabilityPeriod" :reservations="this.reservations" :actions="this.actions"></calendar>

  </div>
</template>

<script>
import cottageOwnerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
import calendar from "@/components/calendar";
import CottageService from "@/servieces/cottage_owner/CottageService";
import PeriodAvailabilityUnavailabilityService from "@/servieces/PeriodAvailabilityUnavailabilityService";
import DefinePeriod from "@/components/owner/cottage_owner/definePeriod";
import swal from "sweetalert2";
import ShipServce from "@/servieces/ShipServce";
export default {
  name: "calendarCottageOwner",
  components:{DefinePeriod, calendar,cottageOwnerHeader},
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
      calendarKey:0,
      actions:[],
      buttonNameAvailability:"Definiši period dostupnosti",
      headerAvailability:"Unesite početni i krajnji datum za period dostupnosti",
      indexAvailability:"availabilityId",
      buttonNameUnavailability:"Definiši period nedostupnosti",
      headerUnavailability:"Unesite početni i krajnji datum za period nedostupnosti",
      indexUnavailability:"unavailabilityId",
      unavailDateAns: false,
      availDateAns: false,
      periodData:{
        start:"",
        end:"",
        offerType:"cottage"
      }
    }
  },
  methods:{
    showCalendar(i){
      console.log(i);
      console.log(this.allCottages[i]);
      this.cottageId=this.allCottages[i].id;
      console.log(this.cottageId);
      console.log(this.cottageId);
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
    },

    showCalendarAfterDefine(cottageId){

      PeriodAvailabilityUnavailabilityService.getAvailabilityPeriods(cottageId).then((response) => {
        this.availabilityPeriod = response.data;
        console.log(this.availabilityPeriod);
        console.log(response.data);
        this.calendarKey++;
      });
      PeriodAvailabilityUnavailabilityService.getUnavailabilityPeriods(cottageId).then((response) => {
        this.unavailabilityPeriod = response.data;
        console.log("nedostupno iz fish");
        console.log(this.unavailabilityPeriod);
        console.log(response.data);
        this.calendarKey++;

      })
      ShipServce.getReservationsForShip(cottageId).then((response) => {
        this.reservations = response.data;
        console.log(this.reservations);
        this.calendarKey++;
      })
    },
    defineUnavailabilityPeriod(periodDate){
      this.periodData.start = periodDate.start;
      this.periodData.end = periodDate.end;
      PeriodAvailabilityUnavailabilityService.defineUnavailability(this.cottageId, this.periodData).then((response) => {
        this.unavailDateAns = response.data;


        if (this.unavailDateAns === true) {
          swal.fire({title:'Uspešno ste dodali period nedostupnosti!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          this.calendarKey--;
          this.showCalendarAfterDefine(this.cottageId);
        } else {
          swal.fire({title:'Nije moguće dodati uneti period nedostupnosti!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }


      }).catch(function (error) {
        console.log(error.toJSON());
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
        } else if (error.request) {
          // The request was made but no response was received
          // error.request is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          console.log(error.request);
        } else {
          // Something happened in setting up the request that triggered an Error
          console.log('Error', error.message);
        }
        console.log(error.config);
      });
    },

    defineAvailabilityPeriod(periodDate){
      this.periodData.start = periodDate.start;
      this.periodData.end = periodDate.end;
      PeriodAvailabilityUnavailabilityService.defineAvailability(this.cottageId, this.periodData).then((response) => {

        this.availDateAns = response.data;
        console.log(response.data);

        console.log(this.availDateAns)

        if (this.availDateAns === true) {
          this.showCalendarAfterDefine(this.cottageId);
          swal.fire({title:'Uspešno ste dodali period dostupnosti!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
          this.calendarKey++;
        } else {
          swal.fire({title:'Nije moguće dodati uneti period dostupnosti!',background:'white',color:'#687864',confirmButtonColor:'#687864'});
        }

      }).catch(function (error) {
        console.log(error.toJSON());
        if (error.response) {
          // The request was made and the server responded with a status code
          // that falls out of the range of 2xx
          console.log(error.response.data);
          console.log(error.response.status);
          console.log(error.response.headers);
        } else if (error.request) {
          // The request was made but no response was received
          // error.request is an instance of XMLHttpRequest in the browser and an instance of
          // http.ClientRequest in node.js
          console.log(error.request);
        } else {
          // Something happened in setting up the request that triggered an Error
          console.log('Error', error.message);
        }
        console.log(error.config);
      });
    }
    }
  }

</script>

<style scoped>

</style>