<template>
  <admin-header></admin-header>
  <div class="container">
    <revisionsTable :revisions="revisions"></revisionsTable>

  </div>
</template>

<script>
import adminHeader from "@/components/admin/adminHeader";
import revisionsTable from "@/components/admin/revisionsTable";
import ReviewServce from "@/servieces/ReviewServce";

export default {
  name: "allRevisions",
  components:{
    adminHeader,
    revisionsTable,
  },

  mounted() {
    ReviewServce.getUnproccessedReviews().then((response)=>{
      this.revisions=response.data
      console.log(this.revisions);
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
    return{
      revisions :[],
      revision : {
        id:"",
        clientName:"",
        clientSurname:"",
        clientEmail:"",
        ownerName:"",
        ownerSurname:"",
        ownerEmail:"",
        rate:"",
        offerType:"",
        text:""
      }

    }
  }
}
</script>

<style scoped>

</style>