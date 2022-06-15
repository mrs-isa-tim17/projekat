<template>
  <div class="d-flex justify-content-center">

    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">Redni broj</th>
        <th scope="col">Klijent</th>
        <th scope="col">Email klijenta</th>
        <th scope="col">Ponuda</th>
        <th scope="col">Vlasnik</th>
        <th scope="col">Email vlasnika</th>
        <th scope="col">Period rezervacije</th>
        <th scope="col"></th>
        <th scope="col"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(reservationReport, index) in reports" :key="index">
        <th scope="row">{{index+1}}</th>
        <td>{{reservationReport.clientName}} {{reservationReport.clientSurname}}</td>
        <td> {{reservationReport.clientEmail}}</td>
        <td>{{getSerbianOfferType(reservationReport.offerName)}}</td>
        <td>{{reservationReport.ownerName}} {{reservationReport.ownerSurname}}</td>
        <td> {{reservationReport.ownerEmail}}</td>
        <td> od {{getDateFormat(reservationReport.startDate)}} <br> do {{getDateFormat(reservationReport.endDate)}}</td>

        <td> <reservationReportModal :index="generateReportId(reservationReport.id)" :header="this.modalHeader"
                              :report-id="reservationReport.id" :btnId="generateModalId(reservationReport.id)"
                              btn-text="Detalji" :report="reservationReport" @accept-report="AcceptReport" @reject-report="RejectReport"> </reservationReportModal> </td>
        <td><h6 :id="reservationReport.id"></h6></td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import reservationReportModal from "@/components/admin/reservationReportModal";
import ReservationReportService from "@/servieces/ReservationReportService";
import swal from "sweetalert2";

export default {
  name: "reservationReportTable",
  components:{reservationReportModal},
  props:['reports'],

  created: function () {
    console.log(this.reports);
  },

  methods:{
    generateModalId(id){
      console.log('report'+id);
      return 'report'+id;
    },
    generateReportId(id){
      console.log('rep'+id);
      return 'rep' + id;
    },

    getDateFormat(date) {
      let d = date[2] +"." +date[1]+"." + date[0]
      return d;
    },

    getSerbianOfferType(type){
      if (type==='ADVENTURE') {
        return 'avantura';
      }else if(type==='SHIP'){
        return 'brod';
      }else{
        return 'vikendica';
      }
    },

    fireAlertSuccess(){
      swal.fire({
        title: "Uspešno",
        text: this.answer.text,
        background: 'white',
        color: 'black',
        confirmButtonColor: '#8DF172'
      });
    },

    fireAlertUnSucc() {
      swal.fire({
        title: "Neuspešno",
        text: this.answer.text,
        background: 'white',
        color: 'black',
        confirmButtonColor: '#FECDA6'
      });
    },

    handleAnswer(){
      if(this.answer.successfull){
        this.fireAlertSuccess();
      }else{
        this.fireAlertUnSucc();
      }

    },

    AcceptReport(report){
      console.log(report.id);
        ReservationReportService.approveReservationReport(report).then((response)=>{
          this.answer = response.data;
          this.handleAnswer();
          document.getElementById(report.id).innerText = this.answer.text;
          document.getElementById("report"+report.id).style.visibility="hidden";

          console.log(report);
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

    RejectReport(report){
      console.log(report);
      console.log(this.report)
      ReservationReportService.rejectReservationReport(report).then((response)=>{
        this.answer = response.data;
        this.handleAnswer();
        document.getElementById(report.id).innerText = this.answer.text;
        document.getElementById('report'+report.id).style.visibility="hidden";

        console.log(report);
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
  },

  data(){
    return{
      modalHeader:"Izveštaj o rezervaciji",
      reservationReport: {
        id: "",
        clientName: "",
        clientSurname: "",
        clientEmail: "",
        penalty: "",
        reportText: "",
        ownerName: "",
        ownerSurname: "",
        ownerEmail: "",
        startDate: "",
        endDate: "",
        offerName:""
      },
      answer:{
        text:""
      },
    }

  }
}
</script>

<style scoped>

</style>