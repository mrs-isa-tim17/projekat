<template>
  <admin-header></admin-header>
  <div class="container">
    <div class="row">
      <div>
        <h3>Definišite procenat zarade od svake rezervacije</h3>
      </div>
      <br>
      <br>
      <div align="left" class="col-8">
        <h5>{{ this.defineLabel }} </h5>
        <input type="number" step="any" v-model="percentageDTO.percentage"/>

        <br>
        <br>
        <h5>{{ this.startDateLabel }}</h5>
        <Datepicker v-model="percentageDTO.startDate" style="max-width: 200px"></Datepicker>
        <button type="button" class="btn btn-primary me-md-2 p-2" @click="DefinePercentage">Definiši</button>
      </div>

      <div class="row">
        <percentageTable :percentages="this.allPercentages" :key="myKey"></percentageTable>
      </div>

    </div>


  </div>

</template>

<script>

import Datepicker from "@vuepic/vue-datepicker";
import adminHeader from "@/components/admin/adminHeader";
import percentageTable from "@/components/admin/percentageTable";
import PercentageService from "@/servieces/PercentageService";
import swal from "sweetalert2";

export default {
  name: "definePercentageOfEarnings",
  components: {
    Datepicker,
    adminHeader,
    percentageTable,
  },
  created: function () {
    PercentageService.getAllPercentages().then((response) => {
      this.allPercentages = response.data;
      //    this.myKey++;
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

  methods: {

    fireAlertOn(eventText){
      swal.fire({
        title: "Obaveštenje",
        text: eventText,
        background: 'white',
        color: 'black',
        confirmButtonColor: '#D4F172'
      });
    },

    DefinePercentage() {
      if (this.isDouble(this.percentageDTO.percentage)) {
        PercentageService.defineNewPercentage(this.percentageDTO).then((response) => {
          this.percentageDTO = response.data;
          this.myKey++;
          this.fireAlertOn("Definisali ste novi procenat zarade");

          this.$router.go();

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
      else{
        this.fireAlertOn("Unesite brojčanu vrednost procenta");
      }
    },

    isDouble(x) {
      if (typeof x == 'number' && !isNaN(x)) {
        if (Number.isInteger(x)) {
          return true;
        } else {
          return true;
        }
      } else {
        return false;
      }
    },


  },
  data() {
    return {
      defineLabel: "Unesiti procenat zarade od svake rezervacije: ",
      startDateLabel: "Početni datum: ",
      percentageDTO: {
        id: "",
        startDate: "",
        endDate: "",
        percentage: ""
      },
      allPercentages: [],
      myKey: 0


    }
  },
}
</script>

<style scoped>

</style>