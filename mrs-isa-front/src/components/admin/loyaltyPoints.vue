<template>
  <adminHeader></adminHeader>
  <div class="container">
    <br>
    <!--
    <div class="row-2">
      <ul class="nav justify-content-center">
        <li class="nav-item">
          <a class="nav-link active" aria-current="page" href="#" @click="loadClient('CLIENT')">Klijent</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#" @click="loadOfferer('OFFERER')">Vlasnik</a>
        </li>
      </ul>
    </div>     -->

    <ul class="nav nav-tabs justify-content-center m-2">
      <li class="nav-item active" id="client">
        <a class="nav-link" aria-current="page" href="#" style="font-size: 20px;"  @click="loadClient('CLIENT')">Klijent</a>
      </li>
      <li class="nav-item" id="owner">
        <a class="nav-link" href="#"  style="font-size: 20px;"  @click="loadOfferer('OFFERER')">Vlasnik</a>
      </li>
    </ul>


    <loyalty-points-type :all-points="this.points" :type="this.type" :key="myKey"></loyalty-points-type>

  </div>
</template>

<script>

import LoyaltyProgramService from "@/servieces/LoyaltyProgramService";
import LoyaltyPointsType from "@/components/admin/loyaltyPointsType";
import adminHeader from "@/components/admin/adminHeader";

export default {
  name: "loyaltyPoints",
  components: {
    LoyaltyPointsType,
    adminHeader,

  },

  created: function () {

    LoyaltyProgramService.getPointsForUser('CLIENT').then((response) => {
      this.type = 'client';
      this.points = response.data;
      console.log(this.points);
      this.myKey++;
    })
  },


  methods: {
    loadClient() {
      document.getElementById("client").classList.add("active");
      document.getElementById("owner").classList.remove("active");
      LoyaltyProgramService.getPointsForUser('CLIENT').then((response) => {
        this.type = "client"
        this.points = response.data;
        console.log(this.points);
        this.myKey++;
      })
    },

    loadOfferer() {

      document.getElementById("owner").classList.add("active");
      document.getElementById("client").classList.remove("active");
      LoyaltyProgramService.getPointsForUser('OFFERER').then((response) => {
        this.type = 'offerer';
        this.points = response.data;
        console.log(this.points);
        this.myKey++;
      })
    }

  },

  data() {
    return {
      pointsDTO: {
        id: "",
        startDate: "",
        endDate: "",
        points: "",
        type: ""
      },
      myKey: 0,
      points: [],
      type: ""
    }
  },
}
</script>

<style scoped>
  .active{
    border-color: #0645AD;
    border-width: 1px;
    border-radius: 10px;
    border-style: solid;
  }
</style>