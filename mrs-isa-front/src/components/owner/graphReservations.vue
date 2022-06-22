<template>
  <cottage-owner-header></cottage-owner-header>
  <div class="container p-3">

    <div  style="text-align:center; border:3px solid #31708E;width:300px;left:40%;position:absolute;">
      <label for="report1" style="">

        <input type="radio" id="report1" name="report" checked="true" value="yearly" style="" @change="onChange($event)" />
        <b>Godišnji</b>
      </label>&nbsp;
      <label for="report2">

        <input type="radio" id="report2" name="report"  value="monthly" style="" @change="onChange($event)" />
        <b>Mesečni</b>
      </label>&nbsp;
      <label for="report3">

        <input type="radio" id="report3" name="report"  value="weekly" style="" @change="onChange($event)" />
        <b>Nedeljni</b>
      </label>&nbsp;
      </div>
    <br>
    <br>
    <GChart
        type="ColumnChart"
        :data="reports"
        :options="chartOptions"
    />
  </div>


</template>

<script>
import { GChart } from 'vue-google-charts'
import ReservationService from "@/servieces/ReservationService";
import cottageOwnerHeader from "@/components/owner/cottage_owner/cottageOwnerHeader";
export default {
  name: "graphReservations",
  components:{GChart,cottageOwnerHeader},
  created:
  function(){
    this.coId = JSON.parse(localStorage.user).id;
    ReservationService.getReservationsReportYearly(this.coId).then((response)=>{
      console.log(response.data);
      let offers = [];
      let months = [];
      this.reports.push(['Mesec godina',]); //za head
      for(let i=0;i<response.data.length;i++){
        this.reports[0].push(response.data[i].offerName); //za head
      }
      months.push(response.data[0].monthYear);
      for(let i=0;i<months[0].length;i++){
        this.reports.push([months[0][i]]);
        for(let j=0;j<response.data.length;j++){
          this.reports[i+1].push(response.data[j].number[i]);
        }
      }
      console.log(months);
      console.log(offers);

        })
        console.log(this.reports);
   /* ReservationService.getReservationsReportMonthly(this.coId).then((response)=>{
      console.log(response.data);
      this.reports.push(['mesec']);
      this.reports.push(['mesec']);
      for(let i=0;i<response.data.length;i++){
        this.reports[0].push(response.data[i].offerName); //za head
        this.reports[1].push(response.data[i].numbers);
      }*/
    /*ReservationService.getReservationsReportWeekly(this.coId).then((response)=>{
      console.log(response.data);
      this.reports.push(['nedelja']);
      this.reports.push(['nedelja']);
      for(let i=0;i<response.data.length;i++){
        this.reports[0].push(response.data[i].offerName); //za head
        this.reports[1].push(response.data[i].numbers);
      }*/
      console.log(this.reports);



  },
  methods: {
    onChange(event) {
      this.reports = [];
      var data = event.target.value;
      console.log(data);
      if(data === "monthly"){
        ReservationService.getReservationsReportMonthly(this.coId).then((response)=>{
          console.log(response.data);
          this.reports.push(['mesec']);
          this.reports.push(['mesec']);
          for(let i=0;i<response.data.length;i++){
            this.reports[0].push(response.data[i].offerName); //za head
            this.reports[1].push(response.data[i].numbers);
          }

      })
      }
      else if(data === "weekly") {
        ReservationService.getReservationsReportWeekly(this.coId).then((response) => {
          console.log(response.data);
          this.reports.push(['nedelja']);
          this.reports.push(['nedelja']);
          for (let i = 0; i < response.data.length; i++) {
            this.reports[0].push(response.data[i].offerName); //za head
            this.reports[1].push(response.data[i].numbers);
          }
        })
      }
      else {
        this.coId = JSON.parse(localStorage.user).id;
        ReservationService.getReservationsReportYearly(this.coId).then((response)=>{
          console.log(response.data);
          let offers = [];
          let months = [];
          this.reports.push(['Mesec godina',]); //za head
          for(let i=0;i<response.data.length;i++){
            this.reports[0].push(response.data[i].offerName); //za head
          }
          months.push(response.data[0].monthYear);
          for(let i=0;i<months[0].length;i++){
            this.reports.push([months[0][i]]);
            for(let j=0;j<response.data.length;j++){
              this.reports[i+1].push(response.data[j].number[i]);
            }
          }
          console.log(months);
          console.log(offers);

        })
        console.log(this.reports);
      }
    }
    },
  data () {
    return {
      chartOptions: {
        chart: {
          title: 'Izveštaj o posećenosti',

        },
        chartArea: {width:900}, //ovo se podesva,da bi se ostavilo prostora za legendu
        legend:'right',
        height:500,
      },
      reports:[]
    }
  }
}
</script>

<style scoped>

</style>