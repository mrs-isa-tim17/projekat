<template>
  <admin-header></admin-header>
  <div class="container">
    <reservation-report-table :reports="this.reports" :key="myKey"></reservation-report-table>

  </div>

</template>

<script>
import AdminHeader from "@/components/admin/adminHeader";
import ReservationReportService from "@/servieces/ReservationReportService";
import ReservationReportTable from "@/components/admin/reservationReportTable";

export default {
  name: "reservationReports",
  components:{ReservationReportTable, AdminHeader},

  mounted() {
    ReservationReportService.getUnprocessedReservationReports().then((response)=>{
      this.reports = response.data;
      console.log(this.reports);
      this.myKey++;

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
  data(){
    return{
      reports:[],
      reservationReport: {
        id:"",
        clientName:"",
        clientSurname:"",
        clientEmail:"",
        penalty:"",
        reportText:"",
        ownerName:"",
        ownerSurname:"",
        ownerEmail:"",
        startDate:"",
        endDate:"",
        offerName:""
      },
      myKey:0,
    }
  }
}
</script>

<style scoped>

</style>