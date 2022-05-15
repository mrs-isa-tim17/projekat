<template>
  <div class="d-flex justify-content-center">

    <table class="table table-striped">
      <thead>
      <tr>
        <th scope="col">Redni broj</th>
        <th scope="col">Klijent</th>
        <th scope="col">Email klijenta</th>
        <th scope="col">Ponuda</th>
        <th scope="col">Vlasnik</th>
        <th scope="col">Email vlasnika</th>
        <th scope="col">Period rezervacije</th>
        <th scope="col"></th>
        <th scope="col"></th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="(complaint, index) in complaints" :key="index">
        <th scope="row">{{index+1}}</th>
        <td>{{complaint.clientName}} {{complaint.clientSurname}}</td>
        <td> {{complaint.clientEmail}}</td>
        <td>{{complaint.offerName}}</td>
        <td>{{complaint.ownerName}} {{complaint.ownerSurname}}</td>
        <td> {{complaint.ownerEmail}}</td>
        <td> od {{complaint.startDate}} <br> do {{complaint.endDate}}</td>

        <td> <complaint-modal :index="generateAnswerId(complaint.id)" :header="this.modalHeader" :complaintId="complaint.id" :complaintText="complaint.text" :btnId="generateModalId(complaint.id)" btn-text="Detalji" @answer-text="AnswerComplaint"> </complaint-modal> </td>
        <td><h6 :id="complaint.id"></h6></td>
      </tr>
      </tbody>
    </table>

  </div>
</template>

<script>

import ComplaintModal from "@/components/admin/complaintModal";
import ComplaintServce from "@/servieces/ComplaintServce";

export default {
  name: "complaintsTable",
  components: {ComplaintModal},
  props:['complaints'],
  methods:{

    generateAnswerId(id){
      return "odg" + id;
    },

    generateModalId(id){
      console.log("odgovor"+id);
      return "odgovor"+id;
    },
    AnswerComplaint(id, text){
      console.log(id, text)
      ComplaintServce.answerComplaint(id, text).then((response) => {
        this.answer = response.data
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

      document.getElementById(id).innerText = "Uspešno ste odgovorili na žalbu.";
      document.getElementById("odgovor"+id).style.visibility="hidden";

    }

    },
  data(){
    return{
      modalHeader:"Tekst žalbe",
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
      },
      answer:false
    }
  }
}

</script>

<style scoped>

</style>
