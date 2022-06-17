<template>
<div class="d-flex justify-content-center mw-90" style=" align-content: center">
      <div class="row p-3 " style="background-color: #31708E;color:#F7F9FB;width: 60%">
        <div class="col-5" style="text-align: left;">

                  <b>Početak rezervacije: </b>{{reservation.startDate}}<br>
                  <b>Kraj rezervacije: </b>{{reservation.endDate}}<br>
          <b>Broj dana: </b>{{reservation.duration}}<br>
                  <b>Cena: </b>{{reservation.price}} din.<br>
                </div>


            <div class="col-3">

                <clientProfile :header="clientProfileHeader" :index="clientProfileId" :clientPr="reservation.clientId"></clientProfile><br>
                <reservationReport :header="reportHeader" :reservation="reservation" :client="client" :index="reportId"></reservationReport>

            </div>
        <div class="col-3">
          <p  style="font-weight: bold;">{{this.status}}</p>
          <p  style="font-weight: bold;">{{this.reported}}</p>
        </div>



      </div>
</div>

</template>


<script>
import clientProfile from "@/components/client/clientProfile";
import ClientServce from "@/servieces/ClientServce";
import reservationReport from "@/components/owner/cottage_owner/reservationReport";
//import ReservationReportService from "@/servieces/ReservationReportService";
export default {
  name: "shipOwnerPastReservationsElement",
  props: ["reservation"],
  components: {clientProfile, reservationReport},
  data() {
    return {
      image: "",
      clientProfileHeader: "Profil klijenta",
      clientProfileId: "clientProfile",
      reportHeader: "Izveštaj o rezervaciji",
      reportId: "reportReservation",
      status: "",
      reported: "",
      client: {
        name: "",
        surname: "",
        email: "",
        password: "",
        phoneNumber: "",
        userType: "",
        loyaltyPoints: "",
        penaltyNumber: "",
        benefits: "",
        longitude: "",
        latitude: ""
      }
    }
  },
  created() {
    if (this.reservation.quick) {
      this.status = "Brza rezervacija"
    } else {
      this.status = "Obična rezervacija"
    }
    console.log(this.reservation.clientId);


    ClientServce.getClient(this.reservation.clientId).then((response)=>
        {
          this.client = response.data;
          console.log(this.client);
        }
    );

    /*ReservationReportService.haveReservationReport(this.reservation.id).then((response) => {
      if (response.data) {
        console.log(response.data);
        this.reported = "Ocenjeno";
      }*/

  },
}
</script>

<style scoped>

</style>