<template>
  <div class="d-flex justify-content-center">

    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">Redni broj</th>
        <th scope="col">Ime</th>
        <th scope="col">Prezime</th>
        <th scope="col">Tip registracije</th>
        <th scope="col">Email</th>
        <th scope="col"></th>
        <th scope="col"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(request, index) in requests" :key="index">
        <th scope="row">{{index+1}}</th>
        <td>{{request.name}}</td>
        <td>{{request.surname}}</td>
        <td>{{this.showRegistrationType(request.registrationType)}}</td>
        <td>{{request.email}}</td>
        <td>
          <registrationRequestModal :index="generateRejectId(request.id)" :header="this.modalHeader" :requestId="request.id"
                                    :btnId="generateModalId(request.id)" btnText="Detalji" @reject-text="RejectRegistration"
                                    @approve-reg-req-text="ApproveRegistration" :request-message="request.registrationMessage"> </registrationRequestModal> </td>
        <td><h6 :id="request.id"></h6></td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>
import RegistrationRequestService from "@/servieces/RegistrationRequestService";
import RegistrationRequestModal from "@/components/admin/registrationRequestModal";
import swal from "sweetalert2";
export default {
  name: "registrationRequest.vue",
  components:{
    RegistrationRequestModal
  },
  props: ['requests'],
  methods: {
    showRegistrationType(type) {
      if (type === "COTTAGE_OWNER") {
        return this.cottageOwner
      } else if (type === "SHIP_OWNER") {
        return this.shipOwner
      } else {
        return this.fishingInstructor
      }
    },
    generateApproveId(id){
      return "prihvati"+id;
    },
    generateRejectId(id){
      console.log("o" + id);
      return "o"+id;
    },
    generateModalId(id){
      console.log("odbij"+id);
      return "odbij"+id;
    },
    handleApproveAnswer(id){
      if (this.answer.successfull){
        document.getElementById(id).innerText = "Uspešno ste prihvatili registraciju."

        document.getElementById("odbij"+id).style.visibility="hidden";
      }else{
        swal.fire({
          title:"Nešto se desilo",
          text: this.answer.text,
          background:'white',
          color:'#31708E',
          confirmButtonColor:'#FECDA6'});

      }
    },
    handleRejectAnswer(id){
      if (this.answer.successfull){
        document.getElementById(id).innerText = "Uspešno ste odbili registraciju.";
        document.getElementById(id).style.color="red";
        document.getElementById("odbij"+id).style.visibility="hidden";
      }else{
        swal.fire({
          title:"Nešto se desilo",
          text: this.answer.text,
          background:'white',
          color:'#31708E',
          confirmButtonColor:'#FECDA6'});

      }
    },
    ApproveRegistration(id){
      console.log("approve dugme");
      console.log(id);
      RegistrationRequestService.approveRegistration(id).then((response) => {
        this.answer = response.data
        this.handleApproveAnswer(id);
        console.log(response.data);
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

    RejectRegistration(id, text){
      console.log("reject dugme");
      console.log(id);
      console.log(text);

      RegistrationRequestService.rejectRegistration(id, text).then((response) => {
        this.answer = response.data
        this.handleRejectAnswer(id);
        console.log(response.data);
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
    }
  },

  data() {
    return {
      shipOwner: "Vlasnik broda",
      cottageOwner: "Vlasnik vikendice",
      fishingInstructor: "Instruktor pecanja",
      answer:false,
      modalHeader:"Obrazloženje za odbijanje registraceije naloga",
    }
  }
}
</script>

<style scoped>

</style>
