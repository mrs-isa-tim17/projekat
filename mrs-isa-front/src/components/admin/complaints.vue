<template>
  <admin-header></admin-header>
    <div class="container">
        <div style="margin-top: 15px; margin-bottom: 20px">
          <h2>Žalbe</h2>
        </div>
      <complaints-table :complaints="complaints"></complaints-table>

    </div>


</template>

<script>
import AdminHeader from "@/components/admin/adminHeader";
import CompaintService from "@/servieces/ComplaintServce";
import ComplaintsTable from "@/components/admin/complaintsTable";
export default {
  name: "unprocessedComplaints",
  components: {ComplaintsTable, AdminHeader},
  mounted() {
    console.log("zalba");
    CompaintService.getUnprocessedComplaints().then((response) =>{
      this.complaints = response.data;
      console.log(this.complaints);

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
  data(){
    return {
      complaints :[],
      complaint : {
        id: "",
        text : "",
        clientName:"",
        clientSurname:"",
        clientEmail:"",
        ownerName:"",
        ownerSurname:"",
        ownerEmail:"",
        offerName:"",
        startDate:"",
        endDate:""
      }

    }
  }
  }
</script>

<style scoped>

</style>