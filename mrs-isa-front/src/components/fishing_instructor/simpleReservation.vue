<template>
  <div class="container">
    <!--
      <div class="card">
        <div class="card-header">
          Featured
        </div>
        <div class="card-body">
          <h5 class="card-title">{{clientNameSurnameLabel}} {{reservation.clientName}} {{reservation.clientSurname}}</h5>
          <p class="card-text">{{startDateLabel}} {{reservation.startDate}}</p>
          <p class="card-text">{{endDateLabel}} {{reservation.endDate}}</p>
          <p class="card-text">{{quickLabel}} {{reservation.quick}}</p>
          <a href="#" class="btn btn-primary">Pogledaj profil  klijenta</a>
        </div>
      </div>

    -->
    <!--

      <div class="card" style="width: 18rem;">
        <div class="card-body">
          <h5 class="card-title">{{clientNameSurnameLabel}} {{reservation.clientName}} {{reservation.clientSurname}}</h5>
          <h6 class="card-subtitle mb-2 text-muted">{{startDateLabel}} {{reservation.startDate}}</h6>
          <h6 class="card-subtitle mb-2 text-muted">{{endDateLabel}} {{reservation.endDate}}</h6>
          <p class="card-text">{{quickLabel}} {{reservation.quick}}</p>
          <a href="#" class="card-link">Profil klijenta</a>
        </div>
      </div>
  -->
    <div class="d-flex justify-content-center">

      <table class="table table-striped">
        <thead>
        <tr>
          <th scope="col">Redni broj</th>
          <th scope="col">Ime klijenta</th>
          <th scope="col">Prezime klijenta</th>
          <th scope="col">Početni datum</th>
          <th scope="col">Krajnji datum</th>
          <th scope="col">Naziv avanture</th>
          <th scope="col">Brza rezervacija</th>
          <th scope="col"></th>
          <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(reservation, index) in reservations" :key="index">
          <th scope="row">{{ index + 1 }}</th>
          <td>{{ reservation.clientName }}</td>
          <td>{{ reservation.clientSurname }}</td>
          <td>{{ getDateFormat(reservation.startDate) }}</td>
          <td>{{ getDateFormat(reservation.endDate) }}</td>
          <td>{{ reservation.offerName }}</td>
          <td><input type="checkbox" v-bind:checked="reservation.quick" disabled="disabled"></td>
          <td>
            <client-profile :header="this.header" :index="getIndex(reservation.id)"
                            :client-pr="reservation.clientId"></client-profile>
          </td>
          <td>
            <div v-show="this.lastReservation(reservation)">
              <reservationReport :index="getIndexReport(reservation.id)" :header="this.headerReport"
                                 :reservation="reservation" :client="this.client"></reservationReport>
            </div>
          </td>

        </tr>
        </tbody>
      </table>

    </div>


  </div>
</template>

<script>
import ClientProfile from "@/components/client/clientProfile";
import reservationReport from "@/components/reservationReport";

export default {
  name: "simpleReservation",
  components: {
    ClientProfile,
    reservationReport,
  },
  props: ["reservations"],

  methods: {
    getDateFormat(date) {
      let d = date[2] + "." + date[1] + "." + date[0] + "."
      console.log(this.reservations[0]);
      return d;
    },

    getIndex(id) {
      return this.indexPr + id;
    },

    getIndexReport(id) {
      return this.indexReport + id;
    },
    lastReservation(reservation) {
      let today = new Date();
      let month = reservation.endDate[1];
      let day = reservation.endDate[2];
      let year = reservation.endDate[0];
      console.log("tiiiiiip");
      console.log(typeof reservation.startDate);
      let end = new Date(year + "-" + month + "-" + day);
      if (end > today) {
        return false;
      } else {
        return true;
      }

    },
  },

  data() {
    return {
      clientNameSurnameLabel: "Ime i prezime klijenta: ",
      startDateLabel: "Početni datum: ",
      endDateLabel: "Krajnji datum: ",
      quickLabel: "Brza rezervacija: ",
      header: "Profil klijenta",
      indexPr: "indexPr",
      headerReport: "Izveštaj o rezervaciji",
      indexReport: "repIDX",
      client: {},
    }
  }
}
</script>

<style scoped>

</style>
