<template>
  <div class="row">
    <div>
      <h3 v-if="this.type==='client'">Defiišite broj poena koje skuplja <i>klijent</i> za svaku uspešnu rezervaciju</h3>
      <h3 v-if="this.type==='offerer'">Defiišite broj poena koje skuplja <i>vlasnik</i> za svaku uspešnu rezervaciju</h3>
    </div>
    <br>
    <br>
    <div align="left" class="col-8">
      <h5>{{ this.defineLabel }} </h5>
      <input type="number" step="any" v-model="this.newPoints.points" />

      <br>
      <br>
      <h5>{{ this.startDateLabel }}</h5>
      <Datepicker v-model="this.newPoints.startDate" style="max-width: 200px"></Datepicker>
      <button type="button" class="btn btn-primary me-md-2 p-2" @click="DefinePoints">Definiši</button>
    </div>

    <div class="row">
      <loyalty-points-table :all-points="this.points" :key="myKey"></loyalty-points-table>
    </div>

  </div>
</template>

<script>

import LoyaltyPointsTable from "@/components/admin/loyaltyPointsTable";
import Datepicker from "@vuepic/vue-datepicker";
import LoyaltyProgramService from "@/servieces/LoyaltyProgramService";
import swal from "sweetalert2";

export default {
  name: "loyaltyPointsType",
  components: {
    LoyaltyPointsTable,
    Datepicker,
  },
  props: ['allPoints', 'type'],

  created:function () {
    this.points = this.allPoints;
    console.log(this.type);
    console.log(this.allPoints);
  },

  methods: {
    fireAlertOn(eventText, ok){
      if(ok){
        swal.fire({
          title: "Upozerenje",
          text: eventText,
          background: 'white',
          color: 'black',
          confirmButtonColor: '#D4F172'
        });

      }else {
        swal.fire({
          title: "Upozerenje",
          text: eventText,
          background: 'white',
          color: 'black',
          confirmButtonColor: '#FECDA6'
        });
      }
    },

    DefinePoints() {
      if (this.type === 'client') {
        this.newPoints.type = 'CLIENT';
      } else {
        this.newPoints.type = 'OFFERER';
      }

      if(this.newPoints.points==="" || this.newPoints.startDate===""){
        this.fireAlertOn("Popunite sva polja kako biste definisali nove poene.", false);
      }
      else {
        LoyaltyProgramService.defineNewPoint(this.newPoints).then((response) => {
          this.points = response.data;
          console.log(this.newPoints);
          this.myKey++;
          this.fireAlertOn("Definisali ste nove poene.", true);

        })
      }

    },

  },

  data() {
    return {
      newPoints: {
        id: "",
        startDate: "",
        endDate: "",
        points: "",
        type: ""
      },
      myKey: 0,
      defineLabel: "Unesiti broj poena: ",
      startDateLabel: "Početni datum: ",

      points:[]
    }
  }
}
</script>

<style scoped>

</style>