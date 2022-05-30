<template>
  <admin-header></admin-header>

  <div align="center">
    <h3>Obrisani brodovi</h3><br>
    <div v-for="offer in this.offers" :key="offer">
      <simple-ship :ship=offer></simple-ship>
    </div>
  </div>

</template>

<script>
import AdminHeader from "@/components/admin/adminHeader";
import SimpleShip from "@/components/admin/allEntities/simpleShip";
import ShipService from "@/servieces/ship_owner/ShipService";

export default {
  name: "deletedShips",
  components: {
    SimpleShip,
    AdminHeader,
  },
  created: function () {
    ShipService.getDeletedShips().then((response) => {
      this.offers = response.data;
      console.log(this.offers);
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
  data() {
    return {
      offers: [],
      offer: {
        id: "",
        name: "",
        description: "",
        ownerName: "",
        ownerSurname: "",
        ownerEmail: "",
        capacity: "",
        totalNumberOfReservations: "",
        rate: "",
        longitude: "",
        latitude: "",
        type: "",
        bedQuantity: "",
        roomQuantity: "",
        deleted: ""
      }
    }
  }
}
</script>

<style scoped>

</style>