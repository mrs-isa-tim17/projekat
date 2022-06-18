<template>
  <admin-header></admin-header>
  <div class="container">
    <div style="margin-top: 15px; margin-bottom: 20px">
      <h2>Izveštaj o poslovanju</h2>
    </div>

    <div class="row">
      <div align="right" class="col-6 p-2">
        <label>Unesite početni datum</label>
        <Datepicker v-model="businessReport.startDate" style="max-width: 200px"></Datepicker>
      </div>
      <div class="col-6 p-2" align="left">
        <label>Unesite krajnji datum</label>
        <Datepicker v-model="businessReport.endDate" style="max-width: 200px"></Datepicker>
      </div>

      <div align="center" class="p-2">
        <button class="btn btn-primary" type="button" @click="showIncome()">Prikaži</button>
      </div>
      <div>
        <label>Prihod za odabrani period</label>
        <h4>{{ this.fulIncome.toFixed(2) }}</h4>
      </div>
    </div>

    <div class="row">
      <div class="d-flex justify-content-center">
        <table class="table table-striped">
          <thead>
          <tr>
            <th scope="col">Redni broj</th>
            <th scope="col">Naziv ponude</th>
            <th scope="col">Vlasnik</th>
            <th scope="col">Tip ponude</th>
            <th scope="col">Početni datum</th>
            <th scope="col">Krajnji datum</th>
            <th scope="col">Cena</th>
            <th scope="col">Procenat zarade</th>
            <th scope="col">Zarada</th>
          </tr>
          </thead>
          <tbody>
          <tr v-for="(fullReport, index) in allReports" :key="index">
            <th scope="row">{{ index + 1 }}</th>
            <td> {{ fullReport.offerName }}</td>
            <td> {{ fullReport.ownerName }} {{ fullReport.ownerSurname }} </td>
            <td> {{ getOfferTypeSerbian(fullReport.offerType) }}</td>
            <td>{{ getDateFormat(fullReport.startDate) }}</td>
            <td>{{ getDateFormat(fullReport.endDate) }}</td>
            <td>{{ fullReport.price }}</td>
            <td>{{ fullReport.percentage }}</td>
            <td>{{ fullReport.income.toFixed(2) }}</td>
          </tr>
          </tbody>
        </table>
      </div>
    </div>
    <div class="row">
      <h3 id="mess"></h3>
    </div>


  </div>
</template>

<script>
import Datepicker from "@vuepic/vue-datepicker";
import adminHeader from "@/components/admin/adminHeader";
import BusinessReportAdminService from "@/servieces/BusinessReportAdminService";
import swal from "sweetalert2";

export default {
  name: "businessReport",
  components: {
    Datepicker,
    adminHeader,
  },
  methods: {

    getOfferTypeSerbian(type) {
      if (type === 'COTTAGE')
        return 'Vikendica'
      else if (type === 'SHIP')
        return 'Brod'
      else
        return 'Avantura';
    },

    getDateFormat(date) {
      let d = date[2] + "." + date[1] + "." + date[0] + "."
      return d;
    },

    countFullIncome() {
      let full = 0.0;
      for (let r of this.allReports) {
        full += r.income;
      }
      this.fulIncome = full;
    },
    fireAlertOn(eventText){
      swal.fire({
        title: "Upozerenje",
        text: eventText,
        background:'white',
        color:'black',
        confirmButtonColor:'#FECDA6'});
    },

    showIncome() {
      if (this.businessReport.startDate === "" || this.businessReport.endDate === "") {
        this.fireAlertOn("Odaberite početni i krajnji datum za izveštaj");
      } else {
        BusinessReportAdminService.getReport(this.businessReport).then((response) => {
          this.allReports = response.data;
          console.log(this.businessReport);
          this.countFullIncome();
          if(this.allReports.length===0){
            document.getElementById("mess").innerText="Nema nijedna rezervacija za izabrani period.";
          }
        })
      }
    }
  },
  data() {
    return {
      businessReport: {
        startDate: "",
        endDate: "",
        income: 0,
      },
      fulIncome: 0.00,
      allReports: [],
      fullReport: {
        offerName: "",
        offerType: "",
        startDate: "",
        endDate: "",
        price: "",
        percentage: "",
        income: "",
        ownerName: "",
        ownerSurname: ""
      }
    }
  }
}
</script>

<style scoped>

</style>