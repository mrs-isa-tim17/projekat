<template>
  <admin-header></admin-header>

  <div align="center">
    <button class="btn btn-primary  btn-sm  me-md-2 " @click="goToDeletedAdventures"> Obrisani brodovi</button>
    <div v-for="offer in this.offers" :key="offer">
      <simple-ship :ship=offer @delete-ship="Deleted"></simple-ship>
    </div>
  </div>
</template>

<script>

import adminHeader from "@/components/admin/adminHeader";
import SimpleShip from "@/components/admin/allEntities/simpleShip";
import ShipService from "@/servieces/ship_owner/ShipService";


export default {
  name: "allShips",
  components:{
    SimpleShip,
    adminHeader,

  },
  created: function (){
      ShipService.getActiveShipsForAdmin().then((response)=>{
      this.offers = response.data
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

  methods:{
    goToDeletedAdventures(){
      this.$router.push('/admin/ships/deleted');
    },
    Deleted(){
      this.$router.go();
    },
  },


  data(){
    return {
      offers:[],
      offer:{
        id:"",
        name:"",
        description:"",
        ownerName:"",
        ownerSurname:"",
        ownerEmail:"",
        capacity:"",
        totalNumberOfReservations:"",
        rate:"",
        longitude:"",
        latitude:"",
        type:"",
        bedQuantity:"",
        roomQuantity:"",
        deleted:""
      }

    }
  }
}
</script>

<style scoped>

</style>