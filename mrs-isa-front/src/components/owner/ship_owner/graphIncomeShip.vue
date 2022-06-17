<template>
  <ship-owner-header></ship-owner-header>
  <div class="container p-4">
    <p style="font-size: 20px;font-weight: bold">Period za koji želite izveštaj prihoda</p>
    <div  style="padding:10px;text-align:center;align-content: center; border:3px solid #31708E;width:500px;height:60px;left:35%;position:absolute;">
      <label for="start" style="font-size: 20px;font-weight: bold;">Od:</label>&nbsp;
      <input type="date" :value="datePast" name="start_date" id="start">&nbsp;&nbsp;&nbsp;
      <label for="start" style="font-size: 20px;font-weight: bold;">Do:</label>&nbsp;
      <input type="date" :value="dateStart" name="end_date" id="end">&nbsp;&nbsp;
      <button class="btn btn-secondary" @click="showChart">Prikaži</button>
    </div>
    <br><br><br>
    <GChart
        type="PieChart"
        :data="chartData"
        :options="chartOptions"
    />
  </div>
</template>

<script>

import { GChart } from 'vue-google-charts';
import ReservationService from "@/servieces/ReservationService";
import shipOwnerHeader from "@/components/ship_owner/shipOwnerHeader";
export default {
  name: "graphIncomeShip",
  components:{shipOwnerHeader,
    GChart
  },
  created:
      function(){
        this.coId = JSON.parse(localStorage.user).id;
        var today = new Date();
        var dd = today.getDate();
        var past = today.getMonth();
        var mm = today.getMonth()+1;
        var yyyy = today.getFullYear();
        if(dd<10)
        {
          dd='0'+dd;
        }
        if(past<10)
        {
          past='0'+past;
        }

        if(mm<10)
        {
          mm='0'+mm;
        }

        today = yyyy + "-" + mm + "-" + dd;
        this.dateStart = today; //yyyy-mm-dd
        console.log(this.dateStart);
        this.datePast = yyyy + "-" + past + "-" + dd; //yyyy-mm-dd
        console.log(this.datePast);
        ReservationService.getReservationsPeriodShip(this.coId,this.datePast,this.dateStart).then((response)=> {
              console.log(response.data);
              this.chartData.push(['Brodovi', 'Rezervacije']);
              for(let i=0;i<response.data.length;i++){
                this.chartData.push([response.data[i].offerName,response.data[i].price]);
              }
            }
        )
      },
  data(){
    return{
      chartData : [],
      dateStart:"",
      datePast:"",
      chartOptions: {
        chart: {
          title: 'Izveštaj o posećenosti',

        },
        chartArea: {width:1300}, //ovo se podesva,da bi se ostavilo prostora za legendu
        legend:'right',
        height:400,
        pieSliceText: 'value-and-percentage'
      },
    }
  },
  methods:{
    showChart(){
      this.chartData = [];
      var start = document.getElementById('start').value;
      var end = document.getElementById('end').value;
      ReservationService.getReservationsPeriodShip(this.coId,start,end).then((response)=> {
        console.log(response.data);
        this.chartData.push(['Ponude', 'Rezervacije']);
        for(let i=0;i<response.data.length;i++){
          this.chartData.push([response.data[i].offerName,response.data[i].price]);
          console.log(this.chartData);
        }
      })
    }
  }


}
</script>

<style scoped>

</style>