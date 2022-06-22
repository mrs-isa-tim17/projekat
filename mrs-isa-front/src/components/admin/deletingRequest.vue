<template>
  <admin-header></admin-header>
  <div class="container">
    <div style="margin-top: 15px; margin-bottom: 20px">
      <h2>Zahtevi za brisanje naloga</h2>
    </div>

    <deleting-request-table :delete-requests="deleteRequests"></deleting-request-table>

  </div>
</template>

<script>
import DeletingRequestTable from "@/components/admin/deletingRequestTable";
import adminHeader from "@/components/admin/adminHeader";
import DeleteRequestServce from "@/servieces/DeleteRequestServce";

export default {
  name: "unprocessedDeletingRequest",
  components: {DeletingRequestTable, adminHeader},

  mounted() {
    DeleteRequestServce.getUnprocessedDeleteRequests().then((response) => {
      this.deleteRequests = response.data;
      console.log(this.deleteRequests);
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
      deleteRequests: [],
      deleteRequest: {
        id: "",
        userName: "",
        userSurname: "",
        text: "",
        userType: "",
        role: ""
      },
    }
  }
}
</script>

<style scoped>

</style>