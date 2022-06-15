<template>
  <admin-header></admin-header>
  <div>
    <div class="container">

      <div style="margin-bottom: 20px;margin-top: 15px;">
        <h3>Zahtevi za registraciju</h3>
      </div>

      <div>
        <registration-request :requests="requests"></registration-request>
      </div>

    </div>
  </div>


</template>

<script>

import adminHeader from "@/components/admin/adminHeader";
import registrationRequest from "@/components/admin/registrationRequest";
import RegistrationRequestService from "@/servieces/RegistrationRequestService";

export default {
  name: "requestForRegistration.vue",
  components:{
    adminHeader,
    registrationRequest
  },
  mounted() {
    console.log("zahtev za registraciju");
    RegistrationRequestService.getUnapprovedRequest().then((response) =>{
      this.requests = response.data;
      console.log(this.requests);

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
      requests:[],
      request:{
        id:"",
        name:"",
        surname:"",
        email:"",
        registrationType:""
      }

    }

  }
}
</script>

<style scoped>

</style>
