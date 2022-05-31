<template>
  <admin-header></admin-header>

  <div align="center">
    <h3>Obrisane vikendice</h3><br>
    <div v-for="offer in offers" :key="offer.id">
      <simple-cottage :cottage="offer"></simple-cottage>
    </div>
  </div>

</template>

<script>
import AdminHeader from "@/components/admin/adminHeader";
import CottageService from "@/servieces/cottage_owner/CottageService";
import SimpleCottage from "@/components/admin/allEntities/simpleCottage";

export default {
  name: "deletedCottages",
  components: {
    SimpleCottage,
    AdminHeader,
  },
  created: function () {
    CottageService.getDeletedCottages().then((response) => {
      this.offers = response.data;
      console.log(this.offers);
      this.myKey++;
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
      },
      myKey: 0
    }
  }
}
</script>

<style scoped>

</style>